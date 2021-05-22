package org.apache.spark.sql.delta.sources;
public  class DeltaDataSource$ implements com.databricks.spark.util.DatabricksLogging {
  /**
   * Static reference to the singleton instance of this Scala object.
   */
  public static final DeltaDataSource$ MODULE$ = null;
  public   DeltaDataSource$ ()  { throw new RuntimeException(); }
  public final  java.lang.String TIME_TRAVEL_SOURCE_KEY ()  { throw new RuntimeException(); }
  /**
   * The option key for time traveling using a timestamp. The timestamp should be a valid
   * timestamp string which can be cast to a timestamp type.
   * @return (undocumented)
   */
  public final  java.lang.String TIME_TRAVEL_TIMESTAMP_KEY ()  { throw new RuntimeException(); }
  /**
   * The option key for time traveling using a version of a table. This value should be
   * castable to a long.
   * @return (undocumented)
   */
  public final  java.lang.String TIME_TRAVEL_VERSION_KEY ()  { throw new RuntimeException(); }
  public  java.lang.String encodePartitioningColumns (scala.collection.Seq<java.lang.String> columns)  { throw new RuntimeException(); }
  public  scala.collection.Seq<java.lang.String> decodePartitioningColumns (java.lang.String str)  { throw new RuntimeException(); }
  /**
   * Extract the Delta path if <code>dataset</code> is created to load a Delta table. Otherwise returns <code>None</code>.
   * Table UI in universe will call this.
   * @param dataset (undocumented)
   * @return (undocumented)
   */
  public  scala.Option<java.lang.String> extractDeltaPath (org.apache.spark.sql.Dataset<?> dataset)  { throw new RuntimeException(); }
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
  public  scala.Tuple3<org.apache.hadoop.fs.Path, scala.collection.Seq<scala.Tuple2<java.lang.String, java.lang.String>>, scala.Option<org.apache.spark.sql.delta.DeltaTimeTravelSpec>> parsePathIdentifier (org.apache.spark.sql.SparkSession spark, java.lang.String userPath)  { throw new RuntimeException(); }
  /**
   * Verifies that the provided partition filters are valid and returns the corresponding
   * expressions.
   * @param userPath (undocumented)
   * @param snapshot (undocumented)
   * @param partitionFilters (undocumented)
   * @return (undocumented)
   */
  public  scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Expression> verifyAndCreatePartitionFilters (java.lang.String userPath, org.apache.spark.sql.delta.Snapshot snapshot, scala.collection.Seq<scala.Tuple2<java.lang.String, java.lang.String>> partitionFilters)  { throw new RuntimeException(); }
  /** Extracts whether users provided the option to time travel a relation. */
  public  scala.Option<org.apache.spark.sql.delta.DeltaTimeTravelSpec> getTimeTravelVersion (scala.collection.immutable.Map<java.lang.String, java.lang.String> parameters)  { throw new RuntimeException(); }
}
