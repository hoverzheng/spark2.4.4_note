package org.apache.spark.sql.delta.util;
/**
 * Holds a directory in a partitioned collection of files as well as the partition values
 * in the form of a Row.  Before scanning, the files at <code>path</code> need to be enumerated.
 */
public  class PartitionPath {
  static public  org.apache.spark.sql.delta.util.PartitionPath apply (org.apache.spark.sql.catalyst.InternalRow values, java.lang.String path)  { throw new RuntimeException(); }
  static public  org.apache.spark.sql.delta.util.PartitionPath apply (org.apache.spark.sql.catalyst.InternalRow values, org.apache.hadoop.fs.Path path)  { throw new RuntimeException(); }
  public  org.apache.spark.sql.catalyst.InternalRow values ()  { throw new RuntimeException(); }
  public  java.lang.String pathStr ()  { throw new RuntimeException(); }
  public  org.apache.hadoop.fs.Path path ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.delta.util.PartitionPath withNewValues (org.apache.spark.sql.catalyst.InternalRow newValues)  { throw new RuntimeException(); }
  public  boolean equals (Object other)  { throw new RuntimeException(); }
  public  int hashCode ()  { throw new RuntimeException(); }
  public  java.lang.String toString ()  { throw new RuntimeException(); }
}
