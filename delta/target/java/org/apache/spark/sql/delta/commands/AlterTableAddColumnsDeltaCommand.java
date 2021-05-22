package org.apache.spark.sql.delta.commands;
/**
 * A command that add columns to a Delta table.
 * The syntax of using this command in SQL is:
 * <pre><code>
 *   ALTER TABLE table_identifier
 *   ADD COLUMNS (col_name data_type [COMMENT col_comment], ...);
 * </code></pre>
 */
public  class AlterTableAddColumnsDeltaCommand extends org.apache.spark.sql.catalyst.plans.logical.LogicalPlan implements org.apache.spark.sql.execution.command.RunnableCommand, org.apache.spark.sql.delta.commands.AlterDeltaTableCommand, org.apache.spark.sql.catalyst.plans.logical.IgnoreCachedData, scala.Product, scala.Serializable {
  public  class QualifiedColTypeWithPosition$ {
    public   QualifiedColTypeWithPosition$ ()  { throw new RuntimeException(); }
    public  scala.Option<scala.Tuple3<scala.collection.Seq<java.lang.String>, org.apache.spark.sql.types.StructField, scala.Option<org.apache.spark.sql.connector.catalog.TableChange.ColumnPosition>>> unapply (org.apache.spark.sql.catalyst.plans.logical.QualifiedColType col)  { throw new RuntimeException(); }
  }
  static public abstract  R apply (T1 v1, T2 v2)  ;
  static public  java.lang.String toString ()  { throw new RuntimeException(); }
  /**
   * Accessor for nested Scala object
   * @return (undocumented)
   */
  public  org.apache.spark.sql.delta.commands.AlterTableAddColumnsDeltaCommand.QualifiedColTypeWithPosition$ QualifiedColTypeWithPosition ()  { throw new RuntimeException(); }
  public  scala.collection.immutable.Map<java.lang.String, org.apache.spark.sql.execution.metric.SQLMetric> metrics ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.delta.catalog.DeltaTableV2 table ()  { throw new RuntimeException(); }
  public  scala.collection.Seq<org.apache.spark.sql.catalyst.plans.logical.QualifiedColType> colsToAddWithPosition ()  { throw new RuntimeException(); }
  // not preceding
  public   AlterTableAddColumnsDeltaCommand (org.apache.spark.sql.delta.catalog.DeltaTableV2 table, scala.collection.Seq<org.apache.spark.sql.catalyst.plans.logical.QualifiedColType> colsToAddWithPosition)  { throw new RuntimeException(); }
  public  scala.collection.Seq<org.apache.spark.sql.Row> run (org.apache.spark.sql.SparkSession sparkSession)  { throw new RuntimeException(); }
}
