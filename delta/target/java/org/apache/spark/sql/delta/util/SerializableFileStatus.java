package org.apache.spark.sql.delta.util;
/** A serializable variant of HDFS's FileStatus. */
public  class SerializableFileStatus implements scala.Product, scala.Serializable {
  static public  org.apache.spark.sql.delta.util.SerializableFileStatus fromStatus (org.apache.hadoop.fs.FileStatus status)  { throw new RuntimeException(); }
  static public  org.apache.spark.sql.delta.util.SerializableFileStatus EMPTY ()  { throw new RuntimeException(); }
  public  java.lang.String path ()  { throw new RuntimeException(); }
  public  long length ()  { throw new RuntimeException(); }
  public  boolean isDir ()  { throw new RuntimeException(); }
  public  long modificationTime ()  { throw new RuntimeException(); }
  // not preceding
  public   SerializableFileStatus (java.lang.String path, long length, boolean isDir, long modificationTime)  { throw new RuntimeException(); }
  public  org.apache.hadoop.fs.Path getPath ()  { throw new RuntimeException(); }
  public  long getLen ()  { throw new RuntimeException(); }
  public  long getModificationTime ()  { throw new RuntimeException(); }
  public  boolean isDirectory ()  { throw new RuntimeException(); }
  public  org.apache.hadoop.fs.FileStatus toFileStatus ()  { throw new RuntimeException(); }
  public  boolean equals (Object obj)  { throw new RuntimeException(); }
  public  int hashCode ()  { throw new RuntimeException(); }
}
