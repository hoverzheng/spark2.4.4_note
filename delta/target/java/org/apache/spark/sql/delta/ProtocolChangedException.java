package org.apache.spark.sql.delta;
/**
 * Thrown when the protocol version has changed between the time of read
 * and the time of commit.
 */
public  class ProtocolChangedException extends org.apache.spark.sql.delta.DeltaConcurrentModificationException {
  public   ProtocolChangedException (scala.Option<org.apache.spark.sql.delta.actions.CommitInfo> conflictingCommit)  { throw new RuntimeException(); }
}
