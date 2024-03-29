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

package org.apache.spark.sql.execution

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{execution, AnalysisException, Strategy}
import org.apache.spark.sql.catalyst.InternalRow
import org.apache.spark.sql.catalyst.encoders.RowEncoder
import org.apache.spark.sql.catalyst.expressions._
import org.apache.spark.sql.catalyst.expressions.aggregate.AggregateExpression
import org.apache.spark.sql.catalyst.planning._
import org.apache.spark.sql.catalyst.plans._
import org.apache.spark.sql.catalyst.plans.logical._
import org.apache.spark.sql.catalyst.plans.physical._
import org.apache.spark.sql.catalyst.streaming.InternalOutputModes
import org.apache.spark.sql.execution.columnar.{InMemoryRelation, InMemoryTableScanExec}
import org.apache.spark.sql.execution.command._
import org.apache.spark.sql.execution.exchange.ShuffleExchangeExec
import org.apache.spark.sql.execution.joins.{BuildLeft, BuildRight, BuildSide}
import org.apache.spark.sql.execution.python._
import org.apache.spark.sql.execution.streaming._
import org.apache.spark.sql.execution.streaming.sources.MemoryPlanV2
import org.apache.spark.sql.internal.SQLConf
import org.apache.spark.sql.streaming.{OutputMode, StreamingQuery}
import org.apache.spark.sql.types.StructType

/**
 * Converts a logical plan into zero or more SparkPlans.  This API is exposed for experimenting
 * with the query planner and is not designed to be stable across spark releases.  Developers
 * writing libraries should instead consider using the stable APIs provided in
 * [[org.apache.spark.sql.sources]]
 */
// 把一个逻辑执行计划转换成0个或更多的SparkPlan
abstract class SparkStrategy extends GenericStrategy[SparkPlan] {

  override protected def planLater(plan: LogicalPlan): SparkPlan = PlanLater(plan)
}

case class PlanLater(plan: LogicalPlan) extends LeafExecNode {

  override def output: Seq[Attribute] = plan.output

  protected override def doExecute(): RDD[InternalRow] = {
    throw new UnsupportedOperationException()
  }
}

abstract class SparkStrategies extends QueryPlanner[SparkPlan] {
  self: SparkPlanner =>

  /**
   * Plans special cases of limit operators.
   */
  // 为限制操作提供Plan
  object SpecialLimits extends Strategy {
    override def apply(plan: LogicalPlan): Seq[SparkPlan] = plan match {
      case ReturnAnswer(rootPlan) => rootPlan match {
        case Limit(IntegerLiteral(limit), Sort(order, true, child))
            if limit < conf.topKSortFallbackThreshold =>
          TakeOrderedAndProjectExec(limit, order, child.output, planLater(child)) :: Nil
        case Limit(IntegerLiteral(limit), Project(projectList, Sort(order, true, child)))
            if limit < conf.topKSortFallbackThreshold =>
          TakeOrderedAndProjectExec(limit, order, projectList, planLater(child)) :: Nil
        case Limit(IntegerLiteral(limit), child) =>
          CollectLimitExec(limit, planLater(child)) :: Nil
        case other => planLater(other) :: Nil
      }
      case Limit(IntegerLiteral(limit), Sort(order, true, child))
          if limit < conf.topKSortFallbackThreshold =>
        TakeOrderedAndProjectExec(limit, order, child.output, planLater(child)) :: Nil
      case Limit(IntegerLiteral(limit), Project(projectList, Sort(order, true, child)))
          if limit < conf.topKSortFallbackThreshold =>
        TakeOrderedAndProjectExec(limit, order, projectList, planLater(child)) :: Nil
      case _ => Nil
    }
  }

