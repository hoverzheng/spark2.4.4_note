package org.apache.spark.sql.delta.commands;
/** The result returned by the `describe detail` command. */
public  class TableDetail implements scala.Product, scala.Serializable {
  static public  org.apache.spark.sql.types.StructType schema ()  { throw new RuntimeException(); }
  static public  org.apache.spark.sql.Row toRow (org.apache.spark.sql.delta.commands.TableDetail table)  { throw new RuntimeException(); }
  public  java.lang.String format ()  { throw new RuntimeException(); }
  public  java.lang.String id ()  { throw new RuntimeException(); }
  public  java.lang.String name ()  { throw new RuntimeException(); }
  public  java.lang.String description ()  { throw new RuntimeException(); }
  public  java.lang.String location ()  { throw new RuntimeException(); }
  public  java.sql.Timestamp createdAt ()  { throw new RuntimeException(); }
  public  java.sql.Timestamp lastModified ()  { throw new RuntimeException(); }
  public  scala.collection.Seq<java.lang.String> partitionColumns ()  { throw new RuntimeException(); }
  public  java.lang.Long numFiles ()  { throw new RuntimeException(); }
  public  java.lang.Long sizeInBytes ()  { throw new RuntimeException(); }
  public  scala.collection.immutable.Map<java.lang.String, java.lang.String> properties ()  { throw new RuntimeException(); }
  public  java.lang.Integer minReaderVersion ()  { throw new RuntimeException(); }
  public  java.lang.Integer minWriterVersion ()  { throw new RuntimeException(); }
  // not preceding
  public   TableDetail (java.lang.String format, java.lang.String id, java.lang.String name, java.lang.String description, java.lang.String location, java.sql.Timestamp createdAt, java.sql.Timestamp lastModified, scala.collection.Seq<java.lang.String> partitionColumns, java.lang.Long numFiles, java.lang.Long sizeInBytes, scala.collection.immutable.Map<java.lang.String, java.lang.String> properties, java.lang.Integer minReaderVersion, java.lang.Integer minWriterVersion)  { throw new RuntimeException(); }
}
