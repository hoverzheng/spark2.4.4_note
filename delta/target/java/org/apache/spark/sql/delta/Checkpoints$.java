package org.apache.spark.sql.delta;
public  class Checkpoints$ implements org.apache.spark.sql.delta.metering.DeltaLogging {
  /**
   * Static reference to the singleton instance of this Scala object.
   */
  public static final Checkpoints$ MODULE$ = null;
  public   Checkpoints$ ()  { throw new RuntimeException(); }
  /**
   * Writes out the contents of a {@link Snapshot} into a checkpoint file that
   * can be used to short-circuit future replays of the log.
   * <p>
   * Returns the checkpoint metadata to be committed to a file. We will use the value
   * in this file as the source of truth of the last valid checkpoint.
   * @param spark (undocumented)
   * @param deltaLog (undocumented)
   * @param snapshot (undocumented)
   * @return (undocumented)
   */
    org.apache.spark.sql.delta.CheckpointMetaData writeCheckpoint (org.apache.spark.sql.SparkSession spark, org.apache.spark.sql.delta.DeltaLog deltaLog, org.apache.spark.sql.delta.Snapshot snapshot)  { throw new RuntimeException(); }
  /**
   * Modify the contents of the add column based on the table properties
   * @param state (undocumented)
   * @param snapshot (undocumented)
   * @return (undocumented)
   */
    org.apache.spark.sql.Dataset<org.apache.spark.sql.Row> buildCheckpoint (org.apache.spark.sql.Dataset<org.apache.spark.sql.Row> state, org.apache.spark.sql.delta.Snapshot snapshot)  { throw new RuntimeException(); }
}
