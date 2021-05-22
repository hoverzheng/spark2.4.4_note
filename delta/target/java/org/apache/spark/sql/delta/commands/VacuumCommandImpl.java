package org.apache.spark.sql.delta.commands;
public  interface VacuumCommandImpl extends org.apache.spark.sql.delta.commands.DeltaCommand {
  /**
   * This is used to create the list of files we want to retain during GC.
   * @param file (undocumented)
   * @param isBloomFiltered (undocumented)
   * @return (undocumented)
   */
  public  scala.collection.Seq<java.lang.String> allValidFiles (java.lang.String file, boolean isBloomFiltered)  ;
  /**
   * Attempts to delete the list of candidate files. Returns the number of files deleted.
   * @param diff (undocumented)
   * @param spark (undocumented)
   * @param basePath (undocumented)
   * @param hadoopConf (undocumented)
   * @param parallel (undocumented)
   * @return (undocumented)
   */
  public  long delete (org.apache.spark.sql.Dataset<java.lang.String> diff, org.apache.spark.sql.SparkSession spark, java.lang.String basePath, org.apache.spark.broadcast.Broadcast<org.apache.spark.util.SerializableConfiguration> hadoopConf, boolean parallel)  ;
  /**
   * Wrapper function for DeltaFileOperations.getAllSubDirectories
   * returns all subdirectories that <code>file</code> has with respect to <code>base</code>.
   * @param base (undocumented)
   * @param file (undocumented)
   * @param fs (undocumented)
   * @return (undocumented)
   */
  public  scala.collection.Iterator<java.lang.String> getAllSubdirs (java.lang.String base, java.lang.String file, org.apache.hadoop.fs.FileSystem fs)  ;
  public  java.lang.String pathToString (org.apache.hadoop.fs.Path path)  ;
  /**
   * Attempts to relativize the <code>path</code> with respect to the <code>reservoirBase</code> and converts the path to
   * a string.
   * @param path (undocumented)
   * @param fs (undocumented)
   * @param reservoirBase (undocumented)
   * @param isDir (undocumented)
   * @return (undocumented)
   */
  public  java.lang.String relativize (org.apache.hadoop.fs.Path path, org.apache.hadoop.fs.FileSystem fs, org.apache.hadoop.fs.Path reservoirBase, boolean isDir)  ;
  public  org.apache.hadoop.fs.Path stringToPath (java.lang.String path)  ;
}
