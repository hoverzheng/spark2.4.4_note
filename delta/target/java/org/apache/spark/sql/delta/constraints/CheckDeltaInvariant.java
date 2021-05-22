package org.apache.spark.sql.delta.constraints;
/**
 * An expression that validates a specific invariant on a column, before writing into Delta.
 * <p>
 * param:  child The fully resolved expression to be evaluated to check the constraint.
 * param:  columnExtractors Extractors for each referenced column. Used to generate readable errors.
 * param:  constraint The original constraint definition.
 */
public  class CheckDeltaInvariant extends org.apache.spark.sql.catalyst.expressions.UnaryExpression implements org.apache.spark.sql.catalyst.expressions.NonSQLExpression, org.apache.spark.sql.catalyst.expressions.codegen.CodegenFallback, scala.Product, scala.Serializable {
  static public abstract  R apply (T1 v1, T2 v2, T3 v3)  ;
  static public  java.lang.String toString ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.catalyst.expressions.Expression child ()  { throw new RuntimeException(); }
  public  scala.collection.immutable.Map<java.lang.String, org.apache.spark.sql.catalyst.expressions.Expression> columnExtractors ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.delta.constraints.Constraint constraint ()  { throw new RuntimeException(); }
  // not preceding
  public   CheckDeltaInvariant (org.apache.spark.sql.catalyst.expressions.Expression child, scala.collection.immutable.Map<java.lang.String, org.apache.spark.sql.catalyst.expressions.Expression> columnExtractors, org.apache.spark.sql.delta.constraints.Constraint constraint)  { throw new RuntimeException(); }
  public  org.apache.spark.sql.types.DataType dataType ()  { throw new RuntimeException(); }
  public  boolean foldable ()  { throw new RuntimeException(); }
  public  boolean nullable ()  { throw new RuntimeException(); }
  public  Object eval (org.apache.spark.sql.catalyst.InternalRow input)  { throw new RuntimeException(); }
  protected  org.apache.spark.sql.catalyst.expressions.codegen.ExprCode doGenCode (org.apache.spark.sql.catalyst.expressions.codegen.CodegenContext ctx, org.apache.spark.sql.catalyst.expressions.codegen.ExprCode ev)  { throw new RuntimeException(); }
}
