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

import java.io.NotSerializableException
import java.util.Properties
import java.util.concurrent.{ConcurrentHashMap, TimeUnit}
import java.util.concurrent.atomic.AtomicInteger
import java.util.function.BiFunction

import scala.annotation.tailrec
import scala.collection.Map
import scala.collection.mutable.{ArrayStack, HashMap, HashSet}
import scala.concurrent.duration._
import scala.language.existentials
import scala.language.postfixOps
import scala.util.control.NonFatal

import org.apache.commons.lang3.SerializationUtils

import org.apache.spark._
import org.apache.spark.broadcast.Broadcast
import org.apache.spark.executor.TaskMetrics
import org.apache.spark.internal.Logging
import org.apache.spark.internal.config
import org.apache.spark.network.util.JavaUtils
import org.apache.spark.partial.{ApproximateActionListener, ApproximateEvaluator, PartialResult}
import org.apache.spark.rdd.{DeterministicLevel, RDD, RDDCheckpointData}
import org.apache.spark.rpc.RpcTimeout
import org.apache.spark.storage._
import org.apache.spark.storage.BlockManagerMessages.BlockManagerHeartbeat
import org.apache.spark.util._

/**
 * The high-level scheduling layer that implements stage-oriented scheduling. It computes a DAG of
 * stages for each job, keeps track of which RDDs and stage outputs are materialized, and finds a
 * minimal schedule to run the job. It then submits stages as TaskSets to an underlying
 * TaskScheduler implementation that runs them on the cluster. A TaskSet contains fully independent
 * tasks that can run right away based on the data that's already on the cluster (e.g. map output
 * files from previous stages), though it may fail if this data becomes unavailable.
 *
  * 高层次的调度层，实现了面向stage的调度。它为每个job计算一个stage的DAG(注：各个stage相互依赖，形成一个DAG图)，
  * 并跟踪哪些RDD和stage的输出已经完成，并找到一个最小的调度计划来运行该job。
  * 然后，它以TaskSets的形式来提交stage(xh注：把每个stage转换成多个task，再创建一个TaskSet)，
  * 把TaskSet提交给较低层次的调度器TaskScheduler，这些TaskScheduler的实例运行在cluster中。
  * 一个TaskSet包含所有基于现有数据可以运行的独立的task，若数据不可用，这些task也可能会失败。
  *
 * Spark stages are created by breaking the RDD graph at shuffle boundaries. RDD operations with
 * "narrow" dependencies, like map() and filter(), are pipelined together into one set of tasks
 * in each stage, but operations with shuffle dependencies require multiple stages (one to write a
 * set of map output files, and another to read those files after a barrier). In the end, every
 * stage will have only shuffle dependencies on other stages, and may compute multiple operations
 * inside it. The actual pipelining of these operations happens in the RDD.compute() functions of
 * various RDDs
  *
  * 那么stage是如何创建的呢？Spark是根据RDD依赖关系图中的是否发生shuffle为边界来创建stage的。
  * 因为，RDD的窄依赖操作，比如：map()或filter()，是可以放到一个stage的taskSet中的，它们可以形成pipeline执行。
  * （xh注：之所以可以形成Pipeline执行，是由于没有shuffle操作，所以不需要从其他节点中拉取数据，所以一个操作完成后
  * 的输出，可以作为下一个操作的输入，数据可以在内存中传输，从而优化了处理性能。）
  * 但，会产生shuffle的操作（比如：groupByKey等）可能需要多个stage(一个写出map文件，另一个读取这些文件)。
  *
  * 最后，每个stage和其他stage，只会有一个shuffle依赖。 (xh: 如何理解)
 *
 * In addition to coming up with a DAG of stages, the DAGScheduler also determines the preferred
 * locations to run each task on, based on the current cache status, and passes these to the
 * low-level TaskScheduler. Furthermore, it handles failures due to shuffle output files being
 * lost, in which case old stages may need to be resubmitted. Failures *within* a stage that are
 * not caused by shuffle file loss are handled by the TaskScheduler, which will retry each task
 * a small number of times before cancelling the whole stage.
  *
  * 除了构建一个stage的DAG，DAGScheduler会决定每个task运行的最佳位置。(xh:如何确定的，依据是什么？)
  * 基于目前缓存的状态，把这些任务传递给较低层次的任务调度器：TaskScheduler。
  * 另外，DAGScheduler还会处理由于shuffle过程中输出文件丢失而导致的任务失败，老的stage会被重新提交。
  * 如果不是由于"shuffle文件丢失"而导致的任务失败，由TaskScheduler进行处理，它会在整个stage被取消之前，多次重复提交每个task。
 *
 * When looking through this code, there are several key concepts:
 *
 *  - Jobs (represented by [[ActiveJob]]) are the top-level work items submitted to the scheduler.
 *    For example, when the user calls an action, like count(), a job will be submitted through
 *    submitJob. Each Job may require the execution of multiple stages to build intermediate data.
 *
 *  - Stages ([[Stage]]) are sets of tasks that compute intermediate results in jobs, where each
 *    task computes the same function on partitions of the same RDD. Stages are separated at shuffle
 *    boundaries, which introduce a barrier (where we must wait for the previous stage to finish to
 *    fetch outputs). There are two types of stages: [[ResultStage]], for the final stage that
 *    executes an action, and [[ShuffleMapStage]], which writes map output files for a shuffle.
 *    Stages are often shared across multiple jobs, if these jobs reuse the same RDDs.
 *
 *  - Tasks are individual units of work, each sent to one machine.
  *  Task是独立执行的工作单元，每个task会被发送到一台机器上。
 *
 *  - Cache tracking: the DAGScheduler figures out which RDDs are cached to avoid recomputing them
 *    and likewise remembers which shuffle map stages have already produced output files to avoid
 *    redoing the map side of a shuffle.
 *
  * - 缓存跟踪：DAGScheduler会指出哪些RDD被缓存，以避免重复计算它们。
  *                       同样，它还会记录shuffle的map阶段已经产生的输出文件，避免重复执行shuffle的map任务。
  *
 *  - Preferred locations: the DAGScheduler also computes where to run each task in a stage based
 *    on the preferred locations of its underlying RDDs, or the location of cached or shuffle data.
  * - 计算最佳位置：DAGScheduler会计算task运行的最佳位置；依据是它的父RDD的最佳计算位置 和 缓存或shuffle数据所在的位置。
 *
 *  - Cleanup: all data structures are cleared when the running jobs that depend on them finish,
 *    to prevent memory leaks in a long-running application.
  * - 清理：为了防止内存泄漏，所有数据结构都会在job运行结束时被清理。
 *
 * To recover from failures, the same stage might need to run multiple times, which are called
 * "attempts". If the TaskScheduler reports that a task failed because a map output file from a
 * previous stage was lost, the DAGScheduler resubmits that lost stage. This is detected through a
 * CompletionEvent with FetchFailed, or an ExecutorLost event. The DAGScheduler will wait a small
 * amount of time to see whether other nodes or tasks fail, then resubmit TaskSets for any lost
 * stage(s) that compute the missing tasks. As part of this process, we might also have to create
 * Stage objects for old (finished) stages where we previously cleaned up the Stage object. Since
 * tasks from the old attempt of a stage could still be running, care must be taken to map any
 * events received in the correct Stage object.
 *
  * 为了从故障中恢复，同一stage可能需要运行多次，这称为“attempts”。
  * 如果TaskScheduler报告了一个由于：上一个阶段的shuffle输出文件丢失而导致的任务失败，则DAGScheduler会重新提交该丢失的阶段。
  * 这是通过带有FetchFailed的CompletionEvent或ExecutorLost事件检测到的。
  * DAGScheduler将等待一小段时间，以查看其他节点或任务是否失败，然后针对计算丢失任务的任何丢失阶段重新提交TaskSet。
  * 作为此过程的一部分，我们可能还必须为以前清理过Stage对象的旧（完成）stage创建Stage对象。
  * 由于来自阶段的旧尝试的任务仍然可以运行，因此必须注意映射在正确的Stage对象中接收到的所有事件。
  *
 * Here's a checklist to use when making or reviewing changes to this class:
 *
 *  - All data structures should be cleared when the jobs involving them end to avoid indefinite
 *    accumulation of state in long-running programs.
 *
 *  - When adding a new data structure, update `DAGSchedulerSuite.assertDataStructuresEmpty` to
 *    include the new structure. This will help to catch memory leaks.
 */
/**
  * 高层次的调度器，实现了面向stage的调度。
  * 总的来说DAGScheduler的功能有以下几个：
  * (1) 根据RDD的依赖关系(lineage)，以shuffle依赖为边界，划分stage，构建stage的DAG。
  * (2) 把stage构建成TaskSet
  * (3) 为task寻找最佳执行位置
  * (4) 缓存跟踪，记录哪些RDD被缓存，哪些shuffle map文件已经计算出来，避免重复计算
  * (5) 任务完成时，清理内存，防止内存泄漏。
  * (6) 任务执行时的容错处理。
  */
