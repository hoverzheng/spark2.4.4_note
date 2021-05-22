package org.apache.spark.sql.delta.commands;
/**
 * Command to drop a constraint from a Delta table. No-op if a constraint with the given name
 * doesn't exist.
 * <p>
 * Currently only CHECK constraints are supported.
 * <p>
 * param:  table The table from which the constraint should be dropped
 * param:  name The name of the constraint to drop
 */
public  class AlterTableDropConstraintDeltaCommand extends org.apache.spark.sql.catalyst.plans.logical.LogicalPlan implements org.apache.spark.sql.execution.command.RunnableCommand, org.apache.spark.sql.delta.commands.AlterDeltaTableCommand, org.apache.spark.sql.catalyst.plans.logical.IgnoreCachedData, scala.Product, scala.Serializable {
  static public abstract  R apply (T1 v1, T2 v2)  ;
  static public  java.lang.String toString ()  { throw new RuntimeException(); }
  public  scala.collection.immutable.Map<java.lang.String, org.apache.spark.sql.execution.metric.SQLMetric> metrics ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.delta.catalog.DeltaTableV2 table ()  { throw new RuntimeException(); }
  public  java.lang.String name ()  { throw new RuntimeException(); }
  // not preceding
  public   AlterTableDropConstraintDeltaCommand (org.apache.spark.sql.delta.catalog.DeltaTableV2 table, java.lang.String name)  { throw new RuntimeException(); }
  public  scala.collection.Seq<org.apache.spark.sql.Row> run (org.apache.spark.sql.SparkSession sparkSession)  { throw new RuntimeException(); }
}
