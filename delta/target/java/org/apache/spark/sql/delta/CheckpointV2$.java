package org.apache.spark.sql.delta;
/**
 * Utility methods for generating and using V2 checkpoints. V2 checkpoints have partition values and
 * statistics as struct fields of the <code>add</code> column.
 */
public  class CheckpointV2$ {
  /**
   * Static reference to the singleton instance of this Scala object.
   */
  public static final CheckpointV2$ MODULE$ = null;
  public   CheckpointV2$ ()  { throw new RuntimeException(); }
  public  java.lang.String PARTITIONS_COL_NAME ()  { throw new RuntimeException(); }
  public  java.lang.String STATS_COL_NAME ()  { throw new RuntimeException(); }
  /**
   * Creates a nested struct column of partition values that extract the partition values
   * from the original MapType.
   * @param partitionSchema (undocumented)
   * @return (undocumented)
   */
  public  scala.Option<org.apache.spark.sql.Column> extractPartitionValues (org.apache.spark.sql.types.StructType partitionSchema)  { throw new RuntimeException(); }
}
