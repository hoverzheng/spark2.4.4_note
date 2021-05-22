package org.apache.spark.sql.delta.actions;
public  class Action$ {
  /**
   * Static reference to the singleton instance of this Scala object.
   */
  public static final Action$ MODULE$ = null;
  public   Action$ ()  { throw new RuntimeException(); }
  /** The maximum version of the protocol that this version of Delta understands. */
  public  int readerVersion ()  { throw new RuntimeException(); }
  public  int writerVersion ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.delta.actions.Protocol protocolVersion ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.delta.actions.Action fromJson (java.lang.String json)  { throw new RuntimeException(); }
  // not preceding
  public  org.apache.spark.sql.types.StructType logSchema ()  { throw new RuntimeException(); }
}
