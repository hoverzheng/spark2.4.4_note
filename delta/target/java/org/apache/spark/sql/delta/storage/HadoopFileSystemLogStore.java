package org.apache.spark.sql.delta.storage;
/**
 * Default implementation of {@link LogStore} for Hadoop {@link FileSystem} implementations.
 */
public abstract class HadoopFileSystemLogStore implements org.apache.spark.sql.delta.storage.LogStore {
  public   HadoopFileSystemLogStore (org.apache.spark.SparkConf sparkConf, org.apache.hadoop.conf.Configuration hadoopConf)  { throw new RuntimeException(); }
  public   HadoopFileSystemLogStore (org.apache.spark.SparkContext sc)  { throw new RuntimeException(); }
  protected  org.apache.hadoop.fs.Path createTempPath (org.apache.hadoop.fs.Path path)  { throw new RuntimeException(); }
  protected  org.apache.hadoop.conf.Configuration getHadoopConfiguration ()  { throw new RuntimeException(); }
  public  void invalidateCache ()  { throw new RuntimeException(); }
  public  scala.collection.Iterator<org.apache.hadoop.fs.FileStatus> listFrom (org.apache.hadoop.fs.Path path)  { throw new RuntimeException(); }
  public  scala.collection.Seq<java.lang.String> read (org.apache.hadoop.fs.Path path)  { throw new RuntimeException(); }
  public  org.apache.spark.sql.delta.storage.ClosableIterator<java.lang.String> readAsIterator (org.apache.hadoop.fs.Path path)  { throw new RuntimeException(); }
  public  org.apache.hadoop.fs.Path resolvePathOnPhysicalStorage (org.apache.hadoop.fs.Path path)  { throw new RuntimeException(); }
  /**
   * An internal write implementation that uses FileSystem.rename().
   * <p>
   * This implementation should only be used for the underlying file systems that support atomic
   * renames, e.g., Azure is OK but HDFS is not.
   * @param path (undocumented)
   * @param actions (undocumented)
   * @param overwrite (undocumented)
   */
  protected  void writeWithRename (org.apache.hadoop.fs.Path path, scala.collection.Iterator<java.lang.String> actions, boolean overwrite)  { throw new RuntimeException(); }
}
