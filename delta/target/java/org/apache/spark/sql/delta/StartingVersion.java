package org.apache.spark.sql.delta;
public  class StartingVersion implements org.apache.spark.sql.delta.DeltaStartingVersion, scala.Product, scala.Serializable {
  static public abstract  R apply (T1 v1)  ;
  static public  java.lang.String toString ()  { throw new RuntimeException(); }
  public  long version ()  { throw new RuntimeException(); }
  // not preceding
  public   StartingVersion (long version)  { throw new RuntimeException(); }
}
