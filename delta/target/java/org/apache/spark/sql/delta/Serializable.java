package org.apache.spark.sql.delta;
/**
 * This isolation level will ensure serializability between all read and write operations.
 * Specifically, for write operations, this mode will ensure that the result of
 * the table will be perfectly consistent with the visible history of operations, that is,
 * as if all the operations were executed sequentially one by one.
 */
public  class Serializable {
  static public  java.lang.String toString ()  { throw new RuntimeException(); }
  static public abstract  boolean canEqual (Object that)  ;
  static public abstract  boolean equals (Object that)  ;
  static public abstract  Object productElement (int n)  ;
  static public abstract  int productArity ()  ;
  static public  scala.collection.Iterator<java.lang.Object> productIterator ()  { throw new RuntimeException(); }
  static public  java.lang.String productPrefix ()  { throw new RuntimeException(); }
}
