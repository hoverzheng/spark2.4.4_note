package org.apache.spark.sql.delta.commands;
/**
 * A logical placeholder for describing a Delta table's history, so that the history can be
 * leveraged in subqueries. Replaced with <code>DescribeDeltaHistoryCommand</code> during planning.
 */
public  class DescribeDeltaHistory extends org.apache.spark.sql.catalyst.plans.logical.LeafNode implements org.apache.spark.sql.catalyst.analysis.MultiInstanceRelation, scala.Product, scala.Serializable {
  static public abstract  R apply (T1 v1, T2 v2, T3 v3, T4 v4)  ;
  static public  java.lang.String toString ()  { throw new RuntimeException(); }
  public  scala.Option<java.lang.String> path ()  { throw new RuntimeException(); }
  public  scala.Option<org.apache.spark.sql.catalyst.TableIdentifier> tableIdentifier ()  { throw new RuntimeException(); }
  public  scala.Option<java.lang.Object> limit ()  { throw new RuntimeException(); }
  public  scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Attribute> output ()  { throw new RuntimeException(); }
  // not preceding
  public   DescribeDeltaHistory (scala.Option<java.lang.String> path, scala.Option<org.apache.spark.sql.catalyst.TableIdentifier> tableIdentifier, scala.Option<java.lang.Object> limit, scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Attribute> output)  { throw new RuntimeException(); }
  public  org.apache.spark.sql.catalyst.plans.logical.Statistics computeStats ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.catalyst.plans.logical.LogicalPlan newInstance ()  { throw new RuntimeException(); }
}
