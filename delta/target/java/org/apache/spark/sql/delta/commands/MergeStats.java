package org.apache.spark.sql.delta.commands;
/** State for a merge operation */
public  class MergeStats implements scala.Product, scala.Serializable {
  static public  org.apache.spark.sql.delta.commands.MergeStats fromMergeSQLMetrics (scala.collection.immutable.Map<java.lang.String, org.apache.spark.sql.execution.metric.SQLMetric> metrics, org.apache.spark.sql.catalyst.expressions.Expression condition, scala.collection.Seq<org.apache.spark.sql.catalyst.plans.logical.DeltaMergeIntoMatchedClause> matchedClauses, scala.collection.Seq<org.apache.spark.sql.catalyst.plans.logical.DeltaMergeIntoInsertClause> notMatchedClauses, boolean isPartitioned)  { throw new RuntimeException(); }
  public  java.lang.String conditionExpr ()  { throw new RuntimeException(); }
  public  java.lang.String updateConditionExpr ()  { throw new RuntimeException(); }
  public  scala.collection.Seq<java.lang.String> updateExprs ()  { throw new RuntimeException(); }
  public  java.lang.String insertConditionExpr ()  { throw new RuntimeException(); }
  public  scala.collection.Seq<java.lang.String> insertExprs ()  { throw new RuntimeException(); }
  public  java.lang.String deleteConditionExpr ()  { throw new RuntimeException(); }
  public  scala.collection.Seq<org.apache.spark.sql.delta.commands.MergeClauseStats> matchedStats ()  { throw new RuntimeException(); }
  public  scala.collection.Seq<org.apache.spark.sql.delta.commands.MergeClauseStats> notMatchedStats ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.delta.commands.MergeDataSizes source ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.delta.commands.MergeDataSizes targetBeforeSkipping ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.delta.commands.MergeDataSizes targetAfterSkipping ()  { throw new RuntimeException(); }
  public  long targetFilesRemoved ()  { throw new RuntimeException(); }
  public  long targetFilesAdded ()  { throw new RuntimeException(); }
  public  scala.Option<java.lang.Object> targetBytesRemoved ()  { throw new RuntimeException(); }
  public  scala.Option<java.lang.Object> targetBytesAdded ()  { throw new RuntimeException(); }
  public  scala.Option<java.lang.Object> targetPartitionsRemovedFrom ()  { throw new RuntimeException(); }
  public  scala.Option<java.lang.Object> targetPartitionsAddedTo ()  { throw new RuntimeException(); }
  public  long targetRowsCopied ()  { throw new RuntimeException(); }
  public  long targetRowsUpdated ()  { throw new RuntimeException(); }
  public  long targetRowsInserted ()  { throw new RuntimeException(); }
  public  long targetRowsDeleted ()  { throw new RuntimeException(); }
  // not preceding
  public   MergeStats (java.lang.String conditionExpr, java.lang.String updateConditionExpr, scala.collection.Seq<java.lang.String> updateExprs, java.lang.String insertConditionExpr, scala.collection.Seq<java.lang.String> insertExprs, java.lang.String deleteConditionExpr, scala.collection.Seq<org.apache.spark.sql.delta.commands.MergeClauseStats> matchedStats, scala.collection.Seq<org.apache.spark.sql.delta.commands.MergeClauseStats> notMatchedStats, org.apache.spark.sql.delta.commands.MergeDataSizes source, org.apache.spark.sql.delta.commands.MergeDataSizes targetBeforeSkipping, org.apache.spark.sql.delta.commands.MergeDataSizes targetAfterSkipping, long targetFilesRemoved, long targetFilesAdded, scala.Option<java.lang.Object> targetBytesRemoved, scala.Option<java.lang.Object> targetBytesAdded, scala.Option<java.lang.Object> targetPartitionsRemovedFrom, scala.Option<java.lang.Object> targetPartitionsAddedTo, long targetRowsCopied, long targetRowsUpdated, long targetRowsInserted, long targetRowsDeleted)  { throw new RuntimeException(); }
}
