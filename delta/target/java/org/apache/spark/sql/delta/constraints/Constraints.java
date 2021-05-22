package org.apache.spark.sql.delta.constraints;
/**
 * Utilities for handling constraints. Right now this includes:
 * - Column-level invariants delegated to {@link Invariants}, including both NOT NULL constraints and
 *   an old style of CHECK constraint specified in the column metadata
 * - Table-level CHECK constraints
 */
public  class Constraints {
  /**
   * A constraint that the specified column must not be NULL. Note that when the column is nested,
   * this implies its parents must also not be NULL.
   */
  static public  class NotNull implements org.apache.spark.sql.delta.constraints.Constraint, scala.Product, scala.Serializable {
    public  scala.collection.Seq<java.lang.String> column ()  { throw new RuntimeException(); }
    // not preceding
    public   NotNull (scala.collection.Seq<java.lang.String> column)  { throw new RuntimeException(); }
    public  java.lang.String name ()  { throw new RuntimeException(); }
  }
  static public  class NotNull$ extends scala.runtime.AbstractFunction1<scala.collection.Seq<java.lang.String>, org.apache.spark.sql.delta.constraints.Constraints.NotNull> implements scala.Serializable {
    /**
     * Static reference to the singleton instance of this Scala object.
     */
    public static final NotNull$ MODULE$ = null;
    public   NotNull$ ()  { throw new RuntimeException(); }
  }
  /** A SQL expression to check for when writing out data. */
  static public  class Check implements org.apache.spark.sql.delta.constraints.Constraint, scala.Product, scala.Serializable {
    public  java.lang.String name ()  { throw new RuntimeException(); }
    public  org.apache.spark.sql.catalyst.expressions.Expression expression ()  { throw new RuntimeException(); }
    // not preceding
    public   Check (java.lang.String name, org.apache.spark.sql.catalyst.expressions.Expression expression)  { throw new RuntimeException(); }
  }
  static public  class Check$ extends scala.runtime.AbstractFunction2<java.lang.String, org.apache.spark.sql.catalyst.expressions.Expression, org.apache.spark.sql.delta.constraints.Constraints.Check> implements scala.Serializable {
    /**
     * Static reference to the singleton instance of this Scala object.
     */
    public static final Check$ MODULE$ = null;
    public   Check$ ()  { throw new RuntimeException(); }
  }
  /**
   * Extract CHECK constraints from the table properties. Note that some CHECK constraints may also
   * come from schema metadata; these constraints were never released in a public API but are
   * maintained for protocol compatibility.
   * @param metadata (undocumented)
   * @param spark (undocumented)
   * @return (undocumented)
   */
  static public  scala.collection.Seq<org.apache.spark.sql.delta.constraints.Constraint> getCheckConstraints (org.apache.spark.sql.delta.actions.Metadata metadata, org.apache.spark.sql.SparkSession spark)  { throw new RuntimeException(); }
  /** Extract all constraints from the given Delta table metadata. */
  static public  scala.collection.Seq<org.apache.spark.sql.delta.constraints.Constraint> getAll (org.apache.spark.sql.delta.actions.Metadata metadata, org.apache.spark.sql.SparkSession spark)  { throw new RuntimeException(); }
  /** Get the expression text for a constraint with the given name, if present. */
  static public  scala.Option<java.lang.String> getExprTextByName (java.lang.String name, org.apache.spark.sql.delta.actions.Metadata metadata, org.apache.spark.sql.SparkSession spark)  { throw new RuntimeException(); }
  static public  java.lang.String checkConstraintPropertyName (java.lang.String constraintName)  { throw new RuntimeException(); }
}
