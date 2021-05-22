package org.apache.spark.sql.delta.commands;
/**
 * Convert an existing parquet table to a delta table by creating delta logs based on
 * existing files. Here are the main components:
 * <p>
 *   - File Listing:      Launch a spark job to list files from a given directory in parallel.
 * <p>
 *   - Schema Inference:  Given an iterator on the file list result, we group the iterator into
 *                        sequential batches and launch a spark job to infer schema for each batch,
 *                        and finally merge schemas from all batches.
 * <p>
 *   - Stats collection:  Again, we group the iterator on file list results into sequential batches
 *                        and launch a spark job to collect stats for each batch.
 * <p>
 *   - Commit the files:  We take the iterator of files with stats and write out a delta
 *                        log file as the first commit. This bypasses the transaction protocol, but
 *                        it's ok as this would be the very first commit.
 * <p>
 * param:  tableIdentifier the target parquet table.
 * param:  partitionSchema the partition schema of the table, required when table is partitioned.
 * param:  deltaPath if provided, the delta log will be written to this location.
 */
public abstract class ConvertToDeltaCommandBase extends org.apache.spark.sql.catalyst.plans.logical.LogicalPlan implements org.apache.spark.sql.execution.command.RunnableCommand, org.apache.spark.sql.delta.commands.DeltaCommand {
  protected  class ConvertTarget implements scala.Product, scala.Serializable {
    static public abstract  R apply (T1 v1, T2 v2, T3 v3, T4 v4)  ;
    static public  java.lang.String toString ()  { throw new RuntimeException(); }
    public  scala.Option<org.apache.spark.sql.catalyst.catalog.CatalogTable> catalogTable ()  { throw new RuntimeException(); }
    public  scala.Option<java.lang.String> provider ()  { throw new RuntimeException(); }
    public  java.lang.String targetDir ()  { throw new RuntimeException(); }
    public  scala.collection.immutable.Map<java.lang.String, java.lang.String> properties ()  { throw new RuntimeException(); }
    // not preceding
    public   ConvertTarget (scala.Option<org.apache.spark.sql.catalyst.catalog.CatalogTable> catalogTable, scala.Option<java.lang.String> provider, java.lang.String targetDir, scala.collection.immutable.Map<java.lang.String, java.lang.String> properties)  { throw new RuntimeException(); }
  }
  protected  class ConvertTarget$ extends scala.runtime.AbstractFunction4<scala.Option<org.apache.spark.sql.catalyst.catalog.CatalogTable>, scala.Option<java.lang.String>, java.lang.String, scala.collection.immutable.Map<java.lang.String, java.lang.String>, org.apache.spark.sql.delta.commands.ConvertToDeltaCommandBase.ConvertTarget> implements scala.Serializable {
    /**
     * Static reference to the singleton instance of this Scala object.
     */
    public static final ConvertTarget$ MODULE$ = null;
    public   ConvertTarget$ ()  { throw new RuntimeException(); }
  }
  /** An interface for providing an iterator of files for a table. */
  public  interface FileManifest extends java.io.Closeable {
    /** The base path of a table. Should be a qualified, normalized path. */
    public  java.lang.String basePath ()  ;
    /** Return the active files for a table */
    public  scala.collection.Iterator<org.apache.spark.sql.delta.util.SerializableFileStatus> getFiles ()  ;
  }
  /** A file manifest generated through recursively listing a base path. */
  public  class ManualListingFileManifest implements org.apache.spark.sql.delta.commands.ConvertToDeltaCommandBase.FileManifest {
    // not preceding
    public   ManualListingFileManifest (org.apache.spark.sql.SparkSession spark, java.lang.String basePath, org.apache.spark.util.SerializableConfiguration serializableConf)  { throw new RuntimeException(); }
    public  java.lang.String basePath ()  { throw new RuntimeException(); }
    public  void close ()  { throw new RuntimeException(); }
    protected  org.apache.spark.sql.Dataset<org.apache.spark.sql.delta.util.SerializableFileStatus> doList ()  { throw new RuntimeException(); }
    public  scala.collection.Iterator<org.apache.spark.sql.delta.util.SerializableFileStatus> getFiles ()  { throw new RuntimeException(); }
  }
  /** A file manifest generated from pre-existing parquet MetadataLog. */
  protected  class MetadataLogFileManifest implements org.apache.spark.sql.delta.commands.ConvertToDeltaCommandBase.FileManifest {
    // not preceding
    public   MetadataLogFileManifest (org.apache.spark.sql.SparkSession spark, java.lang.String basePath)  { throw new RuntimeException(); }
    public  java.lang.String basePath ()  { throw new RuntimeException(); }
    public  void close ()  { throw new RuntimeException(); }
    public  scala.collection.Iterator<org.apache.spark.sql.delta.util.SerializableFileStatus> getFiles ()  { throw new RuntimeException(); }
    public  org.apache.spark.sql.execution.streaming.MetadataLogFileIndex index ()  { throw new RuntimeException(); }
  }
  public   ConvertToDeltaCommandBase (org.apache.spark.sql.catalyst.TableIdentifier tableIdentifier, scala.Option<org.apache.spark.sql.types.StructType> partitionSchema, scala.Option<java.lang.String> deltaPath)  { throw new RuntimeException(); }
  /**
   * Construct a table schema by merging data schema and partition schema.
   * We follow the merge logic in {@link org.apache.spark.sql.execution.datasources.HadoopFsRelation}:
   * <p>
   *   When data and partition schemas have overlapping columns, the output
   *   schema respects the order of the data schema for the overlapping columns, and it
   *   respects the data types of the partition schema.
   * @param spark (undocumented)
   * @param dataSchema (undocumented)
   * @param partitionFields (undocumented)
   * @return (undocumented)
   */
  protected  org.apache.spark.sql.types.StructType constructTableSchema (org.apache.spark.sql.SparkSession spark, org.apache.spark.sql.types.StructType dataSchema, scala.collection.Seq<org.apache.spark.sql.types.StructField> partitionFields)  { throw new RuntimeException(); }
  protected  org.apache.spark.sql.delta.actions.AddFile createAddFile (org.apache.spark.sql.delta.util.SerializableFileStatus file, org.apache.hadoop.fs.Path basePath, org.apache.hadoop.fs.FileSystem fs, org.apache.spark.sql.internal.SQLConf conf)  { throw new RuntimeException(); }
  /**
   * Given the file manifest, create corresponding AddFile actions for the entire list of files.
   * @param spark (undocumented)
   * @param manifest (undocumented)
   * @param txn (undocumented)
   * @param fs (undocumented)
   * @return (undocumented)
   */
  protected  scala.collection.Iterator<org.apache.spark.sql.delta.actions.AddFile> createDeltaActions (org.apache.spark.sql.SparkSession spark, org.apache.spark.sql.delta.commands.ConvertToDeltaCommandBase.FileManifest manifest, org.apache.spark.sql.delta.OptimisticTransaction txn, org.apache.hadoop.fs.FileSystem fs)  { throw new RuntimeException(); }
  protected  scala.collection.immutable.Map<java.lang.String, java.lang.String> getContext ()  { throw new RuntimeException(); }
  /**
   * Generate a file manifest for the table with the given base path <code>qualifiedDir</code>.
   * @param spark (undocumented)
   * @param qualifiedDir (undocumented)
   * @param serializableConf (undocumented)
   * @return (undocumented)
   */
  protected  org.apache.spark.sql.delta.commands.ConvertToDeltaCommandBase.FileManifest getFileManifest (org.apache.spark.sql.SparkSession spark, java.lang.String qualifiedDir, org.apache.spark.util.SerializableConfiguration serializableConf)  { throw new RuntimeException(); }
  /** Get the operation to store in the commit message. */
  protected  org.apache.spark.sql.delta.DeltaOperations.Operation getOperation (long numFilesConverted, org.apache.spark.sql.delta.commands.ConvertToDeltaCommandBase.ConvertTarget convertProperties)  { throw new RuntimeException(); }
  /** Infers the schema from a batch of parquet files. */
  protected  org.apache.spark.sql.types.StructType getSchemaForBatch (org.apache.spark.sql.SparkSession spark, scala.collection.Seq<org.apache.spark.sql.delta.util.SerializableFileStatus> batch, org.apache.spark.util.SerializableConfiguration serializedConf)  { throw new RuntimeException(); }
  /**
   * Calls DeltaCommand.isCatalogTable. With Convert, we may get a format check error in cases where
   * the metastore and the underlying table don't align, e.g. external table where the underlying
   * files are converted to delta but the metadata has not been converted yet. In these cases,
   * catch the error and return based on whether the provided Table Identifier could reasonably be
   * a path
   * <p>
   * @param analyzer The session state analyzer to call
   * @param tableIdent Table Identifier to determine whether is path based or not
   * @return Boolean where true means that the table is a table in a metastore and false means the
   *         table is a path based table
   */
  public  boolean isCatalogTable (org.apache.spark.sql.catalyst.analysis.Analyzer analyzer, org.apache.spark.sql.catalyst.TableIdentifier tableIdent)  { throw new RuntimeException(); }
  /**
   * Override this method since parquet paths are valid for Convert
   * @param tableIdent the provided table or path
   * @return Whether or not the ident provided can refer to a table by path
   */
  public  boolean isPathIdentifier (org.apache.spark.sql.catalyst.TableIdentifier tableIdent)  { throw new RuntimeException(); }
  /**
   * This method is forked from {@link ParquetFileFormat}. The only change here is that we use
   * our SchemaUtils.mergeSchemas() instead of StructType.merge(), where we allow upcast between
   * ByteType, ShortType and IntegerType.
   * <p>
   * Figures out a merged Parquet schema with a distributed Spark job.
   * <p>
   * Note that locality is not taken into consideration here because:
   * <p>
   *  1. For a single Parquet part-file, in most cases the footer only resides in the last block of
   *     that file.  Thus we only need to retrieve the location of the last block.  However, Hadoop
   *     <code>FileSystem</code> only provides API to retrieve locations of all blocks, which can be
   *     potentially expensive.
   * <p>
   *  2. This optimization is mainly useful for S3, where file metadata operations can be pretty
   *     slow.  And basically locality is not available when using S3 (you can't run computation on
   *     S3 nodes).
   * @param sparkSession (undocumented)
   * @param filesToTouch (undocumented)
   * @param serializedConf (undocumented)
   * @return (undocumented)
   */
  protected  scala.Option<org.apache.spark.sql.types.StructType> mergeSchemasInParallel (org.apache.spark.sql.SparkSession sparkSession, scala.collection.Seq<org.apache.hadoop.fs.FileStatus> filesToTouch, org.apache.spark.util.SerializableConfiguration serializedConf)  { throw new RuntimeException(); }
  public  scala.collection.immutable.Map<java.lang.String, org.apache.spark.sql.execution.metric.SQLMetric> metrics ()  { throw new RuntimeException(); }
  // not preceding
  public  scala.collection.Seq<java.lang.String> partitionColNames ()  { throw new RuntimeException(); }
  // not preceding
  public  scala.collection.Seq<org.apache.spark.sql.types.StructField> partitionFields ()  { throw new RuntimeException(); }
  public  scala.collection.Seq<org.apache.spark.sql.Row> run (org.apache.spark.sql.SparkSession spark)  { throw new RuntimeException(); }
  public  java.lang.String timestampPartitionPattern ()  { throw new RuntimeException(); }
}
