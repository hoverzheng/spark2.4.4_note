package org.apache.spark.sql.delta.commands;
/**
 * A command to change the column for a Delta table, support changing the comment of a column and
 * reordering columns.
 * <p>
 * The syntax of using this command in SQL is:
 * <pre><code>
 *   ALTER TABLE table_identifier
 *   CHANGE [COLUMN] column_old_name column_new_name column_dataType [COMMENT column_comment]
 *   [FIRST | AFTER column_name];
 * </code></pre>
 */
public  class AlterTableChangeColumnDeltaCommand extends org.apache.spark.sql.catalyst.plans.logical.LogicalPlan implements org.apache.spark.sql.execution.command.RunnableCommand, org.apache.spark.sql.delta.commands.AlterDeltaTableCommand, org.apache.spark.sql.catalyst.plans.logical.IgnoreCachedData, scala.Product, scala.Serializable {
  static public abstract  R apply (T1 v1, T2 v2, T3 v3, T4 v4, T5 v5)  ;
  static public  java.lang.String toString ()  { throw new RuntimeException(); }
  public  scala.collection.immutable.Map<java.lang.String, org.apache.spark.sql.execution.metric.SQLMetric> metrics ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.delta.catalog.DeltaTableV2 table ()  { throw new RuntimeException(); }
  public  scala.collection.Seq<java.lang.String> columnPath ()  { throw new RuntimeException(); }
  public  java.lang.String columnName ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.types.StructField newColumn ()  { throw new RuntimeException(); }
  public  scala.Option<org.apache.spark.sql.connector.catalog.TableChange.ColumnPosition> colPosition ()  { throw new RuntimeException(); }
  // not preceding
  public   AlterTableChangeColumnDeltaCommand (org.apache.spark.sql.delta.catalog.DeltaTableV2 table, scala.collection.Seq<java.lang.String> columnPath, java.lang.String columnName, org.apache.spark.sql.types.StructField newColumn, scala.Option<org.apache.spark.sql.connector.catalog.TableChange.ColumnPosition> colPosition)  { throw new RuntimeException(); }
  public  scala.collection.Seq<org.apache.spark.sql.Row> run (org.apache.spark.sql.SparkSession sparkSession)  { throw new RuntimeException(); }
}
