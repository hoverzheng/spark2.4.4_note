package org.apache.spark.sql.delta;
/**
 * Utilities for DeltaTableIdentifier.
 * TODO(burak): Get rid of these utilities. DeltaCatalog should be the skinny-waist for figuring
 * these things out.
 */
public  class DeltaTableIdentifier$ implements org.apache.spark.internal.Logging, scala.Serializable {
  /**
   * Static reference to the singleton instance of this Scala object.
   */
  public static final DeltaTableIdentifier$ MODULE$ = null;
  public   DeltaTableIdentifier$ ()  { throw new RuntimeException(); }
  /**
   * Check the specified table identifier represents a Delta path.
   * @param spark (undocumented)
   * @param identifier (undocumented)
   * @return (undocumented)
   */
  public  boolean isDeltaPath (org.apache.spark.sql.SparkSession spark, org.apache.spark.sql.catalyst.TableIdentifier identifier)  { throw new RuntimeException(); }
  /**
   * Creates a {@link DeltaTableIdentifier} if the specified table identifier represents a Delta table,
   * otherwise returns {@link None}.
   * @param spark (undocumented)
   * @param identifier (undocumented)
   * @return (undocumented)
   */
  public  scala.Option<org.apache.spark.sql.delta.DeltaTableIdentifier> apply (org.apache.spark.sql.SparkSession spark, org.apache.spark.sql.catalyst.TableIdentifier identifier)  { throw new RuntimeException(); }
  /**
   * When users try to access Delta tables by path, e.g. delta.<code>/some/path</code>, we need to first check
   * if such a table exists in the MetaStore (due to Spark semantics :/). The Glue MetaStore may
   * return Access Denied errors during this check. This method matches on this failure mode.
   * @param e (undocumented)
   * @return (undocumented)
   */
  public  boolean gluePermissionError (org.apache.spark.sql.AnalysisException e)  { throw new RuntimeException(); }
}
