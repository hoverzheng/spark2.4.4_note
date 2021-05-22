package org.apache.spark.sql.delta;
/**
 * Used to query the current state of the log as well as modify it by adding
 * new atomic collections of actions.
 * <p>
 * Internally, this class implements an optimistic concurrency control
 * algorithm to handle multiple readers or writers. Any single read
 * is guaranteed to see a consistent snapshot of the table.
 */
public  class DeltaLog implements org.apache.spark.sql.delta.Checkpoints, org.apache.spark.sql.delta.MetadataCleanup, org.apache.spark.sql.delta.storage.LogStoreProvider, org.apache.spark.sql.delta.SnapshotManagement, org.apache.spark.sql.delta.ReadChecksum {
  /** Helper for creating a log when it stored at the root of the data. */
  static public  org.apache.spark.sql.delta.DeltaLog forTable (org.apache.spark.sql.SparkSession spark, java.lang.String dataPath)  { throw new RuntimeException(); }
  /** Helper for creating a log when it stored at the root of the data. */
  static public  org.apache.spark.sql.delta.DeltaLog forTable (org.apache.spark.sql.SparkSession spark, java.io.File dataPath)  { throw new RuntimeException(); }
  /** Helper for creating a log when it stored at the root of the data. */
  static public  org.apache.spark.sql.delta.DeltaLog forTable (org.apache.spark.sql.SparkSession spark, org.apache.hadoop.fs.Path dataPath)  { throw new RuntimeException(); }
  /** Helper for creating a log when it stored at the root of the data. */
  static public  org.apache.spark.sql.delta.DeltaLog forTable (org.apache.spark.sql.SparkSession spark, java.lang.String dataPath, org.apache.spark.util.Clock clock)  { throw new RuntimeException(); }
  /** Helper for creating a log when it stored at the root of the data. */
  static public  org.apache.spark.sql.delta.DeltaLog forTable (org.apache.spark.sql.SparkSession spark, java.io.File dataPath, org.apache.spark.util.Clock clock)  { throw new RuntimeException(); }
  /** Helper for creating a log when it stored at the root of the data. */
  static public  org.apache.spark.sql.delta.DeltaLog forTable (org.apache.spark.sql.SparkSession spark, org.apache.hadoop.fs.Path dataPath, org.apache.spark.util.Clock clock)  { throw new RuntimeException(); }
  /** Helper for creating a log for the table. */
  static public  org.apache.spark.sql.delta.DeltaLog forTable (org.apache.spark.sql.SparkSession spark, org.apache.spark.sql.catalyst.TableIdentifier tableName)  { throw new RuntimeException(); }
  /** Helper for creating a log for the table. */
  static public  org.apache.spark.sql.delta.DeltaLog forTable (org.apache.spark.sql.SparkSession spark, org.apache.spark.sql.catalyst.catalog.CatalogTable table)  { throw new RuntimeException(); }
  /** Helper for creating a log for the table. */
  static public  org.apache.spark.sql.delta.DeltaLog forTable (org.apache.spark.sql.SparkSession spark, org.apache.spark.sql.catalyst.TableIdentifier tableName, org.apache.spark.util.Clock clock)  { throw new RuntimeException(); }
  /** Helper for creating a log for the table. */
  static public  org.apache.spark.sql.delta.DeltaLog forTable (org.apache.spark.sql.SparkSession spark, org.apache.spark.sql.catalyst.catalog.CatalogTable table, org.apache.spark.util.Clock clock)  { throw new RuntimeException(); }
  /** Helper for creating a log for the table. */
  static public  org.apache.spark.sql.delta.DeltaLog forTable (org.apache.spark.sql.SparkSession spark, org.apache.spark.sql.delta.DeltaTableIdentifier deltaTable)  { throw new RuntimeException(); }
  static public  org.apache.spark.sql.delta.DeltaLog apply (org.apache.spark.sql.SparkSession spark, org.apache.hadoop.fs.Path rawPath, org.apache.spark.util.Clock clock)  { throw new RuntimeException(); }
  /** Invalidate the cached DeltaLog object for the given `dataPath`. */
  static public  void invalidateCache (org.apache.spark.sql.SparkSession spark, org.apache.hadoop.fs.Path dataPath)  { throw new RuntimeException(); }
  static public  void clearCache ()  { throw new RuntimeException(); }
  /**
   * Filters the given {@link Dataset} by the given <code>partitionFilters</code>, returning those that match.
   * @param files The active files in the DeltaLog state, which contains the partition value
   *              information
   * @param partitionFilters Filters on the partition columns
   * @param partitionColumnPrefixes The path to the <code>partitionValues</code> column, if it's nested
   * @param partitionSchema (undocumented)
   * @return (undocumented)
   */
  static public  org.apache.spark.sql.Dataset<org.apache.spark.sql.Row> filterFileList (org.apache.spark.sql.types.StructType partitionSchema, org.apache.spark.sql.Dataset<org.apache.spark.sql.Row> files, scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Expression> partitionFilters, scala.collection.Seq<java.lang.String> partitionColumnPrefixes)  { throw new RuntimeException(); }
  /**
   * Rewrite the given <code>partitionFilters</code> to be used for filtering partition values.
   * We need to explicitly resolve the partitioning columns here because the partition columns
   * are stored as keys of a Map type instead of attributes in the AddFile schema (below) and thus
   * cannot be resolved automatically.
   * <p>
   * @param partitionFilters Filters on the partition columns
   * @param partitionColumnPrefixes The path to the <code>partitionValues</code> column, if it's nested
   * @param partitionSchema (undocumented)
   * @param resolver (undocumented)
   * @return (undocumented)
   */
  static public  scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Expression> rewritePartitionFilters (org.apache.spark.sql.types.StructType partitionSchema, scala.Function2<java.lang.String, java.lang.String, java.lang.Object> resolver, scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Expression> partitionFilters, scala.collection.Seq<java.lang.String> partitionColumnPrefixes)  { throw new RuntimeException(); }
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
  protected  long lastUpdateTimestamp ()  { throw new RuntimeException(); }
  protected  org.apache.spark.sql.delta.Snapshot currentSnapshot ()  { throw new RuntimeException(); }
  public  java.lang.String logStoreClassConfKey ()  { throw new RuntimeException(); }
  public  java.lang.String defaultLogStoreClass ()  { throw new RuntimeException(); }
  public  org.apache.hadoop.fs.Path LAST_CHECKPOINT ()  { throw new RuntimeException(); }
  public  org.apache.hadoop.fs.Path logPath ()  { throw new RuntimeException(); }
  public  org.apache.hadoop.fs.Path dataPath ()  { throw new RuntimeException(); }
  public  org.apache.spark.util.Clock clock ()  { throw new RuntimeException(); }
    scala.concurrent.Future<scala.runtime.BoxedUnit> asyncUpdateTask ()  { throw new RuntimeException(); }
  protected  org.apache.spark.sql.SparkSession spark ()  { throw new RuntimeException(); }
  // not preceding
  public  org.apache.spark.sql.delta.storage.LogStore store ()  { throw new RuntimeException(); }
  // not preceding
    org.apache.hadoop.fs.FileSystem fs ()  { throw new RuntimeException(); }
  /** Use ReentrantLock to allow us to call `lockInterruptibly` */
  protected  java.util.concurrent.locks.ReentrantLock deltaLogLock ()  { throw new RuntimeException(); }
  // not preceding
  public  org.apache.spark.sql.delta.DeltaHistoryManager history ()  { throw new RuntimeException(); }
  /** Returns the checkpoint interval for this log. Not transactional. */
  public  int checkpointInterval ()  { throw new RuntimeException(); }
  /**
   * The max lineage length of a Snapshot before Delta forces to build a Snapshot from scratch.
   * Delta will build a Snapshot on top of the previous one if it doesn't see a checkpoint.
   * However, there is a race condition that when two writers are writing at the same time,
   * a writer may fail to pick up checkpoints written by another one, and the lineage will grow
   * and finally cause StackOverflowError. Hence we have to force to build a Snapshot from scratch
   * when the lineage length is too large to avoid hitting StackOverflowError.
   * @return (undocumented)
   */
  public  int maxSnapshotLineageLength ()  { throw new RuntimeException(); }
  /** How long to keep around logically deleted files before physically deleting them. */
    long tombstoneRetentionMillis ()  { throw new RuntimeException(); }
  protected  org.apache.spark.sql.delta.actions.Metadata metadata ()  { throw new RuntimeException(); }
  /**
   * Tombstones before this timestamp will be dropped from the state and the files can be
   * garbage collected.
   * @return (undocumented)
   */
  public  long minFileRetentionTimestamp ()  { throw new RuntimeException(); }
  /**
   * Checks whether this table only accepts appends. If so it will throw an error in operations that
   * can remove data such as DELETE/UPDATE/MERGE.
   */
  public  void assertRemovable ()  { throw new RuntimeException(); }
  /** The unique identifier for this table. */
  public  java.lang.String tableId ()  { throw new RuntimeException(); }
  /**
   * Combines the tableId with the path of the table to ensure uniqueness. Normally <code>tableId</code>
   * should be globally unique, but nothing stops users from copying a Delta table directly to
   * a separate location, where the transaction log is copied directly, causing the tableIds to
   * match. When users mutate the copied table, and then try to perform some checks joining the
   * two tables, optimizations that depend on <code>tableId</code> alone may not be correct. Hence we use a
   * composite id.
   * @return (undocumented)
   */
    scala.Tuple2<java.lang.String, org.apache.hadoop.fs.Path> compositeId ()  { throw new RuntimeException(); }
  /**
   * Run <code>body</code> inside <code>deltaLogLock</code> lock using <code>lockInterruptibly</code> so that the thread can be
   * interrupted when waiting for the lock.
   * @param body (undocumented)
   * @return (undocumented)
   */
  public <T extends java.lang.Object> T lockInterruptibly (scala.Function0<T> body)  { throw new RuntimeException(); }
  /**
   * Returns a new {@link OptimisticTransaction} that can be used to read the current state of the
   * log and then commit updates. The reads and updates will be checked for logical conflicts
   * with any concurrent writes to the log.
   * <p>
   * Note that all reads in a transaction must go through the returned transaction object, and not
   * directly to the {@link DeltaLog} otherwise they will not be checked for conflicts.
   * @return (undocumented)
   */
  public  org.apache.spark.sql.delta.OptimisticTransaction startTransaction ()  { throw new RuntimeException(); }
  /**
   * Execute a piece of code within a new {@link OptimisticTransaction}. Reads/write sets will
   * be recorded for this table, and all other tables will be read
   * at a snapshot that is pinned on the first access.
   * <p>
   * @note This uses thread-local variable to make the active transaction visible. So do not use
   *       multi-threaded code in the provided thunk.
   * @param thunk (undocumented)
   * @return (undocumented)
   */
  public <T extends java.lang.Object> T withNewTransaction (scala.Function1<org.apache.spark.sql.delta.OptimisticTransaction, T> thunk)  { throw new RuntimeException(); }
  /**
   * Upgrade the table's protocol version, by default to the maximum recognized reader and writer
   * versions in this DBR release.
   * @param newVersion (undocumented)
   */
  public  void upgradeProtocol (org.apache.spark.sql.delta.actions.Protocol newVersion)  { throw new RuntimeException(); }
  /**
   * Get all actions starting from "startVersion" (inclusive). If <code>startVersion</code> doesn't exist,
   * return an empty Iterator.
   * @param startVersion (undocumented)
   * @param failOnDataLoss (undocumented)
   * @return (undocumented)
   */
  public  scala.collection.Iterator<scala.Tuple2<java.lang.Object, scala.collection.Seq<org.apache.spark.sql.delta.actions.Action>>> getChanges (long startVersion, boolean failOnDataLoss)  { throw new RuntimeException(); }
  /**
   * Asserts that the client is up to date with the protocol and
   * allowed to read the table that is using the given <code>protocol</code>.
   * @param protocol (undocumented)
   */
  public  void protocolRead (org.apache.spark.sql.delta.actions.Protocol protocol)  { throw new RuntimeException(); }
  /**
   * Asserts that the client is up to date with the protocol and
   * allowed to write to the table that is using the given <code>protocol</code>.
   * @param protocol (undocumented)
   * @param logUpgradeMessage (undocumented)
   */
  public  void protocolWrite (org.apache.spark.sql.delta.actions.Protocol protocol, boolean logUpgradeMessage)  { throw new RuntimeException(); }
  /** Whether a Delta table exists at this directory. */
  public  boolean tableExists ()  { throw new RuntimeException(); }
  public  boolean isSameLogAs (org.apache.spark.sql.delta.DeltaLog otherLog)  { throw new RuntimeException(); }
  /** Creates the log directory if it does not exist. */
  public  void ensureLogDirectoryExist ()  { throw new RuntimeException(); }
  /**
   * Returns a {@link org.apache.spark.sql.DataFrame} containing the new files within the specified
   * version range.
   * @param snapshot (undocumented)
   * @param addFiles (undocumented)
   * @param isStreaming (undocumented)
   * @param actionTypeOpt (undocumented)
   * @return (undocumented)
   */
  public  org.apache.spark.sql.Dataset<org.apache.spark.sql.Row> createDataFrame (org.apache.spark.sql.delta.Snapshot snapshot, scala.collection.Seq<org.apache.spark.sql.delta.actions.AddFile> addFiles, boolean isStreaming, scala.Option<java.lang.String> actionTypeOpt)  { throw new RuntimeException(); }
  /**
   * Returns a {@link BaseRelation} that contains all of the data present
   * in the table. This relation will be continually updated
   * as files are added or removed from the table. However, new {@link BaseRelation}
   * must be requested in order to see changes to the schema.
   * @param partitionFilters (undocumented)
   * @param snapshotToUseOpt (undocumented)
   * @param isTimeTravelQuery (undocumented)
   * @return (undocumented)
   */
  public  org.apache.spark.sql.sources.BaseRelation createRelation (scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Expression> partitionFilters, scala.Option<org.apache.spark.sql.delta.Snapshot> snapshotToUseOpt, boolean isTimeTravelQuery)  { throw new RuntimeException(); }
}
