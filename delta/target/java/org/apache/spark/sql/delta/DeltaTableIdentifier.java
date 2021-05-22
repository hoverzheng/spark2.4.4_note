package org.apache.spark.sql.delta;
/**
 * An identifier for a Delta table containing one of the path or the table identifier.
 */
public  class DeltaTableIdentifier implements scala.Product, scala.Serializable {
  /**
   * Check the specified table identifier represents a Delta path.
   * @param spark (undocumented)
   * @param identifier (undocumented)
   * @return (undocumented)
   */
  static public  boolean isDeltaPath (org.apache.spark.sql.SparkSession spark, org.apache.spark.sql.catalyst.TableIdentifier identifier)  { throw new RuntimeException(); }
  /**
   * Creates a {@link DeltaTableIdentifier} if the specified table identifier represents a Delta table,
   * otherwise returns {@link None}.
   * @param spark (undocumented)
   * @param identifier (undocumented)
   * @return (undocumented)
   */
  static public  scala.Option<org.apache.spark.sql.delta.DeltaTableIdentifier> apply (org.apache.spark.sql.SparkSession spark, org.apache.spark.sql.catalyst.TableIdentifier identifier)  { throw new RuntimeException(); }
  /**
   * When users try to access Delta tables by path, e.g. delta.<code>/some/path</code>, we need to first check
   * if such a table exists in the MetaStore (due to Spark semantics :/). The Glue MetaStore may
   * return Access Denied errors during this check. This method matches on this failure mode.
   * @param e (undocumented)
   * @return (undocumented)
   */
  static public  boolean gluePermissionError (org.apache.spark.sql.AnalysisException e)  { throw new RuntimeException(); }
  static public  org.slf4j.Logger org$apache$spark$internal$Logging$$log_ ()  { throw new RuntimeException(); }
  static public  void org$apache$spark$internal$Logging$$log__$eq (org.slf4j.Logger x$1)  { throw new RuntimeException(); }
  static protected  java.lang.String logName ()  { throw new RuntimeException(); }
  static protected  org.slf4j.Logger log ()  { throw new RuntimeException(); }
  static protected  void logInfo (scala.Function0<java.lang.String> msg)  { throw new RuntimeException(); }
  static protected  void logDebug (scala.Function0<java.lang.String> msg)  { throw new RuntimeException(); }
  static protected  void logTrace (scala.Function0<java.lang.String> msg)  { throw new RuntimeException(); }
  static protected  void logWarning (scala.Function0<java.lang.String> msg)  { throw new RuntimeException(); }
  static protected  void logError (scala.Function0<java.lang.String> msg)  { throw new RuntimeException(); }
  static protected  void logInfo (scala.Function0<java.lang.String> msg, java.lang.Throwable throwable)  { throw new RuntimeException(); }
  static protected  void logDebug (scala.Function0<java.lang.String> msg, java.lang.Throwable throwable)  { throw new RuntimeException(); }
  static protected  void logTrace (scala.Function0<java.lang.String> msg, java.lang.Throwable throwable)  { throw new RuntimeException(); }
  static protected  void logWarning (scala.Function0<java.lang.String> msg, java.lang.Throwable throwable)  { throw new RuntimeException(); }
  static protected  void logError (scala.Function0<java.lang.String> msg, java.lang.Throwable throwable)  { throw new RuntimeException(); }
  static protected  boolean isTraceEnabled ()  { throw new RuntimeException(); }
  static protected  void initializeLogIfNecessary (boolean isInterpreter)  { throw new RuntimeException(); }
  static protected  boolean initializeLogIfNecessary (boolean isInterpreter, boolean silent)  { throw new RuntimeException(); }
  static protected  boolean initializeLogIfNecessary$default$2 ()  { throw new RuntimeException(); }
  static   void initializeForcefully (boolean isInterpreter, boolean silent)  { throw new RuntimeException(); }
  public  scala.Option<java.lang.String> path ()  { throw new RuntimeException(); }
  public  scala.Option<org.apache.spark.sql.catalyst.TableIdentifier> table ()  { throw new RuntimeException(); }
  // not preceding
  public   DeltaTableIdentifier (scala.Option<java.lang.String> path, scala.Option<org.apache.spark.sql.catalyst.TableIdentifier> table)  { throw new RuntimeException(); }
  public  java.lang.String identifier ()  { throw new RuntimeException(); }
  public  scala.Option<java.lang.String> database ()  { throw new RuntimeException(); }
  public  org.apache.hadoop.fs.Path getPath (org.apache.spark.sql.SparkSession spark)  { throw new RuntimeException(); }
  public  org.apache.spark.sql.delta.DeltaLog getDeltaLog (org.apache.spark.sql.SparkSession spark)  { throw new RuntimeException(); }
  public  java.lang.String quotedString ()  { throw new RuntimeException(); }
  public  java.lang.String unquotedString ()  { throw new RuntimeException(); }
  public  java.lang.String toString ()  { throw new RuntimeException(); }
}
