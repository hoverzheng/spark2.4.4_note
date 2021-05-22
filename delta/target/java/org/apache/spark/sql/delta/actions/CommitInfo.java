package org.apache.spark.sql.delta.actions;
/**
 * Holds provenance information about changes to the table. This {@link Action}
 * is not stored in the checkpoint and has reduced compatibility guarantees.
 * Information stored in it is best effort (i.e. can be falsified by the writer).
 */
public  class CommitInfo implements org.apache.spark.sql.delta.actions.Action, org.apache.spark.sql.delta.actions.CommitMarker, scala.Product, scala.Serializable {
  static public  org.apache.spark.sql.delta.actions.CommitInfo empty (scala.Option<java.lang.Object> version)  { throw new RuntimeException(); }
  static public  org.apache.spark.sql.delta.actions.CommitInfo apply (long time, java.lang.String operation, scala.collection.immutable.Map<java.lang.String, java.lang.String> operationParameters, scala.collection.immutable.Map<java.lang.String, java.lang.String> commandContext, scala.Option<java.lang.Object> readVersion, scala.Option<java.lang.String> isolationLevel, scala.Option<java.lang.Object> isBlindAppend, scala.Option<scala.collection.immutable.Map<java.lang.String, java.lang.String>> operationMetrics, scala.Option<java.lang.String> userMetadata)  { throw new RuntimeException(); }
  public  scala.Option<java.lang.Object> version ()  { throw new RuntimeException(); }
  public  java.sql.Timestamp timestamp ()  { throw new RuntimeException(); }
  public  scala.Option<java.lang.String> userId ()  { throw new RuntimeException(); }
  public  scala.Option<java.lang.String> userName ()  { throw new RuntimeException(); }
  public  java.lang.String operation ()  { throw new RuntimeException(); }
  public  scala.collection.immutable.Map<java.lang.String, java.lang.String> operationParameters ()  { throw new RuntimeException(); }
  public  scala.Option<org.apache.spark.sql.delta.actions.JobInfo> job ()  { throw new RuntimeException(); }
  public  scala.Option<org.apache.spark.sql.delta.actions.NotebookInfo> notebook ()  { throw new RuntimeException(); }
  public  scala.Option<java.lang.String> clusterId ()  { throw new RuntimeException(); }
  public  scala.Option<java.lang.Object> readVersion ()  { throw new RuntimeException(); }
  public  scala.Option<java.lang.String> isolationLevel ()  { throw new RuntimeException(); }
  /** Whether this commit has blindly appended without caring about existing files */
  public  scala.Option<java.lang.Object> isBlindAppend ()  { throw new RuntimeException(); }
  public  scala.Option<scala.collection.immutable.Map<java.lang.String, java.lang.String>> operationMetrics ()  { throw new RuntimeException(); }
  public  scala.Option<java.lang.String> userMetadata ()  { throw new RuntimeException(); }
  // not preceding
  public   CommitInfo (scala.Option<java.lang.Object> version, java.sql.Timestamp timestamp, scala.Option<java.lang.String> userId, scala.Option<java.lang.String> userName, java.lang.String operation, scala.collection.immutable.Map<java.lang.String, java.lang.String> operationParameters, scala.Option<org.apache.spark.sql.delta.actions.JobInfo> job, scala.Option<org.apache.spark.sql.delta.actions.NotebookInfo> notebook, scala.Option<java.lang.String> clusterId, scala.Option<java.lang.Object> readVersion, scala.Option<java.lang.String> isolationLevel, scala.Option<java.lang.Object> isBlindAppend, scala.Option<scala.collection.immutable.Map<java.lang.String, java.lang.String>> operationMetrics, scala.Option<java.lang.String> userMetadata)  { throw new RuntimeException(); }
  /** Whether this commit has blindly appended without caring about existing files */
  public  org.apache.spark.sql.delta.actions.SingleAction wrap ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.delta.actions.CommitInfo withTimestamp (long timestamp)  { throw new RuntimeException(); }
  public  long getTimestamp ()  { throw new RuntimeException(); }
  public  long getVersion ()  { throw new RuntimeException(); }
}