  /**
   * Select the proper physical plan for join based on joining keys and size of logical plan.
   *
   * At first, uses the [[ExtractEquiJoinKeys]] pattern to find joins where at least some of the
   * predicates can be evaluated by matching join keys. If found, join implementations are chosen
   * with the following precedence:
    * 首先，使用 [[ExtractEquiJoinKeys]] 模式查找至少可以通过匹配join的key来评估某些谓词的连接。
    * 如果找到，则按以下优先级选择连接实现：
   *
   * - Broadcast hash join (BHJ):
   *     BHJ is not supported for full outer join. For right outer join, we only can broadcast the
   *     left side. For left outer, left semi, left anti and the internal join type ExistenceJoin,
   *     we only can broadcast the right side. For inner like join, we can broadcast both sides.
   *     Normally, BHJ can perform faster than the other join algorithms when the broadcast side is
   *     small. However, broadcasting tables is a network-intensive operation. It could cause OOM
   *     or perform worse than the other join algorithms, especially when the build/broadcast side
   *     is big.
    *
    *   full outer join不支持BHJ。对于right outer join，我们只能广播左侧。
    *   对于left outer、left semi、left anti和inner连接类型ExistenceJoin，我们只能广播右侧。
    *   对于inner like join，我们可以广播两端。
    *   通常，当广播端较小时，BHJ能比其他连接算法执行得更快。但是，广播表是一项网络密集型操作。
    *   它可能导致OOM或比其他连接算法性能更差，尤其是当构建/广播端很大时。
   *
   *     For the supported cases, users can specify the broadcast hint (e.g. the user applied the
   *     [[org.apache.spark.sql.functions.broadcast()]] function to a DataFrame) and session-based
   *     [[SQLConf.AUTO_BROADCASTJOIN_THRESHOLD]] threshold to adjust whether BHJ is used and
   *     which join side is broadcast.
    *
    *    对于支持BHJ的情况，用户可以指定广播提示（例如，可以在DataFrame上使用[[org.apache.spark.sql.functions.broadcast()]] 函数。
    *    和调整基于会话的[[SQLConf.AUTO_BROADCASTJOIN_THRESHOLD]]阈值，用于调整是否使用BHJ以及广播join的哪个端。
   *
   *     1) Broadcast the join side with the broadcast hint, even if the size is larger than
   *     [[SQLConf.AUTO_BROADCASTJOIN_THRESHOLD]]. If both sides have the hint (only when the type
   *     is inner like join), the side with a smaller estimated physical size will be broadcast.
   *     2) Respect the [[SQLConf.AUTO_BROADCASTJOIN_THRESHOLD]] threshold and broadcast the side
   *     whose estimated physical size is smaller than the threshold. If both sides are below the
   *     threshold, broadcast the smaller side. If neither is smaller, BHJ is not used.
    *
    *    1)根据用广播提示来广播join的某端，即使大小大于[[SQLConf.AUTO_BROADCASTJOIN_THRESHOLD]]。
    *    如果双方都有提示（仅当type为inner like join时），则广播估计物理尺寸较小的一侧。
    *
    *    2）尊重[[SQLConf.AUTO_BROADCASTJOIN_THRESHOLD]]阈值，广播评估后物理大小小于阈值的一侧。
    *    如果双方都低于阈值，则广播较小的一侧。如果两者都不小，则不使用BHJ。
   *
   * - Shuffle hash join:
    *   if the average size of a single partition is small enough to build a hash table.
    *   如果单个分区的平均大小小到足以构建哈希表。
   *
   * - Sort merge: if the matching join keys are sortable.
    *  如果匹配的连接键是可排序的。
   *
   * If there is no joining keys, Join implementations are chosen with the following precedence:
    *  如果没有指定join的key，则Join的实现按以下优先级进行选择：
    *
   * - BroadcastNestedLoopJoin (BNLJ): 广播嵌套循环加入
   *     BNLJ supports all the join types but the impl is OPTIMIZED for the following scenarios:
   *     For right outer join, the left side is broadcast. For left outer, left semi, left anti
   *     and the internal join type ExistenceJoin, the right side is broadcast. For inner like
   *     joins, either side is broadcast.
    *
    *    BNLJ 支持所有连接类型，但针对以下场景优化了实现：
    *    1)对于right outer join，左侧被广播。
    *    2)对于left outer、left semi、left anti和inner连接类型的ExistenceJoin，右侧被广播。
    *    3)对于inner like join，任何一方都被广播。
   *
   *     Like BHJ, users still can specify the broadcast hint and session-based
   *     [[SQLConf.AUTO_BROADCASTJOIN_THRESHOLD]] threshold to impact which side is broadcast.
    *    和BHJ一样，用户仍然可以指定广播提示和基于会话的[[SQLConf.AUTO_BROADCASTJOIN_THRESHOLD]]阈值来影响广播哪一端。
   *
   *     1) Broadcast the join side with the broadcast hint, even if the size is larger than
   *     [[SQLConf.AUTO_BROADCASTJOIN_THRESHOLD]]. If both sides have the hint (i.e., just for
   *     inner-like join), the side with a smaller estimated physical size will be broadcast.
   *     2) Respect the [[SQLConf.AUTO_BROADCASTJOIN_THRESHOLD]] threshold and broadcast the side
   *     whose estimated physical size is smaller than the threshold. If both sides are below the
   *     threshold, broadcast the smaller side. If neither is smaller, BNLJ is not used.
    *    1) 根据广播提示来广播连接端，即使大小大于[[SQLConf.AUTO_BROADCASTJOIN_THRESHOLD]]。
    *    如果双方都有提示（即只针对inner-like join），则广播评估物理尺寸较小的一方。
    *
    *    2）遵循[[SQLConf.AUTO_BROADCASTJOIN_THRESHOLD]]阈值，广播评估物理大小小于阈值的一侧。
    *    如果双方都低于阈值，则广播较小的一侧。 如果两者都不小，则不使用 BNLJ。
   *
   * - CartesianProduct: for inner like join, CartesianProduct is the fallback option.
    *   对于像inner like这样的join类型，CartesianProduct是备选项。
   *
   * - BroadcastNestedLoopJoin (BNLJ):
   *     For the other join types, BNLJ is the fallback option. Here, we just pick the broadcast
   *     side with the broadcast hint. If neither side has a hint, we broadcast the side with
   *     the smaller estimated physical size.
    *  对于其他连接类型，BNLJ是备选项。在这里，我们只选择带有广播提示的广播端。
    *  如果双方都没有提示，我们将广播评估的物理大小较小的一侧。
    *
    * JoinSelection根据join的key和逻辑计划的大小选择合适的join物理计划。
    * 首先，使用 [[ExtractEquiJoinKeys]] 模式查找至少可以通过匹配连接键评估某些谓词的连接。
    * 如果找到，则按以下优先级选择连接实现：
    *
   */
  object JoinSelection extends Strategy with PredicateHelper {

