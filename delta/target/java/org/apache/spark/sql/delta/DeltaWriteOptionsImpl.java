package org.apache.spark.sql.delta;
public  interface DeltaWriteOptionsImpl extends org.apache.spark.sql.delta.DeltaOptionParser {
  /**
   * Whether the user has enabled auto schema merging in writes using either a DataFrame option
   * or SQL Session configuration. Automerging is off when table ACLs are enabled.
   * We always respect the DataFrame writer configuration over the session config.
   * @return (undocumented)
   */
  public  boolean canMergeSchema ()  ;
  /**
   * Whether to allow overwriting the schema of a Delta table in an overwrite mode operation. If
   * ACLs are enabled, we can't change the schema of an operation through a write, which requires
   * MODIFY permissions, when schema changes require OWN permissions.
   * @return (undocumented)
   */
  public  boolean canOverwriteSchema ()  ;
  /**
   * Whether to write new data to the table or just rearrange data that is already
   * part of the table. This option declares that the data being written by this job
   * does not change any data in the table and merely rearranges existing data.
   * This makes sure streaming queries reading from this table will not see any new changes
   * @return (undocumented)
   */
  public  boolean rearrangeOnly ()  ;
}
