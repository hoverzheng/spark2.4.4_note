package org.apache.spark.sql.delta;
public  class DeltaLogFileIndex$ implements scala.Serializable {
  /**
   * Static reference to the singleton instance of this Scala object.
   */
  public static final DeltaLogFileIndex$ MODULE$ = null;
  public   DeltaLogFileIndex$ ()  { throw new RuntimeException(); }
  // not preceding
  public  org.apache.spark.sql.execution.datasources.json.JsonFileFormat COMMIT_FILE_FORMAT ()  { throw new RuntimeException(); }
  // not preceding
  public  org.apache.spark.sql.execution.datasources.parquet.ParquetFileFormat CHECKPOINT_FILE_FORMAT ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.delta.DeltaLogFileIndex apply (org.apache.spark.sql.execution.datasources.FileFormat format, org.apache.hadoop.fs.FileSystem fs, scala.collection.Seq<org.apache.hadoop.fs.Path> paths)  { throw new RuntimeException(); }
  public  scala.Option<org.apache.spark.sql.delta.DeltaLogFileIndex> apply (org.apache.spark.sql.execution.datasources.FileFormat format, scala.collection.Seq<org.apache.hadoop.fs.FileStatus> files)  { throw new RuntimeException(); }
}
