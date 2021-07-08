/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.spark

import java.util.concurrent.{ScheduledFuture, TimeUnit}

import scala.collection.mutable
import scala.concurrent.Future

import org.apache.spark.internal.Logging
import org.apache.spark.rpc.{RpcCallContext, RpcEnv, ThreadSafeRpcEndpoint}
import org.apache.spark.scheduler._
import org.apache.spark.storage.BlockManagerId
import org.apache.spark.util._

/**
 * A heartbeat from executors to the driver. This is a shared message used by several internal
 * components to convey liveness or execution information for in-progress tasks. It will also
 * expire the hosts that have not heartbeated for more than spark.network.timeout.
 * spark.executor.heartbeatInterval should be significantly less than spark.network.timeout.
  *
  * 从Executor到Driver的心跳。 这是几个内部组件使用的共享消息，用于传达正在进行的任务的活跃度或执行信息。
  * 它还将使心跳超过 spark.network.timeout 的主机过期。 spark.executor.heartbeatInterval 应该明显小于 spark.network.timeout。
 */
private[spark] case class Heartbeat(
    executorId: String,
    accumUpdates: Array[(Long, Seq[AccumulatorV2[_, _]])], // taskId -> accumulator updates
    blockManagerId: BlockManagerId)

/**
 * An event that SparkContext uses to notify HeartbeatReceiver that SparkContext.taskScheduler is
 * created.
 */
private[spark] case object TaskSchedulerIsSet

private[spark] case object ExpireDeadHosts

private case class ExecutorRegistered(executorId: String)

private case class ExecutorRemoved(executorId: String)

private[spark] case class HeartbeatResponse(reregisterBlockManager: Boolean)

/**
 * Lives in the driver to receive heartbeats from executors.
  * 在driver端运行，为了接收来自executor的心跳信息。
 */
