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

package org.apache.spark.scheduler

import org.apache.spark.scheduler.SchedulingMode.SchedulingMode
import org.apache.spark.storage.BlockManagerId
import org.apache.spark.util.AccumulatorV2

/**
 * Low-level task scheduler interface, currently implemented exclusively by
 * [[org.apache.spark.scheduler.TaskSchedulerImpl]].
 * This interface allows plugging in different task schedulers. Each TaskScheduler schedules tasks
 * for a single SparkContext. These schedulers get sets of tasks submitted to them from the
 * DAGScheduler for each stage, and are responsible for sending the tasks to the cluster, running
 * them, retrying if there are failures, and mitigating stragglers. They return events to the
 * DAGScheduler.
  *
  * 低层次的任务调度的接口；目前只有一种实现，就是默认的实现：TaskSchedulerImpl。
  * 该接口允许插入不同的TaskScheduler的实现，每个TaskScheduler为单个的SparkContext服务，实现任务的调度。
  * 这些taskscheudler获取来自DAGScheduler的taskSet，然后负责把这些taskSet发送给集群，运行task，若失败会进行重试。
  * 然后，返回事件给DAGScheduler。
  *
 */
private[spark] trait TaskScheduler {

  private val appId = "spark-application-" + System.currentTimeMillis

  def rootPool: Pool

  // 调度模式
  def schedulingMode: SchedulingMode

  def start(): Unit

  // Invoked after system has successfully initialized (typically in spark context).
  // Yarn uses this to bootstrap allocation of resources based on preferred locations,
  // wait for slave registrations, etc.
  def postStartHook() { }

  // Disconnect from the cluster.
  def stop(): Unit

  // Submit a sequence of tasks to run.
  // 提交一组task去运行
  def submitTasks(taskSet: TaskSet): Unit

  // Kill all the tasks in a stage and fail the stage and all the jobs that depend on the stage.
  // Throw UnsupportedOperationException if the backend doesn't support kill tasks.
  def cancelTasks(stageId: Int, interruptThread: Boolean): Unit

  /**
   * Kills a task attempt.
   * Throw UnsupportedOperationException if the backend doesn't support kill a task.
   *
   * @return Whether the task was successfully killed.
   */
  def killTaskAttempt(taskId: Long, interruptThread: Boolean, reason: String): Boolean

  // Kill all the running task attempts in a stage.
  // Throw UnsupportedOperationException if the backend doesn't support kill tasks.
  def killAllTaskAttempts(stageId: Int, interruptThread: Boolean, reason: String): Unit

  // Set the DAG scheduler for upcalls. This is guaranteed to be set before submitTasks is called.
  def setDAGScheduler(dagScheduler: DAGScheduler): Unit

  // Get the default level of parallelism to use in the cluster, as a hint for sizing jobs.
  def defaultParallelism(): Int

  /**
   * Update metrics for in-progress tasks and let the master know that the BlockManager is still
   * alive. Return true if the driver knows about the given block manager. Otherwise, return false,
   * indicating that the block manager should re-register.
   */
  def executorHeartbeatReceived(
      execId: String,
      accumUpdates: Array[(Long, Seq[AccumulatorV2[_, _]])],
      blockManagerId: BlockManagerId): Boolean

  /**
   * Get an application ID associated with the job.
   *
   * @return An application ID
   */
  def applicationId(): String = appId

  /**
   * Process a lost executor
   */
  // 处理一个executor lost错误
  def executorLost(executorId: String, reason: ExecutorLossReason): Unit

  /**
   * Process a removed worker
   */
  def workerRemoved(workerId: String, host: String, message: String): Unit

  /**
   * Get an application's attempt ID associated with the job.
   *
   * @return An application's Attempt ID
   */
  def applicationAttemptId(): Option[String]

}
