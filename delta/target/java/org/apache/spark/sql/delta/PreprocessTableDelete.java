package org.apache.spark.sql.delta;
/**
 * Preprocess the {@link DeltaDelete} plan to convert to {@link DeleteCommand}.
 */
public  class PreprocessTableDelete extends org.apache.spark.sql.catalyst.rules.Rule<org.apache.spark.sql.catalyst.plans.logical.LogicalPlan> implements scala.Product, scala.Serializable {
  static public  java.lang.String toString ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.internal.SQLConf conf ()  { throw new RuntimeException(); }
  // not preceding
  public   PreprocessTableDelete (org.apache.spark.sql.internal.SQLConf conf)  { throw new RuntimeException(); }
  public  org.apache.spark.sql.catalyst.plans.logical.LogicalPlan apply (org.apache.spark.sql.catalyst.plans.logical.LogicalPlan plan)  { throw new RuntimeException(); }
  public  org.apache.spark.sql.delta.commands.DeleteCommand toCommand (org.apache.spark.sql.catalyst.plans.logical.DeltaDelete d)  { throw new RuntimeException(); }
}
