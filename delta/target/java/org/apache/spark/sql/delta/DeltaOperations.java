package org.apache.spark.sql.delta;
/**
 * Exhaustive list of operations that can be performed on a Delta table. These operations are
 * tracked as the first line in delta logs, and power <code>DESCRIBE HISTORY</code> for Delta tables.
 */
public  class DeltaOperations {
  /** Recorded during batch inserts. Predicates can be provided for overwrites. */
  static public  class Write extends org.apache.spark.sql.delta.DeltaOperations.Operation implements scala.Product, scala.Serializable {
    public  org.apache.spark.sql.SaveMode mode ()  { throw new RuntimeException(); }
    public  scala.Option<scala.collection.Seq<java.lang.String>> partitionBy ()  { throw new RuntimeException(); }
    public  scala.Option<java.lang.String> predicate ()  { throw new RuntimeException(); }
    public  scala.Option<java.lang.String> userMetadata ()  { throw new RuntimeException(); }
    // not preceding
    public   Write (org.apache.spark.sql.SaveMode mode, scala.Option<scala.collection.Seq<java.lang.String>> partitionBy, scala.Option<java.lang.String> predicate, scala.Option<java.lang.String> userMetadata)  { throw new RuntimeException(); }
    public  scala.collection.immutable.Map<java.lang.String, java.lang.Object> parameters ()  { throw new RuntimeException(); }
    public  scala.collection.immutable.Set<java.lang.String> operationMetrics ()  { throw new RuntimeException(); }
  }
  static public  class Write$ extends scala.runtime.AbstractFunction4<org.apache.spark.sql.SaveMode, scala.Option<scala.collection.Seq<java.lang.String>>, scala.Option<java.lang.String>, scala.Option<java.lang.String>, org.apache.spark.sql.delta.DeltaOperations.Write> implements scala.Serializable {
    /**
     * Static reference to the singleton instance of this Scala object.
     */
    public static final Write$ MODULE$ = null;
    public   Write$ ()  { throw new RuntimeException(); }
  }
  /** Recorded during streaming inserts. */
  static public  class StreamingUpdate extends org.apache.spark.sql.delta.DeltaOperations.Operation implements scala.Product, scala.Serializable {
    public  org.apache.spark.sql.streaming.OutputMode outputMode ()  { throw new RuntimeException(); }
    public  java.lang.String queryId ()  { throw new RuntimeException(); }
    public  long epochId ()  { throw new RuntimeException(); }
    public  scala.Option<java.lang.String> userMetadata ()  { throw new RuntimeException(); }
    // not preceding
    public   StreamingUpdate (org.apache.spark.sql.streaming.OutputMode outputMode, java.lang.String queryId, long epochId, scala.Option<java.lang.String> userMetadata)  { throw new RuntimeException(); }
    public  scala.collection.immutable.Map<java.lang.String, java.lang.Object> parameters ()  { throw new RuntimeException(); }
    public  scala.collection.immutable.Set<java.lang.String> operationMetrics ()  { throw new RuntimeException(); }
  }
  static public  class StreamingUpdate$ extends scala.runtime.AbstractFunction4<org.apache.spark.sql.streaming.OutputMode, java.lang.String, java.lang.Object, scala.Option<java.lang.String>, org.apache.spark.sql.delta.DeltaOperations.StreamingUpdate> implements scala.Serializable {
    /**
     * Static reference to the singleton instance of this Scala object.
     */
    public static final StreamingUpdate$ MODULE$ = null;
    public   StreamingUpdate$ ()  { throw new RuntimeException(); }
  }
  /** Recorded while deleting certain partitions. */
  static public  class Delete extends org.apache.spark.sql.delta.DeltaOperations.Operation implements scala.Product, scala.Serializable {
    public  scala.collection.Seq<java.lang.String> predicate ()  { throw new RuntimeException(); }
    // not preceding
    public   Delete (scala.collection.Seq<java.lang.String> predicate)  { throw new RuntimeException(); }
    public  scala.collection.immutable.Map<java.lang.String, java.lang.Object> parameters ()  { throw new RuntimeException(); }
    public  scala.collection.immutable.Set<java.lang.String> operationMetrics ()  { throw new RuntimeException(); }
    public  scala.collection.immutable.Map<java.lang.String, java.lang.String> transformMetrics (scala.collection.immutable.Map<java.lang.String, org.apache.spark.sql.execution.metric.SQLMetric> metrics)  { throw new RuntimeException(); }
  }
  static public  class Delete$ extends scala.runtime.AbstractFunction1<scala.collection.Seq<java.lang.String>, org.apache.spark.sql.delta.DeltaOperations.Delete> implements scala.Serializable {
    /**
     * Static reference to the singleton instance of this Scala object.
     */
    public static final Delete$ MODULE$ = null;
    public   Delete$ ()  { throw new RuntimeException(); }
  }
  /** Recorded when truncating the table. */
  static public  class Truncate extends org.apache.spark.sql.delta.DeltaOperations.Operation implements scala.Product, scala.Serializable {
    public   Truncate ()  { throw new RuntimeException(); }
    public  scala.collection.immutable.Map<java.lang.String, java.lang.Object> parameters ()  { throw new RuntimeException(); }
    public  scala.collection.immutable.Set<java.lang.String> operationMetrics ()  { throw new RuntimeException(); }
  }
  static public  class Truncate$ extends scala.runtime.AbstractFunction0<org.apache.spark.sql.delta.DeltaOperations.Truncate> implements scala.Serializable {
    /**
     * Static reference to the singleton instance of this Scala object.
     */
    public static final Truncate$ MODULE$ = null;
    public   Truncate$ ()  { throw new RuntimeException(); }
  }
  /** Recorded when converting a table into a Delta table. */
  static public  class Convert extends org.apache.spark.sql.delta.DeltaOperations.Operation implements scala.Product, scala.Serializable {
    public  long numFiles ()  { throw new RuntimeException(); }
    public  scala.collection.Seq<java.lang.String> partitionBy ()  { throw new RuntimeException(); }
    public  boolean collectStats ()  { throw new RuntimeException(); }
    public  scala.Option<java.lang.String> catalogTable ()  { throw new RuntimeException(); }
    // not preceding
    public   Convert (long numFiles, scala.collection.Seq<java.lang.String> partitionBy, boolean collectStats, scala.Option<java.lang.String> catalogTable)  { throw new RuntimeException(); }
    public  scala.collection.immutable.Map<java.lang.String, java.lang.Object> parameters ()  { throw new RuntimeException(); }
    public  scala.collection.immutable.Set<java.lang.String> operationMetrics ()  { throw new RuntimeException(); }
  }
  static public  class Convert$ extends scala.runtime.AbstractFunction4<java.lang.Object, scala.collection.Seq<java.lang.String>, java.lang.Object, scala.Option<java.lang.String>, org.apache.spark.sql.delta.DeltaOperations.Convert> implements scala.Serializable {
    /**
     * Static reference to the singleton instance of this Scala object.
     */
    public static final Convert$ MODULE$ = null;
    public   Convert$ ()  { throw new RuntimeException(); }
  }
  /** Represents the predicates and action type (insert, update, delete) for a Merge clause */
  static public  class MergePredicate implements scala.Product, scala.Serializable {
    public  scala.Option<java.lang.String> predicate ()  { throw new RuntimeException(); }
    public  java.lang.String actionType ()  { throw new RuntimeException(); }
    // not preceding
    public   MergePredicate (scala.Option<java.lang.String> predicate, java.lang.String actionType)  { throw new RuntimeException(); }
  }
  static public  class MergePredicate$ implements scala.Serializable {
    /**
     * Static reference to the singleton instance of this Scala object.
     */
    public static final MergePredicate$ MODULE$ = null;
    public   MergePredicate$ ()  { throw new RuntimeException(); }
    public  org.apache.spark.sql.delta.DeltaOperations.MergePredicate apply (org.apache.spark.sql.catalyst.plans.logical.DeltaMergeIntoClause mergeClause)  { throw new RuntimeException(); }
  }
  /**
   * Recorded when a merge operation is committed to the table.
   * <p>
   * <code>updatePredicate</code>, <code>deletePredicate</code>, and <code>insertPredicate</code> are DEPRECATED.
   * Only use <code>predicate</code>, <code>matchedPredicates</code>, and <code>notMatchedPredicates</code> to record the merge.
   */
  static public  class Merge extends org.apache.spark.sql.delta.DeltaOperations.Operation implements scala.Product, scala.Serializable {
    public  scala.Option<java.lang.String> predicate ()  { throw new RuntimeException(); }
    public  scala.Option<java.lang.String> updatePredicate ()  { throw new RuntimeException(); }
    public  scala.Option<java.lang.String> deletePredicate ()  { throw new RuntimeException(); }
    public  scala.Option<java.lang.String> insertPredicate ()  { throw new RuntimeException(); }
    public  scala.collection.Seq<org.apache.spark.sql.delta.DeltaOperations.MergePredicate> matchedPredicates ()  { throw new RuntimeException(); }
    public  scala.collection.Seq<org.apache.spark.sql.delta.DeltaOperations.MergePredicate> notMatchedPredicates ()  { throw new RuntimeException(); }
    // not preceding
    public   Merge (scala.Option<java.lang.String> predicate, scala.Option<java.lang.String> updatePredicate, scala.Option<java.lang.String> deletePredicate, scala.Option<java.lang.String> insertPredicate, scala.collection.Seq<org.apache.spark.sql.delta.DeltaOperations.MergePredicate> matchedPredicates, scala.collection.Seq<org.apache.spark.sql.delta.DeltaOperations.MergePredicate> notMatchedPredicates)  { throw new RuntimeException(); }
    public  scala.collection.immutable.Map<java.lang.String, java.lang.Object> parameters ()  { throw new RuntimeException(); }
    public  scala.collection.immutable.Set<java.lang.String> operationMetrics ()  { throw new RuntimeException(); }
  }
  static public  class Merge$ implements scala.Serializable {
    /**
     * Static reference to the singleton instance of this Scala object.
     */
    public static final Merge$ MODULE$ = null;
    public   Merge$ ()  { throw new RuntimeException(); }
    /** constructor to provide default values for deprecated fields */
    public  org.apache.spark.sql.delta.DeltaOperations.Merge apply (scala.Option<java.lang.String> predicate, scala.collection.Seq<org.apache.spark.sql.delta.DeltaOperations.MergePredicate> matchedPredicates, scala.collection.Seq<org.apache.spark.sql.delta.DeltaOperations.MergePredicate> notMatchedPredicates)  { throw new RuntimeException(); }
  }
  /** Recorded when an update operation is committed to the table. */
  static public  class Update extends org.apache.spark.sql.delta.DeltaOperations.Operation implements scala.Product, scala.Serializable {
    public  scala.Option<java.lang.String> predicate ()  { throw new RuntimeException(); }
    // not preceding
    public   Update (scala.Option<java.lang.String> predicate)  { throw new RuntimeException(); }
    public  scala.collection.immutable.Map<java.lang.String, java.lang.Object> parameters ()  { throw new RuntimeException(); }
    public  scala.collection.immutable.Set<java.lang.String> operationMetrics ()  { throw new RuntimeException(); }
    public  scala.collection.immutable.Map<java.lang.String, java.lang.String> transformMetrics (scala.collection.immutable.Map<java.lang.String, org.apache.spark.sql.execution.metric.SQLMetric> metrics)  { throw new RuntimeException(); }
  }
  static public  class Update$ extends scala.runtime.AbstractFunction1<scala.Option<java.lang.String>, org.apache.spark.sql.delta.DeltaOperations.Update> implements scala.Serializable {
    /**
     * Static reference to the singleton instance of this Scala object.
     */
    public static final Update$ MODULE$ = null;
    public   Update$ ()  { throw new RuntimeException(); }
  }
  /** Recorded when the table is created. */
  static public  class CreateTable extends org.apache.spark.sql.delta.DeltaOperations.Operation implements scala.Product, scala.Serializable {
    public  org.apache.spark.sql.delta.actions.Metadata metadata ()  { throw new RuntimeException(); }
    public  boolean isManaged ()  { throw new RuntimeException(); }
    public  boolean asSelect ()  { throw new RuntimeException(); }
    // not preceding
    public   CreateTable (org.apache.spark.sql.delta.actions.Metadata metadata, boolean isManaged, boolean asSelect)  { throw new RuntimeException(); }
    public  scala.collection.immutable.Map<java.lang.String, java.lang.Object> parameters ()  { throw new RuntimeException(); }
    public  scala.collection.immutable.Set<java.lang.String> operationMetrics ()  { throw new RuntimeException(); }
  }
  static public  class CreateTable$ extends scala.runtime.AbstractFunction3<org.apache.spark.sql.delta.actions.Metadata, java.lang.Object, java.lang.Object, org.apache.spark.sql.delta.DeltaOperations.CreateTable> implements scala.Serializable {
    /**
     * Static reference to the singleton instance of this Scala object.
     */
    public static final CreateTable$ MODULE$ = null;
    public   CreateTable$ ()  { throw new RuntimeException(); }
  }
  /** Recorded when the table is replaced. */
  static public  class ReplaceTable extends org.apache.spark.sql.delta.DeltaOperations.Operation implements scala.Product, scala.Serializable {
    public  org.apache.spark.sql.delta.actions.Metadata metadata ()  { throw new RuntimeException(); }
    public  boolean isManaged ()  { throw new RuntimeException(); }
    public  boolean orCreate ()  { throw new RuntimeException(); }
    public  boolean asSelect ()  { throw new RuntimeException(); }
    // not preceding
    public   ReplaceTable (org.apache.spark.sql.delta.actions.Metadata metadata, boolean isManaged, boolean orCreate, boolean asSelect)  { throw new RuntimeException(); }
    public  scala.collection.immutable.Map<java.lang.String, java.lang.Object> parameters ()  { throw new RuntimeException(); }
    public  scala.collection.immutable.Set<java.lang.String> operationMetrics ()  { throw new RuntimeException(); }
  }
  static public  class ReplaceTable$ extends scala.runtime.AbstractFunction4<org.apache.spark.sql.delta.actions.Metadata, java.lang.Object, java.lang.Object, java.lang.Object, org.apache.spark.sql.delta.DeltaOperations.ReplaceTable> implements scala.Serializable {
    /**
     * Static reference to the singleton instance of this Scala object.
     */
    public static final ReplaceTable$ MODULE$ = null;
    public   ReplaceTable$ ()  { throw new RuntimeException(); }
  }
  /** Recorded when the table properties are set. */
  static public  class SetTableProperties extends org.apache.spark.sql.delta.DeltaOperations.Operation implements scala.Product, scala.Serializable {
    public  scala.collection.immutable.Map<java.lang.String, java.lang.String> properties ()  { throw new RuntimeException(); }
    // not preceding
    public   SetTableProperties (scala.collection.immutable.Map<java.lang.String, java.lang.String> properties)  { throw new RuntimeException(); }
    public  scala.collection.immutable.Map<java.lang.String, java.lang.Object> parameters ()  { throw new RuntimeException(); }
  }
  static public  class SetTableProperties$ extends scala.runtime.AbstractFunction1<scala.collection.immutable.Map<java.lang.String, java.lang.String>, org.apache.spark.sql.delta.DeltaOperations.SetTableProperties> implements scala.Serializable {
    /**
     * Static reference to the singleton instance of this Scala object.
     */
    public static final SetTableProperties$ MODULE$ = null;
    public   SetTableProperties$ ()  { throw new RuntimeException(); }
  }
  /** Recorded when the table properties are unset. */
  static public  class UnsetTableProperties extends org.apache.spark.sql.delta.DeltaOperations.Operation implements scala.Product, scala.Serializable {
    public  scala.collection.Seq<java.lang.String> propKeys ()  { throw new RuntimeException(); }
    public  boolean ifExists ()  { throw new RuntimeException(); }
    // not preceding
    public   UnsetTableProperties (scala.collection.Seq<java.lang.String> propKeys, boolean ifExists)  { throw new RuntimeException(); }
    public  scala.collection.immutable.Map<java.lang.String, java.lang.Object> parameters ()  { throw new RuntimeException(); }
  }
  static public  class UnsetTableProperties$ extends scala.runtime.AbstractFunction2<scala.collection.Seq<java.lang.String>, java.lang.Object, org.apache.spark.sql.delta.DeltaOperations.UnsetTableProperties> implements scala.Serializable {
    /**
     * Static reference to the singleton instance of this Scala object.
     */
    public static final UnsetTableProperties$ MODULE$ = null;
    public   UnsetTableProperties$ ()  { throw new RuntimeException(); }
  }
  /** Recorded when columns are added. */
  static public  class AddColumns extends org.apache.spark.sql.delta.DeltaOperations.Operation implements scala.Product, scala.Serializable {
    public  scala.collection.Seq<org.apache.spark.sql.delta.DeltaOperations.QualifiedColTypeWithPositionForLog> colsToAdd ()  { throw new RuntimeException(); }
    // not preceding
    public   AddColumns (scala.collection.Seq<org.apache.spark.sql.delta.DeltaOperations.QualifiedColTypeWithPositionForLog> colsToAdd)  { throw new RuntimeException(); }
    public  scala.collection.immutable.Map<java.lang.String, java.lang.Object> parameters ()  { throw new RuntimeException(); }
  }
  static public  class AddColumns$ extends scala.runtime.AbstractFunction1<scala.collection.Seq<org.apache.spark.sql.delta.DeltaOperations.QualifiedColTypeWithPositionForLog>, org.apache.spark.sql.delta.DeltaOperations.AddColumns> implements scala.Serializable {
    /**
     * Static reference to the singleton instance of this Scala object.
     */
    public static final AddColumns$ MODULE$ = null;
    public   AddColumns$ ()  { throw new RuntimeException(); }
  }
  /** Recorded when columns are changed. */
  static public  class ChangeColumn extends org.apache.spark.sql.delta.DeltaOperations.Operation implements scala.Product, scala.Serializable {
    public  scala.collection.Seq<java.lang.String> columnPath ()  { throw new RuntimeException(); }
    public  java.lang.String columnName ()  { throw new RuntimeException(); }
    public  org.apache.spark.sql.types.StructField newColumn ()  { throw new RuntimeException(); }
    public  scala.Option<java.lang.String> colPosition ()  { throw new RuntimeException(); }
    // not preceding
    public   ChangeColumn (scala.collection.Seq<java.lang.String> columnPath, java.lang.String columnName, org.apache.spark.sql.types.StructField newColumn, scala.Option<java.lang.String> colPosition)  { throw new RuntimeException(); }
    public  scala.collection.immutable.Map<java.lang.String, java.lang.Object> parameters ()  { throw new RuntimeException(); }
  }
  static public  class ChangeColumn$ extends scala.runtime.AbstractFunction4<scala.collection.Seq<java.lang.String>, java.lang.String, org.apache.spark.sql.types.StructField, scala.Option<java.lang.String>, org.apache.spark.sql.delta.DeltaOperations.ChangeColumn> implements scala.Serializable {
    /**
     * Static reference to the singleton instance of this Scala object.
     */
    public static final ChangeColumn$ MODULE$ = null;
    public   ChangeColumn$ ()  { throw new RuntimeException(); }
  }
  /** Recorded when columns are replaced. */
  static public  class ReplaceColumns extends org.apache.spark.sql.delta.DeltaOperations.Operation implements scala.Product, scala.Serializable {
    public  scala.collection.Seq<org.apache.spark.sql.types.StructField> columns ()  { throw new RuntimeException(); }
    // not preceding
    public   ReplaceColumns (scala.collection.Seq<org.apache.spark.sql.types.StructField> columns)  { throw new RuntimeException(); }
    public  scala.collection.immutable.Map<java.lang.String, java.lang.Object> parameters ()  { throw new RuntimeException(); }
  }
  static public  class ReplaceColumns$ extends scala.runtime.AbstractFunction1<scala.collection.Seq<org.apache.spark.sql.types.StructField>, org.apache.spark.sql.delta.DeltaOperations.ReplaceColumns> implements scala.Serializable {
    /**
     * Static reference to the singleton instance of this Scala object.
     */
    public static final ReplaceColumns$ MODULE$ = null;
    public   ReplaceColumns$ ()  { throw new RuntimeException(); }
  }
  static public  class UpgradeProtocol extends org.apache.spark.sql.delta.DeltaOperations.Operation implements scala.Product, scala.Serializable {
    public  org.apache.spark.sql.delta.actions.Protocol newProtocol ()  { throw new RuntimeException(); }
    // not preceding
    public   UpgradeProtocol (org.apache.spark.sql.delta.actions.Protocol newProtocol)  { throw new RuntimeException(); }
    public  scala.collection.immutable.Map<java.lang.String, java.lang.Object> parameters ()  { throw new RuntimeException(); }
  }
  static public  class UpgradeProtocol$ extends scala.runtime.AbstractFunction1<org.apache.spark.sql.delta.actions.Protocol, org.apache.spark.sql.delta.DeltaOperations.UpgradeProtocol> implements scala.Serializable {
    /**
     * Static reference to the singleton instance of this Scala object.
     */
    public static final UpgradeProtocol$ MODULE$ = null;
    public   UpgradeProtocol$ ()  { throw new RuntimeException(); }
  }
  static public  class ManualUpdate$ extends org.apache.spark.sql.delta.DeltaOperations.Operation {
    /**
     * Static reference to the singleton instance of this Scala object.
     */
    public static final ManualUpdate$ MODULE$ = null;
    public   ManualUpdate$ ()  { throw new RuntimeException(); }
    public  scala.collection.immutable.Map<java.lang.String, java.lang.Object> parameters ()  { throw new RuntimeException(); }
  }
  static public  class UpdateColumnMetadata extends org.apache.spark.sql.delta.DeltaOperations.Operation implements scala.Product, scala.Serializable {
    public  java.lang.String operationName ()  { throw new RuntimeException(); }
    public  scala.collection.Seq<scala.Tuple2<scala.collection.Seq<java.lang.String>, org.apache.spark.sql.types.StructField>> columns ()  { throw new RuntimeException(); }
    // not preceding
    public   UpdateColumnMetadata (java.lang.String operationName, scala.collection.Seq<scala.Tuple2<scala.collection.Seq<java.lang.String>, org.apache.spark.sql.types.StructField>> columns)  { throw new RuntimeException(); }
    public  scala.collection.immutable.Map<java.lang.String, java.lang.Object> parameters ()  { throw new RuntimeException(); }
  }
  static public  class UpdateColumnMetadata$ extends scala.runtime.AbstractFunction2<java.lang.String, scala.collection.Seq<scala.Tuple2<scala.collection.Seq<java.lang.String>, org.apache.spark.sql.types.StructField>>, org.apache.spark.sql.delta.DeltaOperations.UpdateColumnMetadata> implements scala.Serializable {
    /**
     * Static reference to the singleton instance of this Scala object.
     */
    public static final UpdateColumnMetadata$ MODULE$ = null;
    public   UpdateColumnMetadata$ ()  { throw new RuntimeException(); }
  }
  static public  class UpdateSchema extends org.apache.spark.sql.delta.DeltaOperations.Operation implements scala.Product, scala.Serializable {
    public  org.apache.spark.sql.types.StructType oldSchema ()  { throw new RuntimeException(); }
    public  org.apache.spark.sql.types.StructType newSchema ()  { throw new RuntimeException(); }
    // not preceding
    public   UpdateSchema (org.apache.spark.sql.types.StructType oldSchema, org.apache.spark.sql.types.StructType newSchema)  { throw new RuntimeException(); }
    public  scala.collection.immutable.Map<java.lang.String, java.lang.Object> parameters ()  { throw new RuntimeException(); }
  }
  static public  class UpdateSchema$ extends scala.runtime.AbstractFunction2<org.apache.spark.sql.types.StructType, org.apache.spark.sql.types.StructType, org.apache.spark.sql.delta.DeltaOperations.UpdateSchema> implements scala.Serializable {
    /**
     * Static reference to the singleton instance of this Scala object.
     */
    public static final UpdateSchema$ MODULE$ = null;
    public   UpdateSchema$ ()  { throw new RuntimeException(); }
  }
  static public  class AddConstraint extends org.apache.spark.sql.delta.DeltaOperations.Operation implements scala.Product, scala.Serializable {
    public  java.lang.String constraintName ()  { throw new RuntimeException(); }
    public  java.lang.String expr ()  { throw new RuntimeException(); }
    // not preceding
    public   AddConstraint (java.lang.String constraintName, java.lang.String expr)  { throw new RuntimeException(); }
    public  scala.collection.immutable.Map<java.lang.String, java.lang.Object> parameters ()  { throw new RuntimeException(); }
  }
  static public  class AddConstraint$ extends scala.runtime.AbstractFunction2<java.lang.String, java.lang.String, org.apache.spark.sql.delta.DeltaOperations.AddConstraint> implements scala.Serializable {
    /**
     * Static reference to the singleton instance of this Scala object.
     */
    public static final AddConstraint$ MODULE$ = null;
    public   AddConstraint$ ()  { throw new RuntimeException(); }
  }
  static public  class DropConstraint extends org.apache.spark.sql.delta.DeltaOperations.Operation implements scala.Product, scala.Serializable {
    public  java.lang.String constraintName ()  { throw new RuntimeException(); }
    public  scala.Option<java.lang.String> expr ()  { throw new RuntimeException(); }
    // not preceding
    public   DropConstraint (java.lang.String constraintName, scala.Option<java.lang.String> expr)  { throw new RuntimeException(); }
    public  scala.collection.immutable.Map<java.lang.String, java.lang.Object> parameters ()  { throw new RuntimeException(); }
  }
  static public  class DropConstraint$ extends scala.runtime.AbstractFunction2<java.lang.String, scala.Option<java.lang.String>, org.apache.spark.sql.delta.DeltaOperations.DropConstraint> implements scala.Serializable {
    /**
     * Static reference to the singleton instance of this Scala object.
     */
    public static final DropConstraint$ MODULE$ = null;
    public   DropConstraint$ ()  { throw new RuntimeException(); }
  }
  /**
   * Qualified column type with position. We define a copy of the type here to avoid depending on
   * the parser output classes in our logging.
   */
  static public  class QualifiedColTypeWithPositionForLog implements scala.Product, scala.Serializable {
    public  scala.collection.Seq<java.lang.String> columnPath ()  { throw new RuntimeException(); }
    public  org.apache.spark.sql.types.StructField column ()  { throw new RuntimeException(); }
    public  scala.Option<java.lang.String> colPosition ()  { throw new RuntimeException(); }
    // not preceding
    public   QualifiedColTypeWithPositionForLog (scala.collection.Seq<java.lang.String> columnPath, org.apache.spark.sql.types.StructField column, scala.Option<java.lang.String> colPosition)  { throw new RuntimeException(); }
  }
  static public  class QualifiedColTypeWithPositionForLog$ extends scala.runtime.AbstractFunction3<scala.collection.Seq<java.lang.String>, org.apache.spark.sql.types.StructField, scala.Option<java.lang.String>, org.apache.spark.sql.delta.DeltaOperations.QualifiedColTypeWithPositionForLog> implements scala.Serializable {
    /**
     * Static reference to the singleton instance of this Scala object.
     */
    public static final QualifiedColTypeWithPositionForLog$ MODULE$ = null;
    public   QualifiedColTypeWithPositionForLog$ ()  { throw new RuntimeException(); }
  }
  /**
   * An operation that can be performed on a Delta table.
   * param:  name The name of the operation.
   */
  static public abstract class Operation {
    // not preceding
    public   Operation (java.lang.String name)  { throw new RuntimeException(); }
    // not preceding
    public  scala.collection.immutable.Map<java.lang.String, java.lang.String> jsonEncodedValues ()  { throw new RuntimeException(); }
    public  java.lang.String name ()  { throw new RuntimeException(); }
    public  scala.collection.immutable.Set<java.lang.String> operationMetrics ()  { throw new RuntimeException(); }
    public abstract  scala.collection.immutable.Map<java.lang.String, java.lang.Object> parameters ()  ;
    public  scala.collection.immutable.Map<java.lang.String, java.lang.String> transformMetrics (scala.collection.immutable.Map<java.lang.String, org.apache.spark.sql.execution.metric.SQLMetric> metrics)  { throw new RuntimeException(); }
    public  scala.Option<java.lang.String> userMetadata ()  { throw new RuntimeException(); }
  }
}