    /**
     * Matches a plan whose output should be small enough to be used in broadcast join.
      * 匹配其输出应该足够小以，用于广播连接的计划。
     */
    private def canBroadcast(plan: LogicalPlan): Boolean = {
      plan.stats.sizeInBytes >= 0 && plan.stats.sizeInBytes <= conf.autoBroadcastJoinThreshold
    }

    /**
     * Matches a plan whose single partition should be small enough to build a hash table.
      * 匹配其单个分区应该足够小，以构建哈希表的计划。
     *
     * Note: this assume that the number of partition is fixed, requires additional work if it's
     * dynamic.
     */
    private def canBuildLocalHashMap(plan: LogicalPlan): Boolean = {
      plan.stats.sizeInBytes < conf.autoBroadcastJoinThreshold * conf.numShufflePartitions
    }

    /**
     * Returns whether plan a is much smaller (3X) than plan b.
      * 返回是否plan a比plan b小3倍。
     *
     * The cost to build hash map is higher than sorting, we should only build hash map on a table
     * that is much smaller than other one. Since we does not have the statistic for number of rows,
     * use the size of bytes here as estimation.
      * 由于构建哈希映射的成本高于排序，我们应该只在比其他表小得多的表上构建哈希映射。
      * 由于我们没有行数的统计数据，这里使用字节大小作为估计。
     */
    private def muchSmaller(a: LogicalPlan, b: LogicalPlan): Boolean = {
      a.stats.sizeInBytes * 3 <= b.stats.sizeInBytes
    }

    private def canBuildRight(joinType: JoinType): Boolean = joinType match {
      case _: InnerLike | LeftOuter | LeftSemi | LeftAnti | _: ExistenceJoin => true
      case _ => false
    }

    private def canBuildLeft(joinType: JoinType): Boolean = joinType match {
      case _: InnerLike | RightOuter => true
      case _ => false
    }

    private def broadcastSide(
        canBuildLeft: Boolean,
        canBuildRight: Boolean,
        left: LogicalPlan,
        right: LogicalPlan): BuildSide = {

      def smallerSide =
        if (right.stats.sizeInBytes <= left.stats.sizeInBytes) BuildRight else BuildLeft

      if (canBuildRight && canBuildLeft) {
        // Broadcast smaller side base on its estimated physical size
        // if both sides have broadcast hint
        smallerSide
      } else if (canBuildRight) {
        BuildRight
      } else if (canBuildLeft) {
        BuildLeft
      } else {
        // for the last default broadcast nested loop join
        smallerSide
      }
    }

