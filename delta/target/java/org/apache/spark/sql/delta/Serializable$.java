package org.apache.spark.sql.delta;
/**
 * This isolation level will ensure serializability between all read and write operations.
 * Specifically, for write operations, this mode will ensure that the result of
 * the table will be perfectly consistent with the visible history of operations, that is,
 * as if all the operations were executed sequentially one by one.
 */
public  class Serializable$ implements org.apache.spark.sql.delta.IsolationLevel, scala.Product, scala.Serializable {
  /**
   * Static reference to the singleton instance of this Scala object.
   */
  public static final Serializable$ MODULE$ = null;
  public   Serializable$ ()  { throw new RuntimeException(); }
}
