package org.apache.spark.sql.delta.schema;
/**
 * A trait that writers into Delta can extend to update the schema and/or partitioning of the table.
 */
public  interface ImplicitMetadataOperation extends org.apache.spark.sql.delta.metering.DeltaLogging {
  public  boolean canMergeSchema ()  ;
  public  boolean canOverwriteSchema ()  ;
  public  scala.collection.Seq<java.lang.String> normalizePartitionColumns (org.apache.spark.sql.SparkSession spark, scala.collection.Seq<java.lang.String> partitionCols, org.apache.spark.sql.types.StructType schema)  ;
  public  void updateMetadata (org.apache.spark.sql.delta.OptimisticTransaction txn, org.apache.spark.sql.Dataset<?> data, scala.collection.Seq<java.lang.String> partitionColumns, scala.collection.immutable.Map<java.lang.String, java.lang.String> configuration, boolean isOverwriteMode, boolean rearrangeOnly)  ;
  public  void updateMetadata (org.apache.spark.sql.SparkSession spark, org.apache.spark.sql.delta.OptimisticTransaction txn, org.apache.spark.sql.types.StructType schema, scala.collection.Seq<java.lang.String> partitionColumns, scala.collection.immutable.Map<java.lang.String, java.lang.String> configuration, boolean isOverwriteMode, boolean rearrangeOnly)  ;
}