    private def canBroadcastByHints(joinType: JoinType, left: LogicalPlan, right: LogicalPlan)
      : Boolean = {
      val buildLeft = canBuildLeft(joinType) && left.stats.hints.broadcast
      val buildRight = canBuildRight(joinType) && right.stats.hints.broadcast
      buildLeft || buildRight
    }

    private def broadcastSideByHints(joinType: JoinType, left: LogicalPlan, right: LogicalPlan)
      : BuildSide = {
      val buildLeft = canBuildLeft(joinType) && left.stats.hints.broadcast
      val buildRight = canBuildRight(joinType) && right.stats.hints.broadcast
      broadcastSide(buildLeft, buildRight, left, right)
    }

    private def canBroadcastBySizes(joinType: JoinType, left: LogicalPlan, right: LogicalPlan)
      : Boolean = {
      val buildLeft = canBuildLeft(joinType) && canBroadcast(left)
      val buildRight = canBuildRight(joinType) && canBroadcast(right)
      buildLeft || buildRight
    }

    private def broadcastSideBySizes(joinType: JoinType, left: LogicalPlan, right: LogicalPlan)
      : BuildSide = {
      val buildLeft = canBuildLeft(joinType) && canBroadcast(left)
      val buildRight = canBuildRight(joinType) && canBroadcast(right)
      broadcastSide(buildLeft, buildRight, left, right)
    }

    def apply(plan: LogicalPlan): Seq[SparkPlan] = plan match {

      // --- BroadcastHashJoin --------------------------------------------------------------------

      // broadcast hints were specified
        // 明确提示使用broadcast，比如：使用broadcast()函数
      case ExtractEquiJoinKeys(joinType, leftKeys, rightKeys, condition, left, right)
        // 根据提示的线索来判断是否使用广播
        if canBroadcastByHints(joinType, left, right) =>
        // 判断广播哪一端
        val buildSide = broadcastSideByHints(joinType, left, right)
        Seq(joins.BroadcastHashJoinExec(
          leftKeys, rightKeys, joinType, buildSide, condition, planLater(left), planLater(right)))

      // broadcast hints were not specified, so need to infer it from size and configuration.
        // 没有明确提示使用broadcast，需要根据数据集的大小和配置来进行推断
      case ExtractEquiJoinKeys(joinType, leftKeys, rightKeys, condition, left, right)
        // 通过大小和配置来推断是否使用广播
        if canBroadcastBySizes(joinType, left, right) =>
        // 判断广播哪一端
        val buildSide = broadcastSideBySizes(joinType, left, right)
        Seq(joins.BroadcastHashJoinExec(
          leftKeys, rightKeys, joinType, buildSide, condition, planLater(left), planLater(right)))

      // --- ShuffledHashJoin ---------------------------------------------------------------------

      case ExtractEquiJoinKeys(joinType, leftKeys, rightKeys, condition, left, right)
         // 判断是否使用shufflehashJoin: 广播右侧
         if !conf.preferSortMergeJoin && canBuildRight(joinType) && canBuildLocalHashMap(right)
           && muchSmaller(right, left) ||
           !RowOrdering.isOrderable(leftKeys) =>
        Seq(joins.ShuffledHashJoinExec(
          leftKeys, rightKeys, joinType, BuildRight, condition, planLater(left), planLater(right)))

      case ExtractEquiJoinKeys(joinType, leftKeys, rightKeys, condition, left, right)
         // 广播左侧
         if !conf.preferSortMergeJoin && canBuildLeft(joinType) && canBuildLocalHashMap(left)
           && muchSmaller(left, right) ||
           !RowOrdering.isOrderable(leftKeys) =>
        Seq(joins.ShuffledHashJoinExec(
          leftKeys, rightKeys, joinType, BuildLeft, condition, planLater(left), planLater(right)))

        // --- SortMergeJoin ------------------------------------------------------------

      case ExtractEquiJoinKeys(joinType, leftKeys, rightKeys, condition, left, right)
        if RowOrdering.isOrderable(leftKeys) =>
        joins.SortMergeJoinExec(
          leftKeys, rightKeys, joinType, condition, planLater(left), planLater(right)) :: Nil

      // --- Without joining keys ------------------------------------------------------------

      // Pick BroadcastNestedLoopJoin if one side could be broadcast
      case j @ logical.Join(left, right, joinType, condition)
        // 有明确的广播提示
          if canBroadcastByHints(joinType, left, right) =>
        val buildSide = broadcastSideByHints(joinType, left, right)
        joins.BroadcastNestedLoopJoinExec(
          planLater(left), planLater(right), buildSide, joinType, condition) :: Nil

      case j @ logical.Join(left, right, joinType, condition)
          // 通过size来判断是否可以广播
          if canBroadcastBySizes(joinType, left, right) =>
        val buildSide = broadcastSideBySizes(joinType, left, right)
        joins.BroadcastNestedLoopJoinExec(
          planLater(left), planLater(right), buildSide, joinType, condition) :: Nil

      // Pick CartesianProduct for InnerJoin
      case logical.Join(left, right, _: InnerLike, condition) =>
        // inner join是笛卡尔积
        joins.CartesianProductExec(planLater(left), planLater(right), condition) :: Nil

      case logical.Join(left, right, joinType, condition) =>
        val buildSide = broadcastSide(
          left.stats.hints.broadcast, right.stats.hints.broadcast, left, right)
        // This join could be very slow or OOM
        // 该join可能会很慢或出现OOM
        joins.BroadcastNestedLoopJoinExec(
          planLater(left), planLater(right), buildSide, joinType, condition) :: Nil

      // --- Cases where this strategy does not apply ---------------------------------------------

      case _ => Nil
    }
  }