private[spark] class DAGScheduler(
    private[scheduler] val sc: SparkContext,
    private[scheduler] val taskScheduler: TaskScheduler,
    listenerBus: LiveListenerBus,
    mapOutputTracker: MapOutputTrackerMaster,
    blockManagerMaster: BlockManagerMaster,
    env: SparkEnv,
    clock: Clock = new SystemClock())
  extends Logging {

  def this(sc: SparkContext, taskScheduler: TaskScheduler) = {
    this(
      sc,
      taskScheduler,
      sc.listenerBus,
      sc.env.mapOutputTracker.asInstanceOf[MapOutputTrackerMaster],
      sc.env.blockManager.master,
      sc.env)
  }

  def this(sc: SparkContext) = this(sc, sc.taskScheduler)

  private[spark] val metricsSource: DAGSchedulerSource = new DAGSchedulerSource(this)

  private[scheduler] val nextJobId = new AtomicInteger(0)
  private[scheduler] def numTotalJobs: Int = nextJobId.get()
  private val nextStageId = new AtomicInteger(0)

  // jobId对应的StageId的集合
  private[scheduler] val jobIdToStageIds = new HashMap[Int, HashSet[Int]]
  // stageId到Stage对象的map
  private[scheduler] val stageIdToStage = new HashMap[Int, Stage]
  /**
   * Mapping from shuffle dependency ID to the ShuffleMapStage that will generate the data for
   * that dependency. Only includes stages that are part of currently running job (when the job(s)
   * that require the shuffle stage complete, the mapping will be removed, and the only record of
   * the shuffle data will be in the MapOutputTracker).
   */
    // shuffle依赖对象id和ShuffleMapStage对象的对应关系。
  private[scheduler] val shuffleIdToMapStage = new HashMap[Int, ShuffleMapStage]
  private[scheduler] val jobIdToActiveJob = new HashMap[Int, ActiveJob]

  // Stages we need to run whose parents aren't done
  private[scheduler] val waitingStages = new HashSet[Stage]

  // Stages we are running right now
  private[scheduler] val runningStages = new HashSet[Stage]

  // Stages that must be resubmitted due to fetch failures
  // 由于failed失败而需要重新提交的stage。
  private[scheduler] val failedStages = new HashSet[Stage]

  private[scheduler] val activeJobs = new HashSet[ActiveJob]

  /**
   * Contains the locations that each RDD's partitions are cached on.  This map's keys are RDD ids
   * and its values are arrays indexed by partition numbers. Each array value is the set of
   * locations where that RDD partition is cached.
   *
   * All accesses to this map should be guarded by synchronizing on it (see SPARK-4454).
   */
    // 包含了每个RDD分区被缓存的位置。该map的key是rdd的id，而value是分区数组的索引。
    // 每个数组的value是rdd分区缓存的位置。
  private val cacheLocs = new HashMap[Int, IndexedSeq[Seq[TaskLocation]]]

  // For tracking failed nodes, we use the MapOutputTracker's epoch number, which is sent with
  // every task. When we detect a node failing, we note the current epoch number and failed
  // executor, increment it for new tasks, and use this to ignore stray ShuffleMapTask results.
  //
  // TODO: Garbage collect information about failure epochs when we know there are no more
  //       stray messages to detect.
   private val failedEpoch = new HashMap[String, Long]

  private [scheduler] val outputCommitCoordinator = env.outputCommitCoordinator

  // A closure serializer that we reuse.
  // This is only safe because DAGScheduler runs in a single thread.
  private val closureSerializer = SparkEnv.get.closureSerializer.newInstance()

  /** If enabled, FetchFailed will not cause stage retry, in order to surface the problem. */
    // 如果启用，则FetchFailed错误不会引起Stage的重试，目的是为了解决该问题。
  private val disallowStageRetryForTest = sc.getConf.getBoolean("spark.test.noStageRetry", false)

  /**
   * Whether to unregister all the outputs on the host in condition that we receive a FetchFailure,
   * this is set default to false, which means, we only unregister the outputs related to the exact
   * executor(instead of the host) on a FetchFailure.
   */
  private[scheduler] val unRegisterOutputOnHostOnFetchFailure =
    sc.getConf.get(config.UNREGISTER_OUTPUT_ON_HOST_ON_FETCH_FAILURE)

  /**
   * Number of consecutive stage attempts allowed before a stage is aborted.
   */
    // 终止Stage之前的连续尝试次数。默认是4
  private[scheduler] val maxConsecutiveStageAttempts =
    sc.getConf.getInt("spark.stage.maxConsecutiveAttempts",
      DAGScheduler.DEFAULT_MAX_CONSECUTIVE_STAGE_ATTEMPTS)

  /**
   * Number of max concurrent tasks check failures for each barrier job.
   */
  private[scheduler] val barrierJobIdToNumTasksCheckFailures = new ConcurrentHashMap[Int, Int]

  /**
   * Time in seconds to wait between a max concurrent tasks check failure and the next check.
   */
  private val timeIntervalNumTasksCheck = sc.getConf
    .get(config.BARRIER_MAX_CONCURRENT_TASKS_CHECK_INTERVAL)

  /**
   * Max number of max concurrent tasks check failures allowed for a job before fail the job
   * submission.
   */
    // 在job提交失败之前，一个job允许的最大并发任务数。
  private val maxFailureNumTasksCheck = sc.getConf
    .get(config.BARRIER_MAX_CONCURRENT_TASKS_CHECK_MAX_FAILURES)

  private val messageScheduler =
    ThreadUtils.newDaemonSingleThreadScheduledExecutor("dag-scheduler-message")

  // 创建DAGScheduler的事件处理循环对象，会在DAGScheduler构造函数的最后启动该事件(创建DAGScheduler对象时就会启动)
  private[spark] val eventProcessLoop = new DAGSchedulerEventProcessLoop(this)
  taskScheduler.setDAGScheduler(this)

  /**
   * Called by the TaskSetManager to report task's starting.
   */
  def taskStarted(task: Task[_], taskInfo: TaskInfo) {
    eventProcessLoop.post(BeginEvent(task, taskInfo))
  }

  /**
   * Called by the TaskSetManager to report that a task has completed
   * and results are being fetched remotely.
   */
  def taskGettingResult(taskInfo: TaskInfo) {
    eventProcessLoop.post(GettingResultEvent(taskInfo))
  }

  /**
   * Called by the TaskSetManager to report task completions or failures.
   */
  // 被TaskSetManager调用，去报道任务完成或失败。
  def taskEnded(
      task: Task[_],
      reason: TaskEndReason,
      result: Any,
      accumUpdates: Seq[AccumulatorV2[_, _]],
      taskInfo: TaskInfo): Unit = {
    eventProcessLoop.post(
      CompletionEvent(task, reason, result, accumUpdates, taskInfo))
  }

  /**
   * Update metrics for in-progress tasks and let the master know that the BlockManager is still
   * alive. Return true if the driver knows about the given block manager. Otherwise, return false,
   * indicating that the block manager should re-register.
   */
  def executorHeartbeatReceived(
      execId: String,
      // (taskId, stageId, stageAttemptId, accumUpdates)
      accumUpdates: Array[(Long, Int, Int, Seq[AccumulableInfo])],
      blockManagerId: BlockManagerId): Boolean = {
    listenerBus.post(SparkListenerExecutorMetricsUpdate(execId, accumUpdates))
    blockManagerMaster.driverEndpoint.askSync[Boolean](
      BlockManagerHeartbeat(blockManagerId), new RpcTimeout(600 seconds, "BlockManagerHeartbeat"))
  }

  /**
   * Called by TaskScheduler implementation when an executor fails.
   */
  def executorLost(execId: String, reason: ExecutorLossReason): Unit = {
    eventProcessLoop.post(ExecutorLost(execId, reason))
  }

  /**
   * Called by TaskScheduler implementation when a worker is removed.
   */
  def workerRemoved(workerId: String, host: String, message: String): Unit = {
    eventProcessLoop.post(WorkerRemoved(workerId, host, message))
  }

  /**
   * Called by TaskScheduler implementation when a host is added.
   */
  def executorAdded(execId: String, host: String): Unit = {
    eventProcessLoop.post(ExecutorAdded(execId, host))
  }

  /**
   * Called by the TaskSetManager to cancel an entire TaskSet due to either repeated failures or
   * cancellation of the job itself.
   */
  def taskSetFailed(taskSet: TaskSet, reason: String, exception: Option[Throwable]): Unit = {
    eventProcessLoop.post(TaskSetFailed(taskSet, reason, exception))
  }

  /**
   * Called by the TaskSetManager when it decides a speculative task is needed.
   */
  def speculativeTaskSubmitted(task: Task[_]): Unit = {
    eventProcessLoop.post(SpeculativeTaskSubmitted(task))
  }

  // 获取cache的位置，由TaskLocation对象来标识
  private[scheduler]
  def getCacheLocs(rdd: RDD[_]): IndexedSeq[Seq[TaskLocation]] = cacheLocs.synchronized {
    // Note: this doesn't use `getOrElse()` because this method is called O(num tasks) times
    if (!cacheLocs.contains(rdd.id)) { // 若位置缓存中不包含该rdd
      // Note: if the storage level is NONE, we don't need to get locations from block manager.
      val locs: IndexedSeq[Seq[TaskLocation]] = if (rdd.getStorageLevel == StorageLevel.NONE) {
        // 若存储级别是NONE: todo: hover
        IndexedSeq.fill(rdd.partitions.length)(Nil)
      } else {
        // 存储级别不是NONE，
        val blockIds =
          rdd.partitions.indices.map(index => RDDBlockId(rdd.id, index)).toArray[BlockId]
        // 获取block块的位置
        blockManagerMaster.getLocations(blockIds).map { bms =>
          bms.map(bm => TaskLocation(bm.host, bm.executorId))
        }
      }
      // 把位置记录到位置缓存中
      cacheLocs(rdd.id) = locs
    }
    // 返回分区号的分区id数组
    cacheLocs(rdd.id)
  }

  private def clearCacheLocs(): Unit = cacheLocs.synchronized {
    cacheLocs.clear()
  }

  /**
   * Gets a shuffle map stage if one exists in shuffleIdToMapStage. Otherwise, if the
   * shuffle map stage doesn't already exist, this method will create the shuffle map stage in
   * addition to any missing ancestor shuffle map stages.
   */
  // 获取一个shuffle map stag，若该stag在shuffleIdToMapStage中存在。
  // 否则，若shuffle map stage不存在，该方法会为缺失的父shuffle map stage来创建shuffle map stage。
  private def getOrCreateShuffleMapStage(
      shuffleDep: ShuffleDependency[_, _, _],
      firstJobId: Int): ShuffleMapStage = {
    shuffleIdToMapStage.get(shuffleDep.shuffleId) match {
      case Some(stage) =>
        stage

      case None =>  // shuffleId和shuffleMapStage对应关系不存在
        // Create stages for all missing ancestor shuffle dependencies.
        // 为缺失的祖先shuffleDep创建stage
        getMissingAncestorShuffleDependencies(shuffleDep.rdd).foreach { dep =>
          // Even though getMissingAncestorShuffleDependencies only returns shuffle dependencies
          // that were not already in shuffleIdToMapStage, it's possible that by the time we
          // get to a particular dependency in the foreach loop, it's been added to
          // shuffleIdToMapStage by the stage creation process for an earlier dependency. See
          // SPARK-13902 for more information.
          if (!shuffleIdToMapStage.contains(dep.shuffleId)) {
            createShuffleMapStage(dep, firstJobId)
          }
        }
        // Finally, create a stage for the given shuffle dependency.
        // 最后为给定shuffleDep创建一个stage
        createShuffleMapStage(shuffleDep, firstJobId)
    }
  }

  /**
   * Check to make sure we don't launch a barrier stage with unsupported RDD chain pattern. The
   * following patterns are not supported:
   * 1. Ancestor RDDs that have different number of partitions from the resulting RDD (eg.
   * union()/coalesce()/first()/take()/PartitionPruningRDD);
   * 2. An RDD that depends on multiple barrier RDDs (eg. barrierRdd1.zip(barrierRdd2)).
   */
  private def checkBarrierStageWithRDDChainPattern(rdd: RDD[_], numTasksInStage: Int): Unit = {
    val predicate: RDD[_] => Boolean = (r =>
      r.getNumPartitions == numTasksInStage && r.dependencies.filter(_.rdd.isBarrier()).size <= 1)
    if (rdd.isBarrier() && !traverseParentRDDsWithinStage(rdd, predicate)) {
      throw new BarrierJobUnsupportedRDDChainException
    }
  }

  /**
   * Creates a ShuffleMapStage that generates the given shuffle dependency's partitions. If a
   * previously run stage generated the same shuffle data, this function will copy the output
   * locations that are still available from the previous shuffle to avoid unnecessarily
   * regenerating data.
   */
  // 创建一个shuffleMapStage，用来产生给定shuffleDep的分区。
  def createShuffleMapStage(shuffleDep: ShuffleDependency[_, _, _], jobId: Int): ShuffleMapStage = {
    // 获取shuffle依赖的rdd
    val rdd = shuffleDep.rdd
    checkBarrierStageWithDynamicAllocation(rdd)
    checkBarrierStageWithNumSlots(rdd)
    checkBarrierStageWithRDDChainPattern(rdd, rdd.getNumPartitions)
    // 获取依赖rdd的分区数，任务数等于rdd的分区数
    val numTasks = rdd.partitions.length
    // 获取最近的依赖的父rdd的Stage列表
    val parents = getOrCreateParentStages(rdd, jobId)
    // 为新的stage产生stageId，该值会自动加1
    val id = nextStageId.getAndIncrement()
    // 创建一个新的ShuffleMapStage
    val stage = new ShuffleMapStage(
      id, rdd, numTasks, parents, jobId, rdd.creationSite, shuffleDep, mapOutputTracker)

    // 把stage放入stageId到stage对象的hashMap中
    stageIdToStage(id) = stage
    shuffleIdToMapStage(shuffleDep.shuffleId) = stage
    // 更新jobId到StageId的map数据，同时把stage更新到jobId的stageId集合中
    updateJobIdStageIdMaps(jobId, stage)

    if (!mapOutputTracker.containsShuffle(shuffleDep.shuffleId)) {
      // Kind of ugly: need to register RDDs with the cache and map output tracker here
      // since we can't do it in the RDD constructor because # of partitions is unknown
      logInfo("Registering RDD " + rdd.id + " (" + rdd.getCreationSite + ")")
      mapOutputTracker.registerShuffle(shuffleDep.shuffleId, rdd.partitions.length)
    }
    stage
  }

  /**
   * We don't support run a barrier stage with dynamic resource allocation enabled, it shall lead
   * to some confusing behaviors (eg. with dynamic resource allocation enabled, it may happen that
   * we acquire some executors (but not enough to launch all the tasks in a barrier stage) and
   * later release them due to executor idle time expire, and then acquire again).
   *
   * We perform the check on job submit and fail fast if running a barrier stage with dynamic
   * resource allocation enabled.
   *
   * TODO SPARK-24942 Improve cluster resource management with jobs containing barrier stage
   */
  private def checkBarrierStageWithDynamicAllocation(rdd: RDD[_]): Unit = {
    if (rdd.isBarrier() && Utils.isDynamicAllocationEnabled(sc.getConf)) {
      throw new BarrierJobRunWithDynamicAllocationException
    }
  }

  /**
   * Check whether the barrier stage requires more slots (to be able to launch all tasks in the
   * barrier stage together) than the total number of active slots currently. Fail current check
   * if trying to submit a barrier stage that requires more slots than current total number. If
   * the check fails consecutively beyond a configured number for a job, then fail current job
   * submission.
   */
  private def checkBarrierStageWithNumSlots(rdd: RDD[_]): Unit = {
    if (rdd.isBarrier() && rdd.getNumPartitions > sc.maxNumConcurrentTasks) {
      throw new BarrierJobSlotsNumberCheckFailed
    }
  }

  /**
   * Create a ResultStage associated with the provided jobId.
   */
  // 为jobId创建一个ResultStage。并创建shuffleMapStage的DAG图
  // 这里的rdd是：执行action的rdd
  private def createResultStage(
      rdd: RDD[_],
      func: (TaskContext, Iterator[_]) => _,
      partitions: Array[Int],
      jobId: Int,
      callSite: CallSite): ResultStage = {
    checkBarrierStageWithDynamicAllocation(rdd)
    checkBarrierStageWithNumSlots(rdd)
    checkBarrierStageWithRDDChainPattern(rdd, partitions.toSet.size)
    // 创建Stage的DAG图，并由parents是该DAG的最底层Stage列表。
    // 创建中间过程的shuffleMapStage
    val parents = getOrCreateParentStages(rdd, jobId)
    val id = nextStageId.getAndIncrement()
    // 为触发actioin的RDD创建一个ResultStage
    val stage = new ResultStage(id, rdd, func, partitions, parents, jobId, callSite)
    stageIdToStage(id) = stage
    // 更新JobId和StageId的映射集合
    updateJobIdStageIdMaps(jobId, stage)
    stage
  }

  /**
   * Get or create the list of parent stages for a given RDD.  The new Stages will be created with
   * the provided firstJobId.
   */
  // 为给定RDD获取或创建一个父stage的list。新的Stage会根据提供的JobId来创建。
  private def getOrCreateParentStages(rdd: RDD[_], firstJobId: Int): List[Stage] = {
    // 根据rdd来获取其依赖的所有的shuffleDep
    getShuffleDependencies(rdd).map { shuffleDep =>
      getOrCreateShuffleMapStage(shuffleDep, firstJobId)
    }.toList
  }

  /** Find ancestor shuffle dependencies that are not registered in shuffleToMapStage yet */
  private def getMissingAncestorShuffleDependencies(
      rdd: RDD[_]): ArrayStack[ShuffleDependency[_, _, _]] = {
    val ancestors = new ArrayStack[ShuffleDependency[_, _, _]]
    val visited = new HashSet[RDD[_]]
    // We are manually maintaining a stack here to prevent StackOverflowError
    // caused by recursively visiting
    val waitingForVisit = new ArrayStack[RDD[_]]
    waitingForVisit.push(rdd)
    while (waitingForVisit.nonEmpty) {
      val toVisit = waitingForVisit.pop()
      if (!visited(toVisit)) {
        visited += toVisit
        getShuffleDependencies(toVisit).foreach { shuffleDep =>
          if (!shuffleIdToMapStage.contains(shuffleDep.shuffleId)) {
            ancestors.push(shuffleDep)
            waitingForVisit.push(shuffleDep.rdd)
          } // Otherwise, the dependency and its ancestors have already been registered.
        }
      }
    }
    ancestors
  }

  /**
   * Returns shuffle dependencies that are immediate parents of the given RDD.
   *
   * This function will not return more distant ancestors.  For example, if C has a shuffle
   * dependency on B which has a shuffle dependency on A:
   *
   * A <-- B <-- C
   *
   * calling this function with rdd C will only return the B <-- C dependency.
   *
   * This function is scheduler-visible for the purpose of unit testing.
   */
  // 返回一个所给RDD的直接父级的shuffle依赖
  // 该函数不会返回多级祖先的shuffle依赖。若a<-b<-c，若给定c，则只会返回b。
  // 也就是说：若第一次访问判断是否是shuffle依赖，若是，则返回该shuffle依赖。若不是，继续向上递归，直到碰到一个shuffle依赖，结束递归。
  // todo: xh
  private[scheduler] def getShuffleDependencies(
      rdd: RDD[_]): HashSet[ShuffleDependency[_, _, _]] = {
    val parents = new HashSet[ShuffleDependency[_, _, _]]
    val visited = new HashSet[RDD[_]]
    val waitingForVisit = new ArrayStack[RDD[_]]
    // 把rdd压栈
    waitingForVisit.push(rdd)
    while (waitingForVisit.nonEmpty) {   // 若栈不为空
      val toVisit = waitingForVisit.pop()  // 从栈中弹出一个元素
      if (!visited(toVisit)) {   // 若visited集合中不存在该元素
        visited += toVisit       // 把该元素放到visited集合中
        toVisit.dependencies.foreach { // 遍历没有访问的rdd的依赖
          case shuffleDep: ShuffleDependency[_, _, _] =>  // 是shuffle依赖，不需要再递归，直接记录
            parents += shuffleDep
          case dependency =>     // 非shuffle依赖，压如栈中，继续递归，直到找shuffle依赖
            waitingForVisit.push(dependency.rdd)
        }
      }
    }
    parents
  }

  /**
   * Traverses the given RDD and its ancestors within the same stage and checks whether all of the
   * RDDs satisfy a given predicate.
   */
  private def traverseParentRDDsWithinStage(rdd: RDD[_], predicate: RDD[_] => Boolean): Boolean = {
    val visited = new HashSet[RDD[_]]
    val waitingForVisit = new ArrayStack[RDD[_]]
    waitingForVisit.push(rdd)
    while (waitingForVisit.nonEmpty) {
      val toVisit = waitingForVisit.pop()
      if (!visited(toVisit)) {
        if (!predicate(toVisit)) {
          return false
        }
        visited += toVisit
        toVisit.dependencies.foreach {
          case _: ShuffleDependency[_, _, _] =>
            // Not within the same stage with current rdd, do nothing.
          case dependency =>
            waitingForVisit.push(dependency.rdd)
        }
      }
    }
    true
  }

  // 获取缺失的父stage(没有生成的父stage)
  private def getMissingParentStages(stage: Stage): List[Stage] = {
    val missing = new HashSet[Stage]
    val visited = new HashSet[RDD[_]]
    // We are manually maintaining a stack here to prevent StackOverflowError
    // caused by recursively visiting

    val waitingForVisit = new ArrayStack[RDD[_]]
    def visit(rdd: RDD[_]) {
      if (!visited(rdd)) { // 若RDD没有被访问
        visited += rdd     // 把rdd放入visited集合中
        val rddHasUncachedPartitions = getCacheLocs(rdd).contains(Nil)
        if (rddHasUncachedPartitions) {
          for (dep <- rdd.dependencies) { // 遍历rdd的依赖rdd列表
            dep match {
              case shufDep: ShuffleDependency[_, _, _] =>
                // 若是宽依赖(shuffle依赖)才创建ShuffleMapStage
                val mapStage = getOrCreateShuffleMapStage(shufDep, stage.firstJobId)
                if (!mapStage.isAvailable) {
                  missing += mapStage
                }
              case narrowDep: NarrowDependency[_] =>
                // 若是窄依赖，则直接把依赖的rdd放到栈中，继续向上查找和访问依赖的rdd
                waitingForVisit.push(narrowDep.rdd)
            }
          }
        }
      }
    }
    waitingForVisit.push(stage.rdd)
    while (waitingForVisit.nonEmpty) {
      visit(waitingForVisit.pop())
    }
    // 把缺失的Stage作为数组返回
    missing.toList
  }

  /**
   * Registers the given jobId among the jobs that need the given stage and
   * all of that stage's ancestors.
   */
  // 把给定的jobId，注册到给定stage和stage的所有祖先中
  private def updateJobIdStageIdMaps(jobId: Int, stage: Stage): Unit = {
    @tailrec
    def updateJobIdStageIdMapsList(stages: List[Stage]) {
      if (stages.nonEmpty) {
        val s = stages.head
        s.jobIds += jobId
        // JobId对应的StageId的集合
        jobIdToStageIds.getOrElseUpdate(jobId, new HashSet[Int]()) += s.id
        // 若父stage不属于这个jobId，则需要过滤掉。这种情况如何发生的？？
        val parentsWithoutThisJobId = s.parents.filter { ! _.jobIds.contains(jobId) }
        updateJobIdStageIdMapsList(parentsWithoutThisJobId ++ stages.tail)
      }
    }
    updateJobIdStageIdMapsList(List(stage))
  }

  /**
   * Removes state for job and any stages that are not needed by any other job.  Does not
   * handle cancelling tasks or notifying the SparkListener about finished jobs/stages/tasks.
   *
   * @param job The job whose state to cleanup.
   */
  private def cleanupStateForJobAndIndependentStages(job: ActiveJob): Unit = {
    val registeredStages = jobIdToStageIds.get(job.jobId)
    if (registeredStages.isEmpty || registeredStages.get.isEmpty) {
      logError("No stages registered for job " + job.jobId)
    } else {
      stageIdToStage.filterKeys(stageId => registeredStages.get.contains(stageId)).foreach {
        case (stageId, stage) =>
          val jobSet = stage.jobIds
          if (!jobSet.contains(job.jobId)) {
            logError(
              "Job %d not registered for stage %d even though that stage was registered for the job"
              .format(job.jobId, stageId))
          } else {
            def removeStage(stageId: Int) {
              // data structures based on Stage
              for (stage <- stageIdToStage.get(stageId)) {
                if (runningStages.contains(stage)) {
                  logDebug("Removing running stage %d".format(stageId))
                  runningStages -= stage
                }
                for ((k, v) <- shuffleIdToMapStage.find(_._2 == stage)) {
                  shuffleIdToMapStage.remove(k)
                }
                if (waitingStages.contains(stage)) {
                  logDebug("Removing stage %d from waiting set.".format(stageId))
                  waitingStages -= stage
                }
                if (failedStages.contains(stage)) {
                  logDebug("Removing stage %d from failed set.".format(stageId))
                  failedStages -= stage
                }
              }
              // data structures based on StageId
              stageIdToStage -= stageId
              logDebug("After removal of stage %d, remaining stages = %d"
                .format(stageId, stageIdToStage.size))
            }

            jobSet -= job.jobId
            if (jobSet.isEmpty) { // no other job needs this stage
              removeStage(stageId)
            }
          }
      }
    }
    jobIdToStageIds -= job.jobId
    jobIdToActiveJob -= job.jobId
    activeJobs -= job
    job.finalStage match {
      case r: ResultStage => r.removeActiveJob()
      case m: ShuffleMapStage => m.removeActiveJob(job)
    }
  }

  /**
   * Submit an action job to the scheduler.
   *
   * @param rdd target RDD to run tasks on
   * @param func a function to run on each partition of the RDD
   * @param partitions set of partitions to run on; some jobs may not want to compute on all
   *   partitions of the target RDD, e.g. for operations like first()
   * @param callSite where in the user program this job was called
   * @param resultHandler callback to pass each result to
   * @param properties scheduler properties to attach to this job, e.g. fair scheduler pool name
   *
   * @return a JobWaiter object that can be used to block until the job finishes executing
   *         or can be used to cancel the job.
   *
   * @throws IllegalArgumentException when partitions ids are illegal
   */
  def submitJob[T, U](
      rdd: RDD[T],
      func: (TaskContext, Iterator[T]) => U,
      partitions: Seq[Int],
      callSite: CallSite,
      resultHandler: (Int, U) => Unit,
      properties: Properties): JobWaiter[U] = {
    // Check to make sure we are not launching a task on a partition that does not exist.
    // 检查执行job的rdd是否有分区存在
    val maxPartitions = rdd.partitions.length
    // 遍历每个分区id，若`分区id`<0或大于最大id（这是不可能的），此时抛出异常
    partitions.find(p => p >= maxPartitions || p < 0).foreach { p =>
      throw new IllegalArgumentException(
        "Attempting to access a non-existent partition: " + p + ". " +
          "Total number of partitions: " + maxPartitions)
    }

    // 为job创建一个唯一的jobId，这是一个自增的id
    val jobId = nextJobId.getAndIncrement()
    // 若rdd的分区数为0，则不会提交job，直接返回结果
    if (partitions.size == 0) {
      // Return immediately if the job is running 0 tasks
      return new JobWaiter[U](this, jobId, 0, resultHandler)
    }

    assert(partitions.size > 0)
    // todo: xh
    val func2 = func.asInstanceOf[(TaskContext, Iterator[_]) => _]
    val waiter = new JobWaiter(this, jobId, partitions.size, resultHandler)
    // 向事件总线提交JobSubmitted事件，进入正式的job提交流程
    eventProcessLoop.post(JobSubmitted(
      jobId, rdd, func2, partitions.toArray, callSite, waiter,
      SerializationUtils.clone(properties)))
    waiter
  }

  /**
   * Run an action job on the given RDD and pass all the results to the resultHandler function as
   * they arrive.
   *
   * @param rdd target RDD to run tasks on
   * @param func a function to run on each partition of the RDD
   * @param partitions set of partitions to run on; some jobs may not want to compute on all
   *   partitions of the target RDD, e.g. for operations like first()
   * @param callSite where in the user program this job was called
   * @param resultHandler callback to pass each result to
   * @param properties scheduler properties to attach to this job, e.g. fair scheduler pool name
   *
   * @note Throws `Exception` when the job fails
   */
  // 在给定的RDD上运行action的job，并传递计算结果给resultHandler函数。
  def runJob[T, U](
      rdd: RDD[T],
      func: (TaskContext, Iterator[T]) => U,
      partitions: Seq[Int],
      callSite: CallSite,
      resultHandler: (Int, U) => Unit,
      properties: Properties): Unit = {
    // 记录job开始时间
    val start = System.nanoTime
    // 调用DAGScheduler#submitJob来提交job，并返回一个JobWaiter对象。
    // JobWaiter对象：可用来等待任务执行完成，返回任务结果；也可以用来取消任务
    val waiter = submitJob(rdd, func, partitions, callSite, resultHandler, properties)
    // 等待结果的返回
    ThreadUtils.awaitReady(waiter.completionFuture, Duration.Inf)
    // 通过JobWaiter对象来获取job执行的结果状态
    waiter.completionFuture.value.get match {
      case scala.util.Success(_) =>
        logInfo("Job %d finished: %s, took %f s".format
          (waiter.jobId, callSite.shortForm, (System.nanoTime - start) / 1e9))
      case scala.util.Failure(exception) =>
        logInfo("Job %d failed: %s, took %f s".format
          (waiter.jobId, callSite.shortForm, (System.nanoTime - start) / 1e9))
        // SPARK-8644: Include user stack trace in exceptions coming from DAGScheduler.
        val callerStackTrace = Thread.currentThread().getStackTrace.tail
        exception.setStackTrace(exception.getStackTrace ++ callerStackTrace)
        throw exception
    }
  }

  /**
   * Run an approximate job on the given RDD and pass all the results to an ApproximateEvaluator
   * as they arrive. Returns a partial result object from the evaluator.
   *
   * @param rdd target RDD to run tasks on
   * @param func a function to run on each partition of the RDD
   * @param evaluator `ApproximateEvaluator` to receive the partial results
   * @param callSite where in the user program this job was called
   * @param timeout maximum time to wait for the job, in milliseconds
   * @param properties scheduler properties to attach to this job, e.g. fair scheduler pool name
   */
  def runApproximateJob[T, U, R](
      rdd: RDD[T],
      func: (TaskContext, Iterator[T]) => U,
      evaluator: ApproximateEvaluator[U, R],
      callSite: CallSite,
      timeout: Long,
      properties: Properties): PartialResult[R] = {
    val listener = new ApproximateActionListener(rdd, func, evaluator, timeout)
    val func2 = func.asInstanceOf[(TaskContext, Iterator[_]) => _]
    val partitions = (0 until rdd.partitions.length).toArray
    val jobId = nextJobId.getAndIncrement()
    eventProcessLoop.post(JobSubmitted(
      jobId, rdd, func2, partitions, callSite, listener, SerializationUtils.clone(properties)))
    listener.awaitResult()    // Will throw an exception if the job fails
  }

  /**
   * Submit a shuffle map stage to run independently and get a JobWaiter object back. The waiter
   * can be used to block until the job finishes executing or can be used to cancel the job.
   * This method is used for adaptive query planning, to run map stages and look at statistics
   * about their outputs before submitting downstream stages.
   *
   * @param dependency the ShuffleDependency to run a map stage for
   * @param callback function called with the result of the job, which in this case will be a
   *   single MapOutputStatistics object showing how much data was produced for each partition
   * @param callSite where in the user program this job was submitted
   * @param properties scheduler properties to attach to this job, e.g. fair scheduler pool name
   */
  def submitMapStage[K, V, C](
      dependency: ShuffleDependency[K, V, C],
      callback: MapOutputStatistics => Unit,
      callSite: CallSite,
      properties: Properties): JobWaiter[MapOutputStatistics] = {

    val rdd = dependency.rdd
    val jobId = nextJobId.getAndIncrement()
    if (rdd.partitions.length == 0) {
      throw new SparkException("Can't run submitMapStage on RDD with 0 partitions")
    }

    // We create a JobWaiter with only one "task", which will be marked as complete when the whole
    // map stage has completed, and will be passed the MapOutputStatistics for that stage.
    // This makes it easier to avoid race conditions between the user code and the map output
    // tracker that might result if we told the user the stage had finished, but then they queries
    // the map output tracker and some node failures had caused the output statistics to be lost.
    val waiter = new JobWaiter(this, jobId, 1, (i: Int, r: MapOutputStatistics) => callback(r))
    eventProcessLoop.post(MapStageSubmitted(
      jobId, dependency, callSite, waiter, SerializationUtils.clone(properties)))
    waiter
  }

  /**
   * Cancel a job that is running or waiting in the queue.
   */
  def cancelJob(jobId: Int, reason: Option[String]): Unit = {
    logInfo("Asked to cancel job " + jobId)
    eventProcessLoop.post(JobCancelled(jobId, reason))
  }

  /**
   * Cancel all jobs in the given job group ID.
   */
  def cancelJobGroup(groupId: String): Unit = {
    logInfo("Asked to cancel job group " + groupId)
    eventProcessLoop.post(JobGroupCancelled(groupId))
  }

  /**
   * Cancel all jobs that are running or waiting in the queue.
   */
  def cancelAllJobs(): Unit = {
    eventProcessLoop.post(AllJobsCancelled)
  }

  private[scheduler] def doCancelAllJobs() {
    // Cancel all running jobs.
    runningStages.map(_.firstJobId).foreach(handleJobCancellation(_,
      Option("as part of cancellation of all jobs")))
    activeJobs.clear() // These should already be empty by this point,
    jobIdToActiveJob.clear() // but just in case we lost track of some jobs...
  }

  /**
   * Cancel all jobs associated with a running or scheduled stage.
   */
  def cancelStage(stageId: Int, reason: Option[String]) {
    eventProcessLoop.post(StageCancelled(stageId, reason))
  }

  /**
   * Kill a given task. It will be retried.
   *
   * @return Whether the task was successfully killed.
   */
  def killTaskAttempt(taskId: Long, interruptThread: Boolean, reason: String): Boolean = {
    taskScheduler.killTaskAttempt(taskId, interruptThread, reason)
  }

  /**
   * Resubmit any failed stages. Ordinarily called after a small amount of time has passed since
   * the last fetch failure.
   */
  // 重新提交任何失败的stage。通常，自从上次调用失败后会等一段时间。
  private[scheduler] def resubmitFailedStages() {
    if (failedStages.size > 0) {
      // Failed stages may be removed by job cancellation, so failed might be empty even if
      // the ResubmitFailedStages event has been scheduled.
      logInfo("Resubmitting failed stages")
      // 清除缓存位置信息,有可能此时的缓存位置已经失效了
      clearCacheLocs()
      val failedStagesCopy = failedStages.toArray
      failedStages.clear()
      for (stage <- failedStagesCopy.sortBy(_.firstJobId)) {
        submitStage(stage)
      }
    }
  }

  /**
   * Check for waiting stages which are now eligible for resubmission.
   * Submits stages that depend on the given parent stage. Called when the parent stage completes
   * successfully.
   */
  private def submitWaitingChildStages(parent: Stage) {
    logTrace(s"Checking if any dependencies of $parent are now runnable")
    logTrace("running: " + runningStages)
    logTrace("waiting: " + waitingStages)
    logTrace("failed: " + failedStages)
    val childStages = waitingStages.filter(_.parents.contains(parent)).toArray
    waitingStages --= childStages
    for (stage <- childStages.sortBy(_.firstJobId)) {
      submitStage(stage)
    }
  }

  /** Finds the earliest-created active job that needs the stage */
  // TODO: Probably should actually find among the active jobs that need this
  // stage the one with the highest priority (highest-priority pool, earliest created).
  // That should take care of at least part of the priority inversion problem with
  // cross-job dependencies.
  // 找到最早创建的需要stage的activeJob，对应的id
  // 具有最高优先级的stage
  private def activeJobForStage(stage: Stage): Option[Int] = {
    // 对jobId进行排序
    val jobsThatUseStage: Array[Int] = stage.jobIds.toArray.sorted
    jobsThatUseStage.find(jobIdToActiveJob.contains)
  }

  private[scheduler] def handleJobGroupCancelled(groupId: String) {
    // Cancel all jobs belonging to this job group.
    // First finds all active jobs with this group id, and then kill stages for them.
    val activeInGroup = activeJobs.filter { activeJob =>
      Option(activeJob.properties).exists {
        _.getProperty(SparkContext.SPARK_JOB_GROUP_ID) == groupId
      }
    }
    val jobIds = activeInGroup.map(_.jobId)
    jobIds.foreach(handleJobCancellation(_,
        Option("part of cancelled job group %s".format(groupId))))
  }

  private[scheduler] def handleBeginEvent(task: Task[_], taskInfo: TaskInfo) {
    // Note that there is a chance that this task is launched after the stage is cancelled.
    // In that case, we wouldn't have the stage anymore in stageIdToStage.
    val stageAttemptId =
      stageIdToStage.get(task.stageId).map(_.latestInfo.attemptNumber).getOrElse(-1)
    listenerBus.post(SparkListenerTaskStart(task.stageId, stageAttemptId, taskInfo))
  }

  private[scheduler] def handleSpeculativeTaskSubmitted(task: Task[_]): Unit = {
    listenerBus.post(SparkListenerSpeculativeTaskSubmitted(task.stageId))
  }

  private[scheduler] def handleTaskSetFailed(
      taskSet: TaskSet,
      reason: String,
      exception: Option[Throwable]): Unit = {
    stageIdToStage.get(taskSet.stageId).foreach { abortStage(_, reason, exception) }
  }

  private[scheduler] def cleanUpAfterSchedulerStop() {
    for (job <- activeJobs) {
      val error =
        new SparkException(s"Job ${job.jobId} cancelled because SparkContext was shut down")
      job.listener.jobFailed(error)
      // Tell the listeners that all of the running stages have ended.  Don't bother
      // cancelling the stages because if the DAG scheduler is stopped, the entire application
      // is in the process of getting stopped.
      val stageFailedMessage = "Stage cancelled because SparkContext was shut down"
      // The `toArray` here is necessary so that we don't iterate over `runningStages` while
      // mutating it.
      runningStages.toArray.foreach { stage =>
        markStageAsFinished(stage, Some(stageFailedMessage))
      }
      listenerBus.post(SparkListenerJobEnd(job.jobId, clock.getTimeMillis(), JobFailed(error)))
    }
  }

  private[scheduler] def handleGetTaskResult(taskInfo: TaskInfo) {
    listenerBus.post(SparkListenerTaskGettingResult(taskInfo))
  }

  // 处理job提交事件。在调用RDD的action算子时，就会触发job的提交，最终会通过该函数来完成最终的job提交。
  private[scheduler] def handleJobSubmitted(jobId: Int,
      finalRDD: RDD[_],
      func: (TaskContext, Iterator[_]) => _,
      partitions: Array[Int],
      callSite: CallSite,
      listener: JobListener,
      properties: Properties) {
    // 定义一个ResultStage变量
    var finalStage: ResultStage = null
    try {
      // New stage creation may throw an exception if, for example, jobs are run on a
      // HadoopRDD whose underlying HDFS files have been deleted.
      // 因为触发action操作才会执行该函数，而执行action操作的rdd，一定就是最后的rdd，也就是finalRDD.
      // 根据是否产生shuffle操作(以shuffle操作为边界)来创建Stage
      finalStage = createResultStage(finalRDD, func, partitions, jobId, callSite)
    } catch {
      case e: BarrierJobSlotsNumberCheckFailed =>
        logWarning(s"The job $jobId requires to run a barrier stage that requires more slots " +
          "than the total number of slots in the cluster currently.")
        // If jobId doesn't exist in the map, Scala coverts its value null to 0: Int automatically.
        val numCheckFailures = barrierJobIdToNumTasksCheckFailures.compute(jobId,
          new BiFunction[Int, Int, Int] {
            override def apply(key: Int, value: Int): Int = value + 1
          })
        if (numCheckFailures <= maxFailureNumTasksCheck) {
          messageScheduler.schedule(
            new Runnable {
              override def run(): Unit = eventProcessLoop.post(JobSubmitted(jobId, finalRDD, func,
                partitions, callSite, listener, properties))
            },
            timeIntervalNumTasksCheck,
            TimeUnit.SECONDS
          )
          return
        } else {
          // Job failed, clear internal data.
          barrierJobIdToNumTasksCheckFailures.remove(jobId)
          listener.jobFailed(e)
          return
        }

      case e: Exception =>
        logWarning("Creating new stage failed due to exception - job: " + jobId, e)
        listener.jobFailed(e)
        return
    }
    // Job submitted, clear internal data.
    barrierJobIdToNumTasksCheckFailures.remove(jobId)

    // stage划分和创建完成，这里创建一个ActiveJob对象，代表一个job
    // 有两种job:一种是计算ResultStage；一种是用来计算ShuffleMapStage。
    val job = new ActiveJob(jobId, finalStage, callSite, listener, properties)
    // 清除rdd分区位置的缓存
    clearCacheLocs()
    logInfo("Got job %s (%s) with %d output partitions".format(
      job.jobId, callSite.shortForm, partitions.length))
    logInfo("Final stage: " + finalStage + " (" + finalStage.name + ")")
    logInfo("Parents of final stage: " + finalStage.parents)
    logInfo("Missing parents: " + getMissingParentStages(finalStage))

    // job提交的时间
    val jobSubmissionTime = clock.getTimeMillis()
    // jobId对应的ActiveJob对象句柄，把提交的job记录到一个hashmap中
    jobIdToActiveJob(jobId) = job
    // job集合
    activeJobs += job
    // 每个ResultStage都对应一个ActiveJob对象，创建完成后会设置该job对象
    finalStage.setActiveJob(job)
    // 把该job所有的stage保存到数组中
    val stageIds = jobIdToStageIds(jobId).toArray
    // todo: xh
    val stageInfos = stageIds.flatMap(id => stageIdToStage.get(id).map(_.latestInfo))
    // todo: xh
    listenerBus.post(
      SparkListenerJobStart(job.jobId, jobSubmissionTime, stageInfos, properties))
    // 提交finaStage，然后递归地提交依赖的stage
    submitStage(finalStage)
  }

  private[scheduler] def handleMapStageSubmitted(jobId: Int,
      dependency: ShuffleDependency[_, _, _],
      callSite: CallSite,
      listener: JobListener,
      properties: Properties) {
    // Submitting this map stage might still require the creation of some parent stages, so make
    // sure that happens.
    var finalStage: ShuffleMapStage = null
    try {
      // New stage creation may throw an exception if, for example, jobs are run on a
      // HadoopRDD whose underlying HDFS files have been deleted.
      finalStage = getOrCreateShuffleMapStage(dependency, jobId)
    } catch {
      case e: Exception =>
        logWarning("Creating new stage failed due to exception - job: " + jobId, e)
        listener.jobFailed(e)
        return
    }

    val job = new ActiveJob(jobId, finalStage, callSite, listener, properties)
    clearCacheLocs()
    logInfo("Got map stage job %s (%s) with %d output partitions".format(
      jobId, callSite.shortForm, dependency.rdd.partitions.length))
    logInfo("Final stage: " + finalStage + " (" + finalStage.name + ")")
    logInfo("Parents of final stage: " + finalStage.parents)
    logInfo("Missing parents: " + getMissingParentStages(finalStage))

    val jobSubmissionTime = clock.getTimeMillis()
    jobIdToActiveJob(jobId) = job
    activeJobs += job
    finalStage.addActiveJob(job)
    val stageIds = jobIdToStageIds(jobId).toArray
    val stageInfos = stageIds.flatMap(id => stageIdToStage.get(id).map(_.latestInfo))
    listenerBus.post(
      SparkListenerJobStart(job.jobId, jobSubmissionTime, stageInfos, properties))
    submitStage(finalStage)

    // If the whole stage has already finished, tell the listener and remove it
    if (finalStage.isAvailable) {
      markMapStageJobAsFinished(job, mapOutputTracker.getStatistics(dependency))
    }
  }

  /** Submits stage, but first recursively submits any missing parents. */
  // 提交stage，但首先会递归的提交缺失的父stage。
  private def submitStage(stage: Stage) {
    val jobId = activeJobForStage(stage)
    if (jobId.isDefined) {
      logDebug("submitStage(" + stage + ")")
      if (!waitingStages(stage) && !runningStages(stage) && !failedStages(stage)) {
        // 获取或创建缺失的父stage
        val missing = getMissingParentStages(stage).sortBy(_.id)
        logDebug("missing: " + missing)
        if (missing.isEmpty) { // 若已经没有缺失的父Stage了，则提交当前stage
          logInfo("Submitting " + stage + " (" + stage.rdd + "), which has no missing parents")
          // 先提交依赖的，并缺失的stage的任务
          submitMissingTasks(stage, jobId.get)
        } else {
          // 若有缺失的父stage，先提交父stage
          for (parent <- missing) {
            submitStage(parent)
          }
          // 若已经提交该stage，就把该stage添加到stage的等待集合中
          waitingStages += stage
        }
      }
    } else {
      abortStage(stage, "No active job for stage " + stage.id, None)
    }
  }

  /** Called when stage's parents are available and we can now do its task. */
  private def submitMissingTasks(stage: Stage, jobId: Int) {
    logDebug("submitMissingTasks(" + stage + ")")

    // First figure out the indexes of partition ids to compute.
    // 先找到需要计算的分区id
    val partitionsToCompute: Seq[Int] = stage.findMissingPartitions()

    // Use the scheduling pool, job group, description, etc. from an ActiveJob associated
    // with this Stage
    // 使用调度池，job组，描述等。来自和该stage联系的ActiveJob。
    val properties = jobIdToActiveJob(jobId).properties

    runningStages += stage
    // SparkListenerStageSubmitted should be posted before testing whether tasks are
    // serializable. If tasks are not serializable, a SparkListenerStageCompleted event
    // will be posted, which should always come after a corresponding SparkListenerStageSubmitted
    // event.
    stage match { // 初始化stage的状态
      case s: ShuffleMapStage =>
        outputCommitCoordinator.stageStart(stage = s.id, maxPartitionId = s.numPartitions - 1)
      case s: ResultStage =>
        outputCommitCoordinator.stageStart(
          stage = s.id, maxPartitionId = s.rdd.partitions.length - 1)
    }
    // 找到计算该stage时，计算每个分区的最佳位置
    val taskIdToLocations: Map[Int, Seq[TaskLocation]] = try {
      stage match {
        case s: ShuffleMapStage =>
          partitionsToCompute.map { id => (id, getPreferredLocs(stage.rdd, id))}.toMap
        case s: ResultStage =>
          partitionsToCompute.map { id =>
            val p = s.partitions(id)
            (id, getPreferredLocs(stage.rdd, p))
          }.toMap
      }
    } catch {
      case NonFatal(e) =>
        stage.makeNewStageAttempt(partitionsToCompute.size)
        listenerBus.post(SparkListenerStageSubmitted(stage.latestInfo, properties))
        abortStage(stage, s"Task creation failed: $e\n${Utils.exceptionString(e)}", Some(e))
        runningStages -= stage
        return
    }

    stage.makeNewStageAttempt(partitionsToCompute.size, taskIdToLocations.values.toSeq)

    // If there are tasks to execute, record the submission time of the stage. Otherwise,
    // post the even without the submission time, which indicates that this stage was
    // skipped.
    if (partitionsToCompute.nonEmpty) {
      stage.latestInfo.submissionTime = Some(clock.getTimeMillis())
    }
    listenerBus.post(SparkListenerStageSubmitted(stage.latestInfo, properties))

    // TODO: Maybe we can keep the taskBinary in Stage to avoid serializing it multiple times.
    // Broadcasted binary for the task, used to dispatch tasks to executors. Note that we broadcast
    // the serialized copy of the RDD and for each task we will deserialize it, which means each
    // task gets a different copy of the RDD. This provides stronger isolation between tasks that
    // might modify state of objects referenced in their closures. This is necessary in Hadoop
    // where the JobConf/Configuration object is not thread-safe.
    var taskBinary: Broadcast[Array[Byte]] = null
    var partitions: Array[Partition] = null
    try {
      // For ShuffleMapTask, serialize and broadcast (rdd, shuffleDep).
      // For ResultTask, serialize and broadcast (rdd, func).
      var taskBinaryBytes: Array[Byte] = null
      // taskBinaryBytes and partitions are both effected by the checkpoint status. We need
      // this synchronization in case another concurrent job is checkpointing this RDD, so we get a
      // consistent view of both variables.
      RDDCheckpointData.synchronized {
        taskBinaryBytes = stage match {
          case stage: ShuffleMapStage =>
            JavaUtils.bufferToArray(
              closureSerializer.serialize((stage.rdd, stage.shuffleDep): AnyRef))
          case stage: ResultStage =>
            // 序列化stage的函数和rdd，该rdd是运行action函数的rdd，依赖的最后一个rdd
            // func是计算结果的函数
            JavaUtils.bufferToArray(closureSerializer.serialize((stage.rdd, stage.func): AnyRef))
        }

        partitions = stage.rdd.partitions
      }

      taskBinary = sc.broadcast(taskBinaryBytes)
    } catch {
      // In the case of a failure during serialization, abort the stage.
      case e: NotSerializableException =>
        abortStage(stage, "Task not serializable: " + e.toString, Some(e))
        runningStages -= stage

        // Abort execution
        return
      case e: Throwable =>
        abortStage(stage, s"Task serialization failed: $e\n${Utils.exceptionString(e)}", Some(e))
        runningStages -= stage

        // Abort execution
        return
    }

    // 为stage创建一个TaskSet，每个stage对应一组task
    val tasks: Seq[Task[_]] = try {
      val serializedTaskMetrics = closureSerializer.serialize(stage.latestInfo.taskMetrics).array()
      stage match {
        case stage: ShuffleMapStage =>
          stage.pendingPartitions.clear()
          // 遍历需要计算的分区id，为每个分区创建一个Task
          partitionsToCompute.map { id =>
            val locs = taskIdToLocations(id)
            val part = partitions(id)
            stage.pendingPartitions += id
            // 若ShuffleMapStage，则创建一个ShuffleMapTask
            new ShuffleMapTask(stage.id, stage.latestInfo.attemptNumber,
              taskBinary, part, locs, properties, serializedTaskMetrics, Option(jobId),
              Option(sc.applicationId), sc.applicationAttemptId, stage.rdd.isBarrier())
          }

        case stage: ResultStage =>
          // 最后一个Stage，用来计算最终结果
          partitionsToCompute.map { id =>
            // 获取id对应的Partition对象
            val p: Int = stage.partitions(id)
            val part = partitions(p)
            // 获取task执行的最佳位置
            val locs = taskIdToLocations(id)
            // 创建resultTask
            new ResultTask(stage.id, stage.latestInfo.attemptNumber,
              taskBinary, part, locs, id, properties, serializedTaskMetrics,
              Option(jobId), Option(sc.applicationId), sc.applicationAttemptId,
              stage.rdd.isBarrier())
          }
      }
    } catch {
      case NonFatal(e) =>
        abortStage(stage, s"Task creation failed: $e\n${Utils.exceptionString(e)}", Some(e))
        runningStages -= stage
        return
    }

    // 若创建的task的集合不为空，则调用任务调度器来提交taskSet
    if (tasks.size > 0) {
      logInfo(s"Submitting ${tasks.size} missing tasks from $stage (${stage.rdd}) (first 15 " +
        s"tasks are for partitions ${tasks.take(15).map(_.partitionId)})")
      // 通过低层的TaskScheduler来提交任务集(taskSet)
      taskScheduler.submitTasks(new TaskSet(
        tasks.toArray, stage.id, stage.latestInfo.attemptNumber, jobId, properties))
    } else {
      // Because we posted SparkListenerStageSubmitted earlier, we should mark
      // the stage as completed here in case there are no tasks to run
      markStageAsFinished(stage, None)

      stage match {
        case stage: ShuffleMapStage =>
          logDebug(s"Stage ${stage} is actually done; " +
              s"(available: ${stage.isAvailable}," +
              s"available outputs: ${stage.numAvailableOutputs}," +
              s"partitions: ${stage.numPartitions})")
          markMapStageJobsAsFinished(stage)
        case stage : ResultStage =>
          logDebug(s"Stage ${stage} is actually done; (partitions: ${stage.numPartitions})")
      }
      submitWaitingChildStages(stage)
    }
  }

  /**
   * Merge local values from a task into the corresponding accumulators previously registered
   * here on the driver.
   *
   * Although accumulators themselves are not thread-safe, this method is called only from one
   * thread, the one that runs the scheduling loop. This means we only handle one task
   * completion event at a time so we don't need to worry about locking the accumulators.
   * This still doesn't stop the caller from updating the accumulator outside the scheduler,
   * but that's not our problem since there's nothing we can do about that.
   */
  private def updateAccumulators(event: CompletionEvent): Unit = {
    val task = event.task
    val stage = stageIdToStage(task.stageId)

    event.accumUpdates.foreach { updates =>
      val id = updates.id
      try {
        // Find the corresponding accumulator on the driver and update it
        val acc: AccumulatorV2[Any, Any] = AccumulatorContext.get(id) match {
          case Some(accum) => accum.asInstanceOf[AccumulatorV2[Any, Any]]
          case None =>
            throw new SparkException(s"attempted to access non-existent accumulator $id")
        }
        acc.merge(updates.asInstanceOf[AccumulatorV2[Any, Any]])
        // To avoid UI cruft, ignore cases where value wasn't updated
        if (acc.name.isDefined && !updates.isZero) {
          stage.latestInfo.accumulables(id) = acc.toInfo(None, Some(acc.value))
          event.taskInfo.setAccumulables(
            acc.toInfo(Some(updates.value), Some(acc.value)) +: event.taskInfo.accumulables)
        }
      } catch {
        case NonFatal(e) =>
          // Log the class name to make it easy to find the bad implementation
          val accumClassName = AccumulatorContext.get(id) match {
            case Some(accum) => accum.getClass.getName
            case None => "Unknown class"
          }
          logError(
            s"Failed to update accumulator $id ($accumClassName) for task ${task.partitionId}",
            e)
      }
    }
  }

  private def postTaskEnd(event: CompletionEvent): Unit = {
    val taskMetrics: TaskMetrics =
      if (event.accumUpdates.nonEmpty) {
        try {
          TaskMetrics.fromAccumulators(event.accumUpdates)
        } catch {
          case NonFatal(e) =>
            val taskId = event.taskInfo.taskId
            logError(s"Error when attempting to reconstruct metrics for task $taskId", e)
            null
        }
      } else {
        null
      }

    listenerBus.post(SparkListenerTaskEnd(event.task.stageId, event.task.stageAttemptId,
      Utils.getFormattedClassName(event.task), event.reason, event.taskInfo, taskMetrics))
  }

  /**
   * Responds to a task finishing. This is called inside the event loop so it assumes that it can
   * modify the scheduler's internal state. Use taskEnded() to post a task end event from outside.
   */
  private[scheduler] def handleTaskCompletion(event: CompletionEvent) {
    // 获取事件中的task对象
    val task = event.task
    // 获取task的stageId信息
    val stageId = task.stageId

    outputCommitCoordinator.taskCompleted(
      stageId,
      task.stageAttemptId,
      task.partitionId,
      event.taskInfo.attemptNumber, // this is a task attempt number
      event.reason)

    // 若该stage不在全部stage信息集合中，可能该stage已经结束
    if (!stageIdToStage.contains(task.stageId)) {
      // The stage may have already finished when we get this event -- eg. maybe it was a
      // speculative task. It is important that we send the TaskEnd event in any case, so listeners
      // are properly notified and can chose to handle it. For instance, some listeners are
      // doing their own accounting and if they don't get the task end event they think
      // tasks are still running when they really aren't.
      postTaskEnd(event)

      // Skip all the actions if the stage has been cancelled.
      return
    }

    val stage = stageIdToStage(task.stageId)

    // Make sure the task's accumulators are updated before any other processing happens, so that
    // we can post a task end event before any jobs or stages are updated. The accumulators are
    // only updated in certain cases.
    event.reason match {
      case Success =>
        task match {
          case rt: ResultTask[_, _] =>
            val resultStage = stage.asInstanceOf[ResultStage]
            resultStage.activeJob match {
              case Some(job) =>
                // Only update the accumulator once for each result task.
                if (!job.finished(rt.outputId)) {
                  updateAccumulators(event)
                }
              case None => // Ignore update if task's job has finished.
            }
          case _ =>
            updateAccumulators(event)
        }
      case _: ExceptionFailure | _: TaskKilled => updateAccumulators(event)
      case _ =>
    }
    postTaskEnd(event)

    event.reason match {
      case Success =>
        task match {
          case rt: ResultTask[_, _] =>
            // Cast to ResultStage here because it's part of the ResultTask
            // TODO Refactor this out to a function that accepts a ResultStage
            val resultStage = stage.asInstanceOf[ResultStage]
            resultStage.activeJob match {
              case Some(job) =>
                if (!job.finished(rt.outputId)) {
                  job.finished(rt.outputId) = true
                  job.numFinished += 1
                  // If the whole job has finished, remove it
                  if (job.numFinished == job.numPartitions) {
                    markStageAsFinished(resultStage)
                    cleanupStateForJobAndIndependentStages(job)
                    listenerBus.post(
                      SparkListenerJobEnd(job.jobId, clock.getTimeMillis(), JobSucceeded))
                  }

                  // taskSucceeded runs some user code that might throw an exception. Make sure
                  // we are resilient against that.
                  try {
                    job.listener.taskSucceeded(rt.outputId, event.result)
                  } catch {
                    case e: Exception =>
                      // TODO: Perhaps we want to mark the resultStage as failed?
                      job.listener.jobFailed(new SparkDriverExecutionException(e))
                  }
                }
              case None =>
                logInfo("Ignoring result from " + rt + " because its job has finished")
            }

          case smt: ShuffleMapTask =>
            val shuffleStage = stage.asInstanceOf[ShuffleMapStage]
            shuffleStage.pendingPartitions -= task.partitionId
            val status = event.result.asInstanceOf[MapStatus]
            val execId = status.location.executorId
            logDebug("ShuffleMapTask finished on " + execId)
            if (failedEpoch.contains(execId) && smt.epoch <= failedEpoch(execId)) {
              logInfo(s"Ignoring possibly bogus $smt completion from executor $execId")
            } else {
              // The epoch of the task is acceptable (i.e., the task was launched after the most
              // recent failure we're aware of for the executor), so mark the task's output as
              // available.
              mapOutputTracker.registerMapOutput(
                shuffleStage.shuffleDep.shuffleId, smt.partitionId, status)
            }

            if (runningStages.contains(shuffleStage) && shuffleStage.pendingPartitions.isEmpty) {
              markStageAsFinished(shuffleStage)
              logInfo("looking for newly runnable stages")
              logInfo("running: " + runningStages)
              logInfo("waiting: " + waitingStages)
              logInfo("failed: " + failedStages)

              // This call to increment the epoch may not be strictly necessary, but it is retained
              // for now in order to minimize the changes in behavior from an earlier version of the
              // code. This existing behavior of always incrementing the epoch following any
              // successful shuffle map stage completion may have benefits by causing unneeded
              // cached map outputs to be cleaned up earlier on executors. In the future we can
              // consider removing this call, but this will require some extra investigation.
              // See https://github.com/apache/spark/pull/17955/files#r117385673 for more details.
              mapOutputTracker.incrementEpoch()

              clearCacheLocs()

              // shuffleStage的分区输出不完整，说明有些Task已经失败
              if (!shuffleStage.isAvailable) {
                // Some tasks had failed; let's resubmit this shuffleStage.
                // TODO: Lower-level scheduler should also deal with this
                logInfo("Resubmitting " + shuffleStage + " (" + shuffleStage.name +
                  ") because some of its tasks had failed: " +
                  shuffleStage.findMissingPartitions().mkString(", "))
                submitStage(shuffleStage)
              } else {
                markMapStageJobsAsFinished(shuffleStage)
                submitWaitingChildStages(shuffleStage)
              }
            }
        }

      case FetchFailed(bmAddress, shuffleId, mapId, _, failureMessage) =>
        val failedStage = stageIdToStage(task.stageId)
        val mapStage = shuffleIdToMapStage(shuffleId)

        if (failedStage.latestInfo.attemptNumber != task.stageAttemptId) {
          logInfo(s"Ignoring fetch failure from $task as it's from $failedStage attempt" +
            s" ${task.stageAttemptId} and there is a more recent attempt for that stage " +
            s"(attempt ${failedStage.latestInfo.attemptNumber}) running")
        } else {
          failedStage.failedAttemptIds.add(task.stageAttemptId)
          // 是否应该放弃这个Stage，或则次数超出了最大尝试次数，或则设置了不需要重试的选项
          val shouldAbortStage =
            failedStage.failedAttemptIds.size >= maxConsecutiveStageAttempts ||
            disallowStageRetryForTest

          // It is likely that we receive multiple FetchFailed for a single stage (because we have
          // multiple tasks running concurrently on different executors). In that case, it is
          // possible the fetch failure has already been handled by the scheduler.
          if (runningStages.contains(failedStage)) {
            logInfo(s"Marking $failedStage (${failedStage.name}) as failed " +
              s"due to a fetch failure from $mapStage (${mapStage.name})")
            markStageAsFinished(failedStage, errorMessage = Some(failureMessage),
              willRetry = !shouldAbortStage)
          } else {
            logDebug(s"Received fetch failure from $task, but its from $failedStage which is no " +
              s"longer running")
          }

          if (mapStage.rdd.isBarrier()) {
            // Mark all the map as broken in the map stage, to ensure retry all the tasks on
            // resubmitted stage attempt.
            mapOutputTracker.unregisterAllMapOutput(shuffleId)
          } else if (mapId != -1) {
            // Mark the map whose fetch failed as broken in the map stage
            mapOutputTracker.unregisterMapOutput(shuffleId, mapId, bmAddress)
          }

          if (failedStage.rdd.isBarrier()) {
            failedStage match {
              case failedMapStage: ShuffleMapStage =>
                // Mark all the map as broken in the map stage, to ensure retry all the tasks on
                // resubmitted stage attempt.
                mapOutputTracker.unregisterAllMapOutput(failedMapStage.shuffleDep.shuffleId)

              case failedResultStage: ResultStage =>
                // Abort the failed result stage since we may have committed output for some
                // partitions.
                val reason = "Could not recover from a failed barrier ResultStage. Most recent " +
                  s"failure reason: $failureMessage"
                abortStage(failedResultStage, reason, None)
            }
          }

          if (shouldAbortStage) {
            val abortMessage = if (disallowStageRetryForTest) {
              "Fetch failure will not retry stage due to testing config"
            } else {
              s"""$failedStage (${failedStage.name})
                 |has failed the maximum allowable number of
                 |times: $maxConsecutiveStageAttempts.
                 |Most recent failure reason: $failureMessage""".stripMargin.replaceAll("\n", " ")
            }
            abortStage(failedStage, abortMessage, None)
          } else { // update failedStages and make sure a ResubmitFailedStages event is enqueued
            // TODO: Cancel running tasks in the failed stage -- cf. SPARK-17064
            val noResubmitEnqueued = !failedStages.contains(failedStage)
            failedStages += failedStage
            failedStages += mapStage
            if (noResubmitEnqueued) {
              // If the map stage is INDETERMINATE, which means the map tasks may return
              // different result when re-try, we need to re-try all the tasks of the failed
              // stage and its succeeding stages, because the input data will be changed after the
              // map tasks are re-tried.
              // Note that, if map stage is UNORDERED, we are fine. The shuffle partitioner is
              // guaranteed to be determinate, so the input data of the reducers will not change
              // even if the map tasks are re-tried.
              if (mapStage.rdd.outputDeterministicLevel == DeterministicLevel.INDETERMINATE) {
                // It's a little tricky to find all the succeeding stages of `mapStage`, because
                // each stage only know its parents not children. Here we traverse the stages from
                // the leaf nodes (the result stages of active jobs), and rollback all the stages
                // in the stage chains that connect to the `mapStage`. To speed up the stage
                // traversing, we collect the stages to rollback first. If a stage needs to
                // rollback, all its succeeding stages need to rollback to.
                val stagesToRollback = HashSet[Stage](mapStage)

                def collectStagesToRollback(stageChain: List[Stage]): Unit = {
                  if (stagesToRollback.contains(stageChain.head)) {
                    stageChain.drop(1).foreach(s => stagesToRollback += s)
                  } else {
                    stageChain.head.parents.foreach { s =>
                      collectStagesToRollback(s :: stageChain)
                    }
                  }
                }

                def generateErrorMessage(stage: Stage): String = {
                  "A shuffle map stage with indeterminate output was failed and retried. " +
                    s"However, Spark cannot rollback the $stage to re-process the input data, " +
                    "and has to fail this job. Please eliminate the indeterminacy by " +
                    "checkpointing the RDD before repartition and try again."
                }

                activeJobs.foreach(job => collectStagesToRollback(job.finalStage :: Nil))

                stagesToRollback.foreach {
                  case mapStage: ShuffleMapStage =>
                    val numMissingPartitions = mapStage.findMissingPartitions().length
                    if (numMissingPartitions < mapStage.numTasks) {
                      // TODO: support to rollback shuffle files.
                      // Currently the shuffle writing is "first write wins", so we can't re-run a
                      // shuffle map stage and overwrite existing shuffle files. We have to finish
                      // SPARK-8029 first.
                      abortStage(mapStage, generateErrorMessage(mapStage), None)
                    }

                  case resultStage: ResultStage if resultStage.activeJob.isDefined =>
                    val numMissingPartitions = resultStage.findMissingPartitions().length
                    if (numMissingPartitions < resultStage.numTasks) {
                      // TODO: support to rollback result tasks.
                      abortStage(resultStage, generateErrorMessage(resultStage), None)
                    }

                  case _ =>
                }
              }

              // We expect one executor failure to trigger many FetchFailures in rapid succession,
              // but all of those task failures can typically be handled by a single resubmission of
              // the failed stage.  We avoid flooding the scheduler's event queue with resubmit
              // messages by checking whether a resubmit is already in the event queue for the
              // failed stage.  If there is already a resubmit enqueued for a different failed
              // stage, that event would also be sufficient to handle the current failed stage, but
              // producing a resubmit for each failed stage makes debugging and logging a little
              // simpler while not producing an overwhelming number of scheduler events.
              logInfo(
                s"Resubmitting $mapStage (${mapStage.name}) and " +
                  s"$failedStage (${failedStage.name}) due to fetch failure"
              )
              messageScheduler.schedule(
                new Runnable {
                  override def run(): Unit = eventProcessLoop.post(ResubmitFailedStages)
                },
                DAGScheduler.RESUBMIT_TIMEOUT,
                TimeUnit.MILLISECONDS
              )
            }
          }

          // TODO: mark the executor as failed only if there were lots of fetch failures on it
          if (bmAddress != null) {
            val hostToUnregisterOutputs = if (env.blockManager.externalShuffleServiceEnabled &&
              unRegisterOutputOnHostOnFetchFailure) {
              // We had a fetch failure with the external shuffle service, so we
              // assume all shuffle data on the node is bad.
              Some(bmAddress.host)
            } else {
              // Unregister shuffle data just for one executor (we don't have any
              // reason to believe shuffle data has been lost for the entire host).
              None
            }
            removeExecutorAndUnregisterOutputs(
              execId = bmAddress.executorId,
              fileLost = true,
              hostToUnregisterOutputs = hostToUnregisterOutputs,
              maybeEpoch = Some(task.epoch))
          }
        }

      case failure: TaskFailedReason if task.isBarrier =>
        // Also handle the task failed reasons here.
        failure match {
          case Resubmitted =>
            handleResubmittedFailure(task, stage)

          case _ => // Do nothing.
        }

        // Always fail the current stage and retry all the tasks when a barrier task fail.
        val failedStage = stageIdToStage(task.stageId)
        if (failedStage.latestInfo.attemptNumber != task.stageAttemptId) {
          logInfo(s"Ignoring task failure from $task as it's from $failedStage attempt" +
            s" ${task.stageAttemptId} and there is a more recent attempt for that stage " +
            s"(attempt ${failedStage.latestInfo.attemptNumber}) running")
        } else {
          logInfo(s"Marking $failedStage (${failedStage.name}) as failed due to a barrier task " +
            "failed.")
          val message = s"Stage failed because barrier task $task finished unsuccessfully.\n" +
            failure.toErrorString
          try {
            // killAllTaskAttempts will fail if a SchedulerBackend does not implement killTask.
            val reason = s"Task $task from barrier stage $failedStage (${failedStage.name}) " +
              "failed."
            taskScheduler.killAllTaskAttempts(stageId, interruptThread = false, reason)
          } catch {
            case e: UnsupportedOperationException =>
              // Cannot continue with barrier stage if failed to cancel zombie barrier tasks.
              // TODO SPARK-24877 leave the zombie tasks and ignore their completion events.
              logWarning(s"Could not kill all tasks for stage $stageId", e)
              abortStage(failedStage, "Could not kill zombie barrier tasks for stage " +
                s"$failedStage (${failedStage.name})", Some(e))
          }
          markStageAsFinished(failedStage, Some(message))

          failedStage.failedAttemptIds.add(task.stageAttemptId)
          // TODO Refactor the failure handling logic to combine similar code with that of
          // FetchFailed.
          val shouldAbortStage =
            failedStage.failedAttemptIds.size >= maxConsecutiveStageAttempts ||
              disallowStageRetryForTest

          if (shouldAbortStage) {
            val abortMessage = if (disallowStageRetryForTest) {
              "Barrier stage will not retry stage due to testing config. Most recent failure " +
                s"reason: $message"
            } else {
              s"""$failedStage (${failedStage.name})
                 |has failed the maximum allowable number of
                 |times: $maxConsecutiveStageAttempts.
                 |Most recent failure reason: $message
               """.stripMargin.replaceAll("\n", " ")
            }
            abortStage(failedStage, abortMessage, None)
          } else {
            failedStage match {
              case failedMapStage: ShuffleMapStage =>
                // Mark all the map as broken in the map stage, to ensure retry all the tasks on
                // resubmitted stage attempt.
                mapOutputTracker.unregisterAllMapOutput(failedMapStage.shuffleDep.shuffleId)

              case failedResultStage: ResultStage =>
                // Abort the failed result stage since we may have committed output for some
                // partitions.
                val reason = "Could not recover from a failed barrier ResultStage. Most recent " +
                  s"failure reason: $message"
                abortStage(failedResultStage, reason, None)
            }
            // In case multiple task failures triggered for a single stage attempt, ensure we only
            // resubmit the failed stage once.
            val noResubmitEnqueued = !failedStages.contains(failedStage)
            failedStages += failedStage
            if (noResubmitEnqueued) {
              logInfo(s"Resubmitting $failedStage (${failedStage.name}) due to barrier stage " +
                "failure.")
              messageScheduler.schedule(new Runnable {
                override def run(): Unit = eventProcessLoop.post(ResubmitFailedStages)
              }, DAGScheduler.RESUBMIT_TIMEOUT, TimeUnit.MILLISECONDS)
            }
          }
        }

      case Resubmitted =>
        handleResubmittedFailure(task, stage)

      case _: TaskCommitDenied =>
        // Do nothing here, left up to the TaskScheduler to decide how to handle denied commits

      case _: ExceptionFailure | _: TaskKilled =>
        // Nothing left to do, already handled above for accumulator updates.

      case TaskResultLost =>
        // Do nothing here; the TaskScheduler handles these failures and resubmits the task.

      case _: ExecutorLostFailure | UnknownReason =>
        // Unrecognized failure - also do nothing. If the task fails repeatedly, the TaskScheduler
        // will abort the job.
    }
  }

  private def handleResubmittedFailure(task: Task[_], stage: Stage): Unit = {
    logInfo(s"Resubmitted $task, so marking it as still running.")
    stage match {
      case sms: ShuffleMapStage =>
        sms.pendingPartitions += task.partitionId

      case _ =>
        throw new SparkException("TaskSetManagers should only send Resubmitted task " +
          "statuses for tasks in ShuffleMapStages.")
    }
  }

  private[scheduler] def markMapStageJobsAsFinished(shuffleStage: ShuffleMapStage): Unit = {
    // Mark any map-stage jobs waiting on this stage as finished
    if (shuffleStage.isAvailable && shuffleStage.mapStageJobs.nonEmpty) {
      val stats = mapOutputTracker.getStatistics(shuffleStage.shuffleDep)
      for (job <- shuffleStage.mapStageJobs) {
        markMapStageJobAsFinished(job, stats)
      }
    }
  }

  /**
   * Responds to an executor being lost. This is called inside the event loop, so it assumes it can
   * modify the scheduler's internal state. Use executorLost() to post a loss event from outside.
   *
   * We will also assume that we've lost all shuffle blocks associated with the executor if the
   * executor serves its own blocks (i.e., we're not using external shuffle), the entire slave
   * is lost (likely including the shuffle service), or a FetchFailed occurred, in which case we
   * presume all shuffle data related to this executor to be lost.
   *
   * Optionally the epoch during which the failure was caught can be passed to avoid allowing
   * stray fetch failures from possibly retriggering the detection of a node as lost.
   */
  private[scheduler] def handleExecutorLost(
      execId: String,
      workerLost: Boolean): Unit = {
    // if the cluster manager explicitly tells us that the entire worker was lost, then
    // we know to unregister shuffle output.  (Note that "worker" specifically refers to the process
    // from a Standalone cluster, where the shuffle service lives in the Worker.)
    val fileLost = workerLost || !env.blockManager.externalShuffleServiceEnabled
    removeExecutorAndUnregisterOutputs(
      execId = execId,
      fileLost = fileLost,
      hostToUnregisterOutputs = None,
      maybeEpoch = None)
  }

  private def removeExecutorAndUnregisterOutputs(
      execId: String,
      fileLost: Boolean,
      hostToUnregisterOutputs: Option[String],
      maybeEpoch: Option[Long] = None): Unit = {
    val currentEpoch = maybeEpoch.getOrElse(mapOutputTracker.getEpoch)
    if (!failedEpoch.contains(execId) || failedEpoch(execId) < currentEpoch) {
      failedEpoch(execId) = currentEpoch
      logInfo("Executor lost: %s (epoch %d)".format(execId, currentEpoch))
      blockManagerMaster.removeExecutor(execId)
      if (fileLost) {
        hostToUnregisterOutputs match {
          case Some(host) =>
            logInfo("Shuffle files lost for host: %s (epoch %d)".format(host, currentEpoch))
            mapOutputTracker.removeOutputsOnHost(host)
          case None =>
            logInfo("Shuffle files lost for executor: %s (epoch %d)".format(execId, currentEpoch))
            mapOutputTracker.removeOutputsOnExecutor(execId)
        }
        clearCacheLocs()

      } else {
        logDebug("Additional executor lost message for %s (epoch %d)".format(execId, currentEpoch))
      }
    }
  }

  /**
   * Responds to a worker being removed. This is called inside the event loop, so it assumes it can
   * modify the scheduler's internal state. Use workerRemoved() to post a loss event from outside.
   *
   * We will assume that we've lost all shuffle blocks associated with the host if a worker is
   * removed, so we will remove them all from MapStatus.
   *
   * @param workerId identifier of the worker that is removed.
   * @param host host of the worker that is removed.
   * @param message the reason why the worker is removed.
   */
  private[scheduler] def handleWorkerRemoved(
      workerId: String,
      host: String,
      message: String): Unit = {
    logInfo("Shuffle files lost for worker %s on host %s".format(workerId, host))
    mapOutputTracker.removeOutputsOnHost(host)
    clearCacheLocs()
  }

  private[scheduler] def handleExecutorAdded(execId: String, host: String) {
    // remove from failedEpoch(execId) ?
    if (failedEpoch.contains(execId)) {
      logInfo("Host added was in lost list earlier: " + host)
      failedEpoch -= execId
    }
  }

  private[scheduler] def handleStageCancellation(stageId: Int, reason: Option[String]) {
    stageIdToStage.get(stageId) match {
      case Some(stage) =>
        val jobsThatUseStage: Array[Int] = stage.jobIds.toArray
        jobsThatUseStage.foreach { jobId =>
          val reasonStr = reason match {
            case Some(originalReason) =>
              s"because $originalReason"
            case None =>
              s"because Stage $stageId was cancelled"
          }
          handleJobCancellation(jobId, Option(reasonStr))
        }
      case None =>
        logInfo("No active jobs to kill for Stage " + stageId)
    }
  }

  private[scheduler] def handleJobCancellation(jobId: Int, reason: Option[String]) {
    if (!jobIdToStageIds.contains(jobId)) {
      logDebug("Trying to cancel unregistered job " + jobId)
    } else {
      failJobAndIndependentStages(
        jobIdToActiveJob(jobId), "Job %d cancelled %s".format(jobId, reason.getOrElse("")))
    }
  }

  /**
   * Marks a stage as finished and removes it from the list of running stages.
   */
  private def markStageAsFinished(
      stage: Stage,
      errorMessage: Option[String] = None,
      willRetry: Boolean = false): Unit = {
    val serviceTime = stage.latestInfo.submissionTime match {
      case Some(t) => "%.03f".format((clock.getTimeMillis() - t) / 1000.0)
      case _ => "Unknown"
    }
    if (errorMessage.isEmpty) {
      logInfo("%s (%s) finished in %s s".format(stage, stage.name, serviceTime))
      stage.latestInfo.completionTime = Some(clock.getTimeMillis())

      // Clear failure count for this stage, now that it's succeeded.
      // We only limit consecutive failures of stage attempts,so that if a stage is
      // re-used many times in a long-running job, unrelated failures don't eventually cause the
      // stage to be aborted.
      stage.clearFailures()
    } else {
      stage.latestInfo.stageFailed(errorMessage.get)
      logInfo(s"$stage (${stage.name}) failed in $serviceTime s due to ${errorMessage.get}")
    }

    if (!willRetry) {
      outputCommitCoordinator.stageEnd(stage.id)
    }
    listenerBus.post(SparkListenerStageCompleted(stage.latestInfo))
    runningStages -= stage
  }

  /**
   * Aborts all jobs depending on a particular Stage. This is called in response to a task set
   * being canceled by the TaskScheduler. Use taskSetFailed() to inject this event from outside.
   */
  private[scheduler] def abortStage(
      failedStage: Stage,
      reason: String,
      exception: Option[Throwable]): Unit = {
    if (!stageIdToStage.contains(failedStage.id)) {
      // Skip all the actions if the stage has been removed.
      return
    }
    val dependentJobs: Seq[ActiveJob] =
      activeJobs.filter(job => stageDependsOn(job.finalStage, failedStage)).toSeq
    failedStage.latestInfo.completionTime = Some(clock.getTimeMillis())
    for (job <- dependentJobs) {
      failJobAndIndependentStages(job, s"Job aborted due to stage failure: $reason", exception)
    }
    if (dependentJobs.isEmpty) {
      logInfo("Ignoring failure of " + failedStage + " because all jobs depending on it are done")
    }
  }

  /** Fails a job and all stages that are only used by that job, and cleans up relevant state. */
  private def failJobAndIndependentStages(
      job: ActiveJob,
      failureReason: String,
      exception: Option[Throwable] = None): Unit = {
    val error = new SparkException(failureReason, exception.getOrElse(null))
    var ableToCancelStages = true

    val shouldInterruptThread =
      if (job.properties == null) false
      else job.properties.getProperty(SparkContext.SPARK_JOB_INTERRUPT_ON_CANCEL, "false").toBoolean

    // Cancel all independent, running stages.
    // 取消所有独立的，运行的stage
    val stages = jobIdToStageIds(job.jobId)
    // 需要取消的stage为空
    if (stages.isEmpty) {
      logError("No stages registered for job " + job.jobId)
    }
    // 遍历stages集合
    stages.foreach { stageId =>
      val jobsForStage: Option[HashSet[Int]] = stageIdToStage.get(stageId).map(_.jobIds)
      if (jobsForStage.isEmpty || !jobsForStage.get.contains(job.jobId)) {
        logError(
          "Job %d not registered for stage %d even though that stage was registered for the job"
            .format(job.jobId, stageId))
      } else if (jobsForStage.get.size == 1) { // 若job数为1，也就是没有Job共用该stage时才能取消
        if (!stageIdToStage.contains(stageId)) {
          logError(s"Missing Stage for stage with id $stageId")
        } else {
          // This is the only job that uses this stage, so fail the stage if it is running.
          val stage = stageIdToStage(stageId)
          if (runningStages.contains(stage)) {
            try { // cancelTasks will fail if a SchedulerBackend does not implement killTask
              taskScheduler.cancelTasks(stageId, shouldInterruptThread)
              markStageAsFinished(stage, Some(failureReason))
            } catch {
              case e: UnsupportedOperationException =>
                logInfo(s"Could not cancel tasks for stage $stageId", e)
              ableToCancelStages = false
            }
          }
        }
      }
    }

    if (ableToCancelStages) {
      // SPARK-15783 important to cleanup state first, just for tests where we have some asserts
      // against the state.  Otherwise we have a *little* bit of flakiness in the tests.
      cleanupStateForJobAndIndependentStages(job)
      job.listener.jobFailed(error)
      listenerBus.post(SparkListenerJobEnd(job.jobId, clock.getTimeMillis(), JobFailed(error)))
    }
  }

  /** Return true if one of stage's ancestors is target. */
  private def stageDependsOn(stage: Stage, target: Stage): Boolean = {
    if (stage == target) {
      return true
    }
    val visitedRdds = new HashSet[RDD[_]]
    // We are manually maintaining a stack here to prevent StackOverflowError
    // caused by recursively visiting
    val waitingForVisit = new ArrayStack[RDD[_]]
    def visit(rdd: RDD[_]) {
      if (!visitedRdds(rdd)) {
        visitedRdds += rdd
        for (dep <- rdd.dependencies) {
          dep match {
            case shufDep: ShuffleDependency[_, _, _] =>
              val mapStage = getOrCreateShuffleMapStage(shufDep, stage.firstJobId)
              if (!mapStage.isAvailable) {
                waitingForVisit.push(mapStage.rdd)
              }  // Otherwise there's no need to follow the dependency back
            case narrowDep: NarrowDependency[_] =>
              waitingForVisit.push(narrowDep.rdd)
          }
        }
      }
    }
    waitingForVisit.push(stage.rdd)
    while (waitingForVisit.nonEmpty) {
      visit(waitingForVisit.pop())
    }
    visitedRdds.contains(target.rdd)
  }

  /**
   * Gets the locality information associated with a partition of a particular RDD.
   *
   * This method is thread-safe and is called from both DAGScheduler and SparkContext.
   *
   * @param rdd whose partitions are to be looked at
   * @param partition to lookup locality information for
   * @return list of machines that are preferred by the partition
   */
  // 获取给定RDD分区的首选位置信息
  private[spark]
  def getPreferredLocs(rdd: RDD[_], partition: Int): Seq[TaskLocation] = {
    getPreferredLocsInternal(rdd, partition, new HashSet)
  }

  /**
   * Recursive implementation for getPreferredLocs.
   *
   * This method is thread-safe because it only accesses DAGScheduler state through thread-safe
   * methods (getCacheLocs()); please be careful when modifying this method, because any new
   * DAGScheduler state accessed by it may require additional synchronization.
   */
  // 递归实现获取首选位置
  private def getPreferredLocsInternal(
      rdd: RDD[_],
      partition: Int,
      visited: HashSet[(RDD[_], Int)]): Seq[TaskLocation] = {
    // If the partition has already been visited, no need to re-visit.
    // This avoids exponential path exploration.  SPARK-695
    // 把已经访问过的RDD的分区位置记录到一个HashSet中，
    // 若分区已经被访问过，就不需要再找该分区的位置
    if (!visited.add((rdd, partition))) {
      // Nil has already been returned for previously visited partitions.
      return Nil
    }
    // If the partition is cached, return the cache locations
    // 若该分区被缓存起来了，就返回缓存的位置
    val cached = getCacheLocs(rdd)(partition)
    if (cached.nonEmpty) {
      return cached
    }
    // If the RDD has some placement preferences (as is the case for input RDDs), get those
    // 若RDD有一些首选位置（例如：输入rdd时），则获取这些位置？？
    val rddPrefs = rdd.preferredLocations(rdd.partitions(partition)).toList
    if (rddPrefs.nonEmpty) {
      return rddPrefs.map(TaskLocation(_))
    }

    // If the RDD has narrow dependencies, pick the first partition of the first narrow dependency
    // that has any placement preferences. Ideally we would choose based on transfer sizes,
    // but this will do for now.
    // 若RDD有窄依赖，获取窄依赖的第一个有任何首选位置的分区。理想状态下，我们会基于传输大小来选择，但现在可以这样做了。
    rdd.dependencies.foreach {
      case n: NarrowDependency[_] =>
        for (inPart <- n.getParents(partition)) { // 父RDD的分区列表
          val locs = getPreferredLocsInternal(n.rdd, inPart, visited)
          if (locs != Nil) {
            return locs
          }
        }

      case _ =>
    }

    Nil
  }

  /** Mark a map stage job as finished with the given output stats, and report to its listener. */
  def markMapStageJobAsFinished(job: ActiveJob, stats: MapOutputStatistics): Unit = {
    // In map stage jobs, we only create a single "task", which is to finish all of the stage
    // (including reusing any previous map outputs, etc); so we just mark task 0 as done
    job.finished(0) = true
    job.numFinished += 1
    job.listener.taskSucceeded(0, stats)
    cleanupStateForJobAndIndependentStages(job)
    listenerBus.post(SparkListenerJobEnd(job.jobId, clock.getTimeMillis(), JobSucceeded))
  }

  def stop() {
    messageScheduler.shutdownNow()
    eventProcessLoop.stop()
    taskScheduler.stop()
  }

  // 在创建DAGscheduler对象时启动事件处理
  // xh注：这其实是一个生产者消费者模型，通过阻塞来保存事件很容易实现
  eventProcessLoop.start()
}

