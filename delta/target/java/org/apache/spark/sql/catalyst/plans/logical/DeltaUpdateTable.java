package org.apache.spark.sql.catalyst.plans.logical;
/**
 * Perform UPDATE on a table
 * <p>
 * param:  child the logical plan representing target table
 * param:  updateColumns: the to-be-updated target columns
 * param:  updateExpressions: the corresponding update expression if the condition is matched
 * param:  condition: Only rows that match the condition will be updated
 */
public  class DeltaUpdateTable extends org.apache.spark.sql.catalyst.plans.logical.UnaryNode implements scala.Product, scala.Serializable {
  /**
   * Extracts name parts from a resolved expression referring to a nested or non-nested column
   * - For non-nested column, the resolved expression will be like <code>AttributeReference(...)</code>.
   * - For nested column, the resolved expression will be like <code>Alias(GetStructField(...))</code>.
   * <p>
   * In the nested case, the function recursively traverses through the expression to find
   * the name parts. For example, a nested field of a.b.c would be resolved to an expression
   * <p>
   *    <code>Alias(c, GetStructField(c, GetStructField(b, AttributeReference(a)))</code>
   * <p>
   * for which this method recursively extracts the name parts as follows:
   * <p>
   *    <code>Alias(c, GetStructField(c, GetStructField(b, AttributeReference(a)))</code>
   *    ->  <code>GetStructField(c, GetStructField(b, AttributeReference(a)))</code>
   *      ->  <code>GetStructField(b, AttributeReference(a))</code> ++ Seq(c)
   *        ->  <code>AttributeReference(a)</code> ++ Seq(b, c)
   *          ->  [a, b, c]
   * @param resolvedTargetCol (undocumented)
   * @param errMsg (undocumented)
   * @return (undocumented)
   */
  static public  scala.collection.Seq<java.lang.String> getTargetColNameParts (org.apache.spark.sql.catalyst.expressions.Expression resolvedTargetCol, java.lang.String errMsg)  { throw new RuntimeException(); }
  public  org.apache.spark.sql.catalyst.plans.logical.LogicalPlan child ()  { throw new RuntimeException(); }
  public  scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.NamedExpression> updateColumns ()  { throw new RuntimeException(); }
  public  scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Expression> updateExpressions ()  { throw new RuntimeException(); }
  public  scala.Option<org.apache.spark.sql.catalyst.expressions.Expression> condition ()  { throw new RuntimeException(); }
  // not preceding
  public   DeltaUpdateTable (org.apache.spark.sql.catalyst.plans.logical.LogicalPlan child, scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.NamedExpression> updateColumns, scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Expression> updateExpressions, scala.Option<org.apache.spark.sql.catalyst.expressions.Expression> condition)  { throw new RuntimeException(); }
  public  scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Attribute> output ()  { throw new RuntimeException(); }
}
