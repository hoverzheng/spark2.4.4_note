package org.apache.spark.sql.delta;
/**
 * Used to perform a set of reads in a transaction and then commit a set of updates to the
 * state of the log.  All reads from the {@link DeltaLog}, MUST go through this instance rather
 * than directly to the {@link DeltaLog} otherwise they will not be check for logical conflicts
 * with concurrent updates.
 * <p>
 * This trait is not thread-safe.
 */
public  interface OptimisticTransactionImpl extends org.apache.spark.sql.delta.files.TransactionalWrite, org.apache.spark.sql.delta.files.SQLMetricsReporting, org.apache.spark.sql.delta.metering.DeltaLogging {
  public  org.apache.spark.sql.SparkSession _spark ()  ;
  /**
   * Looks at actions that have happened since the txn started and checks for logical
   * conflicts with the read/writes. If no conflicts are found return the commit version to attempt
   * next.
   * @param checkVersion (undocumented)
   * @param actions (undocumented)
   * @param attemptNumber (undocumented)
   * @param commitIsolationLevel (undocumented)
   * @return (undocumented)
   */
  public  long checkForConflicts (long checkVersion, scala.collection.Seq<org.apache.spark.sql.delta.actions.Action> actions, int attemptNumber, org.apache.spark.sql.delta.IsolationLevel commitIsolationLevel)  ;
  public  org.apache.spark.util.Clock clock ()  ;
  /**
   * Modifies the state of the log by adding a new commit that is based on a read at
   * the given <code>lastVersion</code>.  In the case of a conflict with a concurrent writer this
   * method will throw an exception.
   * <p>
   * @param actions     Set of actions to commit
   * @param op          Details of operation that is performing this transactional commit
   * @return (undocumented)
   */
  public  long commit (scala.collection.Seq<org.apache.spark.sql.delta.actions.Action> actions, org.apache.spark.sql.delta.DeltaOperations.Operation op) throws java.util.ConcurrentModificationException ;
  /**
   * Tracks the start time since we started trying to write a particular commit.
   * Used for logging duration of retried transactions.
   * @return (undocumented)
   */
  public  long commitAttemptStartTime ()  ;
  public  org.apache.spark.sql.delta.actions.CommitInfo commitInfo ()  ;
  public  long commitStartNano ()  ;
  /** Tracks if this transaction has already committed. */
  public  boolean committed ()  ;
  public  org.apache.spark.sql.delta.DeltaLog deltaLog ()  ;
  /**
   * Commit <code>actions</code> using <code>attemptVersion</code> version number. Throws a FileAlreadyExistsException
   * if any conflicts are detected.
   * <p>
   * @return the real version that was committed.
   * @param attemptVersion (undocumented)
   * @param actions (undocumented)
   * @param attemptNumber (undocumented)
   * @param isolationLevel (undocumented)
   */
  public  long doCommit (long attemptVersion, scala.collection.Seq<org.apache.spark.sql.delta.actions.Action> actions, int attemptNumber, org.apache.spark.sql.delta.IsolationLevel isolationLevel)  ;
  /**
   * Commit <code>actions</code> using <code>attemptVersion</code> version number. If there are any conflicts that are
   * found, we will retry a fixed number of times.
   * <p>
   * @return the real version that was committed
   * @param attemptVersion (undocumented)
   * @param actions (undocumented)
   * @param isolationLevel (undocumented)
   */
  public  long doCommitRetryIteratively (long attemptVersion, scala.collection.Seq<org.apache.spark.sql.delta.actions.Action> actions, org.apache.spark.sql.delta.IsolationLevel isolationLevel)  ;
  /** Returns files matching the given predicates. */
  public  scala.collection.Seq<org.apache.spark.sql.delta.actions.AddFile> filterFiles ()  ;
  /** Returns files matching the given predicates. */
  public  scala.collection.Seq<org.apache.spark.sql.delta.actions.AddFile> filterFiles (scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Expression> filters)  ;
  /** Returns the next attempt version given the last attempted version */
  public  long getNextAttemptVersion (long previousAttemptVersion)  ;
  /**
   * Return the operation metrics for the operation if it is enabled
   * @param op (undocumented)
   * @return (undocumented)
   */
  public  scala.Option<scala.collection.immutable.Map<java.lang.String, java.lang.String>> getOperationMetrics (org.apache.spark.sql.delta.DeltaOperations.Operation op)  ;
  /** A helper function for pretty printing a specific partition directory. */
  public  java.lang.String getPrettyPartitionMessage (scala.collection.immutable.Map<java.lang.String, java.lang.String> partitionValues)  ;
  /**
   * Return the user-defined metadata for the operation.
   * @param op (undocumented)
   * @return (undocumented)
   */
  public  scala.Option<java.lang.String> getUserMetadata (org.apache.spark.sql.delta.DeltaOperations.Operation op)  ;
  public  void logError (scala.Function0<java.lang.String> msg)  ;
  public  void logError (scala.Function0<java.lang.String> msg, java.lang.Throwable throwable)  ;
  public  void logInfo (scala.Function0<java.lang.String> msg)  ;
  public  void logWarning (scala.Function0<java.lang.String> msg)  ;
  public  void logWarning (scala.Function0<java.lang.String> msg, java.lang.Throwable throwable)  ;
  /**
   * Returns the metadata for this transaction. The metadata refers to the metadata of the snapshot
   * at the transaction's read version unless updated during the transaction.
   * @return (undocumented)
   */
  public  org.apache.spark.sql.delta.actions.Metadata metadata ()  ;
  /** Stores the updated metadata (if any) that will result from this txn. */
  public  scala.Option<org.apache.spark.sql.delta.actions.Metadata> newMetadata ()  ;
  /** Stores the updated protocol (if any) that will result from this txn. */
  public  scala.Option<org.apache.spark.sql.delta.actions.Protocol> newProtocol ()  ;
  /** Perform post-commit operations */
  public  void postCommit (long commitVersion, scala.collection.Seq<org.apache.spark.sql.delta.actions.Action> commitActions)  ;
  public  scala.collection.mutable.ArrayBuffer<org.apache.spark.sql.delta.hooks.PostCommitHook> postCommitHooks ()  ;
  /**
   * Prepare for a commit by doing all necessary pre-commit checks and modifications to the actions.
   * @return The finalized set of actions.
   * @param actions (undocumented)
   * @param op (undocumented)
   */
  public  scala.collection.Seq<org.apache.spark.sql.delta.actions.Action> prepareCommit (scala.collection.Seq<org.apache.spark.sql.delta.actions.Action> actions, org.apache.spark.sql.delta.DeltaOperations.Operation op)  ;
  /** The protocol of the snapshot that this transaction is reading at. */
  public  org.apache.spark.sql.delta.actions.Protocol protocol ()  ;
  /** Tracks specific files that have been seen by this transaction. */
  public  scala.collection.mutable.HashSet<org.apache.spark.sql.delta.actions.AddFile> readFiles ()  ;
  /**
   * Tracks the data that could have been seen by recording the partition
   * predicates by which files have been queried by this transaction.
   * @return (undocumented)
   */
  public  scala.collection.mutable.ArrayBuffer<org.apache.spark.sql.catalyst.expressions.Expression> readPredicates ()  ;
  /** Tracks the appIds that have been seen by this transaction. */
  public  scala.collection.mutable.ArrayBuffer<java.lang.String> readTxn ()  ;
  /** The version that this transaction is reading from. */
  public  long readVersion ()  ;
  /** Mark the entire table as tainted by this transaction. */
  public  void readWholeTable ()  ;
  /** Register a hook that will be executed once a commit is successful. */
  public  void registerPostCommitHook (org.apache.spark.sql.delta.hooks.PostCommitHook hook)  ;
  /** Executes the registered post commit hooks. */
  public  void runPostCommitHooks (long version, scala.collection.Seq<org.apache.spark.sql.delta.actions.Action> committedActions)  ;
  public  org.apache.spark.sql.delta.Snapshot snapshot ()  ;
  public  org.apache.spark.sql.SparkSession spark ()  ;
  public  long txnStartNano ()  ;
  /**
   * Returns the latest version that has committed for the idempotent transaction with given <code>id</code>.
   * @param id (undocumented)
   * @return (undocumented)
   */
  public  long txnVersion (java.lang.String id)  ;
  /**
   * Records an update to the metadata that should be committed with this transaction.
   * Note that this must be done before writing out any files so that file writing
   * and checks happen with the final metadata for the table.
   * <p>
   * IMPORTANT: It is the responsibility of the caller to ensure that files currently
   * present in the table are still valid under the new metadata.
   * @param metadata (undocumented)
   */
  public  void updateMetadata (org.apache.spark.sql.delta.actions.Metadata metadata)  ;
  /**
   * Records an update to the metadata that should be committed with this transaction and when
   * this transaction is logically creating a new table, e.g. replacing a previous table with new
   * metadata. Note that this must be done before writing out any files so that file writing
   * and checks happen with the final metadata for the table.
   * <p>
   * IMPORTANT: It is the responsibility of the caller to ensure that files currently
   * present in the table are still valid under the new metadata.
   * @param metadata (undocumented)
   */
  public  void updateMetadataForNewTable (org.apache.spark.sql.delta.actions.Metadata metadata)  ;
  public  void verifyNewMetadata (org.apache.spark.sql.delta.actions.Metadata metadata)  ;
  /** Creates new metadata with global Delta configuration defaults. */
  public  org.apache.spark.sql.delta.actions.Metadata withGlobalConfigDefaults (org.apache.spark.sql.delta.actions.Metadata metadata)  ;
}
