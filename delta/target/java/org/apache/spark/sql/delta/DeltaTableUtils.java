package org.apache.spark.sql.delta;
public  class DeltaTableUtils {
  /** Check whether this table is a Delta table based on information from the Catalog. */
  static public  boolean isDeltaTable (org.apache.spark.sql.catalyst.catalog.CatalogTable table)  { throw new RuntimeException(); }
  /**
   * Check whether the provided table name is a Delta table based on information from the Catalog.
   * @param spark (undocumented)
   * @param tableName (undocumented)
   * @return (undocumented)
   */
  static public  boolean isDeltaTable (org.apache.spark.sql.SparkSession spark, org.apache.spark.sql.catalyst.TableIdentifier tableName)  { throw new RuntimeException(); }
  /** Check if the provided path is the root or the children of a Delta table. */
  static public  boolean isDeltaTable (org.apache.spark.sql.SparkSession spark, org.apache.hadoop.fs.Path path)  { throw new RuntimeException(); }
  /**
   * Checks whether TableIdentifier is a path or a table name
   * We assume it is a path unless the table and database both exist in the catalog
   * @param catalog session catalog used to check whether db/table exist
   * @param tableIdent the provided table or path
   * @return true if using table name, false if using path, error otherwise
   */
  static public  boolean isCatalogTable (org.apache.spark.sql.catalyst.catalog.SessionCatalog catalog, org.apache.spark.sql.catalyst.TableIdentifier tableIdent)  { throw new RuntimeException(); }
  /**
   * @param tableIdent the provided table or path
   * @return whether or not the provided TableIdentifier can specify a path for parquet or delta
   */
  static public  boolean isValidPath (org.apache.spark.sql.catalyst.TableIdentifier tableIdent)  { throw new RuntimeException(); }
  /** Find the root of a Delta table from the provided path. */
  static public  scala.Option<org.apache.hadoop.fs.Path> findDeltaTableRoot (org.apache.spark.sql.SparkSession spark, org.apache.hadoop.fs.Path path, scala.collection.immutable.Map<java.lang.String, java.lang.String> options)  { throw new RuntimeException(); }
  /** Whether a path should be hidden for delta-related file operations, such as Vacuum and Fsck. */
  static public  boolean isHiddenDirectory (scala.collection.Seq<java.lang.String> partitionColumnNames, java.lang.String pathName)  { throw new RuntimeException(); }
  /**
   * Enrich the metadata received from the catalog on Delta tables with the Delta table metadata.
   * @param sparkSession (undocumented)
   * @param table (undocumented)
   * @return (undocumented)
   */
  static public  org.apache.spark.sql.catalyst.catalog.CatalogTable combineWithCatalogMetadata (org.apache.spark.sql.SparkSession sparkSession, org.apache.spark.sql.catalyst.catalog.CatalogTable table)  { throw new RuntimeException(); }
  /**
   * Does the predicate only contains partition columns?
   * @param condition (undocumented)
   * @param partitionColumns (undocumented)
   * @param spark (undocumented)
   * @return (undocumented)
   */
  static public  boolean isPredicatePartitionColumnsOnly (org.apache.spark.sql.catalyst.expressions.Expression condition, scala.collection.Seq<java.lang.String> partitionColumns, org.apache.spark.sql.SparkSession spark)  { throw new RuntimeException(); }
  /**
   * Partition the given condition into two sequence of conjunctive predicates:
   * - predicates that can be evaluated using metadata only.
   * - other predicates.
   * @param condition (undocumented)
   * @param partitionColumns (undocumented)
   * @param spark (undocumented)
   * @return (undocumented)
   */
  static public  scala.Tuple2<scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Expression>, scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Expression>> splitMetadataAndDataPredicates (org.apache.spark.sql.catalyst.expressions.Expression condition, scala.collection.Seq<java.lang.String> partitionColumns, org.apache.spark.sql.SparkSession spark)  { throw new RuntimeException(); }
  /**
   * Check if condition involves a subquery expression.
   * @param condition (undocumented)
   * @return (undocumented)
   */
  static public  boolean containsSubquery (org.apache.spark.sql.catalyst.expressions.Expression condition)  { throw new RuntimeException(); }
  /**
   * Check if condition can be evaluated using only metadata. In Delta, this means the condition
   * only references partition columns and involves no subquery.
   * @param condition (undocumented)
   * @param partitionColumns (undocumented)
   * @param spark (undocumented)
   * @return (undocumented)
   */
  static public  boolean isPredicateMetadataOnly (org.apache.spark.sql.catalyst.expressions.Expression condition, scala.collection.Seq<java.lang.String> partitionColumns, org.apache.spark.sql.SparkSession spark)  { throw new RuntimeException(); }
  /**
   * Replace the file index in a logical plan and return the updated plan.
   * It's a common pattern that, in Delta commands, we use data skipping to determine a subset of
   * files that can be affected by the command, so we replace the whole-table file index in the
   * original logical plan with a new index of potentially affected files, while everything else in
   * the original plan, e.g., resolved references, remain unchanged.
   * <p>
   * @param target the logical plan in which we replace the file index
   * @param fileIndex the new file index
   * @return (undocumented)
   */
  static public  org.apache.spark.sql.catalyst.plans.logical.LogicalPlan replaceFileIndex (org.apache.spark.sql.catalyst.plans.logical.LogicalPlan target, org.apache.spark.sql.execution.datasources.FileIndex fileIndex)  { throw new RuntimeException(); }
  /**
   * Check if the given path contains time travel syntax with the <code>@</code>. If the path genuinely exists,
   * return <code>None</code>. If the path doesn't exist, but is specifying time travel, return the
   * <code>DeltaTimeTravelSpec</code> as well as the real path.
   * @param session (undocumented)
   * @param path (undocumented)
   * @return (undocumented)
   */
  static public  scala.Tuple2<java.lang.String, scala.Option<org.apache.spark.sql.delta.DeltaTimeTravelSpec>> extractIfPathContainsTimeTravel (org.apache.spark.sql.SparkSession session, java.lang.String path)  { throw new RuntimeException(); }
  /**
   * Given a time travel node, resolve which version it is corresponding to for the given table and
   * return the resolved version as well as the access type, i.e. by version or timestamp.
   * @param conf (undocumented)
   * @param deltaLog (undocumented)
   * @param tt (undocumented)
   * @return (undocumented)
   */
  static public  scala.Tuple2<java.lang.Object, java.lang.String> resolveTimeTravelVersion (org.apache.spark.sql.internal.SQLConf conf, org.apache.spark.sql.delta.DeltaLog deltaLog, org.apache.spark.sql.delta.DeltaTimeTravelSpec tt)  { throw new RuntimeException(); }
  static protected  scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Expression> splitConjunctivePredicates (org.apache.spark.sql.catalyst.expressions.Expression condition)  { throw new RuntimeException(); }
  static public  scala.Option<scala.Tuple2<org.apache.spark.sql.catalyst.expressions.Expression, org.apache.spark.sql.catalyst.plans.logical.LogicalPlan>> findExpressionAndTrackLineageDown (org.apache.spark.sql.catalyst.expressions.Expression exp, org.apache.spark.sql.catalyst.plans.logical.LogicalPlan plan)  { throw new RuntimeException(); }
  static protected  scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Expression> splitDisjunctivePredicates (org.apache.spark.sql.catalyst.expressions.Expression condition)  { throw new RuntimeException(); }
  static protected  org.apache.spark.sql.catalyst.expressions.Expression replaceAlias (org.apache.spark.sql.catalyst.expressions.Expression condition, org.apache.spark.sql.catalyst.expressions.AttributeMap<org.apache.spark.sql.catalyst.expressions.Expression> aliases)  { throw new RuntimeException(); }
  static protected  boolean canEvaluate (org.apache.spark.sql.catalyst.expressions.Expression expr, org.apache.spark.sql.catalyst.plans.logical.LogicalPlan plan)  { throw new RuntimeException(); }
  static protected  boolean canEvaluateWithinJoin (org.apache.spark.sql.catalyst.expressions.Expression expr)  { throw new RuntimeException(); }
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
