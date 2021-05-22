package org.apache.spark.sql.delta;
/**
 * Record the state of the table as a checksum file along with a commit.
 */
public  interface RecordChecksum extends org.apache.spark.sql.delta.metering.DeltaLogging {
  public  org.apache.spark.sql.delta.DeltaLog deltaLog ()  ;
  public  org.apache.spark.sql.SparkSession spark ()  ;
  public  void writeChecksumFile (org.apache.spark.sql.delta.Snapshot snapshot)  ;
}
