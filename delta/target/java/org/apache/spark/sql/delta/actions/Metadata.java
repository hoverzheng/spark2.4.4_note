package org.apache.spark.sql.delta.actions;
/**
 * Updates the metadata of the table. Only the last update to the {@link Metadata}
 * of a table is kept. It is the responsibility of the writer to ensure that
 * any data already present in the table is still valid after any change.
 */
public  class Metadata implements org.apache.spark.sql.delta.actions.Action, scala.Product, scala.Serializable {
  static public abstract  R apply (T1 v1, T2 v2, T3 v3, T4 v4, T5 v5, T6 v6, T7 v7, T8 v8)  ;
  static public  java.lang.String toString ()  { throw new RuntimeException(); }
  public  java.lang.String id ()  { throw new RuntimeException(); }
  public  java.lang.String name ()  { throw new RuntimeException(); }
  public  java.lang.String description ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.delta.actions.Format format ()  { throw new RuntimeException(); }
  public  java.lang.String schemaString ()  { throw new RuntimeException(); }
  public  scala.collection.Seq<java.lang.String> partitionColumns ()  { throw new RuntimeException(); }
  public  scala.collection.immutable.Map<java.lang.String, java.lang.String> configuration ()  { throw new RuntimeException(); }
  public  scala.Option<java.lang.Object> createdTime ()  { throw new RuntimeException(); }
  // not preceding
  public   Metadata (java.lang.String id, java.lang.String name, java.lang.String description, org.apache.spark.sql.delta.actions.Format format, java.lang.String schemaString, scala.collection.Seq<java.lang.String> partitionColumns, scala.collection.immutable.Map<java.lang.String, java.lang.String> configuration, scala.Option<java.lang.Object> createdTime)  { throw new RuntimeException(); }
  // not preceding
  public  org.apache.spark.sql.types.StructType schema ()  { throw new RuntimeException(); }
  // not preceding
  public  org.apache.spark.sql.types.StructType partitionSchema ()  { throw new RuntimeException(); }
  // not preceding
  public  org.apache.spark.sql.types.StructType dataSchema ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.delta.actions.SingleAction wrap ()  { throw new RuntimeException(); }
}
