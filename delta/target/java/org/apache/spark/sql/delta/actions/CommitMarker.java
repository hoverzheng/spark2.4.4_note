package org.apache.spark.sql.delta.actions;
/**
 * Interface for objects that represents the information for a commit. Commits can be referred to
 * using a version and timestamp. The timestamp of a commit comes from the remote storage
 * <code>lastModifiedTime</code>, and can be adjusted for clock skew. Hence we have the method <code>withTimestamp</code>.
 */
public  interface CommitMarker {
  /** Get the timestamp of the commit as millis after the epoch. */
  public  long getTimestamp ()  ;
  /** Get the version of the commit. */
  public  long getVersion ()  ;
  /** Return a copy object of this object with the given timestamp. */
  public  org.apache.spark.sql.delta.actions.CommitMarker withTimestamp (long timestamp)  ;
}
