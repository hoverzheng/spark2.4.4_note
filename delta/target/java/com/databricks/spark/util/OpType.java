package com.databricks.spark.util;
public  class OpType implements scala.Product, scala.Serializable {
  static public abstract  R apply (T1 v1, T2 v2)  ;
  static public  java.lang.String toString ()  { throw new RuntimeException(); }
  public  java.lang.String typeName ()  { throw new RuntimeException(); }
  public  java.lang.String description ()  { throw new RuntimeException(); }
  // not preceding
  public   OpType (java.lang.String typeName, java.lang.String description)  { throw new RuntimeException(); }
}
