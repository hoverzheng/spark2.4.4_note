package org.apache.spark.sql.delta.sources;
/**
 * Tracks how far we processed in when reading changes from the {@link DeltaLog}.
 * <p>
 * Note this class retains the naming of <code>Reservoir</code> to maintain compatibility
 * with serialized offsets from the beta period.
 * <p>
 * param:  sourceVersion     The version of serialization that this offset is encoded with.
 * param:  reservoirId       The id of the table we are reading from. Used to detect
 *                          misconfiguration when restarting a query.
 * param:  reservoirVersion  The version of the table that we are current processing.
 * param:  index             The index in the sequence of AddFiles in this version. Used to
 *                          break large commits into multiple batches. This index is created by
 *                          sorting on modificationTimestamp and path.
 * param:  isStartingVersion Whether this offset denotes a query that is starting rather than
 *                          processing changes. When starting a new query, we first process
 *                          all data present in the table at the start and then move on to
 *                          processing new data that has arrived.
 */
public  class DeltaSourceOffset extends org.apache.spark.sql.execution.streaming.Offset implements scala.Product, scala.Serializable {
  static public  int VERSION ()  { throw new RuntimeException(); }
  static public  org.apache.spark.sql.delta.sources.DeltaSourceOffset apply (java.lang.String reservoirId, long reservoirVersion, long index, boolean isStartingVersion)  { throw new RuntimeException(); }
  static public  org.apache.spark.sql.delta.sources.DeltaSourceOffset apply (java.lang.String reservoirId, org.apache.spark.sql.execution.streaming.Offset offset)  { throw new RuntimeException(); }
  public  long sourceVersion ()  { throw new RuntimeException(); }
  public  java.lang.String reservoirId ()  { throw new RuntimeException(); }
  public  long reservoirVersion ()  { throw new RuntimeException(); }
  public  long index ()  { throw new RuntimeException(); }
  public  boolean isStartingVersion ()  { throw new RuntimeException(); }
  // not preceding
  public   DeltaSourceOffset (long sourceVersion, java.lang.String reservoirId, long reservoirVersion, long index, boolean isStartingVersion)  { throw new RuntimeException(); }
  public  java.lang.String json ()  { throw new RuntimeException(); }
}
