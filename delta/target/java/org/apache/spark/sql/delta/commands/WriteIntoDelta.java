package org.apache.spark.sql.delta.commands;
/**
 * Used to write a {@link DataFrame} into a delta table.
 * <p>
 * New Table Semantics
 *  - The schema of the {@link DataFrame} is used to initialize the table.
 *  - The partition columns will be used to partition the table.
 * <p>
 * Existing Table Semantics
 *  - The save mode will control how existing data is handled (i.e. overwrite, append, etc)
 *  - The schema of the DataFrame will be checked and if there are new columns present
 *    they will be added to the tables schema. Conflicting columns (i.e. a INT, and a STRING)
 *    will result in an exception
 *  - The partition columns, if present are validated against the existing metadata. If not
 *    present, then the partitioning of the table is respected.
 * <p>
 * In combination with <code>Overwrite</code>, a <code>replaceWhere</code> option can be used to transactionally
 * replace data that matches a predicate.
 */
public  class WriteIntoDelta extends org.apache.spark.sql.catalyst.plans.logical.LogicalPlan implements org.apache.spark.sql.execution.command.RunnableCommand, org.apache.spark.sql.delta.schema.ImplicitMetadataOperation, org.apache.spark.sql.delta.commands.DeltaCommand, scala.Product, scala.Serializable {
  static public abstract  R apply (T1 v1, T2 v2, T3 v3, T4 v4, T5 v5, T6 v6)  ;
  static public  java.lang.String toString ()  { throw new RuntimeException(); }
  public  scala.collection.immutable.Map<java.lang.String, org.apache.spark.sql.execution.metric.SQLMetric> metrics ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.delta.DeltaLog deltaLog ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.SaveMode mode ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.delta.DeltaOptions options ()  { throw new RuntimeException(); }
  public  scala.collection.Seq<java.lang.String> partitionColumns ()  { throw new RuntimeException(); }
  public  scala.collection.immutable.Map<java.lang.String, java.lang.String> configuration ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.Dataset<org.apache.spark.sql.Row> data ()  { throw new RuntimeException(); }
  // not preceding
  public   WriteIntoDelta (org.apache.spark.sql.delta.DeltaLog deltaLog, org.apache.spark.sql.SaveMode mode, org.apache.spark.sql.delta.DeltaOptions options, scala.collection.Seq<java.lang.String> partitionColumns, scala.collection.immutable.Map<java.lang.String, java.lang.String> configuration, org.apache.spark.sql.Dataset<org.apache.spark.sql.Row> data)  { throw new RuntimeException(); }
  protected  boolean canMergeSchema ()  { throw new RuntimeException(); }
  protected  boolean canOverwriteSchema ()  { throw new RuntimeException(); }
  public  scala.collection.Seq<org.apache.spark.sql.Row> run (org.apache.spark.sql.SparkSession sparkSession)  { throw new RuntimeException(); }
  public  scala.collection.Seq<org.apache.spark.sql.delta.actions.Action> write (org.apache.spark.sql.delta.OptimisticTransaction txn, org.apache.spark.sql.SparkSession sparkSession)  { throw new RuntimeException(); }
}
