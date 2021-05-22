package org.apache.spark.sql.delta;
public  class DeltaOptions$ implements org.apache.spark.sql.delta.metering.DeltaLogging, scala.Serializable {
  /**
   * Static reference to the singleton instance of this Scala object.
   */
  public static final DeltaOptions$ MODULE$ = null;
  public   DeltaOptions$ ()  { throw new RuntimeException(); }
  /** An option to overwrite only the data that matches predicates over partition columns. */
  public  java.lang.String REPLACE_WHERE_OPTION ()  { throw new RuntimeException(); }
  /** An option to allow automatic schema merging during a write operation. */
  public  java.lang.String MERGE_SCHEMA_OPTION ()  { throw new RuntimeException(); }
  /** An option to allow overwriting schema and partitioning during an overwrite write operation. */
  public  java.lang.String OVERWRITE_SCHEMA_OPTION ()  { throw new RuntimeException(); }
  /** An option to specify user-defined metadata in commitInfo */
  public  java.lang.String USER_METADATA_OPTION ()  { throw new RuntimeException(); }
  public  java.lang.String MAX_FILES_PER_TRIGGER_OPTION ()  { throw new RuntimeException(); }
  public  int MAX_FILES_PER_TRIGGER_OPTION_DEFAULT ()  { throw new RuntimeException(); }
  public  java.lang.String MAX_BYTES_PER_TRIGGER_OPTION ()  { throw new RuntimeException(); }
  public  java.lang.String EXCLUDE_REGEX_OPTION ()  { throw new RuntimeException(); }
  public  java.lang.String IGNORE_FILE_DELETION_OPTION ()  { throw new RuntimeException(); }
  public  java.lang.String IGNORE_CHANGES_OPTION ()  { throw new RuntimeException(); }
  public  java.lang.String IGNORE_DELETES_OPTION ()  { throw new RuntimeException(); }
  public  java.lang.String FAIL_ON_DATA_LOSS_OPTION ()  { throw new RuntimeException(); }
  public  java.lang.String OPTIMIZE_WRITE_OPTION ()  { throw new RuntimeException(); }
  public  java.lang.String DATA_CHANGE_OPTION ()  { throw new RuntimeException(); }
  public  java.lang.String STARTING_VERSION_OPTION ()  { throw new RuntimeException(); }
  public  java.lang.String STARTING_TIMESTAMP_OPTION ()  { throw new RuntimeException(); }
  public  scala.collection.immutable.Set<java.lang.String> validOptionKeys ()  { throw new RuntimeException(); }
  /** Iterates over all user passed options and logs any that are not valid. */
  public  void verifyOptions (org.apache.spark.sql.catalyst.util.CaseInsensitiveMap<java.lang.String> options)  { throw new RuntimeException(); }
}
