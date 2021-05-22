package org.apache.spark.sql.delta;
/** Provides the capability for partition pruning when querying a Delta table. */
public  interface PartitionFiltering {
  public  org.apache.spark.sql.delta.stats.DeltaScan filesForScan (scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Attribute> projection, scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Expression> filters)  ;
}
