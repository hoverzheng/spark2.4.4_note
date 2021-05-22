package org.apache.spark.sql.delta.hooks;
/**
 * Post commit hook to generate hive-style manifests for Delta table. This is useful for
 * compatibility with Presto / Athena.
 */
public  class GenerateSymlinkManifest {
  static public  java.lang.String CONFIG_NAME_ROOT ()  { throw new RuntimeException(); }
  static public  java.lang.String MANIFEST_LOCATION ()  { throw new RuntimeException(); }
  static public  java.lang.String OP_TYPE_ROOT ()  { throw new RuntimeException(); }
  static public  java.lang.String FULL_MANIFEST_OP_TYPE ()  { throw new RuntimeException(); }
  static public  java.lang.String INCREMENTAL_MANIFEST_OP_TYPE ()  { throw new RuntimeException(); }
  static public  java.lang.String name ()  { throw new RuntimeException(); }
  static public  org.apache.spark.sql.delta.hooks.GenerateSymlinkManifestImpl.SymlinkManifestStats$ SymlinkManifestStats ()  { throw new RuntimeException(); }
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
  static public  void run (org.apache.spark.sql.SparkSession spark, org.apache.spark.sql.delta.OptimisticTransactionImpl txn, scala.collection.Seq<org.apache.spark.sql.delta.actions.Action> committedActions)  { throw new RuntimeException(); }
  static public  void handleError (java.lang.Throwable error, long version)  { throw new RuntimeException(); }
  static protected  void generateIncrementalManifest (org.apache.spark.sql.SparkSession spark, org.apache.spark.sql.delta.DeltaLog deltaLog, org.apache.spark.sql.delta.Snapshot txnReadSnapshot, scala.collection.Seq<org.apache.spark.sql.delta.actions.Action> actions)  { throw new RuntimeException(); }
  static public  void generateFullManifest (org.apache.spark.sql.SparkSession spark, org.apache.spark.sql.delta.DeltaLog deltaLog)  { throw new RuntimeException(); }
  static protected  org.apache.spark.sql.catalyst.expressions.Expression generatePartitionPathExpression (scala.collection.Seq<scala.Tuple2<java.lang.String, org.apache.spark.sql.catalyst.expressions.Attribute>> partitionColNameToAttrib, java.lang.String timeZoneId)  { throw new RuntimeException(); }
  static protected abstract  void org$apache$spark$sql$delta$hooks$GenerateSymlinkManifestImpl$_setter_$CONFIG_NAME_ROOT_$eq (java.lang.String x$1)  ;
  static protected abstract  void org$apache$spark$sql$delta$hooks$GenerateSymlinkManifestImpl$_setter_$MANIFEST_LOCATION_$eq (java.lang.String x$1)  ;
  static protected abstract  void org$apache$spark$sql$delta$hooks$GenerateSymlinkManifestImpl$_setter_$OP_TYPE_ROOT_$eq (java.lang.String x$1)  ;
  static protected abstract  void org$apache$spark$sql$delta$hooks$GenerateSymlinkManifestImpl$_setter_$FULL_MANIFEST_OP_TYPE_$eq (java.lang.String x$1)  ;
  static protected abstract  void org$apache$spark$sql$delta$hooks$GenerateSymlinkManifestImpl$_setter_$INCREMENTAL_MANIFEST_OP_TYPE_$eq (java.lang.String x$1)  ;
  static protected abstract  void org$apache$spark$sql$delta$hooks$GenerateSymlinkManifestImpl$_setter_$name_$eq (java.lang.String x$1)  ;
}
