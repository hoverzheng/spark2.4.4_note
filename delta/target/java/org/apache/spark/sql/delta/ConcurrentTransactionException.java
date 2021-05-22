package org.apache.spark.sql.delta;
/** Thrown when concurrent transaction both attempt to update the same idempotent transaction. */
public  class ConcurrentTransactionException extends org.apache.spark.sql.delta.DeltaConcurrentModificationException {
  public   ConcurrentTransactionException (scala.Option<org.apache.spark.sql.delta.actions.CommitInfo> conflictingCommit)  { throw new RuntimeException(); }
}