// DAGScheduler的事件处理循环
// 在父类EventLoop中定义了一个生产者&消费者模型，通过一个阻塞队列(BlockingQueue)来存放和消费事件
// xh注：由于DAGScheduler会处理多种类型的事件，所以使用P-C模型可以很好完成这个任务。
private[scheduler] class DAGSchedulerEventProcessLoop(dagScheduler: DAGScheduler)
  extends EventLoop[DAGSchedulerEvent]("dag-scheduler-event-loop") with Logging {

  private[this] val timer = dagScheduler.metricsSource.messageProcessingTimer

  /**
   * The main event loop of the DAG scheduler.
   */
  // 重载EventLoop#onReceive函数
  override def onReceive(event: DAGSchedulerEvent): Unit = {
    val timerContext = timer.time()
    try {
      doOnReceive(event)
    } finally {
      timerContext.stop()
    }
  }

  private def doOnReceive(event: DAGSchedulerEvent): Unit = event match {
      // 处理提交job的事件
    case JobSubmitted(jobId, rdd, func, partitions, callSite, listener, properties) =>
      dagScheduler.handleJobSubmitted(jobId, rdd, func, partitions, callSite, listener, properties)

    case MapStageSubmitted(jobId, dependency, callSite, listener, properties) =>
      dagScheduler.handleMapStageSubmitted(jobId, dependency, callSite, listener, properties)

    case StageCancelled(stageId, reason) =>
      dagScheduler.handleStageCancellation(stageId, reason)

    case JobCancelled(jobId, reason) =>
      dagScheduler.handleJobCancellation(jobId, reason)

    case JobGroupCancelled(groupId) =>
      dagScheduler.handleJobGroupCancelled(groupId)

    case AllJobsCancelled =>
      dagScheduler.doCancelAllJobs()

      // 添加executor事件
    case ExecutorAdded(execId, host) =>
      dagScheduler.handleExecutorAdded(execId, host)

    case ExecutorLost(execId, reason) =>
      val workerLost = reason match {
        case SlaveLost(_, true) => true
        case _ => false
      }
      dagScheduler.handleExecutorLost(execId, workerLost)

    case WorkerRemoved(workerId, host, message) =>
      dagScheduler.handleWorkerRemoved(workerId, host, message)

    case BeginEvent(task, taskInfo) =>
      dagScheduler.handleBeginEvent(task, taskInfo)

    case SpeculativeTaskSubmitted(task) =>
      dagScheduler.handleSpeculativeTaskSubmitted(task)

    case GettingResultEvent(taskInfo) =>
      dagScheduler.handleGetTaskResult(taskInfo)

    case completion: CompletionEvent =>
      dagScheduler.handleTaskCompletion(completion)

    case TaskSetFailed(taskSet, reason, exception) =>
      dagScheduler.handleTaskSetFailed(taskSet, reason, exception)

    case ResubmitFailedStages =>
      dagScheduler.resubmitFailedStages()
  }

  override def onError(e: Throwable): Unit = {
    logError("DAGSchedulerEventProcessLoop failed; shutting down SparkContext", e)
    try {
      dagScheduler.doCancelAllJobs()
    } catch {
      case t: Throwable => logError("DAGScheduler failed to cancel all jobs.", t)
    }
    dagScheduler.sc.stopInNewThread()
  }

  override def onStop(): Unit = {
    // Cancel any active jobs in postStop hook
    dagScheduler.cleanUpAfterSchedulerStop()
  }
}

private[spark] object DAGScheduler {
  // The time, in millis, to wait for fetch failure events to stop coming in after one is detected;
  // this is a simplistic way to avoid resubmitting tasks in the non-fetchable map stage one by one
  // as more failure events come in
  val RESUBMIT_TIMEOUT = 200

  // Number of consecutive stage attempts allowed before a stage is aborted
  val DEFAULT_MAX_CONSECUTIVE_STAGE_ATTEMPTS = 4
}
