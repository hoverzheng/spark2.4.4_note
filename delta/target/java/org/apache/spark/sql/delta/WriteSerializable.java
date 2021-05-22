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
public  class WriteSerializable {
  static public  java.lang.String toString ()  { throw new RuntimeException(); }
  static public abstract  boolean canEqual (Object that)  ;
  static public abstract  boolean equals (Object that)  ;
  static public abstract  Object productElement (int n)  ;
  static public abstract  int productArity ()  ;
  static public  scala.collection.Iterator<java.lang.Object> productIterator ()  { throw new RuntimeException(); }
  static public  java.lang.String productPrefix ()  { throw new RuntimeException(); }
}
