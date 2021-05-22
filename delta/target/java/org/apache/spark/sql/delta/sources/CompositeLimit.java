package org.apache.spark.sql.delta.sources;
/** A read limit that admits the given soft-max of `bytes` or max `files`. */
public  class CompositeLimit implements org.apache.spark.sql.connector.read.streaming.ReadLimit, scala.Product, scala.Serializable {
  static public abstract  R apply (T1 v1, T2 v2)  ;
  static public  java.lang.String toString ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.delta.sources.ReadMaxBytes bytes ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.connector.read.streaming.ReadMaxFiles files ()  { throw new RuntimeException(); }
  // not preceding
  public   CompositeLimit (org.apache.spark.sql.delta.sources.ReadMaxBytes bytes, org.apache.spark.sql.connector.read.streaming.ReadMaxFiles files)  { throw new RuntimeException(); }
}
