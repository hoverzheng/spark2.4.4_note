package org.apache.spark.sql.delta.commands;
public  class DeltaVacuumStats implements scala.Product, scala.Serializable {
  static public abstract  R apply (T1 v1, T2 v2, T3 v3, T4 v4, T5 v5, T6 v6)  ;
  static public  java.lang.String toString ()  { throw new RuntimeException(); }
  public  boolean isDryRun ()  { throw new RuntimeException(); }
  public  scala.Option<java.lang.Object> specifiedRetentionMillis ()  { throw new RuntimeException(); }
  public  long defaultRetentionMillis ()  { throw new RuntimeException(); }
  public  long minRetainedTimestamp ()  { throw new RuntimeException(); }
  public  long dirsPresentBeforeDelete ()  { throw new RuntimeException(); }
  public  long objectsDeleted ()  { throw new RuntimeException(); }
  // not preceding
  public   DeltaVacuumStats (boolean isDryRun, scala.Option<java.lang.Object> specifiedRetentionMillis, long defaultRetentionMillis, long minRetainedTimestamp, long dirsPresentBeforeDelete, long objectsDeleted)  { throw new RuntimeException(); }
}
