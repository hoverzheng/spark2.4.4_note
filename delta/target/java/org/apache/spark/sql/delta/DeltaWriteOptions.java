package org.apache.spark.sql.delta;
public  interface DeltaWriteOptions extends org.apache.spark.sql.delta.DeltaWriteOptionsImpl, org.apache.spark.sql.delta.DeltaOptionParser {
  /**
   * Whether to add an adaptive shuffle before writing out the files to break skew, and coalesce
   * data into chunkier files.
   * @return (undocumented)
   */
  public  scala.Option<java.lang.Object> optimizeWrite ()  ;
  public  scala.Option<java.lang.String> replaceWhere ()  ;
  public  scala.Option<java.lang.String> userMetadata ()  ;
}
