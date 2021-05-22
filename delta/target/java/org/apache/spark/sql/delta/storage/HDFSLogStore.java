package org.apache.spark.sql.delta.storage;
/**
 * The {@link LogStore} implementation for HDFS, which uses Hadoop {@link FileContext} API's to
 * provide the necessary atomic and durability guarantees:
 * <p>
 * 1. Atomic visibility of files: <code>FileContext.rename</code> is used write files which is atomic for HDFS.
 * <p>
 * 2. Consistent file listing: HDFS file listing is consistent.
 */
public  class HDFSLogStore extends org.apache.spark.sql.delta.storage.HadoopFileSystemLogStore implements org.apache.spark.internal.Logging {
  public   HDFSLogStore (org.apache.spark.SparkConf sparkConf, org.apache.hadoop.conf.Configuration defaultHadoopConf)  { throw new RuntimeException(); }
  protected  org.apache.hadoop.fs.FileContext getFileContext (org.apache.hadoop.fs.Path path)  { throw new RuntimeException(); }
  public  boolean isPartialWriteVisible (org.apache.hadoop.fs.Path path)  { throw new RuntimeException(); }
  public  java.lang.String noAbstractFileSystemExceptionMessage ()  { throw new RuntimeException(); }
  public  void write (org.apache.hadoop.fs.Path path, scala.collection.Iterator<java.lang.String> actions, boolean overwrite)  { throw new RuntimeException(); }
}
