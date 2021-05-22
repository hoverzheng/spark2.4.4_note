package org.apache.spark.sql.delta;
/**
 * This isolation level will ensure snapshot isolation consistency guarantee between write
 * operations only. In other words, if only the write operations are considered, then
 * there exists a serializable sequence between them that would produce the same result
 * as seen in the table. However, if both read and write operations are considered, then
 * there may not exist a serializable sequence that would explain all the observed reads.
 * <p>
 * This provides a lower consistency guarantee than {@link Serializable} but a higher
 * availability than that. For example, unlike {@link Serializable}, this level allows an UPDATE
 * operation to be committed even if there was a concurrent INSERT operation that has already
 * added data that should have been read by the UPDATE. It will be as if the UPDATE was executed
 * before the INSERT even if the former was committed after the latter. As a side effect,
 * the visible history of operations may not be consistent with the
 * result expected if these operations were executed sequentially one by one.
 */
public  class WriteSerializable$ implements org.apache.spark.sql.delta.IsolationLevel, scala.Product, scala.Serializable {
  /**
   * Static reference to the singleton instance of this Scala object.
   */
  public static final WriteSerializable$ MODULE$ = null;
  public   WriteSerializable$ ()  { throw new RuntimeException(); }
}
