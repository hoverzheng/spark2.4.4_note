package org.apache.spark.sql.delta;
/**
 * Contains list of reservoir configs and validation checks.
 */
public  class DeltaConfigs {
  /**
   * Convert a string to {@link CalendarInterval}. This method is case-insensitive and will throw
   * {@link IllegalArgumentException} when the input string is not a valid interval.
   * <p>
   * TODO Remove this method and use <code>CalendarInterval.fromCaseInsensitiveString</code> instead when
   * upgrading Spark. This is a fork version of <code>CalendarInterval.fromCaseInsensitiveString</code> which
   * will be available in the next Spark release (See SPARK-27735).
   * <p>
   * @throws IllegalArgumentException if the string is not a valid internal.
   * @param s (undocumented)
   * @return (undocumented)
   */
  static public  org.apache.spark.unsafe.types.CalendarInterval parseCalendarInterval (java.lang.String s)  { throw new RuntimeException(); }
  /**
   * A global default value set as a SQLConf will overwrite the default value of a DeltaConfig.
   * For example, user can run:
   *   set spark.databricks.delta.properties.defaults.randomPrefixLength = 5
   * This setting will be populated to a Delta table during its creation time and overwrites
   * the default value of delta.randomPrefixLength.
   * <p>
   * We accept these SQLConfs as strings and only perform validation in DeltaConfig. All the
   * DeltaConfigs set in SQLConf should adopt the same prefix.
   * @return (undocumented)
   */
  static public  java.lang.String sqlConfPrefix ()  { throw new RuntimeException(); }
  /**
   * Validates specified configurations and returns the normalized key -> value map.
   * @param configurations (undocumented)
   * @return (undocumented)
   */
  static public  scala.collection.immutable.Map<java.lang.String, java.lang.String> validateConfigurations (scala.collection.immutable.Map<java.lang.String, java.lang.String> configurations)  { throw new RuntimeException(); }
  /**
   * Table properties for new tables can be specified through SQL Configurations using the
   * <code>sqlConfPrefix</code>. This method checks to see if any of the configurations exist among the SQL
   * configurations and merges them with the user provided configurations. User provided configs
   * take precedence.
   * @param sqlConfs (undocumented)
   * @param tableConf (undocumented)
   * @return (undocumented)
   */
  static public  scala.collection.immutable.Map<java.lang.String, java.lang.String> mergeGlobalConfigs (org.apache.spark.sql.internal.SQLConf sqlConfs, scala.collection.immutable.Map<java.lang.String, java.lang.String> tableConf)  { throw new RuntimeException(); }
  /**
   * Normalize the specified property keys if the key is for a Delta config.
   * @param propKeys (undocumented)
   * @return (undocumented)
   */
  static public  scala.collection.Seq<java.lang.String> normalizeConfigKeys (scala.collection.Seq<java.lang.String> propKeys)  { throw new RuntimeException(); }
  /**
   * Normalize the specified property key if the key is for a Delta config.
   * @param propKey (undocumented)
   * @return (undocumented)
   */
  static public  scala.Option<java.lang.String> normalizeConfigKey (scala.Option<java.lang.String> propKey)  { throw new RuntimeException(); }
  static public  long getMilliSeconds (org.apache.spark.unsafe.types.CalendarInterval i)  { throw new RuntimeException(); }
  /**
   * For configs accepting an interval, we require the user specified string must obey:
   * <p>
   * - Doesn't use months or years, since an internal like this is not deterministic.
   * - The microseconds parsed from the string value must be a non-negative value.
   * <p>
   * The method returns whether a {@link CalendarInterval} satisfies the requirements.
   * @param i (undocumented)
   * @return (undocumented)
   */
  static public  boolean isValidIntervalConfigValue (org.apache.spark.unsafe.types.CalendarInterval i)  { throw new RuntimeException(); }
  /**
   * The protocol reader version modelled as a table property. This property is *not* stored as
   * a table property in the <code>Metadata</code> action. It is stored as its own action. Having it modelled
   * as a table property makes it easier to upgrade, and view the version.
   * @return (undocumented)
   */
  static public  org.apache.spark.sql.delta.DeltaConfig<java.lang.Object> MIN_READER_VERSION ()  { throw new RuntimeException(); }
  /**
   * The protocol reader version modelled as a table property. This property is *not* stored as
   * a table property in the <code>Metadata</code> action. It is stored as its own action. Having it modelled
   * as a table property makes it easier to upgrade, and view the version.
   * @return (undocumented)
   */
  static public  org.apache.spark.sql.delta.DeltaConfig<java.lang.Object> MIN_WRITER_VERSION ()  { throw new RuntimeException(); }
  /**
   * The shortest duration we have to keep delta files around before deleting them. We can only
   * delete delta files that are before a compaction. We may keep files beyond this duration until
   * the next calendar day.
   * @return (undocumented)
   */
  static public  org.apache.spark.sql.delta.DeltaConfig<org.apache.spark.unsafe.types.CalendarInterval> LOG_RETENTION ()  { throw new RuntimeException(); }
  /**
   * The shortest duration we have to keep delta sample files around before deleting them.
   * @return (undocumented)
   */
  static public  org.apache.spark.sql.delta.DeltaConfig<org.apache.spark.unsafe.types.CalendarInterval> SAMPLE_RETENTION ()  { throw new RuntimeException(); }
  /**
   * The shortest duration we have to keep checkpoint files around before deleting them. Note that
   * we'll never delete the most recent checkpoint. We may keep checkpoint files beyond this
   * duration until the next calendar day.
   * @return (undocumented)
   */
  static public  org.apache.spark.sql.delta.DeltaConfig<org.apache.spark.unsafe.types.CalendarInterval> CHECKPOINT_RETENTION_DURATION ()  { throw new RuntimeException(); }
  /** How often to checkpoint the delta log. */
  static public  org.apache.spark.sql.delta.DeltaConfig<java.lang.Object> CHECKPOINT_INTERVAL ()  { throw new RuntimeException(); }
  /** Whether to clean up expired checkpoints and delta logs. */
  static public  org.apache.spark.sql.delta.DeltaConfig<java.lang.Object> ENABLE_EXPIRED_LOG_CLEANUP ()  { throw new RuntimeException(); }
  /**
   * If true, a delta table can be rolled back to any point within LOG_RETENTION. Leaving this on
   * requires converting the oldest delta file we have into a checkpoint, which we do once a day. If
   * doing that operation is too expensive, it can be turned off, but the table can only be rolled
   * back CHECKPOINT_RETENTION_DURATION ago instead of LOG_RETENTION ago.
   * @return (undocumented)
   */
  static public  org.apache.spark.sql.delta.DeltaConfig<java.lang.Object> ENABLE_FULL_RETENTION_ROLLBACK ()  { throw new RuntimeException(); }
  /**
   * The shortest duration we have to keep logically deleted data files around before deleting them
   * physically. This is to prevent failures in stale readers after compactions or partition
   * overwrites.
   * <p>
   * Note: this value should be large enough:
   * - It should be larger than the longest possible duration of a job if you decide to run "VACUUM"
   *   when there are concurrent readers or writers accessing the table.
   * - If you are running a streaming query reading from the table, you should make sure the query
   *   doesn't stop longer than this value. Otherwise, the query may not be able to restart as it
   *   still needs to read old files.
   * @return (undocumented)
   */
  static public  org.apache.spark.sql.delta.DeltaConfig<org.apache.spark.unsafe.types.CalendarInterval> TOMBSTONE_RETENTION ()  { throw new RuntimeException(); }
  /**
   * Whether to use a random prefix in a file path instead of partition information. This is
   * required for very high volume S3 calls to better be partitioned across S3 servers.
   * @return (undocumented)
   */
  static public  org.apache.spark.sql.delta.DeltaConfig<java.lang.Object> RANDOMIZE_FILE_PREFIXES ()  { throw new RuntimeException(); }
  /**
   * Whether to use a random prefix in a file path instead of partition information. This is
   * required for very high volume S3 calls to better be partitioned across S3 servers.
   * @return (undocumented)
   */
  static public  org.apache.spark.sql.delta.DeltaConfig<java.lang.Object> RANDOM_PREFIX_LENGTH ()  { throw new RuntimeException(); }
  /**
   * Whether this Delta table is append-only. Files can't be deleted, or values can't be updated.
   * @return (undocumented)
   */
  static public  org.apache.spark.sql.delta.DeltaConfig<java.lang.Object> IS_APPEND_ONLY ()  { throw new RuntimeException(); }
  /**
   * Whether this table will automagically optimize the layout of files during writes.
   * @return (undocumented)
   */
  static public  org.apache.spark.sql.delta.DeltaConfig<java.lang.Object> AUTO_OPTIMIZE ()  { throw new RuntimeException(); }
  /**
   * The number of columns to collect stats on for data skipping. A value of -1 means collecting
   * stats for all columns. Updating this conf does not trigger stats re-collection, but redefines
   * the stats schema of table, i.e., it will change the behavior of future stats collection
   * (e.g., in append and OPTIMIZE) as well as data skipping (e.g., the column stats beyond this
   * number will be ignored even when they exist).
   * @return (undocumented)
   */
  static public  org.apache.spark.sql.delta.DeltaConfig<java.lang.Object> DATA_SKIPPING_NUM_INDEXED_COLS ()  { throw new RuntimeException(); }
  static public  org.apache.spark.sql.delta.DeltaConfig<java.lang.Object> SYMLINK_FORMAT_MANIFEST_ENABLED ()  { throw new RuntimeException(); }
  /**
   * When enabled, we will write file statistics in the checkpoint in JSON format as the "stats"
   * column.
   * @return (undocumented)
   */
  static public  org.apache.spark.sql.delta.DeltaConfig<java.lang.Object> CHECKPOINT_WRITE_STATS_AS_JSON ()  { throw new RuntimeException(); }
  /**
   * When enabled, we will write file statistics in the checkpoint in the struct format in the
   * "stats_parsed" column. We will also write partition values as a struct as
   * "partitionValues_parsed".
   * @return (undocumented)
   */
  static public  org.apache.spark.sql.delta.DeltaConfig<scala.Option<java.lang.Object>> CHECKPOINT_WRITE_STATS_AS_STRUCT ()  { throw new RuntimeException(); }
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
}
