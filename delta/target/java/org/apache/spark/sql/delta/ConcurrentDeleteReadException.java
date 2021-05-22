package org.apache.spark.sql.delta;
/** Thrown when the current transaction reads data that was deleted by a concurrent transaction. */
public  class ConcurrentDeleteReadException extends org.apache.spark.sql.delta.DeltaConcurrentModificationException {
  public   ConcurrentDeleteReadException (scala.Option<org.apache.spark.sql.delta.actions.CommitInfo> conflictingCommit, java.lang.String file)  { throw new RuntimeException(); }
}
