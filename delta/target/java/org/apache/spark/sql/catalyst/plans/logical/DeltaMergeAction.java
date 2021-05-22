package org.apache.spark.sql.catalyst.plans.logical;
/**
 * Represents an action in MERGE's UPDATE or INSERT clause where a target columns is assigned the
 * value of an expression
 * param:  targetColNameParts The name parts of the target column. This is a sequence to support
 *                           nested fields as targets.
 * param:  expr Expression to generate the value of the target column.o
 */
public  class DeltaMergeAction extends org.apache.spark.sql.catalyst.expressions.UnaryExpression implements org.apache.spark.sql.catalyst.plans.logical.DeltaUnevaluable, scala.Product, scala.Serializable {
  static public abstract  R apply (T1 v1, T2 v2)  ;
  public  scala.collection.Seq<java.lang.String> targetColNameParts ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.catalyst.expressions.Expression expr ()  { throw new RuntimeException(); }
  // not preceding
  public   DeltaMergeAction (scala.collection.Seq<java.lang.String> targetColNameParts, org.apache.spark.sql.catalyst.expressions.Expression expr)  { throw new RuntimeException(); }
  public  org.apache.spark.sql.catalyst.expressions.Expression child ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.types.DataType dataType ()  { throw new RuntimeException(); }
  public  java.lang.String sql ()  { throw new RuntimeException(); }
  public  java.lang.String toString ()  { throw new RuntimeException(); }
}
