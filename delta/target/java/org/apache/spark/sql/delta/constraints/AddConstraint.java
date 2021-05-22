package org.apache.spark.sql.delta.constraints;
/**
 * Change to add a CHECK constraint to a table.
 * <p>
 * param:  constraintName The name of the new constraint. Note that constraint names are
 *                       case insensitive.
 * param:  expr The expression to add, as a SQL parseable string.
 */
public  class AddConstraint implements org.apache.spark.sql.connector.catalog.TableChange, scala.Product, scala.Serializable {
  static public abstract  R apply (T1 v1, T2 v2)  ;
  static public  java.lang.String toString ()  { throw new RuntimeException(); }
  public  java.lang.String constraintName ()  { throw new RuntimeException(); }
  public  java.lang.String expr ()  { throw new RuntimeException(); }
  // not preceding
  public   AddConstraint (java.lang.String constraintName, java.lang.String expr)  { throw new RuntimeException(); }
}
