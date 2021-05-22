package org.apache.spark.sql.delta;
/**
 * Thrown when a concurrent transaction has written data after the current transaction read the
 * table.
 */
public  class ConcurrentWriteException extends org.apache.spark.sql.delta.DeltaConcurrentModificationException {
  public   ConcurrentWriteException (scala.Option<org.apache.spark.sql.delta.actions.CommitInfo> conflictingCommit)  { throw new RuntimeException(); }
}
