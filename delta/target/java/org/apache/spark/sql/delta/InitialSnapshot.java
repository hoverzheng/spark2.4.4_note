package org.apache.spark.sql.delta;
/**
 * An initial snapshot with only metadata specified. Useful for creating a DataFrame from an
 * existing parquet table during its conversion to delta.
 * <p>
 * param:  logPath the path to transaction log
 * param:  deltaLog the delta log object
 * param:  metadata the metadata of the table
 */
public  class InitialSnapshot extends org.apache.spark.sql.delta.Snapshot {
  // not preceding
  public   InitialSnapshot (org.apache.hadoop.fs.Path logPath, org.apache.spark.sql.delta.DeltaLog deltaLog, org.apache.spark.sql.delta.actions.Metadata metadata)  { throw new RuntimeException(); }
  public   InitialSnapshot (org.apache.hadoop.fs.Path logPath, org.apache.spark.sql.delta.DeltaLog deltaLog)  { throw new RuntimeException(); }
  // not preceding
  protected  org.apache.spark.sql.delta.Snapshot.State computedState ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.delta.DeltaLog deltaLog ()  { throw new RuntimeException(); }
  public  org.apache.hadoop.fs.Path logPath ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.delta.actions.Metadata metadata ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.Dataset<org.apache.spark.sql.delta.actions.SingleAction> state ()  { throw new RuntimeException(); }
}
