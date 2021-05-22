package org.apache.spark.sql.delta.actions;
/**
 * Used to block older clients from reading or writing the log when backwards
 * incompatible changes are made to the protocol. Readers and writers are
 * responsible for checking that they meet the minimum versions before performing
 * any other operations.
 * <p>
 * Since this action allows us to explicitly block older clients in the case of a
 * breaking change to the protocol, clients should be tolerant of messages and
 * fields that they do not understand.
 */
public  class Protocol implements org.apache.spark.sql.delta.actions.Action, scala.Product, scala.Serializable {
  static public  java.lang.String MIN_READER_VERSION_PROP ()  { throw new RuntimeException(); }
  static public  java.lang.String MIN_WRITER_VERSION_PROP ()  { throw new RuntimeException(); }
  static public  org.apache.spark.sql.delta.actions.Protocol apply (org.apache.spark.sql.SparkSession spark, scala.Option<org.apache.spark.sql.delta.actions.Metadata> metadataOpt)  { throw new RuntimeException(); }
  /** Picks the protocol version for a new table given potential feature usage. */
  static public  org.apache.spark.sql.delta.actions.Protocol forNewTable (org.apache.spark.sql.SparkSession spark, org.apache.spark.sql.delta.actions.Metadata metadata)  { throw new RuntimeException(); }
  /** Cast the table property for the protocol version to an integer. */
  static public  int getVersion (java.lang.String key, java.lang.String value)  { throw new RuntimeException(); }
  /**
   * Verify that the protocol version of the table satisfies the version requirements of all the
   * configurations to be set for the table. Returns the minimum required protocol if not.
   * @param spark (undocumented)
   * @param metadata (undocumented)
   * @param current (undocumented)
   * @return (undocumented)
   */
  static public  scala.Option<org.apache.spark.sql.delta.actions.Protocol> checkProtocolRequirements (org.apache.spark.sql.SparkSession spark, org.apache.spark.sql.delta.actions.Metadata metadata, org.apache.spark.sql.delta.actions.Protocol current)  { throw new RuntimeException(); }
  public  int minReaderVersion ()  { throw new RuntimeException(); }
  public  int minWriterVersion ()  { throw new RuntimeException(); }
  // not preceding
  public   Protocol (int minReaderVersion, int minWriterVersion)  { throw new RuntimeException(); }
  public  org.apache.spark.sql.delta.actions.SingleAction wrap ()  { throw new RuntimeException(); }
  public  java.lang.String simpleString ()  { throw new RuntimeException(); }
}
