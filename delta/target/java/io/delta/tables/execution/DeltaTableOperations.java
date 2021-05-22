package io.delta.tables.execution;
/**
 * Interface to provide the actual implementations of DeltaTable operations.
 */
public  interface DeltaTableOperations extends org.apache.spark.sql.delta.util.AnalysisHelper {
  public  void executeDelete (scala.Option<org.apache.spark.sql.catalyst.expressions.Expression> condition)  ;
  public  void executeGenerate (java.lang.String tblIdentifier, java.lang.String mode)  ;
  public  org.apache.spark.sql.Dataset<org.apache.spark.sql.Row> executeHistory (org.apache.spark.sql.delta.DeltaLog deltaLog, scala.Option<java.lang.Object> limit)  ;
  public  void executeUpdate (scala.collection.Map<java.lang.String, org.apache.spark.sql.Column> set, scala.Option<org.apache.spark.sql.Column> condition)  ;
  public  org.apache.spark.sql.Dataset<org.apache.spark.sql.Row> executeVacuum (org.apache.spark.sql.delta.DeltaLog deltaLog, scala.Option<java.lang.Object> retentionHours)  ;
  public  org.apache.spark.sql.SparkSession sparkSession ()  ;
  public  scala.collection.Map<java.lang.String, org.apache.spark.sql.Column> toStrColumnMap (scala.collection.Map<java.lang.String, java.lang.String> map)  ;
}
