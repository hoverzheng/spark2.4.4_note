package io.delta.tables;
/**
 * :: Evolving ::
 * <p>
 * Builder to specify how to merge data from source DataFrame into the target Delta table.
 * You can specify 1, 2 or 3 <code>when</code> clauses of which there can be at most 2 <code>whenMatched</code> clauses
 * and at most 1 <code>whenNotMatched</code> clause. Here are the constraints on these clauses.
 * <p>
 *   - <code>whenMatched</code> clauses:
 * <p>
 *     - There can be at most one <code>update</code> action and one <code>delete</code> action in <code>whenMatched</code> clauses.
 * <p>
 *     - Each <code>whenMatched</code> clause can have an optional condition. However, if there are two
 *       <code>whenMatched</code> clauses, then the first one must have a condition.
 * <p>
 *     - When there are two <code>whenMatched</code> clauses and there are conditions (or the lack of)
 *       such that a row matches both clauses, then the first clause/action is executed.
 *       In other words, the order of the <code>whenMatched</code> clauses matter.
 * <p>
 *     - If none of the <code>whenMatched</code> clauses match a source-target row pair that satisfy
 *       the merge condition, then the target rows will not be updated or deleted.
 * <p>
 *     - If you want to update all the columns of the target Delta table with the
 *       corresponding column of the source DataFrame, then you can use the
 *       <code>whenMatched(...).updateAll()</code>. This is equivalent to
 *       <pre>
 *         whenMatched(...).updateExpr(Map(
 *           ("col1", "source.col1"),
 *           ("col2", "source.col2"),
 *           ...))
 *       </pre>
 * <p>
 *   - <code>whenNotMatched</code> clauses:
 * <p>
 *     - This clause can have only an <code>insert</code> action, which can have an optional condition.
 * <p>
 *     - If the <code>whenNotMatched</code> clause is not present or if it is present but the non-matching
 *       source row does not satisfy the condition, then the source row is not inserted.
 * <p>
 *     - If you want to insert all the columns of the target Delta table with the
 *       corresponding column of the source DataFrame, then you can use
 *       <code>whenMatched(...).insertAll()</code>. This is equivalent to
 *       <pre>
 *         whenMatched(...).insertExpr(Map(
 *           ("col1", "source.col1"),
 *           ("col2", "source.col2"),
 *           ...))
 *       </pre>
 * <p>
 * Scala example to update a key-value Delta table with new key-values from a source DataFrame:
 * <pre><code>
 *    deltaTable
 *     .as("target")
 *     .merge(
 *       source.as("source"),
 *       "target.key = source.key")
 *     .whenMatched
 *     .updateExpr(Map(
 *       "value" -&gt; "source.value"))
 *     .whenNotMatched
 *     .insertExpr(Map(
 *       "key" -&gt; "source.key",
 *       "value" -&gt; "source.value"))
 *     .execute()
 * </code></pre>
 * <p>
 * Java example to update a key-value Delta table with new key-values from a source DataFrame:
 * <pre><code>
 *    deltaTable
 *     .as("target")
 *     .merge(
 *       source.as("source"),
 *       "target.key = source.key")
 *     .whenMatched
 *     .updateExpr(
 *        new HashMap&lt;String, String&gt;() {{
 *          put("value", "source.value");
 *        }})
 *     .whenNotMatched
 *     .insertExpr(
 *        new HashMap&lt;String, String&gt;() {{
 *         put("key", "source.key");
 *         put("value", "source.value");
 *       }})
 *     .execute();
 * </code></pre>
 * <p>
 * @since 0.3.0
 */
