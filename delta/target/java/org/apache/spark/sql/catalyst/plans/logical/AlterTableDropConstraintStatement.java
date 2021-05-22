package org.apache.spark.sql.catalyst.plans.logical;
/**
 * ALTER TABLE ... DROP CONSTRAINT command, as parsed from SQL
 */
public  class AlterTableDropConstraintStatement extends org.apache.spark.sql.catalyst.plans.logical.ParsedStatement implements scala.Product, scala.Serializable {
  static public abstract  R apply (T1 v1, T2 v2)  ;
  static public  java.lang.String toString ()  { throw new RuntimeException(); }
  public  scala.collection.Seq<java.lang.String> tableName ()  { throw new RuntimeException(); }
  public  java.lang.String constraintName ()  { throw new RuntimeException(); }
  // not preceding
  public   AlterTableDropConstraintStatement (scala.collection.Seq<java.lang.String> tableName, java.lang.String constraintName)  { throw new RuntimeException(); }
}
