package org.apache.spark.sql.delta;
/**
 * Provides information around which files in the transaction log need to be read to create
 * the given version of the log.
 * param:  logPath The path to the _delta_log directory
 * param:  version The Snapshot version to generate
 * param:  deltas The delta files to read
 * param:  checkpoint The checkpoint file to read
 * param:  checkpointVersion The checkpoint version used to start replay
 * param:  lastCommitTimestamp The "unadjusted" timestamp of the last commit within this segment. By
 *                            unadjusted, we mean that the commit timestamps may not necessarily be
 *                            monotonically increasing for the commits within this segment.
 */
public  class LogSegment implements scala.Product, scala.Serializable {
  /** The LogSegment for an empty transaction log directory. */
  static public  org.apache.spark.sql.delta.LogSegment empty (org.apache.hadoop.fs.Path path)  { throw new RuntimeException(); }
  public  org.apache.hadoop.fs.Path logPath ()  { throw new RuntimeException(); }
  public  long version ()  { throw new RuntimeException(); }
  public  scala.collection.Seq<org.apache.hadoop.fs.FileStatus> deltas ()  { throw new RuntimeException(); }
  public  scala.collection.Seq<org.apache.hadoop.fs.FileStatus> checkpoint ()  { throw new RuntimeException(); }
  public  scala.Option<java.lang.Object> checkpointVersion ()  { throw new RuntimeException(); }
  public  long lastCommitTimestamp ()  { throw new RuntimeException(); }
  // not preceding
  public   LogSegment (org.apache.hadoop.fs.Path logPath, long version, scala.collection.Seq<org.apache.hadoop.fs.FileStatus> deltas, scala.collection.Seq<org.apache.hadoop.fs.FileStatus> checkpoint, scala.Option<java.lang.Object> checkpointVersion, long lastCommitTimestamp)  { throw new RuntimeException(); }
  public  int hashCode ()  { throw new RuntimeException(); }
  /**
   * An efficient way to check if a cached Snapshot's contents actually correspond to a new
   * segment returned through file listing.
   * @param obj (undocumented)
   * @return (undocumented)
   */
  public  boolean equals (Object obj)  { throw new RuntimeException(); }
}
