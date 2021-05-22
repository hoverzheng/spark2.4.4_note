package org.apache.spark.sql.delta;
/**
 * Manages the creation, computation, and access of Snapshot's for Delta tables. Responsibilities
 * include:
 *  - Figuring out the set of files that are required to compute a specific version of a table
 *  - Updating and exposing the latest snapshot of the Delta table in a thread-safe manner
 */
public  interface SnapshotManagement {
  /** The timestamp when the last successful update action is finished. */
  public  long lastUpdateTimestamp ()  ;
  public  org.apache.spark.sql.delta.Snapshot currentSnapshot ()  ;
  /**
   * Get the LogSegment that will help in computing the Snapshot of the table at DeltaLog
   * initialization.
   * <p>
   * @param startingCheckpoint A checkpoint that we can start our listing from
   * @return (undocumented)
   */
  public  org.apache.spark.sql.delta.LogSegment getLogSegmentFrom (scala.Option<org.apache.spark.sql.delta.CheckpointMetaData> startingCheckpoint)  ;
  /** Get an iterator of files in the _delta_log directory starting with the startVersion. */
  public  scala.collection.Iterator<org.apache.hadoop.fs.FileStatus> listFrom (long startVersion)  ;
  /**
   * Get a list of files that can be used to compute a Snapshot at version <code>versionToLoad</code>, If
   * <code>versionToLoad</code> is not provided, will generate the list of files that are needed to load the
   * latest version of the Delta table. This method also performs checks to ensure that the delta
   * files are contiguous.
   * <p>
   * @param startCheckpoint A potential start version to perform the listing of the DeltaLog,
   *                        typically that of a known checkpoint. If this version's not provided,
   *                        we will start listing from version 0.
   * @param versionToLoad A specific version to load. Typically used with time travel and the
   *                      Delta streaming source. If not provided, we will try to load the latest
   *                      version of the table.
   * @return Some LogSegment to build a Snapshot if files do exist after the given
   *         startCheckpoint. None, if there are no new files after <code>startCheckpoint</code>.
   */
  public  org.apache.spark.sql.delta.LogSegment getLogSegmentForVersion (scala.Option<java.lang.Object> startCheckpoint, scala.Option<java.lang.Object> versionToLoad)  ;
  /**
   * Load the Snapshot for this Delta table at initialization. This method uses the <code>lastCheckpoint</code>
   * file as a hint on where to start listing the transaction log directory. If the _delta_log
   * directory doesn't exist, this method will return an <code>InitialSnapshot</code>.
   * @return (undocumented)
   */
  public  org.apache.spark.sql.delta.Snapshot getSnapshotAtInit ()  ;
  /** Returns the current snapshot. Note this does not automatically `update()`. */
  public  org.apache.spark.sql.delta.Snapshot snapshot ()  ;
  public  org.apache.spark.sql.delta.Snapshot createSnapshot (org.apache.spark.sql.delta.LogSegment segment, long minFileRetentionTimestamp, long timestamp)  ;
  /** Checks if the snapshot of the table has surpassed our allowed staleness. */
  public  boolean isSnapshotStale ()  ;
  /**
   * Update ActionLog by applying the new delta files if any.
   * <p>
   * @param stalenessAcceptable Whether we can accept working with a stale version of the table. If
   *                            the table has surpassed our staleness tolerance, we will update to
   *                            the latest state of the table synchronously. If staleness is
   *                            acceptable, and the table hasn't passed the staleness tolerance, we
   *                            will kick off a job in the background to update the table state,
   *                            and can return a stale snapshot in the meantime.
   * @return (undocumented)
   */
  public  org.apache.spark.sql.delta.Snapshot update (boolean stalenessAcceptable)  ;
  /**
   * Try to update ActionLog. If another thread is updating ActionLog, then this method returns
   * at once and return the current snapshot. The return snapshot may be stale.
   * @param isAsync (undocumented)
   * @return (undocumented)
   */
  public  org.apache.spark.sql.delta.Snapshot tryUpdate (boolean isAsync)  ;
  /**
   * Queries the store for new delta files and applies them to the current state.
   * Note: the caller should hold <code>deltaLogLock</code> before calling this method.
   * @param isAsync (undocumented)
   * @return (undocumented)
   */
  public  org.apache.spark.sql.delta.Snapshot updateInternal (boolean isAsync)  ;
  /** Get the snapshot at `version`. */
  public  org.apache.spark.sql.delta.Snapshot getSnapshotAt (long version, scala.Option<java.lang.Object> commitTimestamp, scala.Option<org.apache.spark.sql.delta.CheckpointInstance> lastCheckpointHint)  ;
  /**
   * Verify the versions are contiguous.
   * @param versions (undocumented)
   */
  public  void verifyDeltaVersions (long[] versions)  ;
}
