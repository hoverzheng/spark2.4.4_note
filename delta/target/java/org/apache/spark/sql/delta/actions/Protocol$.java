package org.apache.spark.sql.delta.actions;
public  class Protocol$ implements scala.Serializable {
  /**
   * Static reference to the singleton instance of this Scala object.
   */
  public static final Protocol$ MODULE$ = null;
  public   Protocol$ ()  { throw new RuntimeException(); }
  public  java.lang.String MIN_READER_VERSION_PROP ()  { throw new RuntimeException(); }
  public  java.lang.String MIN_WRITER_VERSION_PROP ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.delta.actions.Protocol apply (org.apache.spark.sql.SparkSession spark, scala.Option<org.apache.spark.sql.delta.actions.Metadata> metadataOpt)  { throw new RuntimeException(); }
  /** Picks the protocol version for a new table given potential feature usage. */
  public  org.apache.spark.sql.delta.actions.Protocol forNewTable (org.apache.spark.sql.SparkSession spark, org.apache.spark.sql.delta.actions.Metadata metadata)  { throw new RuntimeException(); }
  /** Cast the table property for the protocol version to an integer. */
  public  int getVersion (java.lang.String key, java.lang.String value)  { throw new RuntimeException(); }
  /**
   * Verify that the protocol version of the table satisfies the version requirements of all the
   * configurations to be set for the table. Returns the minimum required protocol if not.
   * @param spark (undocumented)
   * @param metadata (undocumented)
   * @param current (undocumented)
   * @return (undocumented)
   */
  public  scala.Option<org.apache.spark.sql.delta.actions.Protocol> checkProtocolRequirements (org.apache.spark.sql.SparkSession spark, org.apache.spark.sql.delta.actions.Metadata metadata, org.apache.spark.sql.delta.actions.Protocol current)  { throw new RuntimeException(); }
}
