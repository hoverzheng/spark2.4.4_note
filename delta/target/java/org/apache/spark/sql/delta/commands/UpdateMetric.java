package org.apache.spark.sql.delta.commands;
/**
 * Used to report details about update.
 * <p>
 * param:  condition: what was the update condition
 * param:  numFilesTotal: how big is the table
 * param:  numTouchedFiles: how many files did we touch
 * param:  numRewrittenFiles: how many files had to be rewritten
 * param:  scanTimeMs: how long did finding take
 * param:  rewriteTimeMs: how long did rewriting take
 * <p>
 * @note All the time units are milliseconds.
 */
public  class UpdateMetric implements scala.Product, scala.Serializable {
  static public abstract  R apply (T1 v1, T2 v2, T3 v3, T4 v4, T5 v5, T6 v6)  ;
  static public  java.lang.String toString ()  { throw new RuntimeException(); }
  public  java.lang.String condition ()  { throw new RuntimeException(); }
  public  long numFilesTotal ()  { throw new RuntimeException(); }
  public  long numTouchedFiles ()  { throw new RuntimeException(); }
  public  long numRewrittenFiles ()  { throw new RuntimeException(); }
  public  long scanTimeMs ()  { throw new RuntimeException(); }
  public  long rewriteTimeMs ()  { throw new RuntimeException(); }
  // not preceding
  public   UpdateMetric (java.lang.String condition, long numFilesTotal, long numTouchedFiles, long numRewrittenFiles, long scanTimeMs, long rewriteTimeMs)  { throw new RuntimeException(); }
}
