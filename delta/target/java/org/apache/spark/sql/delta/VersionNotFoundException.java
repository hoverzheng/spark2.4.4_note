package org.apache.spark.sql.delta;
/**
 * Thrown when time travelling to a version that does not exist in the Delta Log.
 * param:  userVersion - the version time travelling to
 * param:  earliest - earliest version available in the Delta Log
 * param:  latest - The latest version available in the Delta Log
 */
public  class VersionNotFoundException extends org.apache.spark.sql.AnalysisException implements scala.Product, scala.Serializable {
  static public abstract  R apply (T1 v1, T2 v2, T3 v3)  ;
  static public  java.lang.String toString ()  { throw new RuntimeException(); }
  public  long userVersion ()  { throw new RuntimeException(); }
  public  long earliest ()  { throw new RuntimeException(); }
  public  long latest ()  { throw new RuntimeException(); }
  // not preceding
  public   VersionNotFoundException (long userVersion, long earliest, long latest)  { throw new RuntimeException(); }
}
