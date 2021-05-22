package org.apache.spark.sql.delta;
/** Thrown when files are added that would have been read by the current transaction. */
public  class ConcurrentAppendException extends org.apache.spark.sql.delta.DeltaConcurrentModificationException {
  public   ConcurrentAppendException (scala.Option<org.apache.spark.sql.delta.actions.CommitInfo> conflictingCommit, java.lang.String partition, scala.Option<java.lang.String> customRetryMsg)  { throw new RuntimeException(); }
}
