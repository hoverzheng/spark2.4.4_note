package org.apache.spark.sql.delta;
/**
 * Utility methods for generating and using V2 checkpoints. V2 checkpoints have partition values and
 * statistics as struct fields of the <code>add</code> column.
 */
public  class CheckpointV2 {
  static public  java.lang.String PARTITIONS_COL_NAME ()  { throw new RuntimeException(); }
  static public  java.lang.String STATS_COL_NAME ()  { throw new RuntimeException(); }
  /**
   * Creates a nested struct column of partition values that extract the partition values
   * from the original MapType.
   * @param partitionSchema (undocumented)
   * @return (undocumented)
   */
  static public  scala.Option<org.apache.spark.sql.Column> extractPartitionValues (org.apache.spark.sql.types.StructType partitionSchema)  { throw new RuntimeException(); }
}
