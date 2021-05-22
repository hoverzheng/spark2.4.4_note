package org.apache.spark.sql.catalyst.plans.logical;
/**
 * A copy of Spark SQL Unevaluable for cross-version compatibility. In 3.0, implementers of
 * the original Unevaluable must explicitly override foldable to false; in 3.1 onwards, this
 * explicit override is invalid.
 */
public  interface DeltaUnevaluable {
  public  org.apache.spark.sql.catalyst.expressions.codegen.ExprCode doGenCode (org.apache.spark.sql.catalyst.expressions.codegen.CodegenContext ctx, org.apache.spark.sql.catalyst.expressions.codegen.ExprCode ev)  ;
  public  Object eval (org.apache.spark.sql.catalyst.InternalRow input)  ;
  public  boolean foldable ()  ;
}
