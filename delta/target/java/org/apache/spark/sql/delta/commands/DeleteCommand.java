package org.apache.spark.sql.delta.commands;
/**
 * Performs a Delete based on the search condition
 * <p>
 * Algorithm:
 *   1) Scan all the files and determine which files have
 *      the rows that need to be deleted.
 *   2) Traverse the affected files and rebuild the touched files.
 *   3) Use the Delta protocol to atomically write the remaining rows to new files and remove
 *      the affected files that are identified in step 1.
 */
public  class DeleteCommand extends org.apache.spark.sql.catalyst.plans.logical.LogicalPlan implements org.apache.spark.sql.execution.command.RunnableCommand, org.apache.spark.sql.delta.commands.DeltaCommand, scala.Product, scala.Serializable {
  static public  org.apache.spark.sql.delta.commands.DeleteCommand apply (org.apache.spark.sql.catalyst.plans.logical.DeltaDelete delete)  { throw new RuntimeException(); }
  static public  java.lang.String FILE_NAME_COLUMN ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.delta.files.TahoeFileIndex tahoeFileIndex ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.catalyst.plans.logical.LogicalPlan target ()  { throw new RuntimeException(); }
  public  scala.Option<org.apache.spark.sql.catalyst.expressions.Expression> condition ()  { throw new RuntimeException(); }
  // not preceding
  public   DeleteCommand (org.apache.spark.sql.delta.files.TahoeFileIndex tahoeFileIndex, org.apache.spark.sql.catalyst.plans.logical.LogicalPlan target, scala.Option<org.apache.spark.sql.catalyst.expressions.Expression> condition)  { throw new RuntimeException(); }
  public  scala.collection.Seq<org.apache.spark.sql.catalyst.plans.QueryPlan<?>> innerChildren ()  { throw new RuntimeException(); }
  // not preceding
  public  scala.collection.immutable.Map<java.lang.String, org.apache.spark.sql.execution.metric.SQLMetric> metrics ()  { throw new RuntimeException(); }
  public final  scala.collection.Seq<org.apache.spark.sql.Row> run (org.apache.spark.sql.SparkSession sparkSession)  { throw new RuntimeException(); }
}
