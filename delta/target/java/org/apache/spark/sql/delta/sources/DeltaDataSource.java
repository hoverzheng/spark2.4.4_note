package org.apache.spark.sql.delta.sources;
/** A DataSource V1 for integrating Delta into Spark SQL batch and Streaming APIs. */
public  class DeltaDataSource implements org.apache.spark.sql.sources.RelationProvider, org.apache.spark.sql.sources.StreamSourceProvider, org.apache.spark.sql.sources.StreamSinkProvider, org.apache.spark.sql.sources.CreatableRelationProvider, org.apache.spark.sql.sources.DataSourceRegister, org.apache.spark.sql.connector.catalog.TableProvider, org.apache.spark.sql.delta.metering.DeltaLogging {
  static public final  java.lang.String TIME_TRAVEL_SOURCE_KEY ()  { throw new RuntimeException(); }
  /**
   * The option key for time traveling using a timestamp. The timestamp should be a valid
   * timestamp string which can be cast to a timestamp type.
   * @return (undocumented)
   */
  static public final  java.lang.String TIME_TRAVEL_TIMESTAMP_KEY ()  { throw new RuntimeException(); }
  /**
   * The option key for time traveling using a version of a table. This value should be
   * castable to a long.
   * @return (undocumented)
   */
  static public final  java.lang.String TIME_TRAVEL_VERSION_KEY ()  { throw new RuntimeException(); }
  static public  java.lang.String encodePartitioningColumns (scala.collection.Seq<java.lang.String> columns)  { throw new RuntimeException(); }
  static public  scala.collection.Seq<java.lang.String> decodePartitioningColumns (java.lang.String str)  { throw new RuntimeException(); }
  /**
   * Extract the Delta path if <code>dataset</code> is created to load a Delta table. Otherwise returns <code>None</code>.
   * Table UI in universe will call this.
   * @param dataset (undocumented)
   * @return (undocumented)
   */
  static public  scala.Option<java.lang.String> extractDeltaPath (org.apache.spark.sql.Dataset<?> dataset)  { throw new RuntimeException(); }
  /**
   * For Delta, we allow certain magic to be performed through the paths that are provided by users.
   * Normally, a user specified path should point to the root of a Delta table. However, some users
   * are used to providing specific partition values through the path, because of how expensive it
   * was to perform partition discovery before. We treat these partition values as logical partition
   * filters, if a table does not exist at the provided path.
   * <p>
   * In addition, we allow users to provide time travel specifications through the path. This is
   * provided after an <code>@</code> symbol after a path followed by a time specification in
   * <code>yyyyMMddHHmmssSSS</code> format, or a version number preceded by a <code>v</code>.
   * <p>
   * This method parses these specifications and returns these modifiers only if a path does not
   * really exist at the provided path. We first parse out the time travel specification, and then
   * the partition filters. For example, a path specified as:
   *      /some/path/partition=1@v1234
   * will be parsed into <code>/some/path</code> with filters <code>partition=1</code> and a time travel spec of version
   * 1234.
   * <p>
   * @return A tuple of the root path of the Delta table, partition filters, and time travel options
   * @param spark (undocumented)
   * @param userPath (undocumented)
   */
  static public  scala.Tuple3<org.apache.hadoop.fs.Path, scala.collection.Seq<scala.Tuple2<java.lang.String, java.lang.String>>, scala.Option<org.apache.spark.sql.delta.DeltaTimeTravelSpec>> parsePathIdentifier (org.apache.spark.sql.SparkSession spark, java.lang.String userPath)  { throw new RuntimeException(); }
  /**
   * Verifies that the provided partition filters are valid and returns the corresponding
   * expressions.
   * @param userPath (undocumented)
   * @param snapshot (undocumented)
   * @param partitionFilters (undocumented)
   * @return (undocumented)
   */
  static public  scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Expression> verifyAndCreatePartitionFilters (java.lang.String userPath, org.apache.spark.sql.delta.Snapshot snapshot, scala.collection.Seq<scala.Tuple2<java.lang.String, java.lang.String>> partitionFilters)  { throw new RuntimeException(); }
  /** Extracts whether users provided the option to time travel a relation. */
  static public  scala.Option<org.apache.spark.sql.delta.DeltaTimeTravelSpec> getTimeTravelVersion (scala.collection.immutable.Map<java.lang.String, java.lang.String> parameters)  { throw new RuntimeException(); }
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
  public   DeltaDataSource ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.types.StructType inferSchema ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.types.StructType inferSchema (org.apache.spark.sql.util.CaseInsensitiveStringMap options)  { throw new RuntimeException(); }
  public  org.apache.spark.sql.connector.catalog.Table getTable (org.apache.spark.sql.types.StructType schema, org.apache.spark.sql.connector.expressions.Transform[] partitioning, java.util.Map<java.lang.String, java.lang.String> properties)  { throw new RuntimeException(); }
  public  scala.Tuple2<java.lang.String, org.apache.spark.sql.types.StructType> sourceSchema (org.apache.spark.sql.SQLContext sqlContext, scala.Option<org.apache.spark.sql.types.StructType> schema, java.lang.String providerName, scala.collection.immutable.Map<java.lang.String, java.lang.String> parameters)  { throw new RuntimeException(); }
  public  org.apache.spark.sql.execution.streaming.Source createSource (org.apache.spark.sql.SQLContext sqlContext, java.lang.String metadataPath, scala.Option<org.apache.spark.sql.types.StructType> schema, java.lang.String providerName, scala.collection.immutable.Map<java.lang.String, java.lang.String> parameters)  { throw new RuntimeException(); }
  public  org.apache.spark.sql.execution.streaming.Sink createSink (org.apache.spark.sql.SQLContext sqlContext, scala.collection.immutable.Map<java.lang.String, java.lang.String> parameters, scala.collection.Seq<java.lang.String> partitionColumns, org.apache.spark.sql.streaming.OutputMode outputMode)  { throw new RuntimeException(); }
  public  org.apache.spark.sql.sources.BaseRelation createRelation (org.apache.spark.sql.SQLContext sqlContext, org.apache.spark.sql.SaveMode mode, scala.collection.immutable.Map<java.lang.String, java.lang.String> parameters, org.apache.spark.sql.Dataset<org.apache.spark.sql.Row> data)  { throw new RuntimeException(); }
  public  org.apache.spark.sql.sources.BaseRelation createRelation (org.apache.spark.sql.SQLContext sqlContext, scala.collection.immutable.Map<java.lang.String, java.lang.String> parameters)  { throw new RuntimeException(); }
  public  java.lang.String shortName ()  { throw new RuntimeException(); }
}
