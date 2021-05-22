package io.delta.tables.execution;
public  interface DeltaConvertBase {
  public  io.delta.tables.DeltaTable executeConvert (org.apache.spark.sql.SparkSession spark, org.apache.spark.sql.catalyst.TableIdentifier tableIdentifier, scala.Option<org.apache.spark.sql.types.StructType> partitionSchema, scala.Option<java.lang.String> deltaPath)  ;
}
