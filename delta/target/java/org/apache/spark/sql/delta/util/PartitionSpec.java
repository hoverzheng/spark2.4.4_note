package org.apache.spark.sql.delta.util;
public  class PartitionSpec implements scala.Product, scala.Serializable {
  static public  org.apache.spark.sql.delta.util.PartitionSpec emptySpec ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.types.StructType partitionColumns ()  { throw new RuntimeException(); }
  public  scala.collection.Seq<org.apache.spark.sql.delta.util.PartitionPath> partitions ()  { throw new RuntimeException(); }
  // not preceding
  public   PartitionSpec (org.apache.spark.sql.types.StructType partitionColumns, scala.collection.Seq<org.apache.spark.sql.delta.util.PartitionPath> partitions)  { throw new RuntimeException(); }
}
