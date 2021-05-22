package org.apache.spark.sql.delta.schema;
public  class InvariantViolationException$ implements scala.Serializable {
  /**
   * Static reference to the singleton instance of this Scala object.
   */
  public static final InvariantViolationException$ MODULE$ = null;
  public   InvariantViolationException$ ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.delta.schema.InvariantViolationException apply (org.apache.spark.sql.delta.constraints.Constraints.NotNull constraint)  { throw new RuntimeException(); }
  /**
   * Build an exception to report the current row failed a CHECK constraint.
   * <p>
   * @param constraint the constraint definition
   * @param values a map of full column names to their evaluated values in the failed row
   * @return (undocumented)
   */
  public  org.apache.spark.sql.delta.schema.InvariantViolationException apply (org.apache.spark.sql.delta.constraints.Constraints.Check constraint, scala.collection.immutable.Map<java.lang.String, java.lang.Object> values)  { throw new RuntimeException(); }
  /**
   * Columns and values in parallel lists as a shim for Java codegen compatibility.
   * @param constraint (undocumented)
   * @param columns (undocumented)
   * @param values (undocumented)
   * @return (undocumented)
   */
  public  org.apache.spark.sql.delta.schema.InvariantViolationException apply (org.apache.spark.sql.delta.constraints.Constraints.Check constraint, java.util.List<java.lang.String> columns, java.util.List<java.lang.Object> values)  { throw new RuntimeException(); }
}
