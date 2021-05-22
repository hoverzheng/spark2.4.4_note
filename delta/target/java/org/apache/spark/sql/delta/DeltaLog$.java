package org.apache.spark.sql.delta;
public  class DeltaLog$ implements org.apache.spark.sql.delta.metering.DeltaLogging {
  /**
   * Static reference to the singleton instance of this Scala object.
   */
  public static final DeltaLog$ MODULE$ = null;
  public   DeltaLog$ ()  { throw new RuntimeException(); }
  /** Helper for creating a log when it stored at the root of the data. */
  public  org.apache.spark.sql.delta.DeltaLog forTable (org.apache.spark.sql.SparkSession spark, java.lang.String dataPath)  { throw new RuntimeException(); }
  /** Helper for creating a log when it stored at the root of the data. */
  public  org.apache.spark.sql.delta.DeltaLog forTable (org.apache.spark.sql.SparkSession spark, java.io.File dataPath)  { throw new RuntimeException(); }
  /** Helper for creating a log when it stored at the root of the data. */
  public  org.apache.spark.sql.delta.DeltaLog forTable (org.apache.spark.sql.SparkSession spark, org.apache.hadoop.fs.Path dataPath)  { throw new RuntimeException(); }
  /** Helper for creating a log when it stored at the root of the data. */
  public  org.apache.spark.sql.delta.DeltaLog forTable (org.apache.spark.sql.SparkSession spark, java.lang.String dataPath, org.apache.spark.util.Clock clock)  { throw new RuntimeException(); }
  /** Helper for creating a log when it stored at the root of the data. */
  public  org.apache.spark.sql.delta.DeltaLog forTable (org.apache.spark.sql.SparkSession spark, java.io.File dataPath, org.apache.spark.util.Clock clock)  { throw new RuntimeException(); }
  /** Helper for creating a log when it stored at the root of the data. */
  public  org.apache.spark.sql.delta.DeltaLog forTable (org.apache.spark.sql.SparkSession spark, org.apache.hadoop.fs.Path dataPath, org.apache.spark.util.Clock clock)  { throw new RuntimeException(); }
  /** Helper for creating a log for the table. */
  public  org.apache.spark.sql.delta.DeltaLog forTable (org.apache.spark.sql.SparkSession spark, org.apache.spark.sql.catalyst.TableIdentifier tableName)  { throw new RuntimeException(); }
  /** Helper for creating a log for the table. */
  public  org.apache.spark.sql.delta.DeltaLog forTable (org.apache.spark.sql.SparkSession spark, org.apache.spark.sql.catalyst.catalog.CatalogTable table)  { throw new RuntimeException(); }
  /** Helper for creating a log for the table. */
  public  org.apache.spark.sql.delta.DeltaLog forTable (org.apache.spark.sql.SparkSession spark, org.apache.spark.sql.catalyst.TableIdentifier tableName, org.apache.spark.util.Clock clock)  { throw new RuntimeException(); }
  /** Helper for creating a log for the table. */
  public  org.apache.spark.sql.delta.DeltaLog forTable (org.apache.spark.sql.SparkSession spark, org.apache.spark.sql.catalyst.catalog.CatalogTable table, org.apache.spark.util.Clock clock)  { throw new RuntimeException(); }
  /** Helper for creating a log for the table. */
  public  org.apache.spark.sql.delta.DeltaLog forTable (org.apache.spark.sql.SparkSession spark, org.apache.spark.sql.delta.DeltaTableIdentifier deltaTable)  { throw new RuntimeException(); }
  public  org.apache.spark.sql.delta.DeltaLog apply (org.apache.spark.sql.SparkSession spark, org.apache.hadoop.fs.Path rawPath, org.apache.spark.util.Clock clock)  { throw new RuntimeException(); }
  /** Invalidate the cached DeltaLog object for the given `dataPath`. */
  public  void invalidateCache (org.apache.spark.sql.SparkSession spark, org.apache.hadoop.fs.Path dataPath)  { throw new RuntimeException(); }
  public  void clearCache ()  { throw new RuntimeException(); }
  /**
   * Filters the given {@link Dataset} by the given <code>partitionFilters</code>, returning those that match.
   * @param files The active files in the DeltaLog state, which contains the partition value
   *              information
   * @param partitionFilters Filters on the partition columns
   * @param partitionColumnPrefixes The path to the <code>partitionValues</code> column, if it's nested
   * @param partitionSchema (undocumented)
   * @return (undocumented)
   */
  public  org.apache.spark.sql.Dataset<org.apache.spark.sql.Row> filterFileList (org.apache.spark.sql.types.StructType partitionSchema, org.apache.spark.sql.Dataset<org.apache.spark.sql.Row> files, scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Expression> partitionFilters, scala.collection.Seq<java.lang.String> partitionColumnPrefixes)  { throw new RuntimeException(); }
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
  public  scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Expression> rewritePartitionFilters (org.apache.spark.sql.types.StructType partitionSchema, scala.Function2<java.lang.String, java.lang.String, java.lang.Object> resolver, scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Expression> partitionFilters, scala.collection.Seq<java.lang.String> partitionColumnPrefixes)  { throw new RuntimeException(); }
}
