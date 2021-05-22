package org.apache.spark.sql.delta.storage;
/**
 * Default {@link LogStore} implementation (should be used for testing only!).
 * <p>
 * Production users should specify the appropriate {@link [LogStore} implementation in Spark properties.
 * <p>
 * We assume the following from {@link org.apache.hadoop.fs.FileSystem} implementations:
 * - Rename without overwrite is atomic.
 * - List-after-write is consistent.
 * <p>
 * Regarding file creation, this implementation:
 * - Uses atomic rename when overwrite is false; if the destination file exists or the rename
 *   fails, throws an exception.
 * - Uses create-with-overwrite when overwrite is true. This does not make the file atomically
 *   visible and therefore the caller must handle partial files.
 */
public  class LocalLogStore extends org.apache.spark.sql.delta.storage.HadoopFileSystemLogStore {
  public   LocalLogStore (org.apache.spark.SparkConf sparkConf, org.apache.hadoop.conf.Configuration hadoopConf)  { throw new RuntimeException(); }
  public  void invalidateCache ()  { throw new RuntimeException(); }
  public  boolean isPartialWriteVisible (org.apache.hadoop.fs.Path path)  { throw new RuntimeException(); }
  /**
   * This write implementation needs to wraps <code>writeWithRename</code> with <code>synchronized</code> as the rename()
   * for {@link org.apache.hadoop.fs.RawLocalFileSystem} doesn't throw an exception when the target file
   * exists. Hence we must make sure <code>exists + rename</code> in <code>writeWithRename</code> is atomic in our tests.
   * @param path (undocumented)
   * @param actions (undocumented)
   * @param overwrite (undocumented)
   */
  public  void write (org.apache.hadoop.fs.Path path, scala.collection.Iterator<java.lang.String> actions, boolean overwrite)  { throw new RuntimeException(); }
}
