package org.apache.spark.sql.delta.sources;
/** A read limit that admits a soft-max of `maxBytes` per micro-batch. */
public  class ReadMaxBytes implements org.apache.spark.sql.connector.read.streaming.ReadLimit, scala.Product, scala.Serializable {
  static public abstract  R apply (T1 v1)  ;
  static public  java.lang.String toString ()  { throw new RuntimeException(); }
  public  long maxBytes ()  { throw new RuntimeException(); }
  // not preceding
  public   ReadMaxBytes (long maxBytes)  { throw new RuntimeException(); }
}
