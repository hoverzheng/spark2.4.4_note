package org.apache.spark.sql.delta;
public  interface Checkpoints extends org.apache.spark.sql.delta.metering.DeltaLogging {
  public  org.apache.hadoop.fs.Path logPath ()  ;
  public  org.apache.hadoop.fs.Path dataPath ()  ;
  public  org.apache.spark.sql.delta.Snapshot snapshot ()  ;
  public  org.apache.spark.sql.delta.storage.LogStore store ()  ;
  public  org.apache.spark.sql.delta.actions.Metadata metadata ()  ;
  /** Used to clean up stale log files. */
  public  void doLogCleanup ()  ;
  /** The path to the file that holds metadata about the most recent checkpoint. */
  public  org.apache.hadoop.fs.Path LAST_CHECKPOINT ()  ;
  /** Creates a checkpoint at the current log version. */
  public  void checkpoint ()  ;
  public  org.apache.spark.sql.delta.CheckpointMetaData checkpoint (org.apache.spark.sql.delta.Snapshot snapshotToCheckpoint)  ;
  /** Returns information about the most recent checkpoint. */
    scala.Option<org.apache.spark.sql.delta.CheckpointMetaData> lastCheckpoint ()  ;
  /** Loads the checkpoint metadata from the _last_checkpoint file. */
  public  scala.Option<org.apache.spark.sql.delta.CheckpointMetaData> loadMetadataFromFile (int tries)  ;
  /** Loads the given checkpoint manually to come up with the CheckpointMetaData */
  public  org.apache.spark.sql.delta.CheckpointMetaData manuallyLoadCheckpoint (org.apache.spark.sql.delta.CheckpointInstance cv)  ;
  /**
   * Finds the first verified, complete checkpoint before the given version.
   * <p>
   * @param cv The CheckpointVersion to compare against
   * @return (undocumented)
   */
  public  scala.Option<org.apache.spark.sql.delta.CheckpointInstance> findLastCompleteCheckpoint (org.apache.spark.sql.delta.CheckpointInstance cv)  ;
  /**
   * Given a list of checkpoint files, pick the latest complete checkpoint instance which is not
   * later than <code>notLaterThan</code>.
   * @param instances (undocumented)
   * @param notLaterThan (undocumented)
   * @return (undocumented)
   */
  public  scala.Option<org.apache.spark.sql.delta.CheckpointInstance> getLatestCompleteCheckpointFromList (org.apache.spark.sql.delta.CheckpointInstance[] instances, org.apache.spark.sql.delta.CheckpointInstance notLaterThan)  ;
}
