package org.apache.spark.sql.delta.commands;
/**
 * A command to replace columns for a Delta table, support changing the comment of a column,
 * reordering columns, and loosening nullabilities.
 * <p>
 * The syntax of using this command in SQL is:
 * <pre><code>
 *   ALTER TABLE table_identifier REPLACE COLUMNS (col_spec[, col_spec ...]);
 * </code></pre>
 */
public  class AlterTableReplaceColumnsDeltaCommand extends org.apache.spark.sql.catalyst.plans.logical.LogicalPlan implements org.apache.spark.sql.execution.command.RunnableCommand, org.apache.spark.sql.delta.commands.AlterDeltaTableCommand, org.apache.spark.sql.catalyst.plans.logical.IgnoreCachedData, scala.Product, scala.Serializable {
  static public abstract  R apply (T1 v1, T2 v2)  ;
  static public  java.lang.String toString ()  { throw new RuntimeException(); }
  public  scala.collection.immutable.Map<java.lang.String, org.apache.spark.sql.execution.metric.SQLMetric> metrics ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.delta.catalog.DeltaTableV2 table ()  { throw new RuntimeException(); }
  public  scala.collection.Seq<org.apache.spark.sql.types.StructField> columns ()  { throw new RuntimeException(); }
  // not preceding
  public   AlterTableReplaceColumnsDeltaCommand (org.apache.spark.sql.delta.catalog.DeltaTableV2 table, scala.collection.Seq<org.apache.spark.sql.types.StructField> columns)  { throw new RuntimeException(); }
  public  scala.collection.Seq<org.apache.spark.sql.Row> run (org.apache.spark.sql.SparkSession sparkSession)  { throw new RuntimeException(); }
}
