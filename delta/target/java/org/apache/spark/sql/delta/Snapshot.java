package org.apache.spark.sql.delta;
/**
 * An immutable snapshot of the state of the log at some delta version. Internally
 * this class manages the replay of actions stored in checkpoint or delta files.
 * <p>
 * After resolving any new actions, it caches the result and collects the
 * following basic information to the driver:
 *  - Protocol Version
 *  - Metadata
 *  - Transaction state
 * <p>
 * param:  timestamp The timestamp of the latest commit in milliseconds. Can also be set to -1 if the
 *                  timestamp of the commit is unknown or the table has not been initialized, i.e.
 *                  <code>version = -1</code>.
 */
public  class Snapshot implements org.apache.spark.sql.delta.util.StateCache, org.apache.spark.sql.delta.PartitionFiltering, org.apache.spark.sql.delta.DeltaFileFormat, org.apache.spark.sql.delta.metering.DeltaLogging {
  /**
   * Metrics and metadata computed around the Delta table
   * param:  protocol The protocol version of the Delta table
   * param:  metadata The metadata of the table
   * param:  setTransactions The streaming queries writing to this table
   * param:  sizeInBytes The total size of the table (of active files, not including tombstones)
   * param:  numOfFiles The number of files in this table
   * param:  numOfMetadata The number of metadata actions in the state. Should be 1
   * param:  numOfProtocol The number of protocol actions in the state. Should be 1
   * param:  numOfRemoves The number of tombstones in the state
   * param:  numOfSetTransactions Number of streams writing to this table
   */
  static public  class State implements scala.Product, scala.Serializable {
    public  org.apache.spark.sql.delta.actions.Protocol protocol ()  { throw new RuntimeException(); }
    public  org.apache.spark.sql.delta.actions.Metadata metadata ()  { throw new RuntimeException(); }
    public  scala.collection.Seq<org.apache.spark.sql.delta.actions.SetTransaction> setTransactions ()  { throw new RuntimeException(); }
    public  long sizeInBytes ()  { throw new RuntimeException(); }
    public  long numOfFiles ()  { throw new RuntimeException(); }
    public  long numOfMetadata ()  { throw new RuntimeException(); }
    public  long numOfProtocol ()  { throw new RuntimeException(); }
    public  long numOfRemoves ()  { throw new RuntimeException(); }
    public  long numOfSetTransactions ()  { throw new RuntimeException(); }
    // not preceding
    public   State (org.apache.spark.sql.delta.actions.Protocol protocol, org.apache.spark.sql.delta.actions.Metadata metadata, scala.collection.Seq<org.apache.spark.sql.delta.actions.SetTransaction> setTransactions, long sizeInBytes, long numOfFiles, long numOfMetadata, long numOfProtocol, long numOfRemoves, long numOfSetTransactions)  { throw new RuntimeException(); }
  }
  static public  class State$ extends scala.runtime.AbstractFunction9<org.apache.spark.sql.delta.actions.Protocol, org.apache.spark.sql.delta.actions.Metadata, scala.collection.Seq<org.apache.spark.sql.delta.actions.SetTransaction>, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, org.apache.spark.sql.delta.Snapshot.State> implements scala.Serializable {
    /**
     * Static reference to the singleton instance of this Scala object.
     */
    public static final State$ MODULE$ = null;
    public   State$ ()  { throw new RuntimeException(); }
  }
  /** Canonicalize the paths for Actions */
  static   java.lang.String canonicalizePath (java.lang.String path, org.apache.hadoop.conf.Configuration hadoopConf)  { throw new RuntimeException(); }
  static public  org.slf4j.Logger org$apache$spark$internal$Logging$$log_ ()  { throw new RuntimeException(); }
  static public  void org$apache$spark$internal$Logging$$log__$eq (org.slf4j.Logger x$1)  { throw new RuntimeException(); }
  static protected  java.lang.String logName ()  { throw new RuntimeException(); }
  static protected  org.slf4j.Logger log ()  { throw new RuntimeException(); }
  static protected  void logDebug (scala.Function0<java.lang.String> msg)  { throw new RuntimeException(); }
  static protected  void logTrace (scala.Function0<java.lang.String> msg)  { throw new RuntimeException(); }
  static protected  void logDebug (scala.Function0<java.lang.String> msg, java.lang.Throwable throwable)  { throw new RuntimeException(); }
  static protected  void logTrace (scala.Function0<java.lang.String> msg, java.lang.Throwable throwable)  { throw new RuntimeException(); }
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
  public  org.apache.hadoop.fs.Path path ()  { throw new RuntimeException(); }
  public  long version ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.delta.LogSegment logSegment ()  { throw new RuntimeException(); }
  public  long minFileRetentionTimestamp ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.delta.DeltaLog deltaLog ()  { throw new RuntimeException(); }
  public  long timestamp ()  { throw new RuntimeException(); }
  public  scala.Option<org.apache.spark.sql.delta.VersionChecksum> checksumOpt ()  { throw new RuntimeException(); }
  // not preceding
  public   Snapshot (org.apache.hadoop.fs.Path path, long version, org.apache.spark.sql.delta.LogSegment logSegment, long minFileRetentionTimestamp, org.apache.spark.sql.delta.DeltaLog deltaLog, long timestamp, scala.Option<org.apache.spark.sql.delta.VersionChecksum> checksumOpt)  { throw new RuntimeException(); }
  protected  org.apache.spark.sql.SparkSession spark ()  { throw new RuntimeException(); }
  protected  int getNumPartitions ()  { throw new RuntimeException(); }
  /** Performs validations during initialization */
  protected  void init ()  { throw new RuntimeException(); }
  public  java.lang.String redactedPath ()  { throw new RuntimeException(); }
  /** The current set of actions in this {@link Snapshot}. */
  public  org.apache.spark.sql.Dataset<org.apache.spark.sql.delta.actions.SingleAction> state ()  { throw new RuntimeException(); }
  /** Helper method to log missing actions when state reconstruction checks are not enabled */
  protected  void logMissingActionWarning (java.lang.String action)  { throw new RuntimeException(); }
  // not preceding
  protected  org.apache.spark.sql.delta.Snapshot.State computedState ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.delta.actions.Protocol protocol ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.delta.actions.Metadata metadata ()  { throw new RuntimeException(); }
  public  scala.collection.Seq<org.apache.spark.sql.delta.actions.SetTransaction> setTransactions ()  { throw new RuntimeException(); }
  public  long sizeInBytes ()  { throw new RuntimeException(); }
  public  long numOfFiles ()  { throw new RuntimeException(); }
  public  long numOfMetadata ()  { throw new RuntimeException(); }
  public  long numOfProtocol ()  { throw new RuntimeException(); }
  public  long numOfRemoves ()  { throw new RuntimeException(); }
  public  long numOfSetTransactions ()  { throw new RuntimeException(); }
  // not preceding
  public  scala.collection.immutable.Map<java.lang.String, java.lang.Object> transactions ()  { throw new RuntimeException(); }
  /** All of the files present in this {@link Snapshot}. */
  public  org.apache.spark.sql.Dataset<org.apache.spark.sql.delta.actions.AddFile> allFiles ()  { throw new RuntimeException(); }
  /** All unexpired tombstones. */
  public  org.apache.spark.sql.Dataset<org.apache.spark.sql.delta.actions.RemoveFile> tombstones ()  { throw new RuntimeException(); }
  /** Returns the schema of the table. */
  public  org.apache.spark.sql.types.StructType schema ()  { throw new RuntimeException(); }
  /** Returns the data schema of the table, the schema of the columns written out to file. */
  public  org.apache.spark.sql.types.StructType dataSchema ()  { throw new RuntimeException(); }
  // not preceding
  public  int numIndexedCols ()  { throw new RuntimeException(); }
  /** Return the set of properties of the table. */
  public  scala.collection.mutable.HashMap<java.lang.String, java.lang.String> getProperties ()  { throw new RuntimeException(); }
  // not preceding
  protected  scala.Option<org.apache.spark.sql.delta.DeltaLogFileIndex> deltaFileIndexOpt ()  { throw new RuntimeException(); }
  // not preceding
  protected  scala.Option<org.apache.spark.sql.delta.DeltaLogFileIndex> checkpointFileIndexOpt ()  { throw new RuntimeException(); }
  // not preceding
  protected  scala.collection.Seq<org.apache.spark.sql.delta.DeltaLogFileIndex> fileIndices ()  { throw new RuntimeException(); }
  /** Creates a LogicalRelation with the given schema from a DeltaLogFileIndex. */
  protected  org.apache.spark.sql.execution.datasources.LogicalRelation indexToRelation (org.apache.spark.sql.delta.DeltaLogFileIndex index, org.apache.spark.sql.types.StructType schema)  { throw new RuntimeException(); }
  /**
   * Loads the file indices into a Dataset that can be used for LogReplay.
   * @return (undocumented)
   */
  protected  org.apache.spark.sql.Dataset<org.apache.spark.sql.delta.actions.SingleAction> loadActions ()  { throw new RuntimeException(); }
  protected  org.apache.spark.sql.Dataset<org.apache.spark.sql.delta.actions.SingleAction> emptyActions ()  { throw new RuntimeException(); }
  public  void logInfo (scala.Function0<java.lang.String> msg)  { throw new RuntimeException(); }
  public  void logWarning (scala.Function0<java.lang.String> msg)  { throw new RuntimeException(); }
  public  void logWarning (scala.Function0<java.lang.String> msg, java.lang.Throwable throwable)  { throw new RuntimeException(); }
  public  void logError (scala.Function0<java.lang.String> msg)  { throw new RuntimeException(); }
  public  void logError (scala.Function0<java.lang.String> msg, java.lang.Throwable throwable)  { throw new RuntimeException(); }
  public  java.lang.String toString ()  { throw new RuntimeException(); }
}
