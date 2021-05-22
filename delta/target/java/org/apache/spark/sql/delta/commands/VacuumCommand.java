package org.apache.spark.sql.delta.commands;
/**
 * Vacuums the table by clearing all untracked files and folders within this table.
 * First lists all the files and directories in the table, and gets the relative paths with
 * respect to the base of the table. Then it gets the list of all tracked files for this table,
 * which may or may not be within the table base path, and gets the relative paths of
 * all the tracked files with respect to the base of the table. Files outside of the table path
 * will be ignored. Then we take a diff of the files and delete directories that were already empty,
 * and all files that are within the table that are no longer tracked.
 */
public  class VacuumCommand {
  /**
   * Additional check on retention duration to prevent people from shooting themselves in the foot.
   * @param spark (undocumented)
   * @param retentionMs (undocumented)
   * @param configuredRetention (undocumented)
   */
  static protected  void checkRetentionPeriodSafety (org.apache.spark.sql.SparkSession spark, scala.Option<java.lang.Object> retentionMs, long configuredRetention)  { throw new RuntimeException(); }
  /**
   * Clears all untracked files and folders within this table. First lists all the files and
   * directories in the table, and gets the relative paths with respect to the base of the
   * table. Then it gets the list of all tracked files for this table, which may or may not
   * be within the table base path, and gets the relative paths of all the tracked files with
   * respect to the base of the table. Files outside of the table path will be ignored.
   * Then we take a diff of the files and delete directories that were already empty, and all files
   * that are within the table that are no longer tracked.
   * <p>
   * @param dryRun If set to true, no files will be deleted. Instead, we will list all files and
   *               directories that will be cleared.
   * @param retentionHours An optional parameter to override the default Delta tombstone retention
   *                       period
   * @return A Dataset containing the paths of the files/folders to delete in dryRun mode. Otherwise
   *         returns the base path of the table.
   * @param spark (undocumented)
   * @param deltaLog (undocumented)
   * @param clock (undocumented)
   */
  static public  org.apache.spark.sql.Dataset<org.apache.spark.sql.Row> gc (org.apache.spark.sql.SparkSession spark, org.apache.spark.sql.delta.DeltaLog deltaLog, boolean dryRun, scala.Option<java.lang.Object> retentionHours, org.apache.spark.util.Clock clock)  { throw new RuntimeException(); }
  static public  org.slf4j.Logger org$apache$spark$internal$Logging$$log_ ()  { throw new RuntimeException(); }
  static public  void org$apache$spark$internal$Logging$$log__$eq (org.slf4j.Logger x$1)  { throw new RuntimeException(); }
  static protected  java.lang.String logName ()  { throw new RuntimeException(); }
  static protected  org.slf4j.Logger log ()  { throw new RuntimeException(); }
  static protected  void logInfo (scala.Function0<java.lang.String> msg)  { throw new RuntimeException(); }
  static protected  void logDebug (scala.Function0<java.lang.String> msg)  { throw new RuntimeException(); }
  static protected  void logTrace (scala.Function0<java.lang.String> msg)  { throw new RuntimeException(); }
  static protected  void logWarning (scala.Function0<java.lang.String> msg)  { throw new RuntimeException(); }
  static protected  void logError (scala.Function0<java.lang.String> msg)  { throw new RuntimeException(); }
  static protected  void logInfo (scala.Function0<java.lang.String> msg, java.lang.Throwable throwable)  { throw new RuntimeException(); }
  static protected  void logDebug (scala.Function0<java.lang.String> msg, java.lang.Throwable throwable)  { throw new RuntimeException(); }
  static protected  void logTrace (scala.Function0<java.lang.String> msg, java.lang.Throwable throwable)  { throw new RuntimeException(); }
  static protected  void logWarning (scala.Function0<java.lang.String> msg, java.lang.Throwable throwable)  { throw new RuntimeException(); }
  static protected  void logError (scala.Function0<java.lang.String> msg, java.lang.Throwable throwable)  { throw new RuntimeException(); }
  static protected  boolean isTraceEnabled ()  { throw new RuntimeException(); }
  static protected  void initializeLogIfNecessary (boolean isInterpreter)  { throw new RuntimeException(); }
  static protected  boolean initializeLogIfNecessary (boolean isInterpreter, boolean silent)  { throw new RuntimeException(); }
  static protected  boolean initializeLogIfNecessary$default$2 ()  { throw new RuntimeException(); }
  static   void initializeForcefully (boolean isInterpreter, boolean silent)  { throw new RuntimeException(); }
  static public <T extends java.lang.Object> T withStatusCode (java.lang.String statusCode, java.lang.String defaultMessage, scala.collection.immutable.Map<java.lang.String, java.lang.Object> data, scala.Function0<T> body)  { throw new RuntimeException(); }
  static public <T extends java.lang.Object> scala.collection.immutable.Map<java.lang.String, java.lang.Object> withStatusCode$default$3 ()  { throw new RuntimeException(); }
  static public  void logConsole (java.lang.String line)  { throw new RuntimeException(); }
  static public  void recordUsage (com.databricks.spark.util.MetricDefinition metric, double quantity, scala.collection.immutable.Map<com.databricks.spark.util.TagDefinition, java.lang.String> additionalTags, java.lang.String blob, boolean forceSample, boolean trimBlob, boolean silent)  { throw new RuntimeException(); }
  static public  scala.collection.immutable.Map<com.databricks.spark.util.TagDefinition, java.lang.String> recordUsage$default$3 ()  { throw new RuntimeException(); }
  static public  java.lang.String recordUsage$default$4 ()  { throw new RuntimeException(); }
  static public  boolean recordUsage$default$5 ()  { throw new RuntimeException(); }
  static public  boolean recordUsage$default$6 ()  { throw new RuntimeException(); }
  static public  boolean recordUsage$default$7 ()  { throw new RuntimeException(); }
  static public  void recordEvent (com.databricks.spark.util.MetricDefinition metric, scala.collection.immutable.Map<com.databricks.spark.util.TagDefinition, java.lang.String> additionalTags, java.lang.String blob, boolean trimBlob)  { throw new RuntimeException(); }
  static public  scala.collection.immutable.Map<com.databricks.spark.util.TagDefinition, java.lang.String> recordEvent$default$2 ()  { throw new RuntimeException(); }
  static public  java.lang.String recordEvent$default$3 ()  { throw new RuntimeException(); }
  static public  boolean recordEvent$default$4 ()  { throw new RuntimeException(); }
  static public <S extends java.lang.Object> S recordOperation (com.databricks.spark.util.OpType opType, java.lang.String opTarget, scala.collection.immutable.Map<com.databricks.spark.util.TagDefinition, java.lang.String> extraTags, boolean isSynchronous, boolean alwaysRecordStats, boolean allowAuthTags, boolean killJvmIfStuck, com.databricks.spark.util.MetricDefinition outputMetric, boolean silent, scala.Function0<S> thunk)  { throw new RuntimeException(); }
  static public <S extends java.lang.Object> java.lang.String recordOperation$default$2 ()  { throw new RuntimeException(); }
  static public <S extends java.lang.Object> boolean recordOperation$default$4 ()  { throw new RuntimeException(); }
  static public <S extends java.lang.Object> boolean recordOperation$default$5 ()  { throw new RuntimeException(); }
  static public <S extends java.lang.Object> boolean recordOperation$default$6 ()  { throw new RuntimeException(); }
  static public <S extends java.lang.Object> boolean recordOperation$default$7 ()  { throw new RuntimeException(); }
  static public <S extends java.lang.Object> com.databricks.spark.util.MetricDefinition recordOperation$default$8 ()  { throw new RuntimeException(); }
  static public <S extends java.lang.Object> boolean recordOperation$default$9 ()  { throw new RuntimeException(); }
  static protected  void recordDeltaEvent (org.apache.spark.sql.delta.DeltaLog deltaLog, java.lang.String opType, scala.collection.immutable.Map<com.databricks.spark.util.TagDefinition, java.lang.String> tags, java.lang.Object data)  { throw new RuntimeException(); }
  static protected  scala.collection.immutable.Map<com.databricks.spark.util.TagDefinition, java.lang.String> recordDeltaEvent$default$3 ()  { throw new RuntimeException(); }
  static protected  java.lang.Object recordDeltaEvent$default$4 ()  { throw new RuntimeException(); }
  static protected <A extends java.lang.Object> A recordDeltaOperation (org.apache.spark.sql.delta.DeltaLog deltaLog, java.lang.String opType, scala.collection.immutable.Map<com.databricks.spark.util.TagDefinition, java.lang.String> tags, scala.Function0<A> thunk)  { throw new RuntimeException(); }
  static protected <A extends java.lang.Object> scala.collection.immutable.Map<com.databricks.spark.util.TagDefinition, java.lang.String> recordDeltaOperation$default$3 ()  { throw new RuntimeException(); }
  static protected  scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Expression> parsePartitionPredicates (org.apache.spark.sql.SparkSession spark, java.lang.String predicate)  { throw new RuntimeException(); }
  static protected  void verifyPartitionPredicates (org.apache.spark.sql.SparkSession spark, scala.collection.Seq<java.lang.String> partitionColumns, scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Expression> predicates)  { throw new RuntimeException(); }
  static protected  scala.collection.immutable.Map<java.lang.String, org.apache.spark.sql.delta.actions.AddFile> generateCandidateFileMap (org.apache.hadoop.fs.Path basePath, scala.collection.Seq<org.apache.spark.sql.delta.actions.AddFile> candidateFiles)  { throw new RuntimeException(); }
  static protected  scala.collection.Seq<org.apache.spark.sql.delta.actions.RemoveFile> removeFilesFromPaths (org.apache.spark.sql.delta.DeltaLog deltaLog, scala.collection.immutable.Map<java.lang.String, org.apache.spark.sql.delta.actions.AddFile> nameToAddFileMap, scala.collection.Seq<java.lang.String> filesToRewrite, long operationTimestamp)  { throw new RuntimeException(); }
  static protected  org.apache.spark.sql.execution.datasources.HadoopFsRelation buildBaseRelation (org.apache.spark.sql.SparkSession spark, org.apache.spark.sql.delta.OptimisticTransaction txn, java.lang.String actionType, org.apache.hadoop.fs.Path rootPath, scala.collection.Seq<java.lang.String> inputLeafFiles, scala.collection.immutable.Map<java.lang.String, org.apache.spark.sql.delta.actions.AddFile> nameToAddFileMap)  { throw new RuntimeException(); }
  static protected  org.apache.spark.sql.delta.actions.AddFile getTouchedFile (org.apache.hadoop.fs.Path basePath, java.lang.String filePath, scala.collection.immutable.Map<java.lang.String, org.apache.spark.sql.delta.actions.AddFile> nameToAddFileMap)  { throw new RuntimeException(); }
  static protected  org.apache.spark.sql.catalyst.plans.logical.LogicalPlan resolveIdentifier (org.apache.spark.sql.catalyst.analysis.Analyzer analyzer, org.apache.spark.sql.catalyst.TableIdentifier identifier)  { throw new RuntimeException(); }
  static public  boolean isCatalogTable (org.apache.spark.sql.catalyst.analysis.Analyzer analyzer, org.apache.spark.sql.catalyst.TableIdentifier tableIdent)  { throw new RuntimeException(); }
  static protected  boolean isPathIdentifier (org.apache.spark.sql.catalyst.TableIdentifier tableIdent)  { throw new RuntimeException(); }
  static protected  void updateAndCheckpoint (org.apache.spark.sql.SparkSession spark, org.apache.spark.sql.delta.DeltaLog deltaLog, int commitSize, long attemptVersion)  { throw new RuntimeException(); }
  static protected  long commitLarge (org.apache.spark.sql.SparkSession spark, org.apache.spark.sql.delta.OptimisticTransaction txn, scala.collection.Iterator<org.apache.spark.sql.delta.actions.Action> actions, org.apache.spark.sql.delta.DeltaOperations.Operation op, scala.collection.immutable.Map<java.lang.String, java.lang.String> context, scala.collection.immutable.Map<java.lang.String, java.lang.String> metrics)  { throw new RuntimeException(); }
  static protected  java.lang.String relativize (org.apache.hadoop.fs.Path path, org.apache.hadoop.fs.FileSystem fs, org.apache.hadoop.fs.Path reservoirBase, boolean isDir)  { throw new RuntimeException(); }
  static protected  scala.collection.Iterator<java.lang.String> getAllSubdirs (java.lang.String base, java.lang.String file, org.apache.hadoop.fs.FileSystem fs)  { throw new RuntimeException(); }
  static protected  long delete (org.apache.spark.sql.Dataset<java.lang.String> diff, org.apache.spark.sql.SparkSession spark, java.lang.String basePath, org.apache.spark.broadcast.Broadcast<org.apache.spark.util.SerializableConfiguration> hadoopConf, boolean parallel)  { throw new RuntimeException(); }
  static protected  org.apache.hadoop.fs.Path stringToPath (java.lang.String path)  { throw new RuntimeException(); }
  static protected  java.lang.String pathToString (org.apache.hadoop.fs.Path path)  { throw new RuntimeException(); }
  static protected  scala.collection.Seq<java.lang.String> allValidFiles (java.lang.String file, boolean isBloomFiltered)  { throw new RuntimeException(); }
}
