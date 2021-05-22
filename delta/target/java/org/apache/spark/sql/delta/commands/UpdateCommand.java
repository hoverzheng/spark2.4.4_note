package org.apache.spark.sql.delta.commands;
/**
 * Performs an Update using <code>updateExpression</code> on the rows that match <code>condition</code>
 * <p>
 * Algorithm:
 *   1) Identify the affected files, i.e., the files that may have the rows to be updated.
 *   2) Scan affected files, apply the updates, and generate a new DF with updated rows.
 *   3) Use the Delta protocol to atomically write the new DF as new files and remove
 *      the affected files that are identified in step 1.
 */
public  class UpdateCommand extends org.apache.spark.sql.catalyst.plans.logical.LogicalPlan implements org.apache.spark.sql.execution.command.RunnableCommand, org.apache.spark.sql.delta.commands.DeltaCommand, scala.Product, scala.Serializable {
  static public  java.lang.String FILE_NAME_COLUMN ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.delta.files.TahoeFileIndex tahoeFileIndex ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.catalyst.plans.logical.LogicalPlan target ()  { throw new RuntimeException(); }
  public  scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Expression> updateExpressions ()  { throw new RuntimeException(); }
  public  scala.Option<org.apache.spark.sql.catalyst.expressions.Expression> condition ()  { throw new RuntimeException(); }
  // not preceding
  public   UpdateCommand (org.apache.spark.sql.delta.files.TahoeFileIndex tahoeFileIndex, org.apache.spark.sql.catalyst.plans.logical.LogicalPlan target, scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Expression> updateExpressions, scala.Option<org.apache.spark.sql.catalyst.expressions.Expression> condition)  { throw new RuntimeException(); }
  public  scala.collection.Seq<org.apache.spark.sql.catalyst.plans.QueryPlan<?>> innerChildren ()  { throw new RuntimeException(); }
  // not preceding
  public  scala.collection.immutable.Map<java.lang.String, org.apache.spark.sql.execution.metric.SQLMetric> metrics ()  { throw new RuntimeException(); }
  public final  scala.collection.Seq<org.apache.spark.sql.Row> run (org.apache.spark.sql.SparkSession sparkSession)  { throw new RuntimeException(); }
}