  /**
   * Used to plan streaming aggregation queries that are computed incrementally as part of a
   * [[StreamingQuery]]. Currently this rule is injected into the planner
   * on-demand, only when planning in a [[org.apache.spark.sql.execution.streaming.StreamExecution]]
   */
  object StatefulAggregationStrategy extends Strategy {
    override def apply(plan: LogicalPlan): Seq[SparkPlan] = plan match {
      case _ if !plan.isStreaming => Nil

      case EventTimeWatermark(columnName, delay, child) =>
        EventTimeWatermarkExec(columnName, delay, planLater(child)) :: Nil

      case PhysicalAggregation(
        namedGroupingExpressions, aggregateExpressions, rewrittenResultExpressions, child) =>

        if (aggregateExpressions.exists(PythonUDF.isGroupedAggPandasUDF)) {
          throw new AnalysisException(
            "Streaming aggregation doesn't support group aggregate pandas UDF")
        }

        val stateVersion = conf.getConf(SQLConf.STREAMING_AGGREGATION_STATE_FORMAT_VERSION)

        aggregate.AggUtils.planStreamingAggregation(
          namedGroupingExpressions,
          aggregateExpressions.map(expr => expr.asInstanceOf[AggregateExpression]),
          rewrittenResultExpressions,
          stateVersion,
          planLater(child))

      case _ => Nil
    }
  }

  /**
   * Used to plan the streaming deduplicate operator.
   */
  object StreamingDeduplicationStrategy extends Strategy {
    override def apply(plan: LogicalPlan): Seq[SparkPlan] = plan match {
      case Deduplicate(keys, child) if child.isStreaming =>
        StreamingDeduplicateExec(keys, planLater(child)) :: Nil

      case _ => Nil
    }
  }

  /**
   * Used to plan the streaming global limit operator for streams in append mode.
   * We need to check for either a direct Limit or a Limit wrapped in a ReturnAnswer operator,
   * following the example of the SpecialLimits Strategy above.
   * Streams with limit in Append mode use the stateful StreamingGlobalLimitExec.
   * Streams with limit in Complete mode use the stateless CollectLimitExec operator.
   * Limit is unsupported for streams in Update mode.
   */
  case class StreamingGlobalLimitStrategy(outputMode: OutputMode) extends Strategy {
    override def apply(plan: LogicalPlan): Seq[SparkPlan] = plan match {
      case ReturnAnswer(rootPlan) => rootPlan match {
        case Limit(IntegerLiteral(limit), child)
            if plan.isStreaming && outputMode == InternalOutputModes.Append =>
          StreamingGlobalLimitExec(limit, LocalLimitExec(limit, planLater(child))) :: Nil
        case _ => Nil
      }
      case Limit(IntegerLiteral(limit), child)
          if plan.isStreaming && outputMode == InternalOutputModes.Append =>
        StreamingGlobalLimitExec(limit, LocalLimitExec(limit, planLater(child))) :: Nil
      case _ => Nil
    }
  }

