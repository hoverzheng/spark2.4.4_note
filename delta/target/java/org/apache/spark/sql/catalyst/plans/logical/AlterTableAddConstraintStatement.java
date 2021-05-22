package org.apache.spark.sql.catalyst.plans.logical;
/**
 * ALTER TABLE ... ADD CONSTRAINT command, as parsed from SQL
 */
public  class AlterTableAddConstraintStatement extends org.apache.spark.sql.catalyst.plans.logical.ParsedStatement implements scala.Product, scala.Serializable {
  static public abstract  R apply (T1 v1, T2 v2, T3 v3)  ;
  static public  java.lang.String toString ()  { throw new RuntimeException(); }
  public  scala.collection.Seq<java.lang.String> tableName ()  { throw new RuntimeException(); }
  public  java.lang.String constraintName ()  { throw new RuntimeException(); }
  public  java.lang.String expr ()  { throw new RuntimeException(); }
  // not preceding
  public   AlterTableAddConstraintStatement (scala.collection.Seq<java.lang.String> tableName, java.lang.String constraintName, java.lang.String expr)  { throw new RuntimeException(); }
}
