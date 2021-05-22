package org.apache.spark.sql.delta;
/** The basic class for all Tahoe commit conflict exceptions. */
public abstract class DeltaConcurrentModificationException extends java.util.ConcurrentModificationException {
  public   DeltaConcurrentModificationException (java.lang.String message)  { throw new RuntimeException(); }
  public   DeltaConcurrentModificationException (java.lang.String baseMessage, scala.Option<org.apache.spark.sql.delta.actions.CommitInfo> conflictingCommit)  { throw new RuntimeException(); }
  /**
   * Type of the commit conflict.
   * @return (undocumented)
   */
  public  java.lang.String conflictType ()  { throw new RuntimeException(); }
}
