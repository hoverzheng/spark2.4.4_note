package org.apache.spark.sql.delta.commands;
/**
 * Performs a merge of a source query/table into a Delta table.
 * <p>
 * Issues an error message when the ON search_condition of the MERGE statement can match
 * a single row from the target table with multiple rows of the source table-reference.
 * <p>
 * Algorithm:
 * <p>
 * Phase 1: Find the input files in target that are touched by the rows that satisfy
 *    the condition and verify that no two source rows match with the same target row.
 *    This is implemented as an inner-join using the given condition. See {@link findTouchedFiles}
 *    for more details.
 * <p>
 * Phase 2: Read the touched files again and write new files with updated and/or inserted rows.
 * <p>
 * Phase 3: Use the Delta protocol to atomically remove the touched files and add the new files.
 * <p>
 * param:  source            Source data to merge from
 * param:  target            Target table to merge into
 * param:  targetFileIndex   TahoeFileIndex of the target table
 * param:  condition         Condition for a source row to match with a target row
 * param:  matchedClauses    All info related to matched clauses.
 * param:  notMatchedClauses  All info related to not matched clause.
 * param:  migratedSchema    The final schema of the target - may be changed by schema evolution.
 */
public  class MergeIntoCommand extends org.apache.spark.sql.catalyst.plans.logical.LogicalPlan implements org.apache.spark.sql.execution.command.RunnableCommand, org.apache.spark.sql.delta.commands.DeltaCommand, org.apache.spark.sql.catalyst.expressions.PredicateHelper, org.apache.spark.sql.delta.util.AnalysisHelper, org.apache.spark.sql.delta.schema.ImplicitMetadataOperation, scala.Product, scala.Serializable {
  static public  class JoinedRowProcessor implements scala.Serializable {
    public   JoinedRowProcessor (org.apache.spark.sql.catalyst.expressions.Expression targetRowHasNoMatch, org.apache.spark.sql.catalyst.expressions.Expression sourceRowHasNoMatch, scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Expression> matchedConditions, scala.collection.Seq<scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Expression>> matchedOutputs, scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Expression> notMatchedConditions, scala.collection.Seq<scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Expression>> notMatchedOutputs, scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Expression> noopCopyOutput, scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Expression> deleteRowOutput, scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Attribute> joinedAttributes, org.apache.spark.sql.catalyst.encoders.ExpressionEncoder<org.apache.spark.sql.Row> joinedRowEncoder, org.apache.spark.sql.catalyst.encoders.ExpressionEncoder<org.apache.spark.sql.Row> outputRowEncoder)  { throw new RuntimeException(); }
    public  scala.collection.Iterator<org.apache.spark.sql.Row> processPartition (scala.collection.Iterator<org.apache.spark.sql.Row> rowIterator)  { throw new RuntimeException(); }
  }
  /**
   * Spark UI will track all normal accumulators along with Spark tasks to show them on Web UI.
   * However, the accumulator used by <code>MergeIntoCommand</code> can store a very large value since it
   * tracks all files that need to be rewritten. We should ask Spark UI to not remember it,
   * otherwise, the UI data may consume lots of memory. Hence, we use the prefix <code>internal.metrics.</code>
   * to make this accumulator become an internal accumulator, so that it will not be tracked by
   * Spark UI.
   * @return (undocumented)
   */
  static public  java.lang.String TOUCHED_FILES_ACCUM_NAME ()  { throw new RuntimeException(); }
  static public  java.lang.String ROW_ID_COL ()  { throw new RuntimeException(); }
  static public  java.lang.String FILE_NAME_COL ()  { throw new RuntimeException(); }
  static public  java.lang.String SOURCE_ROW_PRESENT_COL ()  { throw new RuntimeException(); }
  static public  java.lang.String TARGET_ROW_PRESENT_COL ()  { throw new RuntimeException(); }
  /** Count the number of distinct partition values in the given AddFiles. */
  static public  scala.Tuple2<java.lang.Object, java.lang.Object> totalBytesAndDistinctPartitionValues (scala.collection.Seq<org.apache.spark.sql.delta.actions.AddFile> files)  { throw new RuntimeException(); }
  public  org.apache.spark.sql.catalyst.plans.logical.LogicalPlan source ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.catalyst.plans.logical.LogicalPlan target ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.delta.files.TahoeFileIndex targetFileIndex ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.catalyst.expressions.Expression condition ()  { throw new RuntimeException(); }
  public  scala.collection.Seq<org.apache.spark.sql.catalyst.plans.logical.DeltaMergeIntoMatchedClause> matchedClauses ()  { throw new RuntimeException(); }
  public  scala.collection.Seq<org.apache.spark.sql.catalyst.plans.logical.DeltaMergeIntoInsertClause> notMatchedClauses ()  { throw new RuntimeException(); }
  public  scala.Option<org.apache.spark.sql.types.StructType> migratedSchema ()  { throw new RuntimeException(); }
  // not preceding
  public   MergeIntoCommand (org.apache.spark.sql.catalyst.plans.logical.LogicalPlan source, org.apache.spark.sql.catalyst.plans.logical.LogicalPlan target, org.apache.spark.sql.delta.files.TahoeFileIndex targetFileIndex, org.apache.spark.sql.catalyst.expressions.Expression condition, scala.collection.Seq<org.apache.spark.sql.catalyst.plans.logical.DeltaMergeIntoMatchedClause> matchedClauses, scala.collection.Seq<org.apache.spark.sql.catalyst.plans.logical.DeltaMergeIntoInsertClause> notMatchedClauses, scala.Option<org.apache.spark.sql.types.StructType> migratedSchema)  { throw new RuntimeException(); }
  public  boolean canMergeSchema ()  { throw new RuntimeException(); }
  public  boolean canOverwriteSchema ()  { throw new RuntimeException(); }
  // not preceding
  public  scala.collection.immutable.Map<java.lang.String, org.apache.spark.sql.execution.metric.SQLMetric> metrics ()  { throw new RuntimeException(); }
  public  scala.collection.Seq<org.apache.spark.sql.Row> run (org.apache.spark.sql.SparkSession spark)  { throw new RuntimeException(); }
  /**
   * Repartitions the output DataFrame by the partition columns if table is partitioned
   * and <code>merge.repartitionBeforeWrite.enabled</code> is set to true.
   * @param spark (undocumented)
   * @param df (undocumented)
   * @param partitionColumns (undocumented)
   * @return (undocumented)
   */
  protected  org.apache.spark.sql.Dataset<org.apache.spark.sql.Row> repartitionIfNeeded (org.apache.spark.sql.SparkSession spark, org.apache.spark.sql.Dataset<org.apache.spark.sql.Row> df, scala.collection.Seq<java.lang.String> partitionColumns)  { throw new RuntimeException(); }
}
