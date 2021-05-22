package org.apache.spark.sql.delta.commands;
/**
 * Single entry point for all write or declaration operations for Delta tables accessed through
 * the table name.
 * <p>
 * param:  table The table identifier for the Delta table
 * param:  existingTableOpt The existing table for the same identifier if exists
 * param:  mode The save mode when writing data. Relevant when the query is empty or set to Ignore
 *             with <code>CREATE TABLE IF NOT EXISTS</code>.
 * param:  query The query to commit into the Delta table if it exist. This can come from
 *                - CTAS
 *                - saveAsTable
 */
public  class CreateDeltaTableCommand extends org.apache.spark.sql.catalyst.plans.logical.LogicalPlan implements org.apache.spark.sql.execution.command.RunnableCommand, org.apache.spark.sql.delta.metering.DeltaLogging, scala.Product, scala.Serializable {
  static public abstract  R apply (T1 v1, T2 v2, T3 v3, T4 v4, T5 v5, T6 v6)  ;
  static public  java.lang.String toString ()  { throw new RuntimeException(); }
  public  scala.collection.immutable.Map<java.lang.String, org.apache.spark.sql.execution.metric.SQLMetric> metrics ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.catalyst.catalog.CatalogTable table ()  { throw new RuntimeException(); }
  public  scala.Option<org.apache.spark.sql.catalyst.catalog.CatalogTable> existingTableOpt ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.SaveMode mode ()  { throw new RuntimeException(); }
  public  scala.Option<org.apache.spark.sql.catalyst.plans.logical.LogicalPlan> query ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.delta.commands.TableCreationModes.CreationMode operation ()  { throw new RuntimeException(); }
  public  boolean tableByPath ()  { throw new RuntimeException(); }
  // not preceding
  public   CreateDeltaTableCommand (org.apache.spark.sql.catalyst.catalog.CatalogTable table, scala.Option<org.apache.spark.sql.catalyst.catalog.CatalogTable> existingTableOpt, org.apache.spark.sql.SaveMode mode, scala.Option<org.apache.spark.sql.catalyst.plans.logical.LogicalPlan> query, org.apache.spark.sql.delta.commands.TableCreationModes.CreationMode operation, boolean tableByPath)  { throw new RuntimeException(); }
  public  scala.collection.Seq<org.apache.spark.sql.Row> run (org.apache.spark.sql.SparkSession sparkSession)  { throw new RuntimeException(); }
}
