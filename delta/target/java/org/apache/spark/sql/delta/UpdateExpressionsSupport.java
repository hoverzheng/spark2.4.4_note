package org.apache.spark.sql.delta;
/**
 * Trait with helper functions to generate expressions to update target columns, even if they are
 * nested fields.
 */
public  interface UpdateExpressionsSupport extends org.apache.spark.sql.catalyst.analysis.CastSupport {
  /**
   * Specifies an operation that updates a target column with the given expression.
   * The target column may or may not be a nested field and it is specified as a full quoted name
   * or as a sequence of split into parts.
   */
  public  class UpdateOperation implements scala.Product, scala.Serializable {
    static public abstract  R apply (T1 v1, T2 v2)  ;
    static public  java.lang.String toString ()  { throw new RuntimeException(); }
    public  scala.collection.Seq<java.lang.String> targetColNameParts ()  { throw new RuntimeException(); }
    public  org.apache.spark.sql.catalyst.expressions.Expression updateExpr ()  { throw new RuntimeException(); }
    // not preceding
    public   UpdateOperation (scala.collection.Seq<java.lang.String> targetColNameParts, org.apache.spark.sql.catalyst.expressions.Expression updateExpr)  { throw new RuntimeException(); }
  }
  public  class UpdateOperation$ extends scala.runtime.AbstractFunction2<scala.collection.Seq<java.lang.String>, org.apache.spark.sql.catalyst.expressions.Expression, org.apache.spark.sql.delta.UpdateExpressionsSupport.UpdateOperation> implements scala.Serializable {
    /**
     * Static reference to the singleton instance of this Scala object.
     */
    public static final UpdateOperation$ MODULE$ = null;
    public   UpdateOperation$ ()  { throw new RuntimeException(); }
  }
  public  org.apache.spark.sql.catalyst.expressions.Expression castIfNeeded (org.apache.spark.sql.catalyst.expressions.Expression child, org.apache.spark.sql.types.DataType dataType)  ;
  /**
   * Given a list of target-column expressions and a set of update operations, generate a list
   * of update expressions, which are aligned with given target-column expressions.
   * <p>
   * For update operations to nested struct fields, this method recursively walks down schema tree
   * and apply the update expressions along the way.
   * For example, assume table <code>target</code> has two attributes a and z, where a is of struct type
   * with 3 fields: b, c and d, and z is of integer type.
   * <p>
   * Given an update command:
   * <p>
   *  - UPDATE target SET a.b = 1, a.c = 2, z = 3
   * <p>
   * this method works as follows:
   * <p>
   * generateUpdateExpressions(targetCols=[a,z], updateOps=[(a.b, 1), (a.c, 2), (z, 3)])
   *   generateUpdateExpressions(targetCols=[b,c,d], updateOps=[(b, 1),(c, 2)], pathPrefix=["a"])
   *     end-of-recursion
   *   -> returns (1, 2, d)
   * -> return ((1, 2, d), 3)
   * <p>
   * @param targetCols a list of expressions to read named columns; these named columns can be
   *                   either the top-level attributes of a table, or the nested fields of a
   *                   StructType column.
   * @param updateOps a set of update operations.
   * @param pathPrefix the path from root to the current (nested) column. Only used for printing out
   *                   full column path in error messages.
   * @param resolver (undocumented)
   * @return (undocumented)
   */
  public  scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Expression> generateUpdateExpressions (scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.NamedExpression> targetCols, scala.collection.Seq<org.apache.spark.sql.delta.UpdateExpressionsSupport.UpdateOperation> updateOps, scala.Function2<java.lang.String, java.lang.String, java.lang.Object> resolver, scala.collection.Seq<java.lang.String> pathPrefix)  ;
  /** See docs on overloaded method. */
  public  scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Expression> generateUpdateExpressions (scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.NamedExpression> targetCols, scala.collection.Seq<scala.collection.Seq<java.lang.String>> nameParts, scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Expression> updateExprs, scala.Function2<java.lang.String, java.lang.String, java.lang.Object> resolver)  ;
}
