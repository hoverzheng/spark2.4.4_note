package io.delta.tables.execution;
/**
 * The <code>vacuum</code> command implementation for Spark SQL. Example SQL:
 * <pre><code>
 *    VACUUM ('/path/to/dir' | delta.`/path/to/dir`) [RETAIN number HOURS] [DRY RUN];
 * </code></pre>
 */
public  class VacuumTableCommand extends org.apache.spark.sql.catalyst.plans.logical.LogicalPlan implements org.apache.spark.sql.execution.command.RunnableCommand, scala.Product, scala.Serializable {
  static public abstract  R apply (T1 v1, T2 v2, T3 v3, T4 v4)  ;
  static public  java.lang.String toString ()  { throw new RuntimeException(); }
  public  scala.collection.immutable.Map<java.lang.String, org.apache.spark.sql.execution.metric.SQLMetric> metrics ()  { throw new RuntimeException(); }
  public  scala.Option<java.lang.String> path ()  { throw new RuntimeException(); }
  public  scala.Option<org.apache.spark.sql.catalyst.TableIdentifier> table ()  { throw new RuntimeException(); }
  public  scala.Option<java.lang.Object> horizonHours ()  { throw new RuntimeException(); }
  public  boolean dryRun ()  { throw new RuntimeException(); }
  // not preceding
  public   VacuumTableCommand (scala.Option<java.lang.String> path, scala.Option<org.apache.spark.sql.catalyst.TableIdentifier> table, scala.Option<java.lang.Object> horizonHours, boolean dryRun)  { throw new RuntimeException(); }
  public  scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Attribute> output ()  { throw new RuntimeException(); }
  public  scala.collection.Seq<org.apache.spark.sql.Row> run (org.apache.spark.sql.SparkSession sparkSession)  { throw new RuntimeException(); }
}
