package org.apache.spark.sql.delta.constraints;
/**
 * Utilities for handling constraints. Right now this includes:
 * - Column-level invariants delegated to {@link Invariants}, including both NOT NULL constraints and
 *   an old style of CHECK constraint specified in the column metadata
 * - Table-level CHECK constraints
 */
public  class Constraints$ {
  /**
   * Static reference to the singleton instance of this Scala object.
   */
  public static final Constraints$ MODULE$ = null;
  public   Constraints$ ()  { throw new RuntimeException(); }
  /**
   * Extract CHECK constraints from the table properties. Note that some CHECK constraints may also
   * come from schema metadata; these constraints were never released in a public API but are
   * maintained for protocol compatibility.
   * @param metadata (undocumented)
   * @param spark (undocumented)
   * @return (undocumented)
   */
  public  scala.collection.Seq<org.apache.spark.sql.delta.constraints.Constraint> getCheckConstraints (org.apache.spark.sql.delta.actions.Metadata metadata, org.apache.spark.sql.SparkSession spark)  { throw new RuntimeException(); }
  /** Extract all constraints from the given Delta table metadata. */
  public  scala.collection.Seq<org.apache.spark.sql.delta.constraints.Constraint> getAll (org.apache.spark.sql.delta.actions.Metadata metadata, org.apache.spark.sql.SparkSession spark)  { throw new RuntimeException(); }
  /** Get the expression text for a constraint with the given name, if present. */
  public  scala.Option<java.lang.String> getExprTextByName (java.lang.String name, org.apache.spark.sql.delta.actions.Metadata metadata, org.apache.spark.sql.SparkSession spark)  { throw new RuntimeException(); }
  public  java.lang.String checkConstraintPropertyName (java.lang.String constraintName)  { throw new RuntimeException(); }
}
