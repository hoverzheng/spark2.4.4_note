package org.apache.spark.sql.delta.commands;
public  class MergeDataSizes implements scala.Product, scala.Serializable {
  static public abstract  R apply (T1 v1, T2 v2, T3 v3, T4 v4)  ;
  static public  java.lang.String toString ()  { throw new RuntimeException(); }
  public  scala.Option<java.lang.Object> rows ()  { throw new RuntimeException(); }
  public  scala.Option<java.lang.Object> files ()  { throw new RuntimeException(); }
  public  scala.Option<java.lang.Object> bytes ()  { throw new RuntimeException(); }
  public  scala.Option<java.lang.Object> partitions ()  { throw new RuntimeException(); }
  // not preceding
  public   MergeDataSizes (scala.Option<java.lang.Object> rows, scala.Option<java.lang.Object> files, scala.Option<java.lang.Object> bytes, scala.Option<java.lang.Object> partitions)  { throw new RuntimeException(); }
}
