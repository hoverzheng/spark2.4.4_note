package org.apache.spark.sql.delta.commands;
public  class MergeIntoCommand$ implements scala.Serializable {
  /**
   * Static reference to the singleton instance of this Scala object.
   */
  public static final MergeIntoCommand$ MODULE$ = null;
  public   MergeIntoCommand$ ()  { throw new RuntimeException(); }
  /**
   * Spark UI will track all normal accumulators along with Spark tasks to show them on Web UI.
   * However, the accumulator used by <code>MergeIntoCommand</code> can store a very large value since it
   * tracks all files that need to be rewritten. We should ask Spark UI to not remember it,
   * otherwise, the UI data may consume lots of memory. Hence, we use the prefix <code>internal.metrics.</code>
   * to make this accumulator become an internal accumulator, so that it will not be tracked by
   * Spark UI.
   * @return (undocumented)
   */
  public  java.lang.String TOUCHED_FILES_ACCUM_NAME ()  { throw new RuntimeException(); }
  public  java.lang.String ROW_ID_COL ()  { throw new RuntimeException(); }
  public  java.lang.String FILE_NAME_COL ()  { throw new RuntimeException(); }
  public  java.lang.String SOURCE_ROW_PRESENT_COL ()  { throw new RuntimeException(); }
  public  java.lang.String TARGET_ROW_PRESENT_COL ()  { throw new RuntimeException(); }
  /** Count the number of distinct partition values in the given AddFiles. */
  public  scala.Tuple2<java.lang.Object, java.lang.Object> totalBytesAndDistinctPartitionValues (scala.collection.Seq<org.apache.spark.sql.delta.actions.AddFile> files)  { throw new RuntimeException(); }
}
