package org.apache.spark.sql.delta.commands;
public  class DeltaGenerateCommand extends org.apache.spark.sql.catalyst.plans.logical.LogicalPlan implements org.apache.spark.sql.execution.command.RunnableCommand, scala.Product, scala.Serializable {
  static public  org.apache.spark.sql.catalyst.util.CaseInsensitiveMap<scala.Function2<org.apache.spark.sql.SparkSession, org.apache.spark.sql.delta.DeltaLog, scala.runtime.BoxedUnit>> modeNameToGenerationFunc ()  { throw new RuntimeException(); }
  public  scala.collection.immutable.Map<java.lang.String, org.apache.spark.sql.execution.metric.SQLMetric> metrics ()  { throw new RuntimeException(); }
  public  java.lang.String modeName ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.catalyst.TableIdentifier tableId ()  { throw new RuntimeException(); }
  // not preceding
  public   DeltaGenerateCommand (java.lang.String modeName, org.apache.spark.sql.catalyst.TableIdentifier tableId)  { throw new RuntimeException(); }
  public  scala.collection.Seq<org.apache.spark.sql.Row> run (org.apache.spark.sql.SparkSession sparkSession)  { throw new RuntimeException(); }
}
