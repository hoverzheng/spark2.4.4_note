package org.apache.spark.sql.delta;
public  class OptimisticTransaction$ {
  /**
   * Static reference to the singleton instance of this Scala object.
   */
  public static final OptimisticTransaction$ MODULE$ = null;
  public   OptimisticTransaction$ ()  { throw new RuntimeException(); }
  /** Get the active transaction */
  public  scala.Option<org.apache.spark.sql.delta.OptimisticTransaction> getActive ()  { throw new RuntimeException(); }
  /**
   * Runs the passed block of code with the given active transaction
   * @param activeTransaction (undocumented)
   * @param block (undocumented)
   * @return (undocumented)
   */
  public <T extends java.lang.Object> T withActive (org.apache.spark.sql.delta.OptimisticTransaction activeTransaction, scala.Function0<T> block)  { throw new RuntimeException(); }
  /**
   * Sets a transaction as the active transaction.
   * <p>
   * @note This is not meant for being called directly, only from
   *       <code>OptimisticTransaction.withNewTransaction</code>. Use that to create and set active txns.
   * @param txn (undocumented)
   */
    void setActive (org.apache.spark.sql.delta.OptimisticTransaction txn)  { throw new RuntimeException(); }
  /**
   * Clears the active transaction as the active transaction.
   * <p>
   * @note This is not meant for being called directly, <code>OptimisticTransaction.withNewTransaction</code>.
   */
    void clearActive ()  { throw new RuntimeException(); }
}
