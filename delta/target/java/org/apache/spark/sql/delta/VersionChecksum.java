package org.apache.spark.sql.delta;
/**
 * Stats calculated within a snapshot, which we store along individual transactions for
 * verification.
 * <p>
 * param:  tableSizeBytes The size of the table in bytes
 * param:  numFiles Number of <code>AddFile</code> actions in the snapshot
 * param:  numMetadata Number of <code>Metadata</code> actions in the snapshot
 * param:  numProtocol Number of <code>Protocol</code> actions in the snapshot
 * param:  numTransactions Number of <code>SetTransaction</code> actions in the snapshot
 */
public  class VersionChecksum implements scala.Product, scala.Serializable {
  static public abstract  R apply (T1 v1, T2 v2, T3 v3, T4 v4, T5 v5)  ;
  static public  java.lang.String toString ()  { throw new RuntimeException(); }
  public  long tableSizeBytes ()  { throw new RuntimeException(); }
  public  long numFiles ()  { throw new RuntimeException(); }
  public  long numMetadata ()  { throw new RuntimeException(); }
  public  long numProtocol ()  { throw new RuntimeException(); }
  public  long numTransactions ()  { throw new RuntimeException(); }
  // not preceding
  public   VersionChecksum (long tableSizeBytes, long numFiles, long numMetadata, long numProtocol, long numTransactions)  { throw new RuntimeException(); }
}
