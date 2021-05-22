package org.apache.spark.sql.delta.storage;
public  interface LogStoreProvider {
  public  org.apache.spark.sql.delta.storage.LogStore createLogStore (org.apache.spark.sql.SparkSession spark)  ;
  public  org.apache.spark.sql.delta.storage.LogStore createLogStore (org.apache.spark.SparkConf sparkConf, org.apache.hadoop.conf.Configuration hadoopConf)  ;
  public  java.lang.String defaultLogStoreClass ()  ;
  public  java.lang.String logStoreClassConfKey ()  ;
}
