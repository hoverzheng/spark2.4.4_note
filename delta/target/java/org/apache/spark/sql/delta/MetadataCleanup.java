package org.apache.spark.sql.delta;
/** Cleans up expired Delta table metadata. */
public  interface MetadataCleanup extends org.apache.spark.sql.delta.metering.DeltaLogging {
  /** Clean up expired delta and checkpoint logs. Exposed for testing. */
    void cleanUpExpiredLogs ()  ;
  /**
   * Returns the duration in millis for how long to keep around obsolete logs. We may keep logs
   * beyond this duration until the next calendar day to avoid constantly creating checkpoints.
   * @return (undocumented)
   */
  public  long deltaRetentionMillis ()  ;
  public  void doLogCleanup ()  ;
  /** Whether to clean up expired log files and checkpoints. */
  public  boolean enableExpiredLogCleanup ()  ;
  /**
   * Returns an iterator of expired delta logs that can be cleaned up. For a delta log to be
   * considered as expired, it must:
   *  - have a checkpoint file after it
   *  - be older than <code>fileCutOffTime</code>
   * @param fileCutOffTime (undocumented)
   * @return (undocumented)
   */
  public  scala.collection.Iterator<org.apache.hadoop.fs.FileStatus> listExpiredDeltaLogs (long fileCutOffTime)  ;
  /** Truncates a timestamp down to the previous midnight and returns the time and a log string */
    java.util.Calendar truncateDay (long timeMillis)  ;
}
