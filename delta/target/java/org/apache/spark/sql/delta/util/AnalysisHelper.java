package org.apache.spark.sql.delta.util;
public  interface AnalysisHelper {
  /** LogicalPlan to help resolve the given expression */
  static public  class FakeLogicalPlan extends org.apache.spark.sql.catalyst.plans.logical.LogicalPlan implements scala.Product, scala.Serializable {
    public  org.apache.spark.sql.catalyst.expressions.Expression expr ()  { throw new RuntimeException(); }
    public  scala.collection.Seq<org.apache.spark.sql.catalyst.plans.logical.LogicalPlan> children ()  { throw new RuntimeException(); }
    // not preceding
    public   FakeLogicalPlan (org.apache.spark.sql.catalyst.expressions.Expression expr, scala.collection.Seq<org.apache.spark.sql.catalyst.plans.logical.LogicalPlan> children)  { throw new RuntimeException(); }
    public  scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Attribute> output ()  { throw new RuntimeException(); }
  }
  static public  class FakeLogicalPlan$ extends scala.runtime.AbstractFunction2<org.apache.spark.sql.catalyst.expressions.Expression, scala.collection.Seq<org.apache.spark.sql.catalyst.plans.logical.LogicalPlan>, org.apache.spark.sql.delta.util.AnalysisHelper.FakeLogicalPlan> implements scala.Serializable {
    /**
     * Static reference to the singleton instance of this Scala object.
     */
    public static final FakeLogicalPlan$ MODULE$ = null;
    public   FakeLogicalPlan$ ()  { throw new RuntimeException(); }
  }
  public  org.apache.spark.sql.catalyst.expressions.Expression tryResolveReferences (org.apache.spark.sql.SparkSession sparkSession, org.apache.spark.sql.catalyst.expressions.Expression expr, org.apache.spark.sql.catalyst.plans.logical.LogicalPlan planContainingExpr)  ;
  public  org.apache.spark.sql.Dataset<org.apache.spark.sql.Row> toDataset (org.apache.spark.sql.SparkSession sparkSession, org.apache.spark.sql.catalyst.plans.logical.LogicalPlan logicalPlan)  ;
  public  void improveUnsupportedOpError (scala.Function0<scala.runtime.BoxedUnit> f)  ;
}
