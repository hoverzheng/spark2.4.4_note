package io.delta.tables;
/**
 * :: Evolving ::
 * <p>
 * Builder class to specify the actions to perform when a source row has not matched any target
 * Delta table row based on the merge condition, but has matched the additional condition
 * if specified.
 * <p>
 * See {@link DeltaMergeBuilder} for more information.
 * <p>
 * @since 0.3.0
 */
public  class DeltaMergeNotMatchedActionBuilder {
  /**
   * :: Unstable ::
   * <p>
   * Private method for internal usage only. Do not call this directly.
   * @param mergeBuilder (undocumented)
   * @param notMatchCondition (undocumented)
   * @return (undocumented)
   */
  static   io.delta.tables.DeltaMergeNotMatchedActionBuilder apply (io.delta.tables.DeltaMergeBuilder mergeBuilder, scala.Option<org.apache.spark.sql.Column> notMatchCondition)  { throw new RuntimeException(); }
  /**
   * :: Evolving ::
   * <p>
   * Insert a new row to the target table based on the rules defined by <code>values</code>.
   * <p>
   * @param values rules to insert a row as a Scala map between target column names and
   *               corresponding expressions as Column objects.
   * @since 0.3.0
   * @return (undocumented)
   */
  public  io.delta.tables.DeltaMergeBuilder insert (scala.collection.Map<java.lang.String, org.apache.spark.sql.Column> values)  { throw new RuntimeException(); }
  /**
   * :: Evolving ::
   * <p>
   * Insert a new row to the target table based on the rules defined by <code>values</code>.
   * <p>
   * @param values rules to insert a row as a Scala map between target column names and
   *               corresponding expressions as SQL formatted strings.
   * @since 0.3.0
   * @return (undocumented)
   */
  public  io.delta.tables.DeltaMergeBuilder insertExpr (scala.collection.Map<java.lang.String, java.lang.String> values)  { throw new RuntimeException(); }
  /**
   * :: Evolving ::
   * <p>
   * Insert a new row to the target table based on the rules defined by <code>values</code>.
   * <p>
   * @param values rules to insert a row as a Java map between target column names and
   *               corresponding expressions as Column objects.
   * @since 0.3.0
   * @return (undocumented)
   */
  public  io.delta.tables.DeltaMergeBuilder insert (java.util.Map<java.lang.String, org.apache.spark.sql.Column> values)  { throw new RuntimeException(); }
  /**
   * :: Evolving ::
   * <p>
   * Insert a new row to the target table based on the rules defined by <code>values</code>.
   * <p>
   * @param values rules to insert a row as a Java map between target column names and
   *               corresponding expressions as SQL formatted strings.
   * <p>
   * @since 0.3.0
   * @return (undocumented)
   */
  public  io.delta.tables.DeltaMergeBuilder insertExpr (java.util.Map<java.lang.String, java.lang.String> values)  { throw new RuntimeException(); }
  /**
   * :: Evolving ::
   * <p>
   * Insert a new target Delta table row by assigning the target columns to the values of the
   * corresponding columns in the source row.
   * @since 0.3.0
   * @return (undocumented)
   */
  public  io.delta.tables.DeltaMergeBuilder insertAll ()  { throw new RuntimeException(); }
}
