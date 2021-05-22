package org.apache.spark.sql.delta;
/**
 * Thrown when the metadata of the Delta table has changed between the time of read
 * and the time of commit.
 */
public  class MetadataChangedException extends org.apache.spark.sql.delta.DeltaConcurrentModificationException {
  public   MetadataChangedException (scala.Option<org.apache.spark.sql.delta.actions.CommitInfo> conflictingCommit)  { throw new RuntimeException(); }
}
