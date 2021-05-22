package org.apache.spark.sql.delta.commands;
/**
 * Vacuums the table by clearing all untracked files and folders within this table.
 * First lists all the files and directories in the table, and gets the relative paths with
 * respect to the base of the table. Then it gets the list of all tracked files for this table,
 * which may or may not be within the table base path, and gets the relative paths of
 * all the tracked files with respect to the base of the table. Files outside of the table path
 * will be ignored. Then we take a diff of the files and delete directories that were already empty,
 * and all files that are within the table that are no longer tracked.
 */
public  class VacuumCommand$ implements org.apache.spark.sql.delta.commands.VacuumCommandImpl, scala.Serializable {
  /**
   * Static reference to the singleton instance of this Scala object.
   */
  public static final VacuumCommand$ MODULE$ = null;
  public   VacuumCommand$ ()  { throw new RuntimeException(); }
  /**
   * Additional check on retention duration to prevent people from shooting themselves in the foot.
   * @param spark (undocumented)
   * @param retentionMs (undocumented)
   * @param configuredRetention (undocumented)
   */
  protected  void checkRetentionPeriodSafety (org.apache.spark.sql.SparkSession spark, scala.Option<java.lang.Object> retentionMs, long configuredRetention)  { throw new RuntimeException(); }
  /**
   * Clears all untracked files and folders within this table. First lists all the files and
   * directories in the table, and gets the relative paths with respect to the base of the
   * table. Then it gets the list of all tracked files for this table, which may or may not
   * be within the table base path, and gets the relative paths of all the tracked files with
   * respect to the base of the table. Files outside of the table path will be ignored.
   * Then we take a diff of the files and delete directories that were already empty, and all files
   * that are within the table that are no longer tracked.
   * <p>
   * @param dryRun If set to true, no files will be deleted. Instead, we will list all files and
   *               directories that will be cleared.
   * @param retentionHours An optional parameter to override the default Delta tombstone retention
   *                       period
   * @return A Dataset containing the paths of the files/folders to delete in dryRun mode. Otherwise
   *         returns the base path of the table.
   * @param spark (undocumented)
   * @param deltaLog (undocumented)
   * @param clock (undocumented)
   */
  public  org.apache.spark.sql.Dataset<org.apache.spark.sql.Row> gc (org.apache.spark.sql.SparkSession spark, org.apache.spark.sql.delta.DeltaLog deltaLog, boolean dryRun, scala.Option<java.lang.Object> retentionHours, org.apache.spark.util.Clock clock)  { throw new RuntimeException(); }
}
