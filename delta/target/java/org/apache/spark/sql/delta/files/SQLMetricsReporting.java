package org.apache.spark.sql.delta.files;
/**
 * This trait is used to register SQL metrics for a Delta Operation.
 * Registering will allow the metrics to be instrumented via the CommitInfo and is accessible via
 * DescribeHistory
 */
public  interface SQLMetricsReporting {
  /**
   * Get the metrics for an operation based on collected SQL Metrics and filtering out
   * the ones based on the metric parameters for that operation.
   * @param operation (undocumented)
   * @return (undocumented)
   */
  public  scala.collection.immutable.Map<java.lang.String, java.lang.String> getMetricsForOperation (org.apache.spark.sql.delta.DeltaOperations.Operation operation)  ;
  /**
   * Register SQL metrics for an operation by appending the supplied metrics map to the
   * operationSQLMetrics map.
   * @param spark (undocumented)
   * @param metrics (undocumented)
   */
  public  void registerSQLMetrics (org.apache.spark.sql.SparkSession spark, scala.collection.immutable.Map<java.lang.String, org.apache.spark.sql.execution.metric.SQLMetric> metrics)  ;
}
