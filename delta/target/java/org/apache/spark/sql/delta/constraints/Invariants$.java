package org.apache.spark.sql.delta.constraints;
/**
 * List of invariants that can be defined on a Delta table that will allow us to perform
 * validation checks during changes to the table.
 */
public  class Invariants$ {
  /**
   * Static reference to the singleton instance of this Scala object.
   */
  public static final Invariants$ MODULE$ = null;
  public   Invariants$ ()  { throw new RuntimeException(); }
  /** Extract invariants from the given schema */
  public  scala.collection.Seq<org.apache.spark.sql.delta.constraints.Constraint> getFromSchema (org.apache.spark.sql.types.StructType schema, org.apache.spark.sql.SparkSession spark)  { throw new RuntimeException(); }
  public  java.lang.String INVARIANTS_FIELD ()  { throw new RuntimeException(); }
}
