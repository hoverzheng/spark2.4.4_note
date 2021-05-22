package org.apache.spark.sql.delta.files;
/**
 * Adds the ability to write files out as part of a transaction. Checks
 * are performed to ensure that the data being written matches either the
 * current metadata or the new metadata being set by this transaction.
 */
public  interface TransactionalWrite extends org.apache.spark.sql.delta.metering.DeltaLogging {
  public  org.apache.spark.sql.delta.DeltaLog deltaLog ()  ;
  public  org.apache.spark.sql.delta.files.DelayedCommitProtocol getCommitter (org.apache.hadoop.fs.Path outputPath)  ;
  public  scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Attribute> getPartitioningColumns (org.apache.spark.sql.types.StructType partitionSchema, scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Attribute> output, boolean colsDropped)  ;
  public  boolean hasWritten ()  ;
  /** Makes the output attributes nullable, so that we don't write unreadable parquet files. */
  public  scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Attribute> makeOutputNullable (scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Attribute> output)  ;
  public  org.apache.spark.sql.delta.actions.Metadata metadata ()  ;
  /**
   * Normalize the schema of the query, and return the QueryExecution to execute. The output
   * attributes of the QueryExecution may not match the attributes we return as the output schema.
   * This is because streaming queries create <code>IncrementalExecution</code>, which cannot be further
   * modified. We can however have the Parquet writer use the physical plan from
   * <code>IncrementalExecution</code> and the output schema provided through the attributes.
   * @param data (undocumented)
   * @param partitionCols (undocumented)
   * @return (undocumented)
   */
  public  scala.Tuple2<org.apache.spark.sql.execution.QueryExecution, scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Attribute>> normalizeData (org.apache.spark.sql.Dataset<?> data, scala.collection.Seq<java.lang.String> partitionCols)  ;
  public  org.apache.spark.sql.delta.actions.Protocol protocol ()  ;
  public  org.apache.spark.sql.delta.Snapshot snapshot ()  ;
  public  scala.collection.Seq<org.apache.spark.sql.delta.actions.AddFile> writeFiles (org.apache.spark.sql.Dataset<?> data)  ;
  public  scala.collection.Seq<org.apache.spark.sql.delta.actions.AddFile> writeFiles (org.apache.spark.sql.Dataset<?> data, scala.Option<org.apache.spark.sql.delta.DeltaOptions> writeOptions)  ;
  public  scala.collection.Seq<org.apache.spark.sql.delta.actions.AddFile> writeFiles (org.apache.spark.sql.Dataset<?> data, boolean isOptimize)  ;
  /**
   * Writes out the dataframe after performing schema validation. Returns a list of
   * actions to append these files to the reservoir.
   * @param data (undocumented)
   * @param writeOptions (undocumented)
   * @param isOptimize (undocumented)
   * @return (undocumented)
   */
  public  scala.collection.Seq<org.apache.spark.sql.delta.actions.AddFile> writeFiles (org.apache.spark.sql.Dataset<?> data, scala.Option<org.apache.spark.sql.delta.DeltaOptions> writeOptions, boolean isOptimize)  ;
}
