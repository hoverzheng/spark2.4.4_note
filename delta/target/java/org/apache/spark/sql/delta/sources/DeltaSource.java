package org.apache.spark.sql.delta.sources;
/**
 * A streaming source for a Delta table.
 * <p>
 * When a new stream is started, delta starts by constructing a
 * {@link org.apache.spark.sql.delta.Snapshot} at
 * the current version of the table. This snapshot is broken up into batches until
 * all existing data has been processed. Subsequent processing is done by tailing
 * the change log looking for new data. This results in the streaming query returning
 * the same answer as a batch query that had processed the entire dataset at any given point.
 */
public  class DeltaSource implements org.apache.spark.sql.execution.streaming.Source, org.apache.spark.sql.connector.read.streaming.SupportsAdmissionControl, org.apache.spark.sql.delta.metering.DeltaLogging, scala.Product, scala.Serializable {
  static public abstract  R apply (T1 v1, T2 v2, T3 v3, T4 v4)  ;
  public  org.apache.spark.sql.SparkSession spark ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.delta.DeltaLog deltaLog ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.delta.DeltaOptions options ()  { throw new RuntimeException(); }
  public  scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Expression> filters ()  { throw new RuntimeException(); }
  // not preceding
  public   DeltaSource (org.apache.spark.sql.SparkSession spark, org.apache.spark.sql.delta.DeltaLog deltaLog, org.apache.spark.sql.delta.DeltaOptions options, scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Expression> filters)  { throw new RuntimeException(); }
  public  org.apache.spark.sql.types.StructType schema ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.connector.read.streaming.ReadLimit getDefaultReadLimit ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.connector.read.streaming.Offset latestOffset (org.apache.spark.sql.connector.read.streaming.Offset startOffset, org.apache.spark.sql.connector.read.streaming.ReadLimit limit)  { throw new RuntimeException(); }
  public  scala.Option<org.apache.spark.sql.execution.streaming.Offset> getOffset ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.Dataset<org.apache.spark.sql.Row> getBatch (scala.Option<org.apache.spark.sql.execution.streaming.Offset> start, org.apache.spark.sql.execution.streaming.Offset end)  { throw new RuntimeException(); }
  public  void stop ()  { throw new RuntimeException(); }
  public  java.lang.String toString ()  { throw new RuntimeException(); }
}
