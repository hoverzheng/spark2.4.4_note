package org.apache.spark.sql.delta;
/**
 * Used to perform a set of reads in a transaction and then commit a set of updates to the
 * state of the log.  All reads from the {@link DeltaLog}, MUST go through this instance rather
 * than directly to the {@link DeltaLog} otherwise they will not be check for logical conflicts
 * with concurrent updates.
 * <p>
 * This class is not thread-safe.
 * <p>
 * param:  deltaLog The Delta Log for the table this transaction is modifying.
 * param:  snapshot The snapshot that this transaction is reading at.
 */
public  class OptimisticTransaction implements org.apache.spark.sql.delta.OptimisticTransactionImpl, org.apache.spark.sql.delta.metering.DeltaLogging {
  /** Get the active transaction */
  static public  scala.Option<org.apache.spark.sql.delta.OptimisticTransaction> getActive ()  { throw new RuntimeException(); }
  /**
   * Runs the passed block of code with the given active transaction
   * @param activeTransaction (undocumented)
   * @param block (undocumented)
   * @return (undocumented)
   */
  static public <T extends java.lang.Object> T withActive (org.apache.spark.sql.delta.OptimisticTransaction activeTransaction, scala.Function0<T> block)  { throw new RuntimeException(); }
  /**
   * Sets a transaction as the active transaction.
   * <p>
   * @note This is not meant for being called directly, only from
   *       <code>OptimisticTransaction.withNewTransaction</code>. Use that to create and set active txns.
   * @param txn (undocumented)
   */
  static   void setActive (org.apache.spark.sql.delta.OptimisticTransaction txn)  { throw new RuntimeException(); }
  /**
   * Clears the active transaction as the active transaction.
   * <p>
   * @note This is not meant for being called directly, <code>OptimisticTransaction.withNewTransaction</code>.
   */
  static   void clearActive ()  { throw new RuntimeException(); }
  protected  org.apache.spark.sql.SparkSession _spark ()  { throw new RuntimeException(); }
  protected  scala.collection.mutable.ArrayBuffer<java.lang.String> readTxn ()  { throw new RuntimeException(); }
  protected  scala.collection.mutable.ArrayBuffer<org.apache.spark.sql.catalyst.expressions.Expression> readPredicates ()  { throw new RuntimeException(); }
  protected  scala.collection.mutable.HashSet<org.apache.spark.sql.delta.actions.AddFile> readFiles ()  { throw new RuntimeException(); }
  protected  boolean committed ()  { throw new RuntimeException(); }
  protected  scala.Option<org.apache.spark.sql.delta.actions.Metadata> newMetadata ()  { throw new RuntimeException(); }
  protected  scala.Option<org.apache.spark.sql.delta.actions.Protocol> newProtocol ()  { throw new RuntimeException(); }
  protected  long txnStartNano ()  { throw new RuntimeException(); }
  protected  long commitStartNano ()  { throw new RuntimeException(); }
  protected  org.apache.spark.sql.delta.actions.CommitInfo commitInfo ()  { throw new RuntimeException(); }
  protected  long commitAttemptStartTime ()  { throw new RuntimeException(); }
  protected  scala.collection.mutable.ArrayBuffer<org.apache.spark.sql.delta.hooks.PostCommitHook> postCommitHooks ()  { throw new RuntimeException(); }
  protected  boolean hasWritten ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.delta.DeltaLog deltaLog ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.delta.Snapshot snapshot ()  { throw new RuntimeException(); }
  public  org.apache.spark.util.Clock clock ()  { throw new RuntimeException(); }
  // not preceding
  public   OptimisticTransaction (org.apache.spark.sql.delta.DeltaLog deltaLog, org.apache.spark.sql.delta.Snapshot snapshot, org.apache.spark.util.Clock clock)  { throw new RuntimeException(); }
  /** Creates a new OptimisticTransaction.
   * <p>
   * @param deltaLog The Delta Log for the table this transaction is modifying.
   * @param clock (undocumented)
   */
  public   OptimisticTransaction (org.apache.spark.sql.delta.DeltaLog deltaLog, org.apache.spark.util.Clock clock)  { throw new RuntimeException(); }
}
