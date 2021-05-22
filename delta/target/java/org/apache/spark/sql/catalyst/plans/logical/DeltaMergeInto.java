package org.apache.spark.sql.catalyst.plans.logical;
/**
 * Merges changes specified in the source plan into a target table, based on the given search
 * condition and the actions to perform when the condition is matched or not matched by the rows.
 * <p>
 * The syntax of the MERGE statement is as follows.
 * <pre><code>
 *    MERGE INTO &lt;target_table_with_alias&gt;
 *    USING &lt;source_table_with_alias&gt;
 *    ON &lt;search_condition&gt;
 *    [ WHEN MATCHED [ AND &lt;condition&gt; ] THEN &lt;matched_action&gt; ]
 *    [ WHEN MATCHED [ AND &lt;condition&gt; ] THEN &lt;matched_action&gt; ]
 *    ...
 *    [ WHEN NOT MATCHED [ AND &lt;condition&gt; ] THEN &lt;not_matched_action&gt; ]
 *    [ WHEN NOT MATCHED [ AND &lt;condition&gt; ] THEN &lt;not_matched_action&gt; ]
 *    ...
 *
 *    where
 *    &lt;matched_action&gt; = DELETE | UPDATE SET column1 = value1 [, column2 = value2 ...]
 *    &lt;not_matched_action&gt; = INSERT (column1 [, column2 ...]) VALUES (expr1 [, expr2 ...])
 * </code></pre>
 * <p>
 * - There can be any number of WHEN clauses.
 * - WHEN MATCHED clauses:
 *    - Each WHEN MATCHED clause can have an optional condition. However, if there are multiple
 * WHEN MATCHED clauses, only the last can omit the condition.
 *    - WHEN MATCHED clauses are dependent on their ordering; that is, the first clause that
 * satisfies the clause's condition has its corresponding action executed.
 * - WHEN NOT MATCHED clause:
 *    - Can only have the INSERT action. If present, they must follow the last WHEN MATCHED clause.
 *    - Each WHEN NOT MATCHED clause can have an optional condition. However, if there are multiple
 * clauses, only the last can omit the condition.
 *    - WHEN NOT MATCHED clauses are dependent on their ordering; that is, the first clause that
 * satisfies the clause's condition has its corresponding action executed.
 */
public  class DeltaMergeInto extends org.apache.spark.sql.catalyst.plans.logical.LogicalPlan implements org.apache.spark.sql.catalyst.plans.logical.Command, scala.Product, scala.Serializable {
  static public  org.apache.spark.sql.catalyst.plans.logical.DeltaMergeInto apply (org.apache.spark.sql.catalyst.plans.logical.LogicalPlan target, org.apache.spark.sql.catalyst.plans.logical.LogicalPlan source, org.apache.spark.sql.catalyst.expressions.Expression condition, scala.collection.Seq<org.apache.spark.sql.catalyst.plans.logical.DeltaMergeIntoClause> whenClauses)  { throw new RuntimeException(); }
  static public  org.apache.spark.sql.catalyst.plans.logical.DeltaMergeInto resolveReferences (org.apache.spark.sql.catalyst.plans.logical.DeltaMergeInto merge, org.apache.spark.sql.internal.SQLConf conf, scala.Function2<org.apache.spark.sql.catalyst.expressions.Expression, org.apache.spark.sql.catalyst.plans.logical.LogicalPlan, org.apache.spark.sql.catalyst.expressions.Expression> resolveExpr)  { throw new RuntimeException(); }
  public  org.apache.spark.sql.catalyst.plans.logical.LogicalPlan target ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.catalyst.plans.logical.LogicalPlan source ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.catalyst.expressions.Expression condition ()  { throw new RuntimeException(); }
  public  scala.collection.Seq<org.apache.spark.sql.catalyst.plans.logical.DeltaMergeIntoMatchedClause> matchedClauses ()  { throw new RuntimeException(); }
  public  scala.collection.Seq<org.apache.spark.sql.catalyst.plans.logical.DeltaMergeIntoInsertClause> notMatchedClauses ()  { throw new RuntimeException(); }
  public  scala.Option<org.apache.spark.sql.types.StructType> migratedSchema ()  { throw new RuntimeException(); }
  // not preceding
  public   DeltaMergeInto (org.apache.spark.sql.catalyst.plans.logical.LogicalPlan target, org.apache.spark.sql.catalyst.plans.logical.LogicalPlan source, org.apache.spark.sql.catalyst.expressions.Expression condition, scala.collection.Seq<org.apache.spark.sql.catalyst.plans.logical.DeltaMergeIntoMatchedClause> matchedClauses, scala.collection.Seq<org.apache.spark.sql.catalyst.plans.logical.DeltaMergeIntoInsertClause> notMatchedClauses, scala.Option<org.apache.spark.sql.types.StructType> migratedSchema)  { throw new RuntimeException(); }
  public  scala.collection.Seq<org.apache.spark.sql.catalyst.plans.logical.LogicalPlan> children ()  { throw new RuntimeException(); }
  public  scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Attribute> output ()  { throw new RuntimeException(); }
}
