package io.delta.tables;
/**
 * :: Evolving ::
 * <p>
 * Companion object to create DeltaTable instances.
 * <p>
 * <pre><code>
 *   DeltaTable.forPath(sparkSession, pathToTheDeltaTable)
 * </code></pre>
 * <p>
 * @since 0.3.0
 */
public  class DeltaTable$ implements scala.Serializable {
  /**
   * Static reference to the singleton instance of this Scala object.
   */
  public static final DeltaTable$ MODULE$ = null;
  public   DeltaTable$ ()  { throw new RuntimeException(); }
  /**
   * :: Evolving ::
   * <p>
   * Create a DeltaTable from the given parquet table and partition schema.
   * Takes an existing parquet table and constructs a delta transaction log in the base path of
   * that table.
   * <p>
   * Note: Any changes to the table during the conversion process may not result in a consistent
   * state at the end of the conversion. Users should stop any changes to the table before the
   * conversion is started.
   * <p>
   * An example usage would be
   * <pre><code>
   *  io.delta.tables.DeltaTable.convertToDelta(
   *   spark,
   *   "parquet.`/path`",
   *   new StructType().add(StructField("key1", LongType)).add(StructField("key2", StringType)))
   * </code></pre>
   * <p>
   * @since 0.4.0
   * @param spark (undocumented)
   * @param identifier (undocumented)
   * @param partitionSchema (undocumented)
   * @return (undocumented)
   */
  public  io.delta.tables.DeltaTable convertToDelta (org.apache.spark.sql.SparkSession spark, java.lang.String identifier, org.apache.spark.sql.types.StructType partitionSchema)  { throw new RuntimeException(); }
  /**
   * :: Evolving ::
   * <p>
   * Create a DeltaTable from the given parquet table and partition schema.
   * Takes an existing parquet table and constructs a delta transaction log in the base path of
   * that table.
   * <p>
   * Note: Any changes to the table during the conversion process may not result in a consistent
   * state at the end of the conversion. Users should stop any changes to the table before the
   * conversion is started.
   * <p>
   * An example usage would be
   * <pre><code>
   *  io.delta.tables.DeltaTable.convertToDelta(
   *   spark,
   *   "parquet.`/path`",
   *   "key1 long, key2 string")
   * </code></pre>
   * <p>
   * @since 0.4.0
   * @param spark (undocumented)
   * @param identifier (undocumented)
   * @param partitionSchema (undocumented)
   * @return (undocumented)
   */
  public  io.delta.tables.DeltaTable convertToDelta (org.apache.spark.sql.SparkSession spark, java.lang.String identifier, java.lang.String partitionSchema)  { throw new RuntimeException(); }
  /**
   * :: Evolving ::
   * <p>
   * Create a DeltaTable from the given parquet table. Takes an existing parquet table and
   * constructs a delta transaction log in the base path of the table.
   * <p>
   * Note: Any changes to the table during the conversion process may not result in a consistent
   * state at the end of the conversion. Users should stop any changes to the table before the
   * conversion is started.
   * <p>
   * An Example would be
   * <pre><code>
   *  io.delta.tables.DeltaTable.convertToDelta(
   *   spark,
   *   "parquet.`/path`"
   * </code></pre>
   * <p>
   * @since 0.4.0
   * @param spark (undocumented)
   * @param identifier (undocumented)
   * @return (undocumented)
   */
  public  io.delta.tables.DeltaTable convertToDelta (org.apache.spark.sql.SparkSession spark, java.lang.String identifier)  { throw new RuntimeException(); }
  /**
   * :: Evolving ::
   * <p>
   * Create a DeltaTable for the data at the given <code>path</code>.
   * <p>
   * Note: This uses the active SparkSession in the current thread to read the table data. Hence,
   * this throws error if active SparkSession has not been set, that is,
   * <code>SparkSession.getActiveSession()</code> is empty.
   * <p>
   * @since 0.3.0
   * @param path (undocumented)
   * @return (undocumented)
   */
  public  io.delta.tables.DeltaTable forPath (java.lang.String path)  { throw new RuntimeException(); }
  /**
   * :: Evolving ::
   * <p>
   * Create a DeltaTable for the data at the given <code>path</code> using the given SparkSession.
   * <p>
   * @since 0.3.0
   * @param sparkSession (undocumented)
   * @param path (undocumented)
   * @return (undocumented)
   */
  public  io.delta.tables.DeltaTable forPath (org.apache.spark.sql.SparkSession sparkSession, java.lang.String path)  { throw new RuntimeException(); }
  /**
   * Create a DeltaTable using the given table or view name using the given SparkSession.
   * <p>
   * Note: This uses the active SparkSession in the current thread to read the table data. Hence,
   * this throws error if active SparkSession has not been set, that is,
   * <code>SparkSession.getActiveSession()</code> is empty.
   * @param tableOrViewName (undocumented)
   * @return (undocumented)
   */
  public  io.delta.tables.DeltaTable forName (java.lang.String tableOrViewName)  { throw new RuntimeException(); }
  /**
   * Create a DeltaTable using the given table or view name using the given SparkSession.
   * @param sparkSession (undocumented)
   * @param tableName (undocumented)
   * @return (undocumented)
   */
  public  io.delta.tables.DeltaTable forName (org.apache.spark.sql.SparkSession sparkSession, java.lang.String tableName)  { throw new RuntimeException(); }
  /**
   * :: Evolving ::
   * <p>
   * Check if the provided <code>identifier</code> string, in this case a file path,
   * is the root of a Delta table using the given SparkSession.
   * <p>
   * An example would be
   * <pre><code>
   *   DeltaTable.isDeltaTable(spark, "path/to/table")
   * </code></pre>
   * <p>
   * @since 0.4.0
   * @param sparkSession (undocumented)
   * @param identifier (undocumented)
   * @return (undocumented)
   */
  public  boolean isDeltaTable (org.apache.spark.sql.SparkSession sparkSession, java.lang.String identifier)  { throw new RuntimeException(); }
  /**
   * :: Evolving ::
   * <p>
   * Check if the provided <code>identifier</code> string, in this case a file path,
   * is the root of a Delta table.
   * <p>
   * Note: This uses the active SparkSession in the current thread to search for the table. Hence,
   * this throws error if active SparkSession has not been set, that is,
   * <code>SparkSession.getActiveSession()</code> is empty.
   * <p>
   * An example would be
   * <pre><code>
   *   DeltaTable.isDeltaTable(spark, "/path/to/table")
   * </code></pre>
   * <p>
   * @since 0.4.0
   * @param identifier (undocumented)
   * @return (undocumented)
   */
  public  boolean isDeltaTable (java.lang.String identifier)  { throw new RuntimeException(); }
}