  object StreamingJoinStrategy extends Strategy {
    override def apply(plan: LogicalPlan): Seq[SparkPlan] = {
      plan match {
        case ExtractEquiJoinKeys(joinType, leftKeys, rightKeys, condition, left, right)
          if left.isStreaming && right.isStreaming =>

          new StreamingSymmetricHashJoinExec(
            leftKeys, rightKeys, joinType, condition, planLater(left), planLater(right)) :: Nil

        case Join(left, right, _, _) if left.isStreaming && right.isStreaming =>
          throw new AnalysisException(
            "Stream-stream join without equality predicate is not supported", plan = Some(plan))

        case _ => Nil
      }
    }
  }

  /**
   * Used to plan the aggregate operator for expressions based on the AggregateFunction2 interface.
   */
  // 针对表达式来规划聚合操作，基于聚合函数接口
  object Aggregation extends Strategy {
    def apply(plan: LogicalPlan): Seq[SparkPlan] = plan match {
      case PhysicalAggregation(groupingExpressions, aggExpressions, resultExpressions, child)
        if aggExpressions.forall(expr => expr.isInstanceOf[AggregateExpression]) =>
        val aggregateExpressions = aggExpressions.map(expr =>
          expr.asInstanceOf[AggregateExpression])

        val (functionsWithDistinct, functionsWithoutDistinct) =
          aggregateExpressions.partition(_.isDistinct)
        if (functionsWithDistinct.map(_.aggregateFunction.children.toSet).distinct.length > 1) {
          // This is a sanity check. We should not reach here when we have multiple distinct
          // column sets. Our `RewriteDistinctAggregates` should take care this case.
          sys.error("You hit a query analyzer bug. Please report your query to " +
              "Spark user mailing list.")
        }

        val aggregateOperator =
          if (functionsWithDistinct.isEmpty) {
            aggregate.AggUtils.planAggregateWithoutDistinct(
              groupingExpressions,
              aggregateExpressions,
              resultExpressions,
              planLater(child))
          } else {
            aggregate.AggUtils.planAggregateWithOneDistinct(
              groupingExpressions,
              functionsWithDistinct,
              functionsWithoutDistinct,
              resultExpressions,
              planLater(child))
          }

        aggregateOperator

      case PhysicalAggregation(groupingExpressions, aggExpressions, resultExpressions, child)
        if aggExpressions.forall(expr => expr.isInstanceOf[PythonUDF]) =>
        val udfExpressions = aggExpressions.map(expr => expr.asInstanceOf[PythonUDF])

        Seq(execution.python.AggregateInPandasExec(
          groupingExpressions,
          udfExpressions,
          resultExpressions,
          planLater(child)))

      case PhysicalAggregation(_, _, _, _) =>
        // If cannot match the two cases above, then it's an error
        throw new AnalysisException(
          "Cannot use a mixture of aggregate function and group aggregate pandas UDF")

      case _ => Nil
    }
  }

  object Window extends Strategy {
    def apply(plan: LogicalPlan): Seq[SparkPlan] = plan match {
      case PhysicalWindow(
        WindowFunctionType.SQL, windowExprs, partitionSpec, orderSpec, child) =>
        execution.window.WindowExec(
          windowExprs, partitionSpec, orderSpec, planLater(child)) :: Nil

      case PhysicalWindow(
        WindowFunctionType.Python, windowExprs, partitionSpec, orderSpec, child) =>
        execution.python.WindowInPandasExec(
          windowExprs, partitionSpec, orderSpec, planLater(child)) :: Nil

      case _ => Nil
    }
  }

  protected lazy val singleRowRdd = sparkContext.parallelize(Seq(InternalRow()), 1)

  object InMemoryScans extends Strategy {
    def apply(plan: LogicalPlan): Seq[SparkPlan] = plan match {
      case PhysicalOperation(projectList, filters, mem: InMemoryRelation) =>
        pruneFilterProject(
          projectList,
          filters,
          identity[Seq[Expression]], // All filters still need to be evaluated.
          InMemoryTableScanExec(_, filters, mem)) :: Nil
      case _ => Nil
    }
  }

