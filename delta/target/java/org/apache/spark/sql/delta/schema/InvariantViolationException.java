package org.apache.spark.sql.delta.schema;
/** Thrown when the given data doesn't match the rules defined on the table. */
public  class InvariantViolationException extends java.lang.IllegalArgumentException implements scala.Product, scala.Serializable {
  static public  org.apache.spark.sql.delta.schema.InvariantViolationException apply (org.apache.spark.sql.delta.constraints.Constraints.NotNull constraint)  { throw new RuntimeException(); }
  /**
   * Build an exception to report the current row failed a CHECK constraint.
   * <p>
   * @param constraint the constraint definition
   * @param values a map of full column names to their evaluated values in the failed row
   * @return (undocumented)
   */
  static public  org.apache.spark.sql.delta.schema.InvariantViolationException apply (org.apache.spark.sql.delta.constraints.Constraints.Check constraint, scala.collection.immutable.Map<java.lang.String, java.lang.Object> values)  { throw new RuntimeException(); }
  /**
   * Columns and values in parallel lists as a shim for Java codegen compatibility.
   * @param constraint (undocumented)
   * @param columns (undocumented)
   * @param values (undocumented)
   * @return (undocumented)
   */
  static public  org.apache.spark.sql.delta.schema.InvariantViolationException apply (org.apache.spark.sql.delta.constraints.Constraints.Check constraint, java.util.List<java.lang.String> columns, java.util.List<java.lang.Object> values)  { throw new RuntimeException(); }
  public  java.lang.String msg ()  { throw new RuntimeException(); }
  // not preceding
  public   InvariantViolationException (java.lang.String msg)  { throw new RuntimeException(); }
}
