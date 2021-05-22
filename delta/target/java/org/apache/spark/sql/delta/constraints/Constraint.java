package org.apache.spark.sql.delta.constraints;
/**
 * A constraint defined on a Delta table, which writers must verify before writing.
 */
public  interface Constraint {
  public  java.lang.String name ()  ;
}