  /**
   * This strategy is just for explaining `Dataset/DataFrame` created by `spark.readStream`.
   * It won't affect the execution, because `StreamingRelation` will be replaced with
   * `StreamingExecutionRelation` in `StreamingQueryManager` and `StreamingExecutionRelation` will
   * be replaced with the real relation using the `Source` in `StreamExecution`.
   */
  object StreamingRelationStrategy extends Strategy {
    def apply(plan: LogicalPlan): Seq[SparkPlan] = plan match {
      case s: StreamingRelation =>
        StreamingRelationExec(s.sourceName, s.output) :: Nil
      case s: StreamingExecutionRelation =>
        StreamingRelationExec(s.toString, s.output) :: Nil
      case s: StreamingRelationV2 =>
        StreamingRelationExec(s.sourceName, s.output) :: Nil
      case _ => Nil
    }
  }

  /**
   * Strategy to convert [[FlatMapGroupsWithState]] logical operator to physical operator
   * in streaming plans. Conversion for batch plans is handled by [[BasicOperators]].
   */
  object FlatMapGroupsWithStateStrategy extends Strategy {
    override def apply(plan: LogicalPlan): Seq[SparkPlan] = plan match {
      case FlatMapGroupsWithState(
        func, keyDeser, valueDeser, groupAttr, dataAttr, outputAttr, stateEnc, outputMode, _,
        timeout, child) =>
        val stateVersion = conf.getConf(SQLConf.FLATMAPGROUPSWITHSTATE_STATE_FORMAT_VERSION)
        val execPlan = FlatMapGroupsWithStateExec(
          func, keyDeser, valueDeser, groupAttr, dataAttr, outputAttr, None, stateEnc, stateVersion,
          outputMode, timeout, batchTimestampMs = None, eventTimeWatermark = None, planLater(child))
        execPlan :: Nil
      case _ =>
        Nil
    }
  }

  /**
   * Strategy to convert EvalPython logical operator to physical operator.
   */
  object PythonEvals extends Strategy {
    override def apply(plan: LogicalPlan): Seq[SparkPlan] = plan match {
      case ArrowEvalPython(udfs, output, child) =>
        ArrowEvalPythonExec(udfs, output, planLater(child)) :: Nil
      case BatchEvalPython(udfs, output, child) =>
        BatchEvalPythonExec(udfs, output, planLater(child)) :: Nil
      case _ =>
        Nil
    }
  }

