package org.apache.spark.sql.delta.sources;
/**
 * A streaming sink that writes data into a Delta Table.
 */
public  class DeltaSink implements org.apache.spark.sql.execution.streaming.Sink, org.apache.spark.sql.delta.schema.ImplicitMetadataOperation, org.apache.spark.sql.delta.metering.DeltaLogging {
  public   DeltaSink (org.apache.spark.sql.SQLContext sqlContext, org.apache.hadoop.fs.Path path, scala.collection.Seq<java.lang.String> partitionColumns, org.apache.spark.sql.streaming.OutputMode outputMode, org.apache.spark.sql.delta.DeltaOptions options)  { throw new RuntimeException(); }
  public  void addBatch (long batchId, org.apache.spark.sql.Dataset<org.apache.spark.sql.Row> data)  { throw new RuntimeException(); }
  protected  boolean canMergeSchema ()  { throw new RuntimeException(); }
  protected  boolean canOverwriteSchema ()  { throw new RuntimeException(); }
  public  java.lang.String toString ()  { throw new RuntimeException(); }
}
