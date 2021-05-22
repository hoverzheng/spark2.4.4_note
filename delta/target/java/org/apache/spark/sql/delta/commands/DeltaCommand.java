package org.apache.spark.sql.delta.commands;
/**
 * Helper trait for all delta commands.
 */
public  interface DeltaCommand extends org.apache.spark.sql.delta.metering.DeltaLogging {
  /**
   * Build a base relation of files that need to be rewritten as part of an update/delete/merge
   * operation.
   * @param spark (undocumented)
   * @param txn (undocumented)
   * @param actionType (undocumented)
   * @param rootPath (undocumented)
   * @param inputLeafFiles (undocumented)
   * @param nameToAddFileMap (undocumented)
   * @return (undocumented)
   */
  public  org.apache.spark.sql.execution.datasources.HadoopFsRelation buildBaseRelation (org.apache.spark.sql.SparkSession spark, org.apache.spark.sql.delta.OptimisticTransaction txn, java.lang.String actionType, org.apache.hadoop.fs.Path rootPath, scala.collection.Seq<java.lang.String> inputLeafFiles, scala.collection.immutable.Map<java.lang.String, org.apache.spark.sql.delta.actions.AddFile> nameToAddFileMap)  ;
  /**
   * Create a large commit on the Delta log by directly writing an iterator of FileActions to the
   * LogStore. This bypasses the Delta transactional protocol, therefore we forego all optimistic
   * concurrency benefits. We assume that transaction conflicts should be rare, because this method
   * is typically used to create new tables.
   * @param spark (undocumented)
   * @param txn (undocumented)
   * @param actions (undocumented)
   * @param op (undocumented)
   * @param context (undocumented)
   * @param metrics (undocumented)
   * @return (undocumented)
   */
  public  long commitLarge (org.apache.spark.sql.SparkSession spark, org.apache.spark.sql.delta.OptimisticTransaction txn, scala.collection.Iterator<org.apache.spark.sql.delta.actions.Action> actions, org.apache.spark.sql.delta.DeltaOperations.Operation op, scala.collection.immutable.Map<java.lang.String, java.lang.String> context, scala.collection.immutable.Map<java.lang.String, java.lang.String> metrics)  ;
  /**
   * Generates a map of file names to add file entries for operations where we will need to
   * rewrite files such as delete, merge, update. We expect file names to be unique, because
   * each file contains a UUID.
   * @param basePath (undocumented)
   * @param candidateFiles (undocumented)
   * @return (undocumented)
   */
  public  scala.collection.immutable.Map<java.lang.String, org.apache.spark.sql.delta.actions.AddFile> generateCandidateFileMap (org.apache.hadoop.fs.Path basePath, scala.collection.Seq<org.apache.spark.sql.delta.actions.AddFile> candidateFiles)  ;
  /**
   * Find the AddFile record corresponding to the file that was read as part of a
   * delete/update/merge operation.
   * <p>
   * @param filePath The path to a file. Can be either absolute or relative
   * @param nameToAddFileMap Map generated through <code>generateCandidateFileMap()</code>
   * @param basePath (undocumented)
   * @return (undocumented)
   */
  public  org.apache.spark.sql.delta.actions.AddFile getTouchedFile (org.apache.hadoop.fs.Path basePath, java.lang.String filePath, scala.collection.immutable.Map<java.lang.String, org.apache.spark.sql.delta.actions.AddFile> nameToAddFileMap)  ;
  /**
   * Use the analyzer to see whether the provided TableIdentifier is for a path based table or not
   * @param analyzer The session state analyzer to call
   * @param tableIdent Table Identifier to determine whether is path based or not
   * @return Boolean where true means that the table is a table in a metastore and false means the
   *         table is a path based table
   */
  public  boolean isCatalogTable (org.apache.spark.sql.catalyst.analysis.Analyzer analyzer, org.apache.spark.sql.catalyst.TableIdentifier tableIdent)  ;
  /**
   * Checks if the given identifier can be for a delta table's path
   * @param tableIdent Table Identifier for which to check
   * @return (undocumented)
   */
  public  boolean isPathIdentifier (org.apache.spark.sql.catalyst.TableIdentifier tableIdent)  ;
  /**
   * Converts string predicates into {@link Expression}s relative to a transaction.
   * <p>
   * @throws AnalysisException if a non-partition column is referenced.
   * @param spark (undocumented)
   * @param predicate (undocumented)
   * @return (undocumented)
   */
  public  scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Expression> parsePartitionPredicates (org.apache.spark.sql.SparkSession spark, java.lang.String predicate)  ;
  /**
   * This method provides the RemoveFile actions that are necessary for files that are touched and
   * need to be rewritten in methods like Delete, Update, and Merge.
   * <p>
   * @param deltaLog The DeltaLog of the table that is being operated on
   * @param nameToAddFileMap A map generated using <code>generateCandidateFileMap</code>.
   * @param filesToRewrite Absolute paths of the files that were touched. We will search for these
   *                       in <code>candidateFiles</code>. Obtained as the output of the <code>input_file_name</code>
   *                       function.
   * @param operationTimestamp The timestamp of the operation
   * @return (undocumented)
   */
  public  scala.collection.Seq<org.apache.spark.sql.delta.actions.RemoveFile> removeFilesFromPaths (org.apache.spark.sql.delta.DeltaLog deltaLog, scala.collection.immutable.Map<java.lang.String, org.apache.spark.sql.delta.actions.AddFile> nameToAddFileMap, scala.collection.Seq<java.lang.String> filesToRewrite, long operationTimestamp)  ;
  /**
   * Use the analyzer to resolve the identifier provided
   * @param analyzer The session state analyzer to call
   * @param identifier Table Identifier to determine whether is path based or not
   * @return
   */
  public  org.apache.spark.sql.catalyst.plans.logical.LogicalPlan resolveIdentifier (org.apache.spark.sql.catalyst.analysis.Analyzer analyzer, org.apache.spark.sql.catalyst.TableIdentifier identifier)  ;
  /** Update the table now that the commit has been made, and write a checkpoint. */
  public  void updateAndCheckpoint (org.apache.spark.sql.SparkSession spark, org.apache.spark.sql.delta.DeltaLog deltaLog, int commitSize, long attemptVersion)  ;
  public  void verifyPartitionPredicates (org.apache.spark.sql.SparkSession spark, scala.collection.Seq<java.lang.String> partitionColumns, scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Expression> predicates)  ;
}
