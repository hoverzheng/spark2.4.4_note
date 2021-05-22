package org.apache.spark.sql.delta;
/**
 * A rule to add helpful error messages when Delta is being used with unsupported Hive operations
 * or if an unsupported operation is being made, e.g. a DML operation like
 * INSERT/UPDATE/DELETE/MERGE when a table doesn't exist.
 */
public  class DeltaUnsupportedOperationsCheck implements scala.Function1<org.apache.spark.sql.catalyst.plans.logical.LogicalPlan, scala.runtime.BoxedUnit>, org.apache.spark.sql.delta.metering.DeltaLogging, scala.Product, scala.Serializable {
  static public  java.lang.String toString ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.SparkSession spark ()  { throw new RuntimeException(); }
  // not preceding
  public   DeltaUnsupportedOperationsCheck (org.apache.spark.sql.SparkSession spark)  { throw new RuntimeException(); }
  public  void apply (org.apache.spark.sql.catalyst.plans.logical.LogicalPlan plan)  { throw new RuntimeException(); }
}
