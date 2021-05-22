package org.apache.spark.sql.delta;
/**
 * Verify the state of the table using the checksum information.
 */
public  interface ValidateChecksum extends org.apache.spark.sql.delta.metering.DeltaLogging {
  public  scala.Option<java.lang.String> checkMismatch (org.apache.spark.sql.delta.VersionChecksum checksum)  ;
  public  void validateChecksum ()  ;
}
