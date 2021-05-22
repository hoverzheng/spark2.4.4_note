package org.apache.spark.sql.delta.commands;
/**
 * A command for describing the details of a table such as the format, name, and size.
 */
public  class DescribeDeltaDetailCommand extends org.apache.spark.sql.catalyst.plans.logical.LogicalPlan implements org.apache.spark.sql.execution.command.RunnableCommand, org.apache.spark.sql.delta.metering.DeltaLogging, scala.Product, scala.Serializable {
  static public abstract  R apply (T1 v1, T2 v2)  ;
  static public  java.lang.String toString ()  { throw new RuntimeException(); }
  public  scala.collection.immutable.Map<java.lang.String, org.apache.spark.sql.execution.metric.SQLMetric> metrics ()  { throw new RuntimeException(); }
  public  scala.Option<java.lang.String> path ()  { throw new RuntimeException(); }
  public  scala.Option<org.apache.spark.sql.catalyst.TableIdentifier> tableIdentifier ()  { throw new RuntimeException(); }
  // not preceding
  public   DescribeDeltaDetailCommand (scala.Option<java.lang.String> path, scala.Option<org.apache.spark.sql.catalyst.TableIdentifier> tableIdentifier)  { throw new RuntimeException(); }
  public  scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Attribute> output ()  { throw new RuntimeException(); }
  public  scala.collection.Seq<org.apache.spark.sql.Row> run (org.apache.spark.sql.SparkSession sparkSession)  { throw new RuntimeException(); }
  /**
   * Resolve <code>path</code> and <code>tableIdentifier</code> to get the underlying storage path, and its <code>CatalogTable</code>
   * if it's a table. The caller will make sure either <code>path</code> or <code>tableIdentifier</code> is set but not
   * both.
   * <p>
   * If <code>path</code> is set, return it and an empty <code>CatalogTable</code> since it's a physical path. If
   * <code>tableIdentifier</code> is set, we will try to see if it's a Delta data source path (such as
   * <code>delta.<a table path></code>). If so, we will return the path and an empty <code>CatalogTable</code>. Otherwise,
   * we will use <code>SessionCatalog</code> to resolve <code>tableIdentifier</code>.
   * @param spark (undocumented)
   * @param path (undocumented)
   * @param tableIdentifier (undocumented)
   * @return (undocumented)
   */
  protected  scala.Tuple2<org.apache.hadoop.fs.Path, scala.Option<org.apache.spark.sql.catalyst.catalog.CatalogTable>> getPathAndTableMetadata (org.apache.spark.sql.SparkSession spark, scala.Option<java.lang.String> path, scala.Option<org.apache.spark.sql.catalyst.TableIdentifier> tableIdentifier)  { throw new RuntimeException(); }
}
