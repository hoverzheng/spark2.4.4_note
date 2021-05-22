package org.apache.spark.sql.delta;
/** Record metrics about a successful commit. */
public  class CommitStats implements scala.Product, scala.Serializable {
  static public abstract  R apply (T1 v1, T2 v2, T3 v3, T4 v4, T5 v5, T6 v6, T7 v7, T8 v8, T9 v9, T10 v10, T11 v11, T12 v12, T13 v13, T14 v14, T15 v15, T16 v16)  ;
  static public  java.lang.String toString ()  { throw new RuntimeException(); }
  /** The version read by the txn when it starts. */
  public  long startVersion ()  { throw new RuntimeException(); }
  /** The version committed by the txn. */
  public  long commitVersion ()  { throw new RuntimeException(); }
  /** The version read by the txn right after it commits. It usually equals to commitVersion,
   * @return (undocumented)
   * but can be larger than commitVersion when there are concurrent commits. */
  public  long readVersion ()  { throw new RuntimeException(); }
  public  long txnDurationMs ()  { throw new RuntimeException(); }
  public  long commitDurationMs ()  { throw new RuntimeException(); }
  public  int numAdd ()  { throw new RuntimeException(); }
  public  int numRemove ()  { throw new RuntimeException(); }
  public  long bytesNew ()  { throw new RuntimeException(); }
  /** The number of files in the table as of version `readVersion`. */
  public  long numFilesTotal ()  { throw new RuntimeException(); }
  /** The table size in bytes as of version `readVersion`. */
  public  long sizeInBytesTotal ()  { throw new RuntimeException(); }
  /** The protocol as of version `readVersion`. */
  public  org.apache.spark.sql.delta.actions.Protocol protocol ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.delta.actions.CommitInfo info ()  { throw new RuntimeException(); }
  public  scala.Option<org.apache.spark.sql.delta.actions.Metadata> newMetadata ()  { throw new RuntimeException(); }
  public  int numAbsolutePathsInAdd ()  { throw new RuntimeException(); }
  public  int numDistinctPartitionsInAdd ()  { throw new RuntimeException(); }
  public  java.lang.String isolationLevel ()  { throw new RuntimeException(); }
  // not preceding
  public   CommitStats (long startVersion, long commitVersion, long readVersion, long txnDurationMs, long commitDurationMs, int numAdd, int numRemove, long bytesNew, long numFilesTotal, long sizeInBytesTotal, org.apache.spark.sql.delta.actions.Protocol protocol, org.apache.spark.sql.delta.actions.CommitInfo info, scala.Option<org.apache.spark.sql.delta.actions.Metadata> newMetadata, int numAbsolutePathsInAdd, int numDistinctPartitionsInAdd, java.lang.String isolationLevel)  { throw new RuntimeException(); }
}
