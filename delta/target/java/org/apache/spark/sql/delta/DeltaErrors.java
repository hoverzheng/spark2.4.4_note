package org.apache.spark.sql.delta;
/**
 * A holder object for Delta errors.
 * <p>
 * IMPORTANT: Any time you add a test that references the docs, add to the Seq defined in
 * DeltaErrorsSuite so that the doc links that are generated can be verified to work in Azure,
 * docs.databricks.com and docs.delta.io
 */
public  class DeltaErrors {
  static public  class TimestampEarlierThanCommitRetentionException extends org.apache.spark.sql.AnalysisException implements scala.Product, scala.Serializable {
    public  java.sql.Timestamp userTimestamp ()  { throw new RuntimeException(); }
    public  java.sql.Timestamp commitTs ()  { throw new RuntimeException(); }
    public  java.lang.String timestampString ()  { throw new RuntimeException(); }
    // not preceding
    public   TimestampEarlierThanCommitRetentionException (java.sql.Timestamp userTimestamp, java.sql.Timestamp commitTs, java.lang.String timestampString)  { throw new RuntimeException(); }
  }
  static public  class TimestampEarlierThanCommitRetentionException$ extends scala.runtime.AbstractFunction3<java.sql.Timestamp, java.sql.Timestamp, java.lang.String, org.apache.spark.sql.delta.DeltaErrors.TimestampEarlierThanCommitRetentionException> implements scala.Serializable {
    /**
     * Static reference to the singleton instance of this Scala object.
     */
    public static final TimestampEarlierThanCommitRetentionException$ MODULE$ = null;
    public   TimestampEarlierThanCommitRetentionException$ ()  { throw new RuntimeException(); }
  }
  static public  class TemporallyUnstableInputException extends org.apache.spark.sql.AnalysisException implements scala.Product, scala.Serializable {
    public  java.sql.Timestamp userTimestamp ()  { throw new RuntimeException(); }
    public  java.sql.Timestamp commitTs ()  { throw new RuntimeException(); }
    public  java.lang.String timestampString ()  { throw new RuntimeException(); }
    public  long commitVersion ()  { throw new RuntimeException(); }
    // not preceding
    public   TemporallyUnstableInputException (java.sql.Timestamp userTimestamp, java.sql.Timestamp commitTs, java.lang.String timestampString, long commitVersion)  { throw new RuntimeException(); }
  }
  static public  class TemporallyUnstableInputException$ extends scala.runtime.AbstractFunction4<java.sql.Timestamp, java.sql.Timestamp, java.lang.String, java.lang.Object, org.apache.spark.sql.delta.DeltaErrors.TemporallyUnstableInputException> implements scala.Serializable {
    /**
     * Static reference to the singleton instance of this Scala object.
     */
    public static final TemporallyUnstableInputException$ MODULE$ = null;
    public   TemporallyUnstableInputException$ ()  { throw new RuntimeException(); }
  }
  static public  java.lang.String baseDocsPath (org.apache.spark.sql.SparkSession spark)  { throw new RuntimeException(); }
  static public  java.lang.String faqRelativePath ()  { throw new RuntimeException(); }
  static public  java.lang.String EmptyCheckpointErrorMessage ()  { throw new RuntimeException(); }
  static public  java.lang.Throwable deltaSourceIgnoreDeleteError (long version, java.lang.String removedFile)  { throw new RuntimeException(); }
  static public  java.lang.Throwable deltaSourceIgnoreChangesError (long version, java.lang.String removedFile)  { throw new RuntimeException(); }
  /**
   * File not found hint for Delta, replacing the normal one which is inapplicable.
   * <p>
   * Note that we must pass in the docAddress as a string, because the config is not available on
   * executors where this method is called.
   * @param faqPath (undocumented)
   * @param path (undocumented)
   * @return (undocumented)
   */
  static public  java.lang.String deltaFileNotFoundHint (java.lang.String faqPath, java.lang.String path)  { throw new RuntimeException(); }
  static public  java.lang.String formatColumn (java.lang.String colName)  { throw new RuntimeException(); }
  static public  java.lang.String formatColumnList (scala.collection.Seq<java.lang.String> colNames)  { throw new RuntimeException(); }
  static public  java.lang.String formatSchema (org.apache.spark.sql.types.StructType schema)  { throw new RuntimeException(); }
  static public  org.apache.spark.sql.AnalysisException analysisException (java.lang.String msg, scala.Option<java.lang.Object> line, scala.Option<java.lang.Object> startPosition, scala.Option<org.apache.spark.sql.catalyst.plans.logical.LogicalPlan> plan, scala.Option<java.lang.Throwable> cause)  { throw new RuntimeException(); }
  static public  java.lang.Throwable notNullColumnMissingException (org.apache.spark.sql.delta.constraints.Constraints.NotNull constraint)  { throw new RuntimeException(); }
  static public  org.apache.spark.sql.AnalysisException nestedNotNullConstraint (java.lang.String parent, org.apache.spark.sql.types.DataType nested, java.lang.String nestType)  { throw new RuntimeException(); }
  static public  org.apache.spark.sql.AnalysisException constraintAlreadyExists (java.lang.String name, java.lang.String oldExpr)  { throw new RuntimeException(); }
  static public  org.apache.spark.sql.AnalysisException checkConstraintNotBoolean (java.lang.String name, java.lang.String expr)  { throw new RuntimeException(); }
  static public  org.apache.spark.sql.AnalysisException newCheckConstraintViolated (long num, java.lang.String tableName, java.lang.String expr)  { throw new RuntimeException(); }
  static public  org.apache.spark.sql.AnalysisException newNotNullViolated (long num, java.lang.String tableName, org.apache.spark.sql.catalyst.analysis.UnresolvedAttribute col)  { throw new RuntimeException(); }
  static public  org.apache.spark.sql.AnalysisException useAddConstraints ()  { throw new RuntimeException(); }
  static public  java.lang.Throwable incorrectLogStoreImplementationException (org.apache.spark.SparkConf sparkConf, java.lang.Throwable cause)  { throw new RuntimeException(); }
  static public  java.lang.Throwable failOnDataLossException (long expectedVersion, long seenVersion)  { throw new RuntimeException(); }
  static public  java.lang.Throwable staticPartitionsNotSupportedException ()  { throw new RuntimeException(); }
  static public  java.lang.Throwable operationNotSupportedException (java.lang.String operation, org.apache.spark.sql.catalyst.TableIdentifier tableIdentifier)  { throw new RuntimeException(); }
  static public  java.lang.Throwable operationNotSupportedException (java.lang.String operation)  { throw new RuntimeException(); }
  static public  java.lang.Throwable emptyDataException ()  { throw new RuntimeException(); }
  static public  java.lang.Throwable notADeltaTableException (org.apache.spark.sql.delta.DeltaTableIdentifier deltaTableIdentifier)  { throw new RuntimeException(); }
  static public  java.lang.Throwable notADeltaTableException (java.lang.String operation, org.apache.spark.sql.delta.DeltaTableIdentifier deltaTableIdentifier)  { throw new RuntimeException(); }
  static public  java.lang.Throwable notADeltaTableException (java.lang.String operation)  { throw new RuntimeException(); }
  static public  java.lang.Throwable notADeltaSourceException (java.lang.String command, scala.Option<org.apache.spark.sql.catalyst.plans.logical.LogicalPlan> plan)  { throw new RuntimeException(); }
  static public  java.lang.Throwable schemaChangedSinceAnalysis (org.apache.spark.sql.types.StructType atAnalysis, org.apache.spark.sql.types.StructType latestSchema)  { throw new RuntimeException(); }
  static public  java.lang.Throwable invalidColumnName (java.lang.String name)  { throw new RuntimeException(); }
  static public  java.lang.Throwable invalidPartitionColumn (org.apache.spark.sql.AnalysisException e)  { throw new RuntimeException(); }
  static public  java.lang.Throwable missingTableIdentifierException (java.lang.String operationName)  { throw new RuntimeException(); }
  static public  java.lang.Throwable viewInDescribeDetailException (org.apache.spark.sql.catalyst.TableIdentifier view)  { throw new RuntimeException(); }
  static public  java.lang.Throwable alterTableChangeColumnException (java.lang.String oldColumns, java.lang.String newColumns)  { throw new RuntimeException(); }
  static public  java.lang.Throwable notEnoughColumnsInInsert (java.lang.String table, int query, int target, scala.Option<java.lang.String> nestedField)  { throw new RuntimeException(); }
  static public  java.lang.Throwable cannotInsertIntoColumn (java.lang.String tableName, java.lang.String source, java.lang.String target, java.lang.String targetType)  { throw new RuntimeException(); }
  static public  java.lang.Throwable alterTableReplaceColumnsException (org.apache.spark.sql.types.StructType oldSchema, org.apache.spark.sql.types.StructType newSchema, java.lang.String reason)  { throw new RuntimeException(); }
  static public  java.lang.Throwable unsetNonExistentPropertyException (java.lang.String propertyKey, org.apache.spark.sql.delta.DeltaTableIdentifier deltaTableIdentifier)  { throw new RuntimeException(); }
  static public  java.lang.Throwable ambiguousPartitionColumnException (java.lang.String columnName, scala.collection.Seq<org.apache.spark.sql.types.StructField> colMatches)  { throw new RuntimeException(); }
  static public  java.lang.Throwable tableNotSupportedException (java.lang.String operation)  { throw new RuntimeException(); }
  static public  java.lang.Throwable vacuumBasePathMissingException (org.apache.hadoop.fs.Path baseDeltaPath)  { throw new RuntimeException(); }
  static public  java.lang.Throwable unexpectedDataChangeException (java.lang.String op)  { throw new RuntimeException(); }
  static public  java.lang.Throwable unknownConfigurationKeyException (java.lang.String confKey)  { throw new RuntimeException(); }
  static public  java.lang.Throwable useDeltaOnOtherFormatPathException (java.lang.String operation, java.lang.String path, org.apache.spark.sql.SparkSession spark)  { throw new RuntimeException(); }
  static public  java.lang.Throwable useOtherFormatOnDeltaPathException (java.lang.String operation, java.lang.String deltaRootPath, java.lang.String path, java.lang.String format, org.apache.spark.sql.SparkSession spark)  { throw new RuntimeException(); }
  static public  java.lang.Throwable pathNotSpecifiedException ()  { throw new RuntimeException(); }
  static public  java.lang.Throwable pathNotExistsException (java.lang.String path)  { throw new RuntimeException(); }
  static public  java.lang.Throwable pathAlreadyExistsException (org.apache.hadoop.fs.Path path)  { throw new RuntimeException(); }
  static public  java.lang.Throwable logFileNotFoundException (org.apache.hadoop.fs.Path path, long version, org.apache.spark.sql.delta.actions.Metadata metadata)  { throw new RuntimeException(); }
  static public  java.lang.Throwable logFileNotFoundExceptionForStreamingSource (java.io.FileNotFoundException e)  { throw new RuntimeException(); }
  static public  java.lang.Throwable multipleLoadPathsException (scala.collection.Seq<java.lang.String> paths)  { throw new RuntimeException(); }
  static public  java.lang.Throwable partitionColumnNotFoundException (java.lang.String colName, scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Attribute> schema)  { throw new RuntimeException(); }
  static public  java.lang.Throwable partitionPathParseException (java.lang.String fragment)  { throw new RuntimeException(); }
  static public  java.lang.Throwable partitionPathInvolvesNonPartitionColumnException (scala.collection.Seq<java.lang.String> badColumns, java.lang.String fragment)  { throw new RuntimeException(); }
  static public  java.lang.Throwable nonPartitionColumnAbsentException (boolean colsDropped)  { throw new RuntimeException(); }
  static public  java.lang.Throwable replaceWhereMismatchException (java.lang.String replaceWhere, java.lang.String badPartitions)  { throw new RuntimeException(); }
  static public  java.lang.Throwable illegalDeltaOptionException (java.lang.String name, java.lang.String input, java.lang.String explain)  { throw new RuntimeException(); }
  static public  java.lang.Throwable startingVersionAndTimestampBothSetException (java.lang.String versionOptKey, java.lang.String timestampOptKey)  { throw new RuntimeException(); }
  static public  java.lang.Throwable unrecognizedLogFile (org.apache.hadoop.fs.Path path)  { throw new RuntimeException(); }
  static public  java.lang.Throwable modifyAppendOnlyTableException ()  { throw new RuntimeException(); }
  static public  java.lang.Throwable missingPartFilesException (long version, java.lang.Exception ae)  { throw new RuntimeException(); }
  static public  java.lang.Throwable deltaVersionsNotContiguousException (org.apache.spark.sql.SparkSession spark, scala.collection.Seq<java.lang.Object> deltaVersions)  { throw new RuntimeException(); }
  static public  java.lang.Throwable actionNotFoundException (java.lang.String action, long version)  { throw new RuntimeException(); }
  static public  java.lang.Throwable schemaChangedException (org.apache.spark.sql.types.StructType oldSchema, org.apache.spark.sql.types.StructType newSchema)  { throw new RuntimeException(); }
  static public  java.lang.Throwable streamWriteNullTypeException ()  { throw new RuntimeException(); }
  static public  java.lang.Throwable schemaNotSetException ()  { throw new RuntimeException(); }
  static public  java.lang.Throwable specifySchemaAtReadTimeException ()  { throw new RuntimeException(); }
  static public  java.lang.Throwable schemaNotProvidedException ()  { throw new RuntimeException(); }
  static public  java.lang.Throwable outputModeNotSupportedException (java.lang.String dataSource, org.apache.spark.sql.streaming.OutputMode outputMode)  { throw new RuntimeException(); }
  static public  java.lang.Throwable updateSetColumnNotFoundException (java.lang.String col, scala.collection.Seq<java.lang.String> colList)  { throw new RuntimeException(); }
  static public  java.lang.Throwable updateSetConflictException (scala.collection.Seq<java.lang.String> cols)  { throw new RuntimeException(); }
  static public  java.lang.Throwable updateNonStructTypeFieldNotSupportedException (java.lang.String col, org.apache.spark.sql.types.DataType s)  { throw new RuntimeException(); }
  static public  java.lang.Throwable truncateTablePartitionNotSupportedException ()  { throw new RuntimeException(); }
  static public  java.lang.Throwable bloomFilterOnPartitionColumnNotSupportedException (java.lang.String name)  { throw new RuntimeException(); }
  static public  java.lang.Throwable bloomFilterOnNestedColumnNotSupportedException (java.lang.String name)  { throw new RuntimeException(); }
  static public  java.lang.Throwable bloomFilterOnColumnTypeNotSupportedException (java.lang.String name, org.apache.spark.sql.types.DataType dataType)  { throw new RuntimeException(); }
  static public  java.lang.Throwable bloomFilterMultipleConfForSingleColumnException (java.lang.String name)  { throw new RuntimeException(); }
  static public  java.lang.Throwable bloomFilterCreateOnNonExistingColumnsException (scala.collection.Seq<java.lang.String> unknownColumns)  { throw new RuntimeException(); }
  static public  java.lang.Throwable bloomFilterInvalidParameterValueException (java.lang.String message)  { throw new RuntimeException(); }
  static public  java.lang.Throwable bloomFilterDropOnNonIndexedColumnException (java.lang.String name)  { throw new RuntimeException(); }
  static public  java.lang.Throwable bloomFilterDropOnNonExistingColumnsException (scala.collection.Seq<java.lang.String> unknownColumns)  { throw new RuntimeException(); }
  static public  java.lang.Throwable multipleSourceRowMatchingTargetRowInMergeException (org.apache.spark.sql.SparkSession spark)  { throw new RuntimeException(); }
  static public  java.lang.Throwable subqueryNotSupportedException (java.lang.String op, org.apache.spark.sql.catalyst.expressions.Expression cond)  { throw new RuntimeException(); }
  static public  java.lang.Throwable multiColumnInPredicateNotSupportedException (java.lang.String operation)  { throw new RuntimeException(); }
  static public  java.lang.Throwable nestedSubqueryNotSupportedException (java.lang.String operation)  { throw new RuntimeException(); }
  static public  java.lang.Throwable nestedFieldNotSupported (java.lang.String operation, java.lang.String field)  { throw new RuntimeException(); }
  static public  java.lang.Throwable inSubqueryNotSupportedException (java.lang.String operation)  { throw new RuntimeException(); }
  static public  java.lang.Throwable convertMetastoreMetadataMismatchException (scala.collection.immutable.Map<java.lang.String, java.lang.String> tableProperties, scala.collection.immutable.Map<java.lang.String, java.lang.String> deltaConfiguration)  { throw new RuntimeException(); }
  static public  java.lang.Throwable createExternalTableWithoutLogException (org.apache.hadoop.fs.Path path, java.lang.String tableName, org.apache.spark.sql.SparkSession spark)  { throw new RuntimeException(); }
  static public  java.lang.Throwable createExternalTableWithoutSchemaException (org.apache.hadoop.fs.Path path, java.lang.String tableName, org.apache.spark.sql.SparkSession spark)  { throw new RuntimeException(); }
  static public  java.lang.Throwable createManagedTableWithoutSchemaException (java.lang.String tableName, org.apache.spark.sql.SparkSession spark)  { throw new RuntimeException(); }
  static public  java.lang.Throwable createTableWithDifferentSchemaException (org.apache.hadoop.fs.Path path, org.apache.spark.sql.types.StructType specifiedSchema, org.apache.spark.sql.types.StructType existingSchema, scala.collection.Seq<java.lang.String> diffs)  { throw new RuntimeException(); }
  static public  java.lang.Throwable createTableWithDifferentPartitioningException (org.apache.hadoop.fs.Path path, scala.collection.Seq<java.lang.String> specifiedColumns, scala.collection.Seq<java.lang.String> existingColumns)  { throw new RuntimeException(); }
  static public  java.lang.Throwable createTableWithDifferentPropertiesException (org.apache.hadoop.fs.Path path, scala.collection.immutable.Map<java.lang.String, java.lang.String> specifiedProperties, scala.collection.immutable.Map<java.lang.String, java.lang.String> existingProperties)  { throw new RuntimeException(); }
  static public  java.lang.Throwable aggsNotSupportedException (java.lang.String op, org.apache.spark.sql.catalyst.expressions.Expression cond)  { throw new RuntimeException(); }
  static public  java.lang.Throwable nonDeterministicNotSupportedException (java.lang.String op, org.apache.spark.sql.catalyst.expressions.Expression cond)  { throw new RuntimeException(); }
  static public  java.lang.Throwable noHistoryFound (org.apache.hadoop.fs.Path logPath)  { throw new RuntimeException(); }
  static public  java.lang.Throwable noReproducibleHistoryFound (org.apache.hadoop.fs.Path logPath)  { throw new RuntimeException(); }
  static public  java.lang.Throwable timestampGreaterThanLatestCommit (java.sql.Timestamp userTimestamp, java.sql.Timestamp commitTs, java.lang.String timestampString)  { throw new RuntimeException(); }
  static public  java.lang.Throwable timeTravelNotSupportedException ()  { throw new RuntimeException(); }
  static public  java.lang.Throwable multipleTimeTravelSyntaxUsed ()  { throw new RuntimeException(); }
  static public  java.lang.Throwable provideOneOfInTimeTravel ()  { throw new RuntimeException(); }
  static public  java.lang.Throwable deltaLogAlreadyExistsException (java.lang.String path)  { throw new RuntimeException(); }
  static public  java.lang.Throwable commitAlreadyExistsException (long version, org.apache.hadoop.fs.Path logPath)  { throw new RuntimeException(); }
  static public  java.lang.Throwable missingProviderForConvertException (java.lang.String path)  { throw new RuntimeException(); }
  static public  java.lang.Throwable convertNonParquetTablesException (org.apache.spark.sql.catalyst.TableIdentifier ident, java.lang.String sourceName)  { throw new RuntimeException(); }
  static public  java.lang.Throwable unexpectedPartitionColumnFromFileNameException (java.lang.String path, java.lang.String parsedCol, java.lang.String expectedCol)  { throw new RuntimeException(); }
  static public  java.lang.Throwable unexpectedNumPartitionColumnsFromFileNameException (java.lang.String path, scala.collection.Seq<java.lang.String> parsedCols, scala.collection.Seq<java.lang.String> expectedCols)  { throw new RuntimeException(); }
  static public  java.lang.Throwable castPartitionValueException (java.lang.String partitionValue, org.apache.spark.sql.types.DataType dataType)  { throw new RuntimeException(); }
  static public  java.lang.Throwable emptyDirectoryException (java.lang.String directory)  { throw new RuntimeException(); }
  static public  java.lang.Throwable alterTableSetLocationSchemaMismatchException (org.apache.spark.sql.types.StructType original, org.apache.spark.sql.types.StructType destination)  { throw new RuntimeException(); }
  static public  java.lang.Throwable setLocationNotSupportedOnPathIdentifiers ()  { throw new RuntimeException(); }
  static public  java.lang.Throwable describeViewHistory ()  { throw new RuntimeException(); }
  static public  java.lang.Throwable copyIntoEncryptionOnlyS3 (java.lang.String scheme)  { throw new RuntimeException(); }
  static public  java.lang.Throwable copyIntoEncryptionSseCRequired ()  { throw new RuntimeException(); }
  static public  java.lang.Throwable copyIntoEncryptionMasterKeyRequired ()  { throw new RuntimeException(); }
  static public  java.lang.Throwable copyIntoCredentialsOnlyS3 (java.lang.String scheme)  { throw new RuntimeException(); }
  static public  java.lang.Throwable copyIntoCredentialsAllRequired (java.lang.Throwable cause)  { throw new RuntimeException(); }
  static public  java.lang.Throwable postCommitHookFailedException (org.apache.spark.sql.delta.hooks.PostCommitHook failedHook, long failedOnCommitVersion, java.lang.String extraErrorMessage, java.lang.Throwable error)  { throw new RuntimeException(); }
  static public  java.lang.Throwable unsupportedGenerateModeException (java.lang.String modeName)  { throw new RuntimeException(); }
  static public  java.lang.Throwable illegalUsageException (java.lang.String option, java.lang.String operation)  { throw new RuntimeException(); }
  static public  java.lang.Throwable columnNotInSchemaException (java.lang.String column, org.apache.spark.sql.types.StructType schema)  { throw new RuntimeException(); }
  static public  java.lang.Throwable metadataAbsentException ()  { throw new RuntimeException(); }
  static public  java.lang.Throwable addFilePartitioningMismatchException (scala.collection.Seq<java.lang.String> addFilePartitions, scala.collection.Seq<java.lang.String> metadataPartitions)  { throw new RuntimeException(); }
  static public  java.lang.String concurrentModificationExceptionMsg (org.apache.spark.SparkConf sparkConf, java.lang.String baseMessage, scala.Option<org.apache.spark.sql.delta.actions.CommitInfo> commit)  { throw new RuntimeException(); }
  static public  java.lang.String ignoreStreamingUpdatesAndDeletesWarning (org.apache.spark.sql.SparkSession spark)  { throw new RuntimeException(); }
  static public  java.lang.Throwable configureSparkSessionWithExtensionAndCatalog (java.lang.Throwable originalException)  { throw new RuntimeException(); }
  static public  java.lang.Throwable maxCommitRetriesExceededException (int attemptNumber, long attemptVersion, long initAttemptVersion, int numActions, long totalCommitAttemptTime)  { throw new RuntimeException(); }
  static public  void assertValidCallingFunction ()  { throw new RuntimeException(); }
  static public  java.lang.String generateDocsLink (org.apache.spark.SparkConf conf, java.lang.String relativePath, boolean skipValidation)  { throw new RuntimeException(); }
  static public  boolean generateDocsLink$default$3 ()  { throw new RuntimeException(); }
  static public  scala.collection.Seq<java.lang.String> errorsWithDocsLinks ()  { throw new RuntimeException(); }
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
  static public <T extends java.lang.Object> T withStatusCode (java.lang.String statusCode, java.lang.String defaultMessage, scala.collection.immutable.Map<java.lang.String, java.lang.Object> data, scala.Function0<T> body)  { throw new RuntimeException(); }
  static public <T extends java.lang.Object> scala.collection.immutable.Map<java.lang.String, java.lang.Object> withStatusCode$default$3 ()  { throw new RuntimeException(); }
  static public  void logConsole (java.lang.String line)  { throw new RuntimeException(); }
  static public  void recordUsage (com.databricks.spark.util.MetricDefinition metric, double quantity, scala.collection.immutable.Map<com.databricks.spark.util.TagDefinition, java.lang.String> additionalTags, java.lang.String blob, boolean forceSample, boolean trimBlob, boolean silent)  { throw new RuntimeException(); }
  static public  scala.collection.immutable.Map<com.databricks.spark.util.TagDefinition, java.lang.String> recordUsage$default$3 ()  { throw new RuntimeException(); }
  static public  java.lang.String recordUsage$default$4 ()  { throw new RuntimeException(); }
  static public  boolean recordUsage$default$5 ()  { throw new RuntimeException(); }
  static public  boolean recordUsage$default$6 ()  { throw new RuntimeException(); }
  static public  boolean recordUsage$default$7 ()  { throw new RuntimeException(); }
  static public  void recordEvent (com.databricks.spark.util.MetricDefinition metric, scala.collection.immutable.Map<com.databricks.spark.util.TagDefinition, java.lang.String> additionalTags, java.lang.String blob, boolean trimBlob)  { throw new RuntimeException(); }
  static public  scala.collection.immutable.Map<com.databricks.spark.util.TagDefinition, java.lang.String> recordEvent$default$2 ()  { throw new RuntimeException(); }
  static public  java.lang.String recordEvent$default$3 ()  { throw new RuntimeException(); }
  static public  boolean recordEvent$default$4 ()  { throw new RuntimeException(); }
  static public <S extends java.lang.Object> S recordOperation (com.databricks.spark.util.OpType opType, java.lang.String opTarget, scala.collection.immutable.Map<com.databricks.spark.util.TagDefinition, java.lang.String> extraTags, boolean isSynchronous, boolean alwaysRecordStats, boolean allowAuthTags, boolean killJvmIfStuck, com.databricks.spark.util.MetricDefinition outputMetric, boolean silent, scala.Function0<S> thunk)  { throw new RuntimeException(); }
  static public <S extends java.lang.Object> java.lang.String recordOperation$default$2 ()  { throw new RuntimeException(); }
  static public <S extends java.lang.Object> boolean recordOperation$default$4 ()  { throw new RuntimeException(); }
  static public <S extends java.lang.Object> boolean recordOperation$default$5 ()  { throw new RuntimeException(); }
  static public <S extends java.lang.Object> boolean recordOperation$default$6 ()  { throw new RuntimeException(); }
  static public <S extends java.lang.Object> boolean recordOperation$default$7 ()  { throw new RuntimeException(); }
  static public <S extends java.lang.Object> com.databricks.spark.util.MetricDefinition recordOperation$default$8 ()  { throw new RuntimeException(); }
  static public <S extends java.lang.Object> boolean recordOperation$default$9 ()  { throw new RuntimeException(); }
  static protected  void recordDeltaEvent (org.apache.spark.sql.delta.DeltaLog deltaLog, java.lang.String opType, scala.collection.immutable.Map<com.databricks.spark.util.TagDefinition, java.lang.String> tags, java.lang.Object data)  { throw new RuntimeException(); }
  static protected  scala.collection.immutable.Map<com.databricks.spark.util.TagDefinition, java.lang.String> recordDeltaEvent$default$3 ()  { throw new RuntimeException(); }
  static protected  java.lang.Object recordDeltaEvent$default$4 ()  { throw new RuntimeException(); }
  static protected <A extends java.lang.Object> A recordDeltaOperation (org.apache.spark.sql.delta.DeltaLog deltaLog, java.lang.String opType, scala.collection.immutable.Map<com.databricks.spark.util.TagDefinition, java.lang.String> tags, scala.Function0<A> thunk)  { throw new RuntimeException(); }
  static protected <A extends java.lang.Object> scala.collection.immutable.Map<com.databricks.spark.util.TagDefinition, java.lang.String> recordDeltaOperation$default$3 ()  { throw new RuntimeException(); }
}
