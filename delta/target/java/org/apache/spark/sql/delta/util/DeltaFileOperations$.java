package org.apache.spark.sql.delta.util;
/**
 * Some utility methods on files, directories, and paths.
 */
public  class DeltaFileOperations$ implements org.apache.spark.sql.delta.metering.DeltaLogging {
  /**
   * Static reference to the singleton instance of this Scala object.
   */
  public static final DeltaFileOperations$ MODULE$ = null;
  public   DeltaFileOperations$ ()  { throw new RuntimeException(); }
  /**
   * Create an absolute path from <code>child</code> using the <code>basePath</code> if the child is a relative path.
   * Return <code>child</code> if it is an absolute path.
   * <p>
   * @param basePath Base path to prepend to <code>child</code> if child is a relative path.
   *                 Note: It is assumed that the basePath do not have any escaped characters and
   *                 is directly readable by Hadoop APIs.
   * @param child    Child path to append to <code>basePath</code> if child is a relative path.
   *                 Note: t is assumed that the child is escaped, that is, all special chars that
   *                 need escaping by URI standards are already escaped.
   * @return Absolute path without escaped chars that is directly readable by Hadoop APIs.
   */
  public  org.apache.hadoop.fs.Path absolutePath (java.lang.String basePath, java.lang.String child)  { throw new RuntimeException(); }
  /**
   * Given a path <code>child</code>:
   *   1. Returns <code>child</code> if the path is already relative
   *   2. Tries relativizing <code>child</code> with respect to <code>basePath</code>
   *      a) If the <code>child</code> doesn't live within the same base path, returns <code>child</code> as is
   *      b) If <code>child</code> lives in a different FileSystem, throws an exception
   * Note that <code>child</code> may physically be pointing to a path within <code>basePath</code>, but may logically
   * belong to a different FileSystem, e.g. DBFS mount points and direct S3 paths.
   * @param fs (undocumented)
   * @param basePath (undocumented)
   * @param child (undocumented)
   * @return (undocumented)
   */
  public  org.apache.hadoop.fs.Path tryRelativizePath (org.apache.hadoop.fs.FileSystem fs, org.apache.hadoop.fs.Path basePath, org.apache.hadoop.fs.Path child)  { throw new RuntimeException(); }
  /**
   * The default filter for hidden files. Files names beginning with _ or . are considered hidden.
   * @param fileName
   * @return true if the file is hidden
   */
  public  boolean defaultHiddenFileFilter (java.lang.String fileName)  { throw new RuntimeException(); }
  /**
   * Recursively lists all the files and directories for the given <code>subDirs</code> in a scalable manner.
   * <p>
   * @param spark The SparkSession
   * @param subDirs Absolute path of the subdirectories to list
   * @param hadoopConf The Hadoop Configuration to get a FileSystem instance
   * @param hiddenFileNameFilter A function that returns true when the file should be considered
   *                             hidden and excluded from results. Defaults to checking for prefixes
   *                             of "." or "_".
   * @param fileListingParallelism (undocumented)
   * @return (undocumented)
   */
  public  org.apache.spark.sql.Dataset<org.apache.spark.sql.delta.util.SerializableFileStatus> recursiveListDirs (org.apache.spark.sql.SparkSession spark, scala.collection.Seq<java.lang.String> subDirs, org.apache.spark.broadcast.Broadcast<org.apache.spark.util.SerializableConfiguration> hadoopConf, scala.Function1<java.lang.String, java.lang.Object> hiddenFileNameFilter, scala.Option<java.lang.Object> fileListingParallelism)  { throw new RuntimeException(); }
  /**
   * Lists the directory locally using LogStore without launching a spark job.
   * @param spark (undocumented)
   * @param dirs (undocumented)
   * @param recursive (undocumented)
   * @param fileFilter (undocumented)
   * @return (undocumented)
   */
  public  scala.collection.Seq<org.apache.spark.sql.delta.util.SerializableFileStatus> localListDirs (org.apache.spark.sql.SparkSession spark, scala.collection.Seq<java.lang.String> dirs, boolean recursive, scala.Function1<java.lang.String, java.lang.Object> fileFilter)  { throw new RuntimeException(); }
  /**
   * Tries deleting a file or directory non-recursively. If the file/folder doesn't exist,
   * that's fine, a separate operation may be deleting files/folders. If a directory is non-empty,
   * we shouldn't delete it. FileSystem implementations throw an <code>IOException</code> in those cases,
   * which we return as a "we failed to delete".
   * <p>
   * Listing on S3 is not consistent after deletes, therefore in case the <code>delete</code> returns <code>false</code>,
   * because the file didn't exist, then we still return <code>true</code>. Retries on S3 rate limits up to 3
   * times.
   * @param fs (undocumented)
   * @param path (undocumented)
   * @param tries (undocumented)
   * @return (undocumented)
   */
  public  boolean tryDeleteNonRecursive (org.apache.hadoop.fs.FileSystem fs, org.apache.hadoop.fs.Path path, int tries)  { throw new RuntimeException(); }
  /**
   * Returns all the levels of sub directories that <code>path</code> has with respect to <code>base</code>. For example:
   * getAllSubDirectories("/base", "/base/a/b/c") =&gt;
   *   (Iterator("/base/a", "/base/a/b"), "/base/a/b/c")
   * @param base (undocumented)
   * @param path (undocumented)
   * @return (undocumented)
   */
  public  scala.Tuple2<scala.collection.Iterator<java.lang.String>, java.lang.String> getAllSubDirectories (java.lang.String base, java.lang.String path)  { throw new RuntimeException(); }
  /** Register a task failure listener to delete a temp file in our best effort. */
  public  void registerTempFileDeletionTaskFailureListener (org.apache.hadoop.conf.Configuration conf, org.apache.hadoop.fs.Path tempPath)  { throw new RuntimeException(); }
  /**
   * Reads Parquet footers in multi-threaded manner.
   * If the config "spark.sql.files.ignoreCorruptFiles" is set to true, we will ignore the corrupted
   * files when reading footers.
   * @param conf (undocumented)
   * @param partFiles (undocumented)
   * @param ignoreCorruptFiles (undocumented)
   * @return (undocumented)
   */
  public  scala.collection.Seq<org.apache.parquet.hadoop.Footer> readParquetFootersInParallel (org.apache.hadoop.conf.Configuration conf, scala.collection.Seq<org.apache.hadoop.fs.FileStatus> partFiles, boolean ignoreCorruptFiles)  { throw new RuntimeException(); }
}