private[spark] class HeartbeatReceiver(sc: SparkContext, clock: Clock)
  extends SparkListener with ThreadSafeRpcEndpoint with Logging {

  def this(sc: SparkContext) {
    this(sc, new SystemClock)
  }

  sc.listenerBus.addToManagementQueue(this)

  override val rpcEnv: RpcEnv = sc.env.rpcEnv

  private[spark] var scheduler: TaskScheduler = null

  // executor ID -> timestamp of when the last heartbeat from this executor was received
  // executor ID到时间戳的对应关系，该时间戳是从该executor接收到最新的心跳时间。
  private val executorLastSeen = new mutable.HashMap[String, Long]

  // "spark.network.timeout" uses "seconds", while `spark.storage.blockManagerSlaveTimeoutMs` uses
  // "milliseconds"
  private val executorTimeoutMs =
    sc.conf.getTimeAsMs("spark.storage.blockManagerSlaveTimeoutMs",
      s"${sc.conf.getTimeAsSeconds("spark.network.timeout", "120s")}s")

  // "spark.network.timeoutInterval" uses "seconds", while
  // "spark.storage.blockManagerTimeoutIntervalMs" uses "milliseconds"
  private val timeoutIntervalMs =
    sc.conf.getTimeAsMs("spark.storage.blockManagerTimeoutIntervalMs", "60s")
  private val checkTimeoutIntervalMs =
    sc.conf.getTimeAsSeconds("spark.network.timeoutInterval", s"${timeoutIntervalMs}ms") * 1000

  private var timeoutCheckingTask: ScheduledFuture[_] = null

  // "eventLoopThread" is used to run some pretty fast actions. The actions running in it should not
  // block the thread for a long time.
  private val eventLoopThread =
    ThreadUtils.newDaemonSingleThreadScheduledExecutor("heartbeat-receiver-event-loop-thread")

  private val killExecutorThread = ThreadUtils.newDaemonSingleThreadExecutor("kill-executor-thread")
  override def onStart(): Unit = {
    timeoutCheckingTask = eventLoopThread.scheduleAtFixedRate(new Runnable {
      override def run(): Unit = Utils.tryLogNonFatalError {
        Option(self).foreach(_.ask[Boolean](ExpireDeadHosts))
      }
    }, 0, checkTimeoutIntervalMs, TimeUnit.MILLISECONDS)
  }

  // 直接接受executor的状态信息
  override def receiveAndReply(context: RpcCallContext): PartialFunction[Any, Unit] = {

    // Messages sent and received locally
    case ExecutorRegistered(executorId) =>
      executorLastSeen(executorId) = clock.getTimeMillis()
      context.reply(true)
    case ExecutorRemoved(executorId) =>
      executorLastSeen.remove(executorId)
      context.reply(true)
    case TaskSchedulerIsSet =>
      scheduler = sc.taskScheduler
      context.reply(true)
    case ExpireDeadHosts =>
      expireDeadHosts()
      context.reply(true)

    // Messages received from executors
    // 每个executor都会启动一个定时线程来向driver端发送心跳信息。心跳信息中包括了executor的blockManagerId的信息。
    case heartbeat @ Heartbeat(executorId, accumUpdates, blockManagerId) =>
      if (scheduler != null) {
        if (executorLastSeen.contains(executorId)) {
          // 设置executorId的executor的最后一次心跳时间
          executorLastSeen(executorId) = clock.getTimeMillis()
          // 启动一个新的线程向executor发送返回信息
          eventLoopThread.submit(new Runnable {
            override def run(): Unit = Utils.tryLogNonFatalError {
              // 向executor的blockmanager发送心跳信息，探测它是否还存活，
              // 若不存活需要告诉executor的blockmanager需要重新注册
              // //todo xh?: 为什么要在这里再注册？开始已经注册了，什么时候注销的？为什么要在注册？
              val unknownExecutor = !scheduler.executorHeartbeatReceived(
                executorId, accumUpdates, blockManagerId)
              // 返回executor心跳的信息
              val response = HeartbeatResponse(reregisterBlockManager = unknownExecutor)
              context.reply(response)
            }
          })
        } else {
          // This may happen if we get an executor's in-flight heartbeat immediately
          // after we just removed it. It's not really an error condition so we should
          // not log warning here. Otherwise there may be a lot of noise especially if
          // we explicitly remove executors (SPARK-4134).
          // 当删除executor后，该executor的心跳信息还在传输，就会发生这种情况。只需要简单的丢弃即可。
          logDebug(s"Received heartbeat from unknown executor $executorId")
          context.reply(HeartbeatResponse(reregisterBlockManager = true))
        }
      } else {
        // Because Executor will sleep several seconds before sending the first "Heartbeat", this
        // case rarely happens. However, if it really happens, log it and ask the executor to
        // register itself again.
        // 在发送第一个心跳信息时，Executor会睡眠几秒。虽然这很少发生，但一旦发生，可以请求executor再次注册它自己。
        logWarning(s"Dropping $heartbeat because TaskScheduler is not ready yet")
        context.reply(HeartbeatResponse(reregisterBlockManager = true))
      }
  }

  /**
   * Send ExecutorRegistered to the event loop to add a new executor. Only for test.
   *
   * @return if HeartbeatReceiver is stopped, return None. Otherwise, return a Some(Future) that
   *         indicate if this operation is successful.
   */
  def addExecutor(executorId: String): Option[Future[Boolean]] = {
    Option(self).map(_.ask[Boolean](ExecutorRegistered(executorId)))
  }

  /**
   * If the heartbeat receiver is not stopped, notify it of executor registrations.
   */
  override def onExecutorAdded(executorAdded: SparkListenerExecutorAdded): Unit = {
    addExecutor(executorAdded.executorId)
  }

  /**
   * Send ExecutorRemoved to the event loop to remove an executor. Only for test.
   *
   * @return if HeartbeatReceiver is stopped, return None. Otherwise, return a Some(Future) that
   *         indicate if this operation is successful.
   */
  def removeExecutor(executorId: String): Option[Future[Boolean]] = {
    Option(self).map(_.ask[Boolean](ExecutorRemoved(executorId)))
  }

  /**
   * If the heartbeat receiver is not stopped, notify it of executor removals so it doesn't
   * log superfluous errors.
   *
   * Note that we must do this after the executor is actually removed to guard against the
   * following race condition: if we remove an executor's metadata from our data structure
   * prematurely, we may get an in-flight heartbeat from the executor before the executor is
   * actually removed, in which case we will still mark the executor as a dead host later
   * and expire it with loud error messages.
   */
  override def onExecutorRemoved(executorRemoved: SparkListenerExecutorRemoved): Unit = {
    removeExecutor(executorRemoved.executorId)
  }

  private def expireDeadHosts(): Unit = {
    logTrace("Checking for hosts with no recent heartbeats in HeartbeatReceiver.")
    val now = clock.getTimeMillis()
    for ((executorId, lastSeenMs) <- executorLastSeen) {
      if (now - lastSeenMs > executorTimeoutMs) {
        logWarning(s"Removing executor $executorId with no recent heartbeats: " +
          s"${now - lastSeenMs} ms exceeds timeout $executorTimeoutMs ms")
        scheduler.executorLost(executorId, SlaveLost("Executor heartbeat " +
          s"timed out after ${now - lastSeenMs} ms"))
          // Asynchronously kill the executor to avoid blocking the current thread
        killExecutorThread.submit(new Runnable {
          override def run(): Unit = Utils.tryLogNonFatalError {
            // Note: we want to get an executor back after expiring this one,
            // so do not simply call `sc.killExecutor` here (SPARK-8119)
            sc.killAndReplaceExecutor(executorId)
          }
        })
        executorLastSeen.remove(executorId)
      }
    }
  }

  override def onStop(): Unit = {
    if (timeoutCheckingTask != null) {
      timeoutCheckingTask.cancel(true)
    }
    eventLoopThread.shutdownNow()
    killExecutorThread.shutdownNow()
  }
}


private[spark] object HeartbeatReceiver {
  val ENDPOINT_NAME = "HeartbeatReceiver"
}
