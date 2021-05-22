package org.apache.spark.sql.delta.storage;
/**
 * Single Spark-driver/JVM LogStore implementation for S3.
 * <p>
 * We assume the following from S3's {@link FileSystem} implementations:
 * - File writing on S3 is all-or-nothing, whether overwrite or not.
 * - List-after-write can be inconsistent.
 * <p>
 * Regarding file creation, this implementation:
 * - Opens a stream to write to S3 (regardless of the overwrite option).
 * - Failures during stream write may leak resources, but may never result in partial writes.
 * <p>
 * Regarding directory listing, this implementation:
 * - returns a list by merging the files listed from S3 and recently-written files from the cache.
 */
public  class S3SingleDriverLogStore extends org.apache.spark.sql.delta.storage.HadoopFileSystemLogStore {
  public   S3SingleDriverLogStore (org.apache.spark.SparkConf sparkConf, org.apache.hadoop.conf.Configuration hadoopConf)  { throw new RuntimeException(); }
  /**
   * List files starting from <code>resolvedPath</code> (inclusive) in the same directory.
   * @param path (undocumented)
   * @return (undocumented)
   */
  public  scala.collection.Iterator<org.apache.hadoop.fs.FileStatus> listFrom (org.apache.hadoop.fs.Path path)  { throw new RuntimeException(); }
  public  void write (org.apache.hadoop.fs.Path path, scala.collection.Iterator<java.lang.String> actions, boolean overwrite)  { throw new RuntimeException(); }
  public  boolean isPartialWriteVisible (org.apache.hadoop.fs.Path path)  { throw new RuntimeException(); }
  public  void invalidateCache ()  { throw new RuntimeException(); }
}
