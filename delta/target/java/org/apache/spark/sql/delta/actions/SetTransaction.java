package org.apache.spark.sql.delta.actions;
/**
 * Sets the committed version for a given application. Used to make operations
 * like streaming append idempotent.
 */
public  class SetTransaction implements org.apache.spark.sql.delta.actions.Action, scala.Product, scala.Serializable {
  static public abstract  R apply (T1 v1, T2 v2, T3 v3)  ;
  static public  java.lang.String toString ()  { throw new RuntimeException(); }
  public  java.lang.String appId ()  { throw new RuntimeException(); }
  public  long version ()  { throw new RuntimeException(); }
  public  scala.Option<java.lang.Object> lastUpdated ()  { throw new RuntimeException(); }
  // not preceding
  public   SetTransaction (java.lang.String appId, long version, scala.Option<java.lang.Object> lastUpdated)  { throw new RuntimeException(); }
  public  org.apache.spark.sql.delta.actions.SingleAction wrap ()  { throw new RuntimeException(); }
}
