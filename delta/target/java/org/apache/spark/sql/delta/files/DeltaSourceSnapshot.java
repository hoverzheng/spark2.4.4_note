package org.apache.spark.sql.delta.files;
/**
 * Converts a <code>Snapshot</code> into the initial set of files read when starting a new streaming query.
 * The list of files that represent the table at the time the query starts are selected by:
 * - Adding <code>version</code> and <code>index</code> to each file to enable splitting of the initial state into
 *   multiple batches.
 * - Filtering files that don't match partition predicates, while preserving the aforementioned
 *   indexing.
 */
public  class DeltaSourceSnapshot implements org.apache.spark.sql.delta.files.SnapshotIterator, org.apache.spark.sql.delta.util.StateCache {
  // not preceding
  public   DeltaSourceSnapshot (org.apache.spark.sql.SparkSession spark, org.apache.spark.sql.delta.Snapshot snapshot, scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Expression> filters)  { throw new RuntimeException(); }
  public  void close (boolean unpersistSnapshot)  { throw new RuntimeException(); }
  protected  scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Expression> dataFilters ()  { throw new RuntimeException(); }
  public  scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Expression> filters ()  { throw new RuntimeException(); }
  protected  org.apache.spark.sql.Dataset<org.apache.spark.sql.delta.sources.IndexedFile> initialFiles ()  { throw new RuntimeException(); }
  protected  scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Expression> partitionFilters ()  { throw new RuntimeException(); }
  protected  org.apache.hadoop.fs.Path path ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.delta.Snapshot snapshot ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.SparkSession spark ()  { throw new RuntimeException(); }
  protected  long version ()  { throw new RuntimeException(); }
}
