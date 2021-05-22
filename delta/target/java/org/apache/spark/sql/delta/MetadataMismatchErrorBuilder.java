package org.apache.spark.sql.delta;
/** A helper class in building a helpful error message in case of metadata mismatches. */
public  class MetadataMismatchErrorBuilder {
  public   MetadataMismatchErrorBuilder ()  { throw new RuntimeException(); }
  public  void addOverwriteBit ()  { throw new RuntimeException(); }
  public  void addPartitioningMismatch (scala.collection.Seq<java.lang.String> original, scala.collection.Seq<java.lang.String> provided)  { throw new RuntimeException(); }
  public  void addSchemaMismatch (org.apache.spark.sql.types.StructType original, org.apache.spark.sql.types.StructType data, java.lang.String id)  { throw new RuntimeException(); }
  public  void finalizeAndThrow (org.apache.spark.sql.internal.SQLConf conf)  { throw new RuntimeException(); }
}
