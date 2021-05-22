package org.apache.spark.sql.delta.constraints;
/** A rule applied on a column to ensure data hygiene. */
public  class Invariant implements scala.Product, scala.Serializable {
  static public abstract  R apply (T1 v1, T2 v2)  ;
  static public  java.lang.String toString ()  { throw new RuntimeException(); }
  public  scala.collection.Seq<java.lang.String> column ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.delta.constraints.Invariants.Rule rule ()  { throw new RuntimeException(); }
  // not preceding
  public   Invariant (scala.collection.Seq<java.lang.String> column, org.apache.spark.sql.delta.constraints.Invariants.Rule rule)  { throw new RuntimeException(); }
}
