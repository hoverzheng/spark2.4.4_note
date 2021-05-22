package org.apache.spark.sql.delta;
/**
 * Options for the Delta data source.
 */
public  class DeltaOptions implements org.apache.spark.sql.delta.DeltaWriteOptions, org.apache.spark.sql.delta.DeltaReadOptions, scala.Serializable {
  /** An option to overwrite only the data that matches predicates over partition columns. */
  static public  java.lang.String REPLACE_WHERE_OPTION ()  { throw new RuntimeException(); }
  /** An option to allow automatic schema merging during a write operation. */
  static public  java.lang.String MERGE_SCHEMA_OPTION ()  { throw new RuntimeException(); }
  /** An option to allow overwriting schema and partitioning during an overwrite write operation. */
  static public  java.lang.String OVERWRITE_SCHEMA_OPTION ()  { throw new RuntimeException(); }
  /** An option to specify user-defined metadata in commitInfo */
  static public  java.lang.String USER_METADATA_OPTION ()  { throw new RuntimeException(); }
  static public  java.lang.String MAX_FILES_PER_TRIGGER_OPTION ()  { throw new RuntimeException(); }
  static public  int MAX_FILES_PER_TRIGGER_OPTION_DEFAULT ()  { throw new RuntimeException(); }
  static public  java.lang.String MAX_BYTES_PER_TRIGGER_OPTION ()  { throw new RuntimeException(); }
  static public  java.lang.String EXCLUDE_REGEX_OPTION ()  { throw new RuntimeException(); }
  static public  java.lang.String IGNORE_FILE_DELETION_OPTION ()  { throw new RuntimeException(); }
  static public  java.lang.String IGNORE_CHANGES_OPTION ()  { throw new RuntimeException(); }
  static public  java.lang.String IGNORE_DELETES_OPTION ()  { throw new RuntimeException(); }
  static public  java.lang.String FAIL_ON_DATA_LOSS_OPTION ()  { throw new RuntimeException(); }
  static public  java.lang.String OPTIMIZE_WRITE_OPTION ()  { throw new RuntimeException(); }
  static public  java.lang.String DATA_CHANGE_OPTION ()  { throw new RuntimeException(); }
  static public  java.lang.String STARTING_VERSION_OPTION ()  { throw new RuntimeException(); }
  static public  java.lang.String STARTING_TIMESTAMP_OPTION ()  { throw new RuntimeException(); }
  static public  scala.collection.immutable.Set<java.lang.String> validOptionKeys ()  { throw new RuntimeException(); }
  /** Iterates over all user passed options and logs any that are not valid. */
  static public  void verifyOptions (org.apache.spark.sql.catalyst.util.CaseInsensitiveMap<java.lang.String> options)  { throw new RuntimeException(); }
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
  public  scala.Option<java.lang.Object> maxFilesPerTrigger ()  { throw new RuntimeException(); }
  public  scala.Option<java.lang.Object> maxBytesPerTrigger ()  { throw new RuntimeException(); }
  public  boolean ignoreFileDeletion ()  { throw new RuntimeException(); }
  public  boolean ignoreChanges ()  { throw new RuntimeException(); }
  public  boolean ignoreDeletes ()  { throw new RuntimeException(); }
  public  boolean failOnDataLoss ()  { throw new RuntimeException(); }
  public  scala.Option<scala.util.matching.Regex> excludeRegex ()  { throw new RuntimeException(); }
  public  scala.Option<org.apache.spark.sql.delta.DeltaStartingVersion> startingVersion ()  { throw new RuntimeException(); }
  public  scala.Option<java.lang.String> startingTimestamp ()  { throw new RuntimeException(); }
  public  scala.Option<java.lang.String> replaceWhere ()  { throw new RuntimeException(); }
  public  scala.Option<java.lang.String> userMetadata ()  { throw new RuntimeException(); }
  public  scala.Option<java.lang.Object> optimizeWrite ()  { throw new RuntimeException(); }
  protected  org.apache.spark.sql.catalyst.util.CaseInsensitiveMap<java.lang.String> options ()  { throw new RuntimeException(); }
  protected  org.apache.spark.sql.internal.SQLConf sqlConf ()  { throw new RuntimeException(); }
  // not preceding
  public   DeltaOptions (org.apache.spark.sql.catalyst.util.CaseInsensitiveMap<java.lang.String> options, org.apache.spark.sql.internal.SQLConf sqlConf)  { throw new RuntimeException(); }
  public   DeltaOptions (scala.collection.immutable.Map<java.lang.String, java.lang.String> options, org.apache.spark.sql.internal.SQLConf conf)  { throw new RuntimeException(); }
}
