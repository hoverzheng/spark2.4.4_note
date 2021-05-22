package org.apache.spark.sql.delta.constraints;
/**
 * Change to drop a constraint from a table. Note that this is always idempotent - no error
 * will be thrown if the constraint doesn't exist.
 * <p>
 * param:  constraintName the name of the constraint to drop - case insensitive
 */
public  class DropConstraint implements org.apache.spark.sql.connector.catalog.TableChange, scala.Product, scala.Serializable {
  static public abstract  R apply (T1 v1)  ;
  static public  java.lang.String toString ()  { throw new RuntimeException(); }
  public  java.lang.String constraintName ()  { throw new RuntimeException(); }
  // not preceding
  public   DropConstraint (java.lang.String constraintName)  { throw new RuntimeException(); }
}
