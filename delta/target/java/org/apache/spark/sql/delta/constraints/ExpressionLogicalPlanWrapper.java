package org.apache.spark.sql.delta.constraints;
/**
 * A dummy wrapper for expressions so we can pass them to the {@link Analyzer}.
 */
  class ExpressionLogicalPlanWrapper extends org.apache.spark.sql.catalyst.plans.logical.LogicalPlan implements scala.Product, scala.Serializable {
  static public abstract  R apply (T1 v1)  ;
  static public  java.lang.String toString ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.catalyst.expressions.Expression e ()  { throw new RuntimeException(); }
  // not preceding
  public   ExpressionLogicalPlanWrapper (org.apache.spark.sql.catalyst.expressions.Expression e)  { throw new RuntimeException(); }
  public  scala.collection.Seq<org.apache.spark.sql.catalyst.plans.logical.LogicalPlan> children ()  { throw new RuntimeException(); }
  public  scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Attribute> output ()  { throw new RuntimeException(); }
}
