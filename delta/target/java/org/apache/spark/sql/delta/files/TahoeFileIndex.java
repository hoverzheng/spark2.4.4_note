package org.apache.spark.sql.delta.files;
/**
 * A {@link FileIndex} that generates the list of files managed by the Tahoe protocol.
 */
public abstract class TahoeFileIndex implements org.apache.spark.sql.execution.datasources.FileIndex {
  // not preceding
  public   TahoeFileIndex (org.apache.spark.sql.SparkSession spark, org.apache.spark.sql.delta.DeltaLog deltaLog, org.apache.hadoop.fs.Path path)  { throw new RuntimeException(); }
  protected  org.apache.hadoop.fs.Path absolutePath (java.lang.String child)  { throw new RuntimeException(); }
  public  org.apache.spark.sql.delta.DeltaLog deltaLog ()  { throw new RuntimeException(); }
  /**
   * Returns the path of the base directory of the given file path (i.e. its parent directory with
   * all the partition directories stripped off).
   * @param filePath (undocumented)
   * @return (undocumented)
   */
  public  scala.Option<org.apache.hadoop.fs.Path> getBasePath (org.apache.hadoop.fs.Path filePath)  { throw new RuntimeException(); }
  public  scala.collection.Seq<org.apache.spark.sql.execution.datasources.PartitionDirectory> listFiles (scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Expression> partitionFilters, scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Expression> dataFilters)  { throw new RuntimeException(); }
  /**
   * Returns all matching/valid files by the given <code>partitionFilters</code> and <code>dataFilters</code>
   * @param partitionFilters (undocumented)
   * @param dataFilters (undocumented)
   * @return (undocumented)
   */
  public abstract  scala.collection.Seq<org.apache.spark.sql.delta.actions.AddFile> matchingFiles (scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Expression> partitionFilters, scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Expression> dataFilters)  ;
  public  org.apache.spark.sql.types.StructType partitionSchema ()  { throw new RuntimeException(); }
  public  org.apache.hadoop.fs.Path path ()  { throw new RuntimeException(); }
  public  scala.collection.Seq<org.apache.hadoop.fs.Path> rootPaths ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.SparkSession spark ()  { throw new RuntimeException(); }
  public  long tableVersion ()  { throw new RuntimeException(); }
  public  java.lang.String toString ()  { throw new RuntimeException(); }
}
