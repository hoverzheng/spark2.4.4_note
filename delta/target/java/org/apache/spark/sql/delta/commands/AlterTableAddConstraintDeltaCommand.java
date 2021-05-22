package org.apache.spark.sql.delta.commands;
/**
 * Command to add a constraint to a Delta table. Currently only CHECK constraints are supported.
 * <p>
 * Adding a constraint will scan all data in the table to verify the constraint currently holds.
 * <p>
 * param:  table The table to which the constraint should be added.
 * param:  name The name of the new constraint.
 * param:  exprText The contents of the new CHECK constraint, to be parsed and evaluated.
 */
public  class AlterTableAddConstraintDeltaCommand extends org.apache.spark.sql.catalyst.plans.logical.LogicalPlan implements org.apache.spark.sql.execution.command.RunnableCommand, org.apache.spark.sql.delta.commands.AlterDeltaTableCommand, org.apache.spark.sql.catalyst.plans.logical.IgnoreCachedData, scala.Product, scala.Serializable {
  static public abstract  R apply (T1 v1, T2 v2, T3 v3)  ;
  static public  java.lang.String toString ()  { throw new RuntimeException(); }
  public  scala.collection.immutable.Map<java.lang.String, org.apache.spark.sql.execution.metric.SQLMetric> metrics ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.delta.catalog.DeltaTableV2 table ()  { throw new RuntimeException(); }
  public  java.lang.String name ()  { throw new RuntimeException(); }
  public  java.lang.String exprText ()  { throw new RuntimeException(); }
  // not preceding
  public   AlterTableAddConstraintDeltaCommand (org.apache.spark.sql.delta.catalog.DeltaTableV2 table, java.lang.String name, java.lang.String exprText)  { throw new RuntimeException(); }
  public  scala.collection.Seq<org.apache.spark.sql.Row> run (org.apache.spark.sql.SparkSession sparkSession)  { throw new RuntimeException(); }
}
