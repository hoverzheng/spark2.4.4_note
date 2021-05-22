package org.apache.spark.sql.delta.util;
/** Helper for creating file names for specific commits / checkpoints. */
public  class FileNames$ {
  /**
   * Static reference to the singleton instance of this Scala object.
   */
  public static final FileNames$ MODULE$ = null;
  public   FileNames$ ()  { throw new RuntimeException(); }
  public  java.util.regex.Pattern deltaFilePattern ()  { throw new RuntimeException(); }
  public  java.util.regex.Pattern checksumFilePattern ()  { throw new RuntimeException(); }
  public  java.util.regex.Pattern checkpointFilePattern ()  { throw new RuntimeException(); }
  /** Returns the path for a given delta file. */
  public  org.apache.hadoop.fs.Path deltaFile (org.apache.hadoop.fs.Path path, long version)  { throw new RuntimeException(); }
  /** Returns the path for a given sample file */
  public  org.apache.hadoop.fs.Path sampleFile (org.apache.hadoop.fs.Path path, long version)  { throw new RuntimeException(); }
  /** Returns the path to the checksum file for the given version. */
  public  org.apache.hadoop.fs.Path checksumFile (org.apache.hadoop.fs.Path path, long version)  { throw new RuntimeException(); }
  /** Returns the version for the given delta path. */
  public  long deltaVersion (org.apache.hadoop.fs.Path path)  { throw new RuntimeException(); }
  /** Returns the version for the given checksum file. */
  public  long checksumVersion (org.apache.hadoop.fs.Path path)  { throw new RuntimeException(); }
  /**
   * Returns the prefix of all checkpoint files for the given version.
   * <p>
   * Intended for use with listFrom to get all files from this version onwards. The returned Path
   * will not exist as a file.
   * @param path (undocumented)
   * @param version (undocumented)
   * @return (undocumented)
   */
  public  org.apache.hadoop.fs.Path checkpointPrefix (org.apache.hadoop.fs.Path path, long version)  { throw new RuntimeException(); }
  /**
   * Returns the path for a singular checkpoint up to the given version.
   * <p>
   * In a future protocol version this path will stop being written.
   * @param path (undocumented)
   * @param version (undocumented)
   * @return (undocumented)
   */
  public  org.apache.hadoop.fs.Path checkpointFileSingular (org.apache.hadoop.fs.Path path, long version)  { throw new RuntimeException(); }
  /**
   * Returns the paths for all parts of the checkpoint up to the given version.
   * <p>
   * In a future protocol version we will write this path instead of checkpointFileSingular.
   * <p>
   * Example of the format: 00000000000000004915.checkpoint.0000000020.0000000060.parquet is
   * checkpoint part 20 out of 60 for the snapshot at version 4915. Zero padding is for
   * lexicographic sorting.
   * @param path (undocumented)
   * @param version (undocumented)
   * @param numParts (undocumented)
   * @return (undocumented)
   */
  public  scala.collection.Seq<org.apache.hadoop.fs.Path> checkpointFileWithParts (org.apache.hadoop.fs.Path path, long version, int numParts)  { throw new RuntimeException(); }
  public  scala.Option<java.lang.Object> numCheckpointParts (org.apache.hadoop.fs.Path path)  { throw new RuntimeException(); }
  public  boolean isCheckpointFile (org.apache.hadoop.fs.Path path)  { throw new RuntimeException(); }
  public  boolean isDeltaFile (org.apache.hadoop.fs.Path path)  { throw new RuntimeException(); }
  public  boolean isChecksumFile (org.apache.hadoop.fs.Path path)  { throw new RuntimeException(); }
  public  long checkpointVersion (org.apache.hadoop.fs.Path path)  { throw new RuntimeException(); }
  /**
   * Get the version of the checkpoint, checksum or delta file. Throws an error if an unexpected
   * file type is seen. These unexpected files should be filtered out to ensure forward
   * compatibility in cases where new file types are added, but without an explicit protocol
   * upgrade.
   * @param path (undocumented)
   * @return (undocumented)
   */
  public  long getFileVersion (org.apache.hadoop.fs.Path path)  { throw new RuntimeException(); }
}
