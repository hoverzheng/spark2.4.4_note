package org.apache.spark.sql.delta.commands;
public  class MergeStats$ implements scala.Serializable {
  /**
   * Static reference to the singleton instance of this Scala object.
   */
  public static final MergeStats$ MODULE$ = null;
  public   MergeStats$ ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.delta.commands.MergeStats fromMergeSQLMetrics (scala.collection.immutable.Map<java.lang.String, org.apache.spark.sql.execution.metric.SQLMetric> metrics, org.apache.spark.sql.catalyst.expressions.Expression condition, scala.collection.Seq<org.apache.spark.sql.catalyst.plans.logical.DeltaMergeIntoMatchedClause> matchedClauses, scala.collection.Seq<org.apache.spark.sql.catalyst.plans.logical.DeltaMergeIntoInsertClause> notMatchedClauses, boolean isPartitioned)  { throw new RuntimeException(); }
}