  // 基础操作
  object BasicOperators extends Strategy {
    def apply(plan: LogicalPlan): Seq[SparkPlan] = plan match {
      case d: DataWritingCommand => DataWritingCommandExec(d, planLater(d.query)) :: Nil
      case r: RunnableCommand => ExecutedCommandExec(r) :: Nil

      case MemoryPlan(sink, output) =>
        val encoder = RowEncoder(sink.schema)
        LocalTableScanExec(output, sink.allData.map(r => encoder.toRow(r).copy())) :: Nil
      case MemoryPlanV2(sink, output) =>
        val encoder = RowEncoder(StructType.fromAttributes(output))
        LocalTableScanExec(output, sink.allData.map(r => encoder.toRow(r).copy())) :: Nil

      case logical.Distinct(child) =>
        throw new IllegalStateException(
          "logical distinct operator should have been replaced by aggregate in the optimizer")
      case logical.Intersect(left, right, false) =>
        throw new IllegalStateException(
          "logical intersect  operator should have been replaced by semi-join in the optimizer")
      case logical.Intersect(left, right, true) =>
        throw new IllegalStateException(
          "logical intersect operator should have been replaced by union, aggregate" +
            " and generate operators in the optimizer")
      case logical.Except(left, right, false) =>
        throw new IllegalStateException(
          "logical except operator should have been replaced by anti-join in the optimizer")
      case logical.Except(left, right, true) =>
        throw new IllegalStateException(
          "logical except (all) operator should have been replaced by union, aggregate" +
            " and generate operators in the optimizer")

      case logical.DeserializeToObject(deserializer, objAttr, child) =>
        execution.DeserializeToObjectExec(deserializer, objAttr, planLater(child)) :: Nil
      case logical.SerializeFromObject(serializer, child) =>
        execution.SerializeFromObjectExec(serializer, planLater(child)) :: Nil
      case logical.MapPartitions(f, objAttr, child) =>
        execution.MapPartitionsExec(f, objAttr, planLater(child)) :: Nil
      case logical.MapPartitionsInR(f, p, b, is, os, objAttr, child) =>
        execution.MapPartitionsExec(
          execution.r.MapPartitionsRWrapper(f, p, b, is, os), objAttr, planLater(child)) :: Nil
      case logical.FlatMapGroupsInR(f, p, b, is, os, key, value, grouping, data, objAttr, child) =>
        execution.FlatMapGroupsInRExec(f, p, b, is, os, key, value, grouping,
          data, objAttr, planLater(child)) :: Nil
      case logical.FlatMapGroupsInPandas(grouping, func, output, child) =>
        execution.python.FlatMapGroupsInPandasExec(grouping, func, output, planLater(child)) :: Nil
      case logical.MapElements(f, _, _, objAttr, child) =>
        execution.MapElementsExec(f, objAttr, planLater(child)) :: Nil
      case logical.AppendColumns(f, _, _, in, out, child) =>
        execution.AppendColumnsExec(f, in, out, planLater(child)) :: Nil
      case logical.AppendColumnsWithObject(f, childSer, newSer, child) =>
        execution.AppendColumnsWithObjectExec(f, childSer, newSer, planLater(child)) :: Nil
      case logical.MapGroups(f, key, value, grouping, data, objAttr, child) =>
        execution.MapGroupsExec(f, key, value, grouping, data, objAttr, planLater(child)) :: Nil
      case logical.FlatMapGroupsWithState(
          f, key, value, grouping, data, output, _, _, _, timeout, child) =>
        execution.MapGroupsExec(
          f, key, value, grouping, data, output, timeout, planLater(child)) :: Nil
      case logical.CoGroup(f, key, lObj, rObj, lGroup, rGroup, lAttr, rAttr, oAttr, left, right) =>
        execution.CoGroupExec(
          f, key, lObj, rObj, lGroup, rGroup, lAttr, rAttr, oAttr,
          planLater(left), planLater(right)) :: Nil

      case logical.Repartition(numPartitions, shuffle, child) =>
        if (shuffle) {
          ShuffleExchangeExec(RoundRobinPartitioning(numPartitions), planLater(child)) :: Nil
        } else {
          execution.CoalesceExec(numPartitions, planLater(child)) :: Nil
        }
      case logical.Sort(sortExprs, global, child) =>
        execution.SortExec(sortExprs, global, planLater(child)) :: Nil
      case logical.Project(projectList, child) =>
        execution.ProjectExec(projectList, planLater(child)) :: Nil
      case logical.Filter(condition, child) =>
        execution.FilterExec(condition, planLater(child)) :: Nil
      case f: logical.TypedFilter =>
        execution.FilterExec(f.typedCondition(f.deserializer), planLater(f.child)) :: Nil
      case e @ logical.Expand(_, _, child) =>
        execution.ExpandExec(e.projections, e.output, planLater(child)) :: Nil
      case logical.Sample(lb, ub, withReplacement, seed, child) =>
        execution.SampleExec(lb, ub, withReplacement, seed, planLater(child)) :: Nil
      case logical.LocalRelation(output, data, _) =>
        LocalTableScanExec(output, data) :: Nil
      case logical.LocalLimit(IntegerLiteral(limit), child) =>
        execution.LocalLimitExec(limit, planLater(child)) :: Nil
      case logical.GlobalLimit(IntegerLiteral(limit), child) =>
        execution.GlobalLimitExec(limit, planLater(child)) :: Nil
      case logical.Union(unionChildren) =>
        execution.UnionExec(unionChildren.map(planLater)) :: Nil
      case g @ logical.Generate(generator, _, outer, _, _, child) =>
        execution.GenerateExec(
          generator, g.requiredChildOutput, outer,
          g.qualifiedGeneratorOutput, planLater(child)) :: Nil
      case _: logical.OneRowRelation =>
        execution.RDDScanExec(Nil, singleRowRdd, "OneRowRelation") :: Nil
      case r: logical.Range =>
        execution.RangeExec(r) :: Nil
      case r: logical.RepartitionByExpression =>
        exchange.ShuffleExchangeExec(r.partitioning, planLater(r.child)) :: Nil
      case ExternalRDD(outputObjAttr, rdd) => ExternalRDDScanExec(outputObjAttr, rdd) :: Nil
      case r: LogicalRDD =>
        RDDScanExec(r.output, r.rdd, "ExistingRDD", r.outputPartitioning, r.outputOrdering) :: Nil
      case h: ResolvedHint => planLater(h.child) :: Nil
      case _ => Nil
    }
  }
}
