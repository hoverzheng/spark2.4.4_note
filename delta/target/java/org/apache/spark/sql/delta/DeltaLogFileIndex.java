package org.apache.spark.sql.delta;
/**
 * A specialized file index for files found in the _delta_log directory. By using this file index,
 * we avoid any additional file listing, partitioning inference, and file existence checks when
 * computing the state of a Delta table.
 * <p>
 * param:  format The file format of the log files. Currently "parquet" or "json"
 * param:  files The files to read
 */
public  class DeltaLogFileIndex implements org.apache.spark.sql.execution.datasources.FileIndex, org.apache.spark.internal.Logging, scala.Product, scala.Serializable {
  // not preceding
  static public  org.apache.spark.sql.execution.datasources.json.JsonFileFormat COMMIT_FILE_FORMAT ()  { throw new RuntimeException(); }
  // not preceding
  static public  org.apache.spark.sql.execution.datasources.parquet.ParquetFileFormat CHECKPOINT_FILE_FORMAT ()  { throw new RuntimeException(); }
  static public  org.apache.spark.sql.delta.DeltaLogFileIndex apply (org.apache.spark.sql.execution.datasources.FileFormat format, org.apache.hadoop.fs.FileSystem fs, scala.collection.Seq<org.apache.hadoop.fs.Path> paths)  { throw new RuntimeException(); }
  static public  scala.Option<org.apache.spark.sql.delta.DeltaLogFileIndex> apply (org.apache.spark.sql.execution.datasources.FileFormat format, scala.collection.Seq<org.apache.hadoop.fs.FileStatus> files)  { throw new RuntimeException(); }
  public  org.apache.spark.sql.execution.datasources.FileFormat format ()  { throw new RuntimeException(); }
  public  org.apache.hadoop.fs.FileStatus[] files ()  { throw new RuntimeException(); }
  // not preceding
  public  scala.collection.Seq<org.apache.hadoop.fs.Path> rootPaths ()  { throw new RuntimeException(); }
  public  scala.collection.Seq<org.apache.spark.sql.execution.datasources.PartitionDirectory> listFiles (scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Expression> partitionFilters, scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Expression> dataFilters)  { throw new RuntimeException(); }
  public  java.lang.String[] inputFiles ()  { throw new RuntimeException(); }
  public  void refresh ()  { throw new RuntimeException(); }
  public  long sizeInBytes ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.types.StructType partitionSchema ()  { throw new RuntimeException(); }
  public  java.lang.String toString ()  { throw new RuntimeException(); }
}
