package org.apache.spark.sql.delta.catalog;
/**
 * The data source V2 representation of a Delta table that exists.
 * <p>
 * param:  path The path to the table
 * param:  tableIdentifier The table identifier for this table
 */
public  class DeltaTableV2 implements org.apache.spark.sql.connector.catalog.Table, org.apache.spark.sql.connector.catalog.SupportsWrite, org.apache.spark.sql.delta.metering.DeltaLogging, scala.Product, scala.Serializable {
  static public abstract  R apply (T1 v1, T2 v2, T3 v3, T4 v4, T5 v5)  ;
  static public  java.lang.String toString ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.SparkSession spark ()  { throw new RuntimeException(); }
  public  org.apache.hadoop.fs.Path path ()  { throw new RuntimeException(); }
  public  scala.Option<org.apache.spark.sql.catalyst.catalog.CatalogTable> catalogTable ()  { throw new RuntimeException(); }
  public  scala.Option<java.lang.String> tableIdentifier ()  { throw new RuntimeException(); }
  public  scala.Option<org.apache.spark.sql.delta.DeltaTimeTravelSpec> timeTravelOpt ()  { throw new RuntimeException(); }
  // not preceding
  public   DeltaTableV2 (org.apache.spark.sql.SparkSession spark, org.apache.hadoop.fs.Path path, scala.Option<org.apache.spark.sql.catalyst.catalog.CatalogTable> catalogTable, scala.Option<java.lang.String> tableIdentifier, scala.Option<org.apache.spark.sql.delta.DeltaTimeTravelSpec> timeTravelOpt)  { throw new RuntimeException(); }
  // not preceding
  public  org.apache.spark.sql.delta.DeltaLog deltaLog ()  { throw new RuntimeException(); }
  public  scala.Option<org.apache.spark.sql.catalyst.TableIdentifier> getTableIdentifierIfExists ()  { throw new RuntimeException(); }
  public  java.lang.String name ()  { throw new RuntimeException(); }
  // not preceding
  public  org.apache.spark.sql.delta.Snapshot snapshot ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.types.StructType schema ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.connector.expressions.Transform[] partitioning ()  { throw new RuntimeException(); }
  public  java.util.Map<java.lang.String, java.lang.String> properties ()  { throw new RuntimeException(); }
  public  java.util.Set<org.apache.spark.sql.connector.catalog.TableCapability> capabilities ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.connector.write.WriteBuilder newWriteBuilder (org.apache.spark.sql.connector.write.LogicalWriteInfo info)  { throw new RuntimeException(); }
  /**
   * Creates a V1 BaseRelation from this Table to allow read APIs to go through V1 DataSource code
   * paths.
   * @return (undocumented)
   */
  public  org.apache.spark.sql.sources.BaseRelation toBaseRelation ()  { throw new RuntimeException(); }
  /**
   * Check the passed in options and existing timeTravelOpt, set new time travel by options.
   * @param options (undocumented)
   * @return (undocumented)
   */
  public  org.apache.spark.sql.delta.catalog.DeltaTableV2 withOptions (scala.collection.immutable.Map<java.lang.String, java.lang.String> options)  { throw new RuntimeException(); }
}
