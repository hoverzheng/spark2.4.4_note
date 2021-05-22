package org.apache.spark.sql.delta.stats;
/**
 * Used to hold details the files and stats for a scan where we have already
 * applied filters and a limit.
 */
public  class DeltaScan implements scala.Product, scala.Serializable {
  public  long version ()  { throw new RuntimeException(); }
  public  scala.collection.Seq<org.apache.spark.sql.delta.actions.AddFile> files ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.delta.stats.DataSize total ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.delta.stats.DataSize partition ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.delta.stats.DataSize scanned ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.catalyst.expressions.ExpressionSet partitionFilters ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.catalyst.expressions.ExpressionSet dataFilters ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.catalyst.expressions.ExpressionSet unusedFilters ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.catalyst.expressions.AttributeSet projection ()  { throw new RuntimeException(); }
  // not preceding
  public   DeltaScan (long version, scala.collection.Seq<org.apache.spark.sql.delta.actions.AddFile> files, org.apache.spark.sql.delta.stats.DataSize total, org.apache.spark.sql.delta.stats.DataSize partition, org.apache.spark.sql.delta.stats.DataSize scanned, org.apache.spark.sql.catalyst.expressions.ExpressionSet partitionFilters, org.apache.spark.sql.catalyst.expressions.ExpressionSet dataFilters, org.apache.spark.sql.catalyst.expressions.ExpressionSet unusedFilters, org.apache.spark.sql.catalyst.expressions.AttributeSet projection)  { throw new RuntimeException(); }
  public  org.apache.spark.sql.catalyst.expressions.ExpressionSet allFilters ()  { throw new RuntimeException(); }
}
