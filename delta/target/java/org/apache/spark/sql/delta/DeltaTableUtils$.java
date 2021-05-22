package org.apache.spark.sql.delta;
public  class DeltaTableUtils$ implements org.apache.spark.sql.catalyst.expressions.PredicateHelper, org.apache.spark.sql.delta.metering.DeltaLogging {
  /**
   * Static reference to the singleton instance of this Scala object.
   */
  public static final DeltaTableUtils$ MODULE$ = null;
  public   DeltaTableUtils$ ()  { throw new RuntimeException(); }
  /** Check whether this table is a Delta table based on information from the Catalog. */
  public  boolean isDeltaTable (org.apache.spark.sql.catalyst.catalog.CatalogTable table)  { throw new RuntimeException(); }
  /**
   * Check whether the provided table name is a Delta table based on information from the Catalog.
   * @param spark (undocumented)
   * @param tableName (undocumented)
   * @return (undocumented)
   */
  public  boolean isDeltaTable (org.apache.spark.sql.SparkSession spark, org.apache.spark.sql.catalyst.TableIdentifier tableName)  { throw new RuntimeException(); }
  /** Check if the provided path is the root or the children of a Delta table. */
  public  boolean isDeltaTable (org.apache.spark.sql.SparkSession spark, org.apache.hadoop.fs.Path path)  { throw new RuntimeException(); }
  /**
   * Checks whether TableIdentifier is a path or a table name
   * We assume it is a path unless the table and database both exist in the catalog
   * @param catalog session catalog used to check whether db/table exist
   * @param tableIdent the provided table or path
   * @return true if using table name, false if using path, error otherwise
   */
  public  boolean isCatalogTable (org.apache.spark.sql.catalyst.catalog.SessionCatalog catalog, org.apache.spark.sql.catalyst.TableIdentifier tableIdent)  { throw new RuntimeException(); }
  /**
   * @param tableIdent the provided table or path
   * @return whether or not the provided TableIdentifier can specify a path for parquet or delta
   */
  public  boolean isValidPath (org.apache.spark.sql.catalyst.TableIdentifier tableIdent)  { throw new RuntimeException(); }
  /** Find the root of a Delta table from the provided path. */
  public  scala.Option<org.apache.hadoop.fs.Path> findDeltaTableRoot (org.apache.spark.sql.SparkSession spark, org.apache.hadoop.fs.Path path, scala.collection.immutable.Map<java.lang.String, java.lang.String> options)  { throw new RuntimeException(); }
  /** Whether a path should be hidden for delta-related file operations, such as Vacuum and Fsck. */
  public  boolean isHiddenDirectory (scala.collection.Seq<java.lang.String> partitionColumnNames, java.lang.String pathName)  { throw new RuntimeException(); }
  /**
   * Enrich the metadata received from the catalog on Delta tables with the Delta table metadata.
   * @param sparkSession (undocumented)
   * @param table (undocumented)
   * @return (undocumented)
   */
  public  org.apache.spark.sql.catalyst.catalog.CatalogTable combineWithCatalogMetadata (org.apache.spark.sql.SparkSession sparkSession, org.apache.spark.sql.catalyst.catalog.CatalogTable table)  { throw new RuntimeException(); }
  /**
   * Does the predicate only contains partition columns?
   * @param condition (undocumented)
   * @param partitionColumns (undocumented)
   * @param spark (undocumented)
   * @return (undocumented)
   */
  public  boolean isPredicatePartitionColumnsOnly (org.apache.spark.sql.catalyst.expressions.Expression condition, scala.collection.Seq<java.lang.String> partitionColumns, org.apache.spark.sql.SparkSession spark)  { throw new RuntimeException(); }
  /**
   * Partition the given condition into two sequence of conjunctive predicates:
   * - predicates that can be evaluated using metadata only.
   * - other predicates.
   * @param condition (undocumented)
   * @param partitionColumns (undocumented)
   * @param spark (undocumented)
   * @return (undocumented)
   */
  public  scala.Tuple2<scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Expression>, scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Expression>> splitMetadataAndDataPredicates (org.apache.spark.sql.catalyst.expressions.Expression condition, scala.collection.Seq<java.lang.String> partitionColumns, org.apache.spark.sql.SparkSession spark)  { throw new RuntimeException(); }
  /**
   * Check if condition involves a subquery expression.
   * @param condition (undocumented)
   * @return (undocumented)
   */
  public  boolean containsSubquery (org.apache.spark.sql.catalyst.expressions.Expression condition)  { throw new RuntimeException(); }
  /**
   * Check if condition can be evaluated using only metadata. In Delta, this means the condition
   * only references partition columns and involves no subquery.
   * @param condition (undocumented)
   * @param partitionColumns (undocumented)
   * @param spark (undocumented)
   * @return (undocumented)
   */
  public  boolean isPredicateMetadataOnly (org.apache.spark.sql.catalyst.expressions.Expression condition, scala.collection.Seq<java.lang.String> partitionColumns, org.apache.spark.sql.SparkSession spark)  { throw new RuntimeException(); }
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
  public  org.apache.spark.sql.catalyst.plans.logical.LogicalPlan replaceFileIndex (org.apache.spark.sql.catalyst.plans.logical.LogicalPlan target, org.apache.spark.sql.execution.datasources.FileIndex fileIndex)  { throw new RuntimeException(); }
  /**
   * Check if the given path contains time travel syntax with the <code>@</code>. If the path genuinely exists,
   * return <code>None</code>. If the path doesn't exist, but is specifying time travel, return the
   * <code>DeltaTimeTravelSpec</code> as well as the real path.
   * @param session (undocumented)
   * @param path (undocumented)
   * @return (undocumented)
   */
  public  scala.Tuple2<java.lang.String, scala.Option<org.apache.spark.sql.delta.DeltaTimeTravelSpec>> extractIfPathContainsTimeTravel (org.apache.spark.sql.SparkSession session, java.lang.String path)  { throw new RuntimeException(); }
  /**
   * Given a time travel node, resolve which version it is corresponding to for the given table and
   * return the resolved version as well as the access type, i.e. by version or timestamp.
   * @param conf (undocumented)
   * @param deltaLog (undocumented)
   * @param tt (undocumented)
   * @return (undocumented)
   */
  public  scala.Tuple2<java.lang.Object, java.lang.String> resolveTimeTravelVersion (org.apache.spark.sql.internal.SQLConf conf, org.apache.spark.sql.delta.DeltaLog deltaLog, org.apache.spark.sql.delta.DeltaTimeTravelSpec tt)  { throw new RuntimeException(); }
}
