package io.delta.tables;
/**
 * :: Evolving ::
 * <p>
 * Main class for programmatically interacting with Delta tables.
 * You can create DeltaTable instances using the static methods.
 * <pre><code>
 *   DeltaTable.forPath(sparkSession, pathToTheDeltaTable)
 * </code></pre>
 * <p>
 * @since 0.3.0
 */
public  class DeltaTable implements io.delta.tables.execution.DeltaTableOperations, scala.Serializable {
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
  static public  io.delta.tables.DeltaTable convertToDelta (org.apache.spark.sql.SparkSession spark, java.lang.String identifier, org.apache.spark.sql.types.StructType partitionSchema)  { throw new RuntimeException(); }
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
  static public  io.delta.tables.DeltaTable convertToDelta (org.apache.spark.sql.SparkSession spark, java.lang.String identifier, java.lang.String partitionSchema)  { throw new RuntimeException(); }
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
  static public  io.delta.tables.DeltaTable convertToDelta (org.apache.spark.sql.SparkSession spark, java.lang.String identifier)  { throw new RuntimeException(); }
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
  static public  io.delta.tables.DeltaTable forPath (java.lang.String path)  { throw new RuntimeException(); }
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
  static public  io.delta.tables.DeltaTable forPath (org.apache.spark.sql.SparkSession sparkSession, java.lang.String path)  { throw new RuntimeException(); }
  /**
   * Create a DeltaTable using the given table or view name using the given SparkSession.
   * <p>
   * Note: This uses the active SparkSession in the current thread to read the table data. Hence,
   * this throws error if active SparkSession has not been set, that is,
   * <code>SparkSession.getActiveSession()</code> is empty.
   * @param tableOrViewName (undocumented)
   * @return (undocumented)
   */
  static public  io.delta.tables.DeltaTable forName (java.lang.String tableOrViewName)  { throw new RuntimeException(); }
  /**
   * Create a DeltaTable using the given table or view name using the given SparkSession.
   * @param sparkSession (undocumented)
   * @param tableName (undocumented)
   * @return (undocumented)
   */
  static public  io.delta.tables.DeltaTable forName (org.apache.spark.sql.SparkSession sparkSession, java.lang.String tableName)  { throw new RuntimeException(); }
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
  static public  boolean isDeltaTable (org.apache.spark.sql.SparkSession sparkSession, java.lang.String identifier)  { throw new RuntimeException(); }
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
  static public  boolean isDeltaTable (java.lang.String identifier)  { throw new RuntimeException(); }
  // not preceding
     DeltaTable (org.apache.spark.sql.Dataset<org.apache.spark.sql.Row> _df, org.apache.spark.sql.delta.catalog.DeltaTableV2 table)  { throw new RuntimeException(); }
  protected  org.apache.spark.sql.delta.DeltaLog deltaLog ()  { throw new RuntimeException(); }
  protected  org.apache.spark.sql.Dataset<org.apache.spark.sql.Row> df ()  { throw new RuntimeException(); }
  /**
   * :: Evolving ::
   * <p>
   * Apply an alias to the DeltaTable. This is similar to <code>Dataset.as(alias)</code> or
   * SQL <code>tableName AS alias</code>.
   * <p>
   * @since 0.3.0
   * @param alias (undocumented)
   * @return (undocumented)
   */
  public  io.delta.tables.DeltaTable as (java.lang.String alias)  { throw new RuntimeException(); }
  /**
   * :: Evolving ::
   * <p>
   * Apply an alias to the DeltaTable. This is similar to <code>Dataset.as(alias)</code> or
   * SQL <code>tableName AS alias</code>.
   * <p>
   * @since 0.3.0
   * @param alias (undocumented)
   * @return (undocumented)
   */
  public  io.delta.tables.DeltaTable alias (java.lang.String alias)  { throw new RuntimeException(); }
  /**
   * :: Evolving ::
   * <p>
   * Get a DataFrame (that is, Dataset[Row]) representation of this Delta table.
   * <p>
   * @since 0.3.0
   * @return (undocumented)
   */
  public  org.apache.spark.sql.Dataset<org.apache.spark.sql.Row> toDF ()  { throw new RuntimeException(); }
  /**
   * :: Evolving ::
   * <p>
   * Recursively delete files and directories in the table that are not needed by the table for
   * maintaining older versions up to the given retention threshold. This method will return an
   * empty DataFrame on successful completion.
   * <p>
   * @param retentionHours The retention threshold in hours. Files required by the table for
   *                       reading versions earlier than this will be preserved and the
   *                       rest of them will be deleted.
   * @since 0.3.0
   * @return (undocumented)
   */
  public  org.apache.spark.sql.Dataset<org.apache.spark.sql.Row> vacuum (double retentionHours)  { throw new RuntimeException(); }
  /**
   * :: Evolving ::
   * <p>
   * Recursively delete files and directories in the table that are not needed by the table for
   * maintaining older versions up to the given retention threshold. This method will return an
   * empty DataFrame on successful completion.
   * <p>
   * note: This will use the default retention period of 7 days.
   * <p>
   * @since 0.3.0
   * @return (undocumented)
   */
  public  org.apache.spark.sql.Dataset<org.apache.spark.sql.Row> vacuum ()  { throw new RuntimeException(); }
  /**
   * :: Evolving ::
   * <p>
   * Get the information of the latest <code>limit</code> commits on this table as a Spark DataFrame.
   * The information is in reverse chronological order.
   * <p>
   * @param limit The number of previous commands to get history for
   * <p>
   * @since 0.3.0
   * @return (undocumented)
   */
  public  org.apache.spark.sql.Dataset<org.apache.spark.sql.Row> history (int limit)  { throw new RuntimeException(); }
  /**
   * :: Evolving ::
   * <p>
   * Get the information available commits on this table as a Spark DataFrame.
   * The information is in reverse chronological order.
   * <p>
   * @since 0.3.0
   * @return (undocumented)
   */
  public  org.apache.spark.sql.Dataset<org.apache.spark.sql.Row> history ()  { throw new RuntimeException(); }
  /**
   * :: Evolving ::
   * <p>
   * Generate a manifest for the given Delta Table
   * <p>
   * @param mode Specifies the mode for the generation of the manifest.
   *             The valid modes are as follows (not case sensitive):
   *              - "symlink_format_manifest" : This will generate manifests in symlink format
   *                                            for Presto and Athena read support.
   *             See the online documentation for more information.
   * @since 0.5.0
   */
  public  void generate (java.lang.String mode)  { throw new RuntimeException(); }
  /**
   * :: Evolving ::
   * <p>
   * Delete data from the table that match the given <code>condition</code>.
   * <p>
   * @param condition Boolean SQL expression
   * <p>
   * @since 0.3.0
   */
  public  void delete (java.lang.String condition)  { throw new RuntimeException(); }
  /**
   * :: Evolving ::
   * <p>
   * Delete data from the table that match the given <code>condition</code>.
   * <p>
   * @param condition Boolean SQL expression
   * <p>
   * @since 0.3.0
   */
  public  void delete (org.apache.spark.sql.Column condition)  { throw new RuntimeException(); }
  /**
   * :: Evolving ::
   * <p>
   * Delete data from the table.
   * <p>
   * @since 0.3.0
   */
  public  void delete ()  { throw new RuntimeException(); }
  /**
   * :: Evolving ::
   * <p>
   * Update rows in the table based on the rules defined by <code>set</code>.
   * <p>
   * Scala example to increment the column <code>data</code>.
   * <pre><code>
   *    import org.apache.spark.sql.functions._
   *
   *    deltaTable.update(Map("data" -&gt; col("data") + 1))
   * </code></pre>
   * <p>
   * @param set rules to update a row as a Scala map between target column names and
   *            corresponding update expressions as Column objects.
   * @since 0.3.0
   */
  public  void update (scala.collection.immutable.Map<java.lang.String, org.apache.spark.sql.Column> set)  { throw new RuntimeException(); }
  /**
   * :: Evolving ::
   * <p>
   * Update rows in the table based on the rules defined by <code>set</code>.
   * <p>
   * Java example to increment the column <code>data</code>.
   * <pre><code>
   *    import org.apache.spark.sql.Column;
   *    import org.apache.spark.sql.functions;
   *
   *    deltaTable.update(
   *      new HashMap&lt;String, Column&gt;() {{
   *        put("data", functions.col("data").plus(1));
   *      }}
   *    );
   * </code></pre>
   * <p>
   * @param set rules to update a row as a Java map between target column names and
   *            corresponding update expressions as Column objects.
   * @since 0.3.0
   */
  public  void update (java.util.Map<java.lang.String, org.apache.spark.sql.Column> set)  { throw new RuntimeException(); }
  /**
   * :: Evolving ::
   * <p>
   * Update data from the table on the rows that match the given <code>condition</code>
   * based on the rules defined by <code>set</code>.
   * <p>
   * Scala example to increment the column <code>data</code>.
   * <pre><code>
   *    import org.apache.spark.sql.functions._
   *
   *    deltaTable.update(
   *      col("date") &gt; "2018-01-01",
   *      Map("data" -&gt; col("data") + 1))
   * </code></pre>
   * <p>
   * @param condition boolean expression as Column object specifying which rows to update.
   * @param set rules to update a row as a Scala map between target column names and
   *            corresponding update expressions as Column objects.
   * @since 0.3.0
   */
  public  void update (org.apache.spark.sql.Column condition, scala.collection.immutable.Map<java.lang.String, org.apache.spark.sql.Column> set)  { throw new RuntimeException(); }
  /**
   * :: Evolving ::
   * <p>
   * Update data from the table on the rows that match the given <code>condition</code>
   * based on the rules defined by <code>set</code>.
   * <p>
   * Java example to increment the column <code>data</code>.
   * <pre><code>
   *    import org.apache.spark.sql.Column;
   *    import org.apache.spark.sql.functions;
   *
   *    deltaTable.update(
   *      functions.col("date").gt("2018-01-01"),
   *      new HashMap&lt;String, Column&gt;() {{
   *        put("data", functions.col("data").plus(1));
   *      }}
   *    );
   * </code></pre>
   * <p>
   * @param condition boolean expression as Column object specifying which rows to update.
   * @param set rules to update a row as a Java map between target column names and
   *            corresponding update expressions as Column objects.
   * @since 0.3.0
   */
  public  void update (org.apache.spark.sql.Column condition, java.util.Map<java.lang.String, org.apache.spark.sql.Column> set)  { throw new RuntimeException(); }
  /**
   * :: Evolving ::
   * <p>
   * Update rows in the table based on the rules defined by <code>set</code>.
   * <p>
   * Scala example to increment the column <code>data</code>.
   * <pre><code>
   *    deltaTable.updateExpr(Map("data" -&gt; "data + 1")))
   * </code></pre>
   * <p>
   * @param set rules to update a row as a Scala map between target column names and
   *            corresponding update expressions as SQL formatted strings.
   * @since 0.3.0
   */
  public  void updateExpr (scala.collection.immutable.Map<java.lang.String, java.lang.String> set)  { throw new RuntimeException(); }
  /**
   * :: Evolving ::
   * <p>
   * Update rows in the table based on the rules defined by <code>set</code>.
   * <p>
   * Java example to increment the column <code>data</code>.
   * <pre><code>
   *    deltaTable.updateExpr(
   *      new HashMap&lt;String, String&gt;() {{
   *        put("data", "data + 1");
   *      }}
   *    );
   * </code></pre>
   * <p>
   * @param set rules to update a row as a Java map between target column names and
   *            corresponding update expressions as SQL formatted strings.
   * @since 0.3.0
   */
  public  void updateExpr (java.util.Map<java.lang.String, java.lang.String> set)  { throw new RuntimeException(); }
  /**
   * :: Evolving ::
   * <p>
   * Update data from the table on the rows that match the given <code>condition</code>,
   * which performs the rules defined by <code>set</code>.
   * <p>
   * Scala example to increment the column <code>data</code>.
   * <pre><code>
   *    deltaTable.update(
   *      "date &gt; '2018-01-01'",
   *      Map("data" -&gt; "data + 1"))
   * </code></pre>
   * <p>
   * @param condition boolean expression as SQL formatted string object specifying
   *                  which rows to update.
   * @param set rules to update a row as a Scala map between target column names and
   *            corresponding update expressions as SQL formatted strings.
   * @since 0.3.0
   */
  public  void updateExpr (java.lang.String condition, scala.collection.immutable.Map<java.lang.String, java.lang.String> set)  { throw new RuntimeException(); }
  /**
   * :: Evolving ::
   * <p>
   * Update data from the table on the rows that match the given <code>condition</code>,
   * which performs the rules defined by <code>set</code>.
   * <p>
   * Java example to increment the column <code>data</code>.
   * <pre><code>
   *    deltaTable.update(
   *      "date &gt; '2018-01-01'",
   *      new HashMap&lt;String, String&gt;() {{
   *        put("data", "data + 1");
   *      }}
   *    );
   * </code></pre>
   * <p>
   * @param condition boolean expression as SQL formatted string object specifying
   *                  which rows to update.
   * @param set rules to update a row as a Java map between target column names and
   *            corresponding update expressions as SQL formatted strings.
   * @since 0.3.0
   */
  public  void updateExpr (java.lang.String condition, java.util.Map<java.lang.String, java.lang.String> set)  { throw new RuntimeException(); }
  /**
   * :: Evolving ::
   * <p>
   * Merge data from the <code>source</code> DataFrame based on the given merge <code>condition</code>. This returns
   * a {@link DeltaMergeBuilder} object that can be used to specify the update, delete, or insert
   * actions to be performed on rows based on whether the rows matched the condition or not.
   * <p>
   * See the {@link DeltaMergeBuilder} for a full description of this operation and what combinations of
   * update, delete and insert operations are allowed.
   * <p>
   * Scala example to update a key-value Delta table with new key-values from a source DataFrame:
   * <pre><code>
   *    deltaTable
   *     .as("target")
   *     .merge(
   *       source.as("source"),
   *       "target.key = source.key")
   *     .whenMatched
   *     .updateExpr(Map(
   *       "value" -&gt; "source.value"))
   *     .whenNotMatched
   *     .insertExpr(Map(
   *       "key" -&gt; "source.key",
   *       "value" -&gt; "source.value"))
   *     .execute()
   * </code></pre>
   * <p>
   * Java example to update a key-value Delta table with new key-values from a source DataFrame:
   * <pre><code>
   *    deltaTable
   *     .as("target")
   *     .merge(
   *       source.as("source"),
   *       "target.key = source.key")
   *     .whenMatched
   *     .updateExpr(
   *        new HashMap&lt;String, String&gt;() {{
   *          put("value" -&gt; "source.value");
   *        }})
   *     .whenNotMatched
   *     .insertExpr(
   *        new HashMap&lt;String, String&gt;() {{
   *         put("key", "source.key");
   *         put("value", "source.value");
   *       }})
   *     .execute();
   * </code></pre>
   * <p>
   * @param source source Dataframe to be merged.
   * @param condition boolean expression as SQL formatted string
   * @since 0.3.0
   * @return (undocumented)
   */
  public  io.delta.tables.DeltaMergeBuilder merge (org.apache.spark.sql.Dataset<org.apache.spark.sql.Row> source, java.lang.String condition)  { throw new RuntimeException(); }
  /**
   * :: Evolving ::
   * <p>
   * Merge data from the <code>source</code> DataFrame based on the given merge <code>condition</code>. This returns
   * a {@link DeltaMergeBuilder} object that can be used to specify the update, delete, or insert
   * actions to be performed on rows based on whether the rows matched the condition or not.
   * <p>
   * See the {@link DeltaMergeBuilder} for a full description of this operation and what combinations of
   * update, delete and insert operations are allowed.
   * <p>
   * Scala example to update a key-value Delta table with new key-values from a source DataFrame:
   * <pre><code>
   *    deltaTable
   *     .as("target")
   *     .merge(
   *       source.as("source"),
   *       "target.key = source.key")
   *     .whenMatched
   *     .updateExpr(Map(
   *       "value" -&gt; "source.value"))
   *     .whenNotMatched
   *     .insertExpr(Map(
   *       "key" -&gt; "source.key",
   *       "value" -&gt; "source.value"))
   *     .execute()
   * </code></pre>
   * <p>
   * Java example to update a key-value Delta table with new key-values from a source DataFrame:
   * <pre><code>
   *    deltaTable
   *     .as("target")
   *     .merge(
   *       source.as("source"),
   *       "target.key = source.key")
   *     .whenMatched
   *     .updateExpr(
   *        new HashMap&lt;String, String&gt;() {{
   *          put("value" -&gt; "source.value")
   *        }})
   *     .whenNotMatched
   *     .insertExpr(
   *        new HashMap&lt;String, String&gt;() {{
   *         put("key", "source.key");
   *         put("value", "source.value");
   *       }})
   *     .execute()
   * </code></pre>
   * <p>
   * @param source source Dataframe to be merged.
   * @param condition boolean expression as a Column object
   * @since 0.3.0
   * @return (undocumented)
   */
  public  io.delta.tables.DeltaMergeBuilder merge (org.apache.spark.sql.Dataset<org.apache.spark.sql.Row> source, org.apache.spark.sql.Column condition)  { throw new RuntimeException(); }
  /**
   * :: Evolving ::
   * <p>
   * Updates the protocol version of the table to leverage new features. Upgrading the reader
   * version will prevent all clients that have an older version of Delta Lake from accessing this
   * table. Upgrading the writer version will prevent older versions of Delta Lake to write to this
   * table. The reader or writer version cannot be downgraded.
   * <p>
   * See online documentation and Delta's protocol specification at PROTOCOL.md for more details.
   * <p>
   * @since 0.8.0
   * @param readerVersion (undocumented)
   * @param writerVersion (undocumented)
   */
  public  void upgradeTableProtocol (int readerVersion, int writerVersion)  { throw new RuntimeException(); }
}
