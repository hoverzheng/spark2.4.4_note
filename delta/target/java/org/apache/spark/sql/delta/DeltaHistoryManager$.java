package org.apache.spark.sql.delta;
/** Contains many utility methods that can also be executed on Spark executors. */
public  class DeltaHistoryManager$ implements org.apache.spark.sql.delta.metering.DeltaLogging {
  /**
   * Static reference to the singleton instance of this Scala object.
   */
  public static final DeltaHistoryManager$ MODULE$ = null;
  public   DeltaHistoryManager$ ()  { throw new RuntimeException(); }
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
    org.apache.spark.sql.delta.DeltaHistoryManager.Commit[] getCommits (org.apache.spark.sql.delta.storage.LogStore logStore, org.apache.hadoop.fs.Path logPath, long start, scala.Option<java.lang.Object> end)  { throw new RuntimeException(); }
}
