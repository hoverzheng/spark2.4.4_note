package org.apache.spark.sql.delta.hooks;
public  interface GenerateSymlinkManifestImpl extends org.apache.spark.sql.delta.hooks.PostCommitHook, org.apache.spark.sql.delta.metering.DeltaLogging, scala.Serializable {
  public  class SymlinkManifestStats implements scala.Product, scala.Serializable {
    static public abstract  R apply (T1 v1, T2 v2, T3 v3)  ;
    static public  java.lang.String toString ()  { throw new RuntimeException(); }
    public  int filesWritten ()  { throw new RuntimeException(); }
    public  int filesDeleted ()  { throw new RuntimeException(); }
    public  boolean partitioned ()  { throw new RuntimeException(); }
    // not preceding
    public   SymlinkManifestStats (int filesWritten, int filesDeleted, boolean partitioned)  { throw new RuntimeException(); }
  }
  public  class SymlinkManifestStats$ extends scala.runtime.AbstractFunction3<java.lang.Object, java.lang.Object, java.lang.Object, org.apache.spark.sql.delta.hooks.GenerateSymlinkManifestImpl.SymlinkManifestStats> implements scala.Serializable {
    /**
     * Static reference to the singleton instance of this Scala object.
     */
    public static final SymlinkManifestStats$ MODULE$ = null;
    public   SymlinkManifestStats$ ()  { throw new RuntimeException(); }
  }
  public  java.lang.String CONFIG_NAME_ROOT ()  ;
  public  java.lang.String FULL_MANIFEST_OP_TYPE ()  ;
  public  java.lang.String INCREMENTAL_MANIFEST_OP_TYPE ()  ;
  public  java.lang.String MANIFEST_LOCATION ()  ;
  public  java.lang.String OP_TYPE_ROOT ()  ;
  /**
   * Delete manifest files in the given paths.
   * <p>
   * @param manifestRootDirPath root directory of the manifest files (e.g., tablePath/_manifest/)
   * @param partitionRelativePathsToDelete partitions to delete manifest files from
   *                                       (e.g., part1=1/part2=2/)
   * @param hadoopConf Hadoop configuration to use
   */
  public  void deleteManifestFiles (java.lang.String manifestRootDirPath, scala.collection.Iterable<java.lang.String> partitionRelativePathsToDelete, org.apache.spark.util.SerializableConfiguration hadoopConf)  ;
  /**
   * Generate manifest files for all the partitions in the table. Note, this will ensure that
   * that stale and unnecessary files will be vacuumed.
   * @param spark (undocumented)
   * @param deltaLog (undocumented)
   */
  public  void generateFullManifest (org.apache.spark.sql.SparkSession spark, org.apache.spark.sql.delta.DeltaLog deltaLog)  ;
  /**
   * Generate manifest files incrementally, that is, only for the table partitions touched by the
   * given actions.
   * @param spark (undocumented)
   * @param deltaLog (undocumented)
   * @param txnReadSnapshot (undocumented)
   * @param actions (undocumented)
   */
  public  void generateIncrementalManifest (org.apache.spark.sql.SparkSession spark, org.apache.spark.sql.delta.DeltaLog deltaLog, org.apache.spark.sql.delta.Snapshot txnReadSnapshot, scala.collection.Seq<org.apache.spark.sql.delta.actions.Action> actions)  ;
  /** Expression that given partition columns builds a path string like: col1=val/col2=val/... */
  public  org.apache.spark.sql.catalyst.expressions.Expression generatePartitionPathExpression (scala.collection.Seq<scala.Tuple2<java.lang.String, org.apache.spark.sql.catalyst.expressions.Attribute>> partitionColNameToAttrib, java.lang.String timeZoneId)  ;
  public  void handleError (java.lang.Throwable error, long version)  ;
  public  java.lang.String name ()  ;
  public  void recordManifestGeneration (org.apache.spark.sql.delta.DeltaLog deltaLog, boolean full, scala.Function0<scala.runtime.BoxedUnit> thunk)  ;
  public  void run (org.apache.spark.sql.SparkSession spark, org.apache.spark.sql.delta.OptimisticTransactionImpl txn, scala.collection.Seq<org.apache.spark.sql.delta.actions.Action> committedActions)  ;
  /**
   * Append a column <code>relativePartitionDir</code> to the given Dataset which has <code>partitionValues</code> as
   * one of the columns. <code>partitionValues</code> is a map-type column that contains values of the
   * given <code>partitionCols</code>.
   * @param spark (undocumented)
   * @param partitionCols (undocumented)
   * @param datasetWithPartitionValues (undocumented)
   * @return (undocumented)
   */
  public  org.apache.spark.sql.Dataset<org.apache.spark.sql.Row> withRelativePartitionDir (org.apache.spark.sql.SparkSession spark, scala.collection.Seq<java.lang.String> partitionCols, org.apache.spark.sql.Dataset<?> datasetWithPartitionValues)  ;
  /**
   * Write the manifest files and return the partition relative paths of the manifests written.
   * <p>
   * @param deltaLogDataPath     path of the table data (e.g., tablePath which has _delta_log in it)
   * @param manifestRootDirPath  root directory of the manifest files (e.g., tablePath/_manifest/)
   * @param fileNamesForManifest relative paths or file names of data files for being written into
   *                             the manifest (e.g., partition=1/xyz.parquet)
   * @param partitionCols        Table partition columns
   * @param hadoopConf           Hadoop configuration to use
   * @return Set of partition relative paths of the written manifest files (e.g., part1=1/part2=2)
   */
  public  scala.collection.immutable.Set<java.lang.String> writeManifestFiles (org.apache.hadoop.fs.Path deltaLogDataPath, java.lang.String manifestRootDirPath, org.apache.spark.sql.Dataset<org.apache.spark.sql.delta.actions.AddFile> fileNamesForManifest, scala.collection.Seq<java.lang.String> partitionCols, org.apache.spark.util.SerializableConfiguration hadoopConf)  ;
}
