package org.apache.spark.sql.delta;
public  class DeltaOperationMetrics {
  static public  scala.collection.immutable.Set<java.lang.String> WRITE ()  { throw new RuntimeException(); }
  static public  scala.collection.immutable.Set<java.lang.String> STREAMING_UPDATE ()  { throw new RuntimeException(); }
  static public  scala.collection.immutable.Set<java.lang.String> DELETE ()  { throw new RuntimeException(); }
  /** Deleting the entire table or partition would prevent row level metrics from being recorded */
  static public  scala.collection.immutable.Set<java.lang.String> DELETE_PARTITIONS ()  { throw new RuntimeException(); }
  static public  scala.collection.immutable.Set<java.lang.String> TRUNCATE ()  { throw new RuntimeException(); }
  static public  scala.collection.immutable.Set<java.lang.String> CONVERT ()  { throw new RuntimeException(); }
  static public  scala.collection.immutable.Set<java.lang.String> MERGE ()  { throw new RuntimeException(); }
  static public  scala.collection.immutable.Set<java.lang.String> UPDATE ()  { throw new RuntimeException(); }
}
