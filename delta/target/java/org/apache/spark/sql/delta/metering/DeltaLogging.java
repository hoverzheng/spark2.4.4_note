package org.apache.spark.sql.delta.metering;
/**
 * Convenience wrappers for logging that include delta specific options and
 * avoids the need to predeclare all operations. Metrics in Delta should respect the following
 * conventions:
 *  - Tags should identify the context of the event (which shard, user, table, machine, etc).
 *  - All actions initiated by a user should be wrapped in a recordOperation so we can track usage
 *    latency and failures. If there is a significant (more than a few seconds) subaction like
 *    identifying candidate files, consider nested recordOperation.
 *  - Events should be used to return detailed statistics about usage. Generally these should be
 *    defined with a case class to ease analysis later.
 *  - Events can also be used to record that a particular codepath was hit (i.e. a checkpoint
 *    failure, a conflict, or a specific optimization).
 *  - Both events and operations should be named hierarchically to allow for analysis at different
 *    levels. For example, to look at the latency of all DDL operations we could scan for operations
 *    that match "delta.ddl.%".
 * <p>
 *  Underneath these functions use the standard usage log reporting defined in
 *  {@link com.databricks.spark.util.DatabricksLogging}.
 */
public  interface DeltaLogging extends org.apache.spark.sql.delta.util.DeltaProgressReporter, com.databricks.spark.util.DatabricksLogging {
  /**
   * Used to record the occurrence of a single event or report detailed, operation specific
   * statistics.
   * @param deltaLog (undocumented)
   * @param opType (undocumented)
   * @param tags (undocumented)
   * @param data (undocumented)
   */
  public  void recordDeltaEvent (org.apache.spark.sql.delta.DeltaLog deltaLog, java.lang.String opType, scala.collection.immutable.Map<com.databricks.spark.util.TagDefinition, java.lang.String> tags, java.lang.Object data)  ;
  /**
   * Used to report the duration as well as the success or failure of an operation.
   * @param deltaLog (undocumented)
   * @param opType (undocumented)
   * @param tags (undocumented)
   * @param thunk (undocumented)
   * @return (undocumented)
   */
  public <A extends java.lang.Object> A recordDeltaOperation (org.apache.spark.sql.delta.DeltaLog deltaLog, java.lang.String opType, scala.collection.immutable.Map<com.databricks.spark.util.TagDefinition, java.lang.String> tags, scala.Function0<A> thunk)  ;
}
