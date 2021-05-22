package org.apache.spark.sql.delta.actions;
/**
 * Replays a history of action, resolving them to produce the current state
 * of the table. The protocol for resolution is as follows:
 *  - The most recent {@link AddFile} and accompanying metadata for any <code>path</code> wins.
 *  - {@link RemoveFile} deletes a corresponding {@link AddFile} and is retained as a
 *    tombstone until <code>minFileRetentionTimestamp</code> has passed.
 *  - The most recent version for any <code>appId</code> in a {@link SetTransaction} wins.
 *  - The most recent {@link Metadata} wins.
 *  - The most recent {@link Protocol} version wins.
 *  - For each path, this class should always output only one {@link FileAction} (either {@link AddFile} or
 *    {@link RemoveFile})
 * <p>
 * This class is not thread safe.
 */
public  class InMemoryLogReplay {
  public   InMemoryLogReplay (long minFileRetentionTimestamp)  { throw new RuntimeException(); }
  public  scala.collection.mutable.HashMap<java.net.URI, org.apache.spark.sql.delta.actions.AddFile> activeFiles ()  { throw new RuntimeException(); }
  public  void append (long version, scala.collection.Iterator<org.apache.spark.sql.delta.actions.Action> actions)  { throw new RuntimeException(); }
  /** Returns the current state of the Table as an iterator of actions. */
  public  scala.collection.Iterator<org.apache.spark.sql.delta.actions.Action> checkpoint ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.delta.actions.Metadata currentMetaData ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.delta.actions.Protocol currentProtocolVersion ()  { throw new RuntimeException(); }
  public  long currentVersion ()  { throw new RuntimeException(); }
  public  scala.collection.mutable.HashMap<java.lang.String, org.apache.spark.sql.delta.actions.SetTransaction> transactions ()  { throw new RuntimeException(); }
}
