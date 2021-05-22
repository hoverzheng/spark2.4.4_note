package org.apache.spark.sql.delta.hooks;
/**
 * A hook which can be executed after a transaction. These hooks are registered to a
 * {@link OptimisticTransaction}, and are executed after a *successful* commit takes place.
 */
public  interface PostCommitHook {
  /**
   * Handle any error caused while running the hook. By default, all errors are ignored as
   * default policy should be to not let post-commit hooks to cause failures in the operation.
   * @param error (undocumented)
   * @param version (undocumented)
   */
  public  void handleError (java.lang.Throwable error, long version)  ;
  /** A user friendly name for the hook for error reporting purposes. */
  public  java.lang.String name ()  ;
  /** Executes the hook. */
  public  void run (org.apache.spark.sql.SparkSession spark, org.apache.spark.sql.delta.OptimisticTransactionImpl txn, scala.collection.Seq<org.apache.spark.sql.delta.actions.Action> committedActions)  ;
}
