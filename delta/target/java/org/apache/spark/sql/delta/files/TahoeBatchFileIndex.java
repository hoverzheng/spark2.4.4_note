package org.apache.spark.sql.delta.files;
/**
 * A {@link TahoeFileIndex} that generates the list of files from a given list of files
 * that are within a version range of DeltaLog.
 */
public  class TahoeBatchFileIndex extends org.apache.spark.sql.delta.files.TahoeFileIndex {
  // not preceding
  public   TahoeBatchFileIndex (org.apache.spark.sql.SparkSession spark, java.lang.String actionType, scala.collection.Seq<org.apache.spark.sql.delta.actions.AddFile> addFiles, org.apache.spark.sql.delta.DeltaLog deltaLog, org.apache.hadoop.fs.Path path, org.apache.spark.sql.delta.Snapshot snapshot)  { throw new RuntimeException(); }
  public  java.lang.String actionType ()  { throw new RuntimeException(); }
  public  scala.collection.Seq<org.apache.spark.sql.delta.actions.AddFile> addFiles ()  { throw new RuntimeException(); }
  public  java.lang.String[] inputFiles ()  { throw new RuntimeException(); }
  public  scala.collection.Seq<org.apache.spark.sql.delta.actions.AddFile> matchingFiles (scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Expression> partitionFilters, scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Expression> dataFilters)  { throw new RuntimeException(); }
  public  org.apache.spark.sql.types.StructType partitionSchema ()  { throw new RuntimeException(); }
  public  void refresh ()  { throw new RuntimeException(); }
  public  long sizeInBytes ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.SparkSession spark ()  { throw new RuntimeException(); }
  public  long tableVersion ()  { throw new RuntimeException(); }
}
