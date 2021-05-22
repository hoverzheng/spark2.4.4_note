package org.apache.spark.sql.delta.actions;
/**
 * Logical removal of a given file from the reservoir. Acts as a tombstone before a file is
 * deleted permanently.
 */
public  class RemoveFile implements org.apache.spark.sql.delta.actions.FileAction, scala.Product, scala.Serializable {
  static public abstract  R apply (T1 v1, T2 v2, T3 v3)  ;
  static public  java.lang.String toString ()  { throw new RuntimeException(); }
  public  java.net.URI pathAsUri ()  { throw new RuntimeException(); }
  public  java.lang.String path ()  { throw new RuntimeException(); }
  public  scala.Option<java.lang.Object> deletionTimestamp ()  { throw new RuntimeException(); }
  public  boolean dataChange ()  { throw new RuntimeException(); }
  // not preceding
  public   RemoveFile (java.lang.String path, scala.Option<java.lang.Object> deletionTimestamp, boolean dataChange)  { throw new RuntimeException(); }
  public  org.apache.spark.sql.delta.actions.SingleAction wrap ()  { throw new RuntimeException(); }
  public  long delTimestamp ()  { throw new RuntimeException(); }
}
