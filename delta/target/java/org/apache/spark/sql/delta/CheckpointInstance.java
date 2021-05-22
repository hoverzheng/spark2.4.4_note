package org.apache.spark.sql.delta;
/**
 * A class to help with comparing checkpoints with each other, where we may have had concurrent
 * writers that checkpoint with different number of parts.
 */
public  class CheckpointInstance implements scala.math.Ordered<org.apache.spark.sql.delta.CheckpointInstance>, scala.Product, scala.Serializable {
  static public  org.apache.spark.sql.delta.CheckpointInstance apply (org.apache.hadoop.fs.Path path)  { throw new RuntimeException(); }
  static public  org.apache.spark.sql.delta.CheckpointInstance apply (org.apache.spark.sql.delta.CheckpointMetaData metadata)  { throw new RuntimeException(); }
  static public  org.apache.spark.sql.delta.CheckpointInstance MaxValue ()  { throw new RuntimeException(); }
  public  long version ()  { throw new RuntimeException(); }
  public  scala.Option<java.lang.Object> numParts ()  { throw new RuntimeException(); }
  // not preceding
  public   CheckpointInstance (long version, scala.Option<java.lang.Object> numParts)  { throw new RuntimeException(); }
  /**
   * Due to lexicographic sorting, a version with more parts will appear after a version with
   * less parts during file listing. We use that logic here as well.
   * @param other (undocumented)
   * @return (undocumented)
   */
  public  boolean isEarlierThan (org.apache.spark.sql.delta.CheckpointInstance other)  { throw new RuntimeException(); }
  public  boolean isNotLaterThan (org.apache.spark.sql.delta.CheckpointInstance other)  { throw new RuntimeException(); }
  public  scala.collection.Seq<org.apache.hadoop.fs.Path> getCorrespondingFiles (org.apache.hadoop.fs.Path path)  { throw new RuntimeException(); }
  public  int compare (org.apache.spark.sql.delta.CheckpointInstance that)  { throw new RuntimeException(); }
}
