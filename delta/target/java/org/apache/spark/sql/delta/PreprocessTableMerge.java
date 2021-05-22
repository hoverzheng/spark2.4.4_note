package org.apache.spark.sql.delta;
public  class PreprocessTableMerge extends org.apache.spark.sql.catalyst.rules.Rule<org.apache.spark.sql.catalyst.plans.logical.LogicalPlan> implements org.apache.spark.sql.delta.UpdateExpressionsSupport, scala.Product, scala.Serializable {
  static public  java.lang.String toString ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.delta.UpdateExpressionsSupport.UpdateOperation$ UpdateOperation ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.internal.SQLConf conf ()  { throw new RuntimeException(); }
  // not preceding
  public   PreprocessTableMerge (org.apache.spark.sql.internal.SQLConf conf)  { throw new RuntimeException(); }
  public  org.apache.spark.sql.catalyst.plans.logical.LogicalPlan apply (org.apache.spark.sql.catalyst.plans.logical.LogicalPlan plan)  { throw new RuntimeException(); }
  public  org.apache.spark.sql.delta.commands.MergeIntoCommand apply (org.apache.spark.sql.catalyst.plans.logical.DeltaMergeInto mergeInto)  { throw new RuntimeException(); }
}
