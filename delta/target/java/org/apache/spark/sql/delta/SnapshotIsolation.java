package org.apache.spark.sql.delta;
/**
 * This isolation level will ensure that all reads will see a consistent
 * snapshot of the table and any transactional write will successfully commit only
 * if the values updated by the transaction have not been changed externally since
 * the snapshot was read by the transaction.
 * <p>
 * This provides a lower consistency guarantee than {@link WriteSerializable} but a higher
 * availability than that. For example, unlike {@link WriteSerializable}, this level allows two
 * concurrent UPDATE operations reading the same data to be committed successfully as long as
 * they don't modify the same data.
 * <p>
 * Note that for operations that do not modify data in the table, Snapshot isolation is same
 * as Serializablity. Hence such operations can be safely committed with Snapshot isolation level.
 */
public  class SnapshotIsolation {
  static public  java.lang.String toString ()  { throw new RuntimeException(); }
  static public abstract  boolean canEqual (Object that)  ;
  static public abstract  boolean equals (Object that)  ;
  static public abstract  Object productElement (int n)  ;
  static public abstract  int productArity ()  ;
  static public  scala.collection.Iterator<java.lang.Object> productIterator ()  { throw new RuntimeException(); }
  static public  java.lang.String productPrefix ()  { throw new RuntimeException(); }
}
