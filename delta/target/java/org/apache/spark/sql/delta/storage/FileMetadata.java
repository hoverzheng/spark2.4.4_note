package org.apache.spark.sql.delta.storage;
/**
 * The file metadata to be stored in the cache.
 */
public  class FileMetadata implements scala.Product, scala.Serializable {
  static public abstract  R apply (T1 v1, T2 v2)  ;
  static public  java.lang.String toString ()  { throw new RuntimeException(); }
  public  long length ()  { throw new RuntimeException(); }
  public  long modificationTime ()  { throw new RuntimeException(); }
  // not preceding
  public   FileMetadata (long length, long modificationTime)  { throw new RuntimeException(); }
}