public  class DeltaMergeBuilder implements org.apache.spark.sql.delta.util.AnalysisHelper, org.apache.spark.internal.Logging {
  /**
   * :: Unstable ::
   * <p>
   * Private method for internal usage only. Do not call this directly.
   * @param targetTable (undocumented)
   * @param source (undocumented)
   * @param onCondition (undocumented)
   * @return (undocumented)
   */
  static   io.delta.tables.DeltaMergeBuilder apply (io.delta.tables.DeltaTable targetTable, org.apache.spark.sql.Dataset<org.apache.spark.sql.Row> source, org.apache.spark.sql.Column onCondition)  { throw new RuntimeException(); }
  /**
   * :: Evolving ::
   * <p>
   * Build the actions to perform when the merge condition was matched.  This returns
   * {@link DeltaMergeMatchedActionBuilder} object which can be used to specify how
   * to update or delete the matched target table row with the source row.
   * @since 0.3.0
   * @return (undocumented)
   */
  public  io.delta.tables.DeltaMergeMatchedActionBuilder whenMatched ()  { throw new RuntimeException(); }
  /**
   * :: Evolving ::
   * <p>
   * Build the actions to perform when the merge condition was matched and
   * the given <code>condition</code> is true. This returns {@link DeltaMergeMatchedActionBuilder} object
   * which can be used to specify how to update or delete the matched target table row with the
   * source row.
   * <p>
   * @param condition boolean expression as a SQL formatted string
   * @since 0.3.0
   * @return (undocumented)
   */
  public  io.delta.tables.DeltaMergeMatchedActionBuilder whenMatched (java.lang.String condition)  { throw new RuntimeException(); }
  /**
   * :: Evolving ::
   * <p>
   * Build the actions to perform when the merge condition was matched and
   * the given <code>condition</code> is true. This returns a {@link DeltaMergeMatchedActionBuilder} object
   * which can be used to specify how to update or delete the matched target table row with the
   * source row.
   * <p>
   * @param condition boolean expression as a Column object
   * @since 0.3.0
   * @return (undocumented)
   */
  public  io.delta.tables.DeltaMergeMatchedActionBuilder whenMatched (org.apache.spark.sql.Column condition)  { throw new RuntimeException(); }
  /**
   * :: Evolving ::
   * <p>
   * Build the action to perform when the merge condition was not matched. This returns
   * {@link DeltaMergeNotMatchedActionBuilder} object which can be used to specify how
   * to insert the new sourced row into the target table.
   * @since 0.3.0
   * @return (undocumented)
   */
  public  io.delta.tables.DeltaMergeNotMatchedActionBuilder whenNotMatched ()  { throw new RuntimeException(); }
  /**
   * :: Evolving ::
   * <p>
   * Build the actions to perform when the merge condition was not matched and
   * the given <code>condition</code> is true. This returns {@link DeltaMergeMatchedActionBuilder} object
   * which can be used to specify how to insert the new sourced row into the target table.
   * <p>
   * @param condition boolean expression as a SQL formatted string
   * @since 0.3.0
   * @return (undocumented)
   */
  public  io.delta.tables.DeltaMergeNotMatchedActionBuilder whenNotMatched (java.lang.String condition)  { throw new RuntimeException(); }
  /**
   * :: Evolving ::
   * <p>
   * Build the actions to perform when the merge condition was not matched and
   * the given <code>condition</code> is true. This returns {@link DeltaMergeMatchedActionBuilder} object
   * which can be used to specify how to insert the new sourced row into the target table.
   * <p>
   * @param condition boolean expression as a Column object
   * @since 0.3.0
   * @return (undocumented)
   */
  public  io.delta.tables.DeltaMergeNotMatchedActionBuilder whenNotMatched (org.apache.spark.sql.Column condition)  { throw new RuntimeException(); }
  /**
   * :: Evolving ::
   * <p>
   * Execute the merge operation based on the built matched and not matched actions.
   * <p>
   * @since 0.3.0
   */
  public  void execute ()  { throw new RuntimeException(); }
  /**
   * :: Unstable ::
   * <p>
   * Private method for internal usage only. Do not call this directly.
   * @param clause (undocumented)
   * @return (undocumented)
   */
    io.delta.tables.DeltaMergeBuilder withClause (org.apache.spark.sql.catalyst.plans.logical.DeltaMergeIntoClause clause)  { throw new RuntimeException(); }
}
