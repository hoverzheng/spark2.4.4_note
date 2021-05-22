package org.apache.spark.sql.delta.files;
/**
 * A {@link TahoeFileIndex} that generates the list of files from DeltaLog with given partition filters.
 */
public  class TahoeLogFileIndex extends org.apache.spark.sql.delta.files.TahoeFileIndex implements scala.Product, scala.Serializable {
  static public  org.apache.spark.sql.delta.files.TahoeLogFileIndex apply (org.apache.spark.sql.SparkSession spark, org.apache.spark.sql.delta.DeltaLog deltaLog)  { throw new RuntimeException(); }
  public  org.apache.spark.sql.SparkSession spark ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.delta.DeltaLog deltaLog ()  { throw new RuntimeException(); }
  public  org.apache.hadoop.fs.Path path ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.delta.Snapshot snapshotAtAnalysis ()  { throw new RuntimeException(); }
  public  scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Expression> partitionFilters ()  { throw new RuntimeException(); }
  public  boolean isTimeTravelQuery ()  { throw new RuntimeException(); }
  // not preceding
  public   TahoeLogFileIndex (org.apache.spark.sql.SparkSession spark, org.apache.spark.sql.delta.DeltaLog deltaLog, org.apache.hadoop.fs.Path path, org.apache.spark.sql.delta.Snapshot snapshotAtAnalysis, scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Expression> partitionFilters, boolean isTimeTravelQuery)  { throw new RuntimeException(); }
  public  long tableVersion ()  { throw new RuntimeException(); }
  protected  org.apache.spark.sql.delta.Snapshot getSnapshotToScan ()  { throw new RuntimeException(); }
  /** Provides the version that's being used as part of the scan if this is a time travel query. */
  public  scala.Option<java.lang.Object> versionToUse ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.delta.Snapshot getSnapshot ()  { throw new RuntimeException(); }
  public  scala.collection.Seq<org.apache.spark.sql.delta.actions.AddFile> matchingFiles (scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Expression> partitionFilters, scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Expression> dataFilters)  { throw new RuntimeException(); }
  public  java.lang.String[] inputFiles ()  { throw new RuntimeException(); }
  public  void refresh ()  { throw new RuntimeException(); }
  public  long sizeInBytes ()  { throw new RuntimeException(); }
  public  boolean equals (Object that)  { throw new RuntimeException(); }
  public  int hashCode ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.types.StructType partitionSchema ()  { throw new RuntimeException(); }
}
