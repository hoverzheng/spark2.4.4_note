package org.apache.spark.sql.delta;
/**
 * Preprocesses the {@link DeltaUpdateTable} logical plan before converting it to {@link UpdateCommand}.
 * - Adjusts the column order, which could be out of order, based on the destination table
 * - Generates expressions to compute the value of all target columns in Delta table, while taking
 * into account that the specified SET clause may only update some columns or nested fields of
 * columns.
 */
public  class PreprocessTableUpdate extends org.apache.spark.sql.catalyst.rules.Rule<org.apache.spark.sql.catalyst.plans.logical.LogicalPlan> implements org.apache.spark.sql.delta.UpdateExpressionsSupport, scala.Product, scala.Serializable {
  static public  java.lang.String toString ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.delta.UpdateExpressionsSupport.UpdateOperation$ UpdateOperation ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.internal.SQLConf conf ()  { throw new RuntimeException(); }
  // not preceding
  public   PreprocessTableUpdate (org.apache.spark.sql.internal.SQLConf conf)  { throw new RuntimeException(); }
  public  org.apache.spark.sql.catalyst.plans.logical.LogicalPlan apply (org.apache.spark.sql.catalyst.plans.logical.LogicalPlan plan)  { throw new RuntimeException(); }
  public  org.apache.spark.sql.delta.commands.UpdateCommand toCommand (org.apache.spark.sql.catalyst.plans.logical.DeltaUpdateTable update)  { throw new RuntimeException(); }
}
