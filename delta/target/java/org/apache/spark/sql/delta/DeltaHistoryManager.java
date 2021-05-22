package org.apache.spark.sql.delta;
/**
 * This class keeps tracks of the version of commits and their timestamps for a Delta table to
 * help with operations like describing the history of a table.
 * <p>
 * param:  deltaLog The transaction log of this table
 * param:  maxKeysPerList How many commits to list when performing a parallel search. Exposed for
 *                       tests. Currently set to <code>1000</code>, which is the maximum keys returned by S3
 *                       per list call. Azure can return <code>5000</code>, therefore we choose 1000.
 */
public  class DeltaHistoryManager implements org.apache.spark.sql.delta.metering.DeltaLogging {
  /** A helper class to represent the timestamp and version of a commit. */
  static public  class Commit implements org.apache.spark.sql.delta.actions.CommitMarker, scala.Product, scala.Serializable {
    public  long version ()  { throw new RuntimeException(); }
    public  long timestamp ()  { throw new RuntimeException(); }
    // not preceding
    public   Commit (long version, long timestamp)  { throw new RuntimeException(); }
    public  org.apache.spark.sql.delta.DeltaHistoryManager.Commit withTimestamp (long timestamp)  { throw new RuntimeException(); }
    public  long getTimestamp ()  { throw new RuntimeException(); }
    public  long getVersion ()  { throw new RuntimeException(); }
  }
  static public  class Commit$ extends scala.runtime.AbstractFunction2<java.lang.Object, java.lang.Object, org.apache.spark.sql.delta.DeltaHistoryManager.Commit> implements scala.Serializable {
    /**
     * Static reference to the singleton instance of this Scala object.
     */
    public static final Commit$ MODULE$ = null;
    public   Commit$ ()  { throw new RuntimeException(); }
  }
  /**
   * An iterator that helps select old log files for deletion. It takes the input iterator of log
   * files from the earliest file, and returns should-be-deleted files until the given maxTimestamp
   * or maxVersion to delete is reached. Note that this iterator may stop deleting files earlier
   * than maxTimestamp or maxVersion if it finds that files that need to be preserved for adjusting
   * the timestamps of subsequent files. Let's go through an example. Assume the following commit
   * history:
   * <p>
   * +---------+-----------+--------------------+
   * | Version | Timestamp | Adjusted Timestamp |
   * +---------+-----------+--------------------+
   * |       0 |         0 |                  0 |
   * |       1 |         5 |                  5 |
   * |       2 |        10 |                 10 |
   * |       3 |         7 |                 11 |
   * |       4 |         8 |                 12 |
   * |       5 |        14 |                 14 |
   * +---------+-----------+--------------------+
   * <p>
   * As you can see from the example, we require timestamps to be monotonically increasing with
   * respect to the version of the commit, and each commit to have a unique timestamp. If we have
   * a commit which doesn't obey one of these two requirements, we adjust the timestamp of that
   * commit to be one millisecond greater than the previous commit.
   * <p>
   * Given the above commit history, the behavior of this iterator will be as follows:
   *  - For maxVersion = 1 and maxTimestamp = 9, we can delete versions 0 and 1
   *  - Until we receive maxVersion >= 4 and maxTimestamp >= 12, we can't delete versions 2 and 3.
   *    This is because version 2 is used to adjust the timestamps of commits up to version 4.
   *  - For maxVersion >= 5 and maxTimestamp >= 14 we can delete everything
   * The semantics of time travel guarantee that for a given timestamp, the user will ALWAYS get the
   * same version. Consider a user asks to get the version at timestamp 11. If all files are there,
   * we would return version 3 (timestamp 11) for this query. If we delete versions 0-2, the
   * original timestamp of version 3 (7) will not have an anchor to adjust on, and if the time
   * travel query is re-executed we would return version 4. This is the motivation behind this
   * iterator implementation.
   * <p>
   * The implementation maintains an internal "maybeDelete" buffer of files that we are unsure of
   * deleting because they may be necessary to adjust time of future files. For each file we get
   * from the underlying iterator, we check whether it needs time adjustment or not. If it does need
   * time adjustment, then we cannot immediately decide whether it is safe to delete that file or
   * not and therefore we put it in each the buffer. Then we iteratively peek ahead at the future
   * files and accordingly decide whether to delete all the buffered files or retain them.
   * <p>
   * param:  underlying The iterator which gives the list of files in ascending version order
   * param:  maxTimestamp The timestamp until which we can delete (inclusive).
   * param:  maxVersion The version until which we can delete (inclusive).
   * param:  versionGetter A method to get the commit version from the file path.
   */
  static public  class BufferingLogDeletionIterator implements scala.collection.Iterator<org.apache.hadoop.fs.FileStatus> {
    public   BufferingLogDeletionIterator (scala.collection.Iterator<org.apache.hadoop.fs.FileStatus> underlying, long maxTimestamp, long maxVersion, scala.Function1<org.apache.hadoop.fs.Path, java.lang.Object> versionGetter)  { throw new RuntimeException(); }
    public  boolean hasNext ()  { throw new RuntimeException(); }
    public  org.apache.hadoop.fs.FileStatus next ()  { throw new RuntimeException(); }
  }
  /**
   * Returns the commit version and timestamps of all commits in <code>[start, end)</code>. If <code>end</code> is not
   * specified, will return all commits that exist after <code>start</code>. Will guarantee that the commits
   * returned will have both monotonically increasing versions as well as timestamps.
   * Exposed for tests.
   * @param logStore (undocumented)
   * @param logPath (undocumented)
   * @param start (undocumented)
   * @param end (undocumented)
   * @return (undocumented)
   */
  static   org.apache.spark.sql.delta.DeltaHistoryManager.Commit[] getCommits (org.apache.spark.sql.delta.storage.LogStore logStore, org.apache.hadoop.fs.Path logPath, long start, scala.Option<java.lang.Object> end)  { throw new RuntimeException(); }
  static public  org.slf4j.Logger org$apache$spark$internal$Logging$$log_ ()  { throw new RuntimeException(); }
  static public  void org$apache$spark$internal$Logging$$log__$eq (org.slf4j.Logger x$1)  { throw new RuntimeException(); }
  static protected  java.lang.String logName ()  { throw new RuntimeException(); }
  static protected  org.slf4j.Logger log ()  { throw new RuntimeException(); }
  static protected  void logInfo (scala.Function0<java.lang.String> msg)  { throw new RuntimeException(); }
  static protected  void logDebug (scala.Function0<java.lang.String> msg)  { throw new RuntimeException(); }
  static protected  void logTrace (scala.Function0<java.lang.String> msg)  { throw new RuntimeException(); }
  static protected  void logWarning (scala.Function0<java.lang.String> msg)  { throw new RuntimeException(); }
  static protected  void logError (scala.Function0<java.lang.String> msg)  { throw new RuntimeException(); }
  static protected  void logInfo (scala.Function0<java.lang.String> msg, java.lang.Throwable throwable)  { throw new RuntimeException(); }
  static protected  void logDebug (scala.Function0<java.lang.String> msg, java.lang.Throwable throwable)  { throw new RuntimeException(); }
  static protected  void logTrace (scala.Function0<java.lang.String> msg, java.lang.Throwable throwable)  { throw new RuntimeException(); }
  static protected  void logWarning (scala.Function0<java.lang.String> msg, java.lang.Throwable throwable)  { throw new RuntimeException(); }
  static protected  void logError (scala.Function0<java.lang.String> msg, java.lang.Throwable throwable)  { throw new RuntimeException(); }
  static protected  boolean isTraceEnabled ()  { throw new RuntimeException(); }
  static protected  void initializeLogIfNecessary (boolean isInterpreter)  { throw new RuntimeException(); }
  static protected  boolean initializeLogIfNecessary (boolean isInterpreter, boolean silent)  { throw new RuntimeException(); }
  static protected  boolean initializeLogIfNecessary$default$2 ()  { throw new RuntimeException(); }
  static   void initializeForcefully (boolean isInterpreter, boolean silent)  { throw new RuntimeException(); }
  static public <T extends java.lang.Object> T withStatusCode (java.lang.String statusCode, java.lang.String defaultMessage, scala.collection.immutable.Map<java.lang.String, java.lang.Object> data, scala.Function0<T> body)  { throw new RuntimeException(); }
  static public <T extends java.lang.Object> scala.collection.immutable.Map<java.lang.String, java.lang.Object> withStatusCode$default$3 ()  { throw new RuntimeException(); }
  static public  void logConsole (java.lang.String line)  { throw new RuntimeException(); }
  static public  void recordUsage (com.databricks.spark.util.MetricDefinition metric, double quantity, scala.collection.immutable.Map<com.databricks.spark.util.TagDefinition, java.lang.String> additionalTags, java.lang.String blob, boolean forceSample, boolean trimBlob, boolean silent)  { throw new RuntimeException(); }
  static public  scala.collection.immutable.Map<com.databricks.spark.util.TagDefinition, java.lang.String> recordUsage$default$3 ()  { throw new RuntimeException(); }
  static public  java.lang.String recordUsage$default$4 ()  { throw new RuntimeException(); }
  static public  boolean recordUsage$default$5 ()  { throw new RuntimeException(); }
  static public  boolean recordUsage$default$6 ()  { throw new RuntimeException(); }
  static public  boolean recordUsage$default$7 ()  { throw new RuntimeException(); }
  static public  void recordEvent (com.databricks.spark.util.MetricDefinition metric, scala.collection.immutable.Map<com.databricks.spark.util.TagDefinition, java.lang.String> additionalTags, java.lang.String blob, boolean trimBlob)  { throw new RuntimeException(); }
  static public  scala.collection.immutable.Map<com.databricks.spark.util.TagDefinition, java.lang.String> recordEvent$default$2 ()  { throw new RuntimeException(); }
  static public  java.lang.String recordEvent$default$3 ()  { throw new RuntimeException(); }
  static public  boolean recordEvent$default$4 ()  { throw new RuntimeException(); }
  static public <S extends java.lang.Object> S recordOperation (com.databricks.spark.util.OpType opType, java.lang.String opTarget, scala.collection.immutable.Map<com.databricks.spark.util.TagDefinition, java.lang.String> extraTags, boolean isSynchronous, boolean alwaysRecordStats, boolean allowAuthTags, boolean killJvmIfStuck, com.databricks.spark.util.MetricDefinition outputMetric, boolean silent, scala.Function0<S> thunk)  { throw new RuntimeException(); }
  static public <S extends java.lang.Object> java.lang.String recordOperation$default$2 ()  { throw new RuntimeException(); }
  static public <S extends java.lang.Object> boolean recordOperation$default$4 ()  { throw new RuntimeException(); }
  static public <S extends java.lang.Object> boolean recordOperation$default$5 ()  { throw new RuntimeException(); }
  static public <S extends java.lang.Object> boolean recordOperation$default$6 ()  { throw new RuntimeException(); }
  static public <S extends java.lang.Object> boolean recordOperation$default$7 ()  { throw new RuntimeException(); }
  static public <S extends java.lang.Object> com.databricks.spark.util.MetricDefinition recordOperation$default$8 ()  { throw new RuntimeException(); }
  static public <S extends java.lang.Object> boolean recordOperation$default$9 ()  { throw new RuntimeException(); }
  static protected  void recordDeltaEvent (org.apache.spark.sql.delta.DeltaLog deltaLog, java.lang.String opType, scala.collection.immutable.Map<com.databricks.spark.util.TagDefinition, java.lang.String> tags, java.lang.Object data)  { throw new RuntimeException(); }
  static protected  scala.collection.immutable.Map<com.databricks.spark.util.TagDefinition, java.lang.String> recordDeltaEvent$default$3 ()  { throw new RuntimeException(); }
  static protected  java.lang.Object recordDeltaEvent$default$4 ()  { throw new RuntimeException(); }
  static protected <A extends java.lang.Object> A recordDeltaOperation (org.apache.spark.sql.delta.DeltaLog deltaLog, java.lang.String opType, scala.collection.immutable.Map<com.databricks.spark.util.TagDefinition, java.lang.String> tags, scala.Function0<A> thunk)  { throw new RuntimeException(); }
  static protected <A extends java.lang.Object> scala.collection.immutable.Map<com.databricks.spark.util.TagDefinition, java.lang.String> recordDeltaOperation$default$3 ()  { throw new RuntimeException(); }
  public   DeltaHistoryManager (org.apache.spark.sql.delta.DeltaLog deltaLog, int maxKeysPerList)  { throw new RuntimeException(); }
  /**
   * Returns the information of the latest <code>limit</code> commits made to this table in reverse
   * chronological order.
   * @param limitOpt (undocumented)
   * @return (undocumented)
   */
  public  scala.collection.Seq<org.apache.spark.sql.delta.actions.CommitInfo> getHistory (scala.Option<java.lang.Object> limitOpt)  { throw new RuntimeException(); }
  /**
   * Get the commit information of the Delta table from commit <code>[start, end)</code>. If <code>end</code> is <code>None</code>,
   * we return all commits from start to now.
   * @param start (undocumented)
   * @param end (undocumented)
   * @return (undocumented)
   */
  public  scala.collection.Seq<org.apache.spark.sql.delta.actions.CommitInfo> getHistory (long start, scala.Option<java.lang.Object> end)  { throw new RuntimeException(); }
  /**
   * Returns the latest commit that happened at or before <code>time</code>.
   * @param timestamp The timestamp to search for
   * @param canReturnLastCommit Whether we can return the latest version of the table if the
   *                            provided timestamp is after the latest commit
   * @param mustBeRecreatable Whether the state at the given commit should be recreatable
   * @param canReturnEarliestCommit Whether we can return the earliest commit if no such commit
   *                                exists.
   * @return (undocumented)
   */
  public  org.apache.spark.sql.delta.DeltaHistoryManager.Commit getActiveCommitAtTime (java.sql.Timestamp timestamp, boolean canReturnLastCommit, boolean mustBeRecreatable, boolean canReturnEarliestCommit)  { throw new RuntimeException(); }
  /**
   * Check whether the given version exists.
   * @param mustBeRecreatable whether the snapshot of this version needs to be recreated.
   * @param version (undocumented)
   */
  public  void checkVersionExists (long version, boolean mustBeRecreatable)  { throw new RuntimeException(); }
  /**
   * Get the earliest commit, which we can recreate. Note that this version isn't guaranteed to
   * exist when performing an action as a concurrent operation can delete the file during cleanup.
   * This value must be used as a lower bound.
   * <p>
   * We search for the earliest checkpoint we have, or whether we have the 0th delta file, because
   * that way we can reconstruct the entire history of the table. This method assumes that the
   * commits are contiguous.
   * @return (undocumented)
   */
    long getEarliestReproducibleCommit ()  { throw new RuntimeException(); }
}
