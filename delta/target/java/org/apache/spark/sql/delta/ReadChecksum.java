package org.apache.spark.sql.delta;
/**
 * Read checksum files.
 */
public  interface ReadChecksum extends org.apache.spark.sql.delta.metering.DeltaLogging {
  public  org.apache.hadoop.fs.Path logPath ()  ;
  public  scala.Option<org.apache.spark.sql.delta.VersionChecksum> readChecksum (long version)  ;
    org.apache.spark.sql.delta.storage.LogStore store ()  ;
}
