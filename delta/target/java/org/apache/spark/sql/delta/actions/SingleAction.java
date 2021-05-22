package org.apache.spark.sql.delta.actions;
/** A serialization helper to create a common action envelope. */
public  class SingleAction implements scala.Product, scala.Serializable {
  static public  org.apache.spark.sql.Encoder<org.apache.spark.sql.delta.actions.SingleAction> encoder ()  { throw new RuntimeException(); }
  static public  org.apache.spark.sql.Encoder<org.apache.spark.sql.delta.actions.AddFile> addFileEncoder ()  { throw new RuntimeException(); }
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
  public  org.apache.spark.sql.delta.actions.SetTransaction txn ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.delta.actions.AddFile add ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.delta.actions.RemoveFile remove ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.delta.actions.Metadata metaData ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.delta.actions.Protocol protocol ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.delta.actions.CommitInfo commitInfo ()  { throw new RuntimeException(); }
  // not preceding
  public   SingleAction (org.apache.spark.sql.delta.actions.SetTransaction txn, org.apache.spark.sql.delta.actions.AddFile add, org.apache.spark.sql.delta.actions.RemoveFile remove, org.apache.spark.sql.delta.actions.Metadata metaData, org.apache.spark.sql.delta.actions.Protocol protocol, org.apache.spark.sql.delta.actions.CommitInfo commitInfo)  { throw new RuntimeException(); }
  public  org.apache.spark.sql.delta.actions.Action unwrap ()  { throw new RuntimeException(); }
}
