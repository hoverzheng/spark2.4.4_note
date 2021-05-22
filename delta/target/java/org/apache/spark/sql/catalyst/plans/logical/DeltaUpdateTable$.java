package org.apache.spark.sql.catalyst.plans.logical;
public  class DeltaUpdateTable$ implements scala.Serializable {
  /**
   * Static reference to the singleton instance of this Scala object.
   */
  public static final DeltaUpdateTable$ MODULE$ = null;
  public   DeltaUpdateTable$ ()  { throw new RuntimeException(); }
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
  public  scala.collection.Seq<java.lang.String> getTargetColNameParts (org.apache.spark.sql.catalyst.expressions.Expression resolvedTargetCol, java.lang.String errMsg)  { throw new RuntimeException(); }
}
