package io.delta.tables;
/**
 * :: Evolving ::
 * <p>
 * Builder class to specify the actions to perform when a target table row has matched a
 * source row based on the given merge condition and optional match condition.
 * <p>
 * See {@link DeltaMergeBuilder} for more information.
 * <p>
 * @since 0.3.0
 */
public  class DeltaMergeMatchedActionBuilder {
  /**
   * :: Unstable ::
   * <p>
   * Private method for internal usage only. Do not call this directly.
   * @param mergeBuilder (undocumented)
   * @param matchCondition (undocumented)
   * @return (undocumented)
   */
  static   io.delta.tables.DeltaMergeMatchedActionBuilder apply (io.delta.tables.DeltaMergeBuilder mergeBuilder, scala.Option<org.apache.spark.sql.Column> matchCondition)  { throw new RuntimeException(); }
  /**
   * :: Evolving ::
   * <p>
   * Update the matched table rows based on the rules defined by <code>set</code>.
   * <p>
   * @param set rules to update a row as a Scala map between target column names and
   *            corresponding update expressions as Column objects.
   * @since 0.3.0
   * @return (undocumented)
   */
  public  io.delta.tables.DeltaMergeBuilder update (scala.collection.Map<java.lang.String, org.apache.spark.sql.Column> set)  { throw new RuntimeException(); }
  /**
   * :: Evolving ::
   * <p>
   * Update the matched table rows based on the rules defined by <code>set</code>.
   * <p>
   * @param set rules to update a row as a Scala map between target column names and
   *            corresponding update expressions as SQL formatted strings.
   * @since 0.3.0
   * @return (undocumented)
   */
  public  io.delta.tables.DeltaMergeBuilder updateExpr (scala.collection.Map<java.lang.String, java.lang.String> set)  { throw new RuntimeException(); }
  /**
   * :: Evolving ::
   * <p>
   * Update a matched table row based on the rules defined by <code>set</code>.
   * <p>
   * @param set rules to update a row as a Java map between target column names and
   *            corresponding expressions as Column objects.
   * @since 0.3.0
   * @return (undocumented)
   */
  public  io.delta.tables.DeltaMergeBuilder update (java.util.Map<java.lang.String, org.apache.spark.sql.Column> set)  { throw new RuntimeException(); }
  /**
   * :: Evolving ::
   * <p>
   * Update a matched table row based on the rules defined by <code>set</code>.
   * <p>
   * @param set rules to update a row as a Java map between target column names and
   *            corresponding expressions as SQL formatted strings.
   * @since 0.3.0
   * @return (undocumented)
   */
  public  io.delta.tables.DeltaMergeBuilder updateExpr (java.util.Map<java.lang.String, java.lang.String> set)  { throw new RuntimeException(); }
  /**
   * :: Evolving ::
   * <p>
   * Update all the columns of the matched table row with the values of the
   * corresponding columns in the source row.
   * @since 0.3.0
   * @return (undocumented)
   */
  public  io.delta.tables.DeltaMergeBuilder updateAll ()  { throw new RuntimeException(); }
  /**
   * :: Evolving ::
   * <p>
   * Delete a matched row from the table.
   * @since 0.3.0
   * @return (undocumented)
   */
  public  io.delta.tables.DeltaMergeBuilder delete ()  { throw new RuntimeException(); }
}
