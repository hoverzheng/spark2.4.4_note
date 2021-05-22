package org.apache.spark.sql.delta.storage;
/**
 * LogStore implementation for Azure.
 * <p>
 * We assume the following from Azure's {@link FileSystem} implementations:
 * - Rename without overwrite is atomic.
 * - List-after-write is consistent.
 * <p>
 * Regarding file creation, this implementation:
 * - Uses atomic rename when overwrite is false; if the destination file exists or the rename
 *   fails, throws an exception.
 * - Uses create-with-overwrite when overwrite is true. This does not make the file atomically
 *   visible and therefore the caller must handle partial files.
 */
public  class AzureLogStore extends org.apache.spark.sql.delta.storage.HadoopFileSystemLogStore {
  public   AzureLogStore (org.apache.spark.SparkConf sparkConf, org.apache.hadoop.conf.Configuration hadoopConf)  { throw new RuntimeException(); }
  public  void invalidateCache ()  { throw new RuntimeException(); }
  public  boolean isPartialWriteVisible (org.apache.hadoop.fs.Path path)  { throw new RuntimeException(); }
  public  void write (org.apache.hadoop.fs.Path path, scala.collection.Iterator<java.lang.String> actions, boolean overwrite)  { throw new RuntimeException(); }
}
