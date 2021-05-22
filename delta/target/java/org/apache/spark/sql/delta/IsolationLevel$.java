package org.apache.spark.sql.delta;
public  class IsolationLevel$ {
  /**
   * Static reference to the singleton instance of this Scala object.
   */
  public static final IsolationLevel$ MODULE$ = null;
  public   IsolationLevel$ ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.delta.WriteSerializable$ DEFAULT ()  { throw new RuntimeException(); }
  /** All possible isolation levels in descending order of guarantees provided */
  public  scala.collection.Seq<org.apache.spark.sql.delta.IsolationLevel> allLevelsInDescOrder ()  { throw new RuntimeException(); }
  /** All the valid isolation levels that can be specified as the table isolation level */
  public  scala.collection.immutable.Set<org.apache.spark.sql.delta.IsolationLevel> validTableIsolationLevels ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.delta.IsolationLevel fromString (java.lang.String s)  { throw new RuntimeException(); }
}
