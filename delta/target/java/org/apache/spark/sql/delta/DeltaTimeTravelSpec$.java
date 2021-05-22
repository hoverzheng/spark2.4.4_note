package org.apache.spark.sql.delta;
public  class DeltaTimeTravelSpec$ implements scala.Serializable {
  /**
   * Static reference to the singleton instance of this Scala object.
   */
  public static final DeltaTimeTravelSpec$ MODULE$ = null;
  public   DeltaTimeTravelSpec$ ()  { throw new RuntimeException(); }
  /** Returns whether the given table identifier may contain time travel syntax. */
  public  boolean isApplicable (org.apache.spark.sql.internal.SQLConf conf, java.lang.String identifier)  { throw new RuntimeException(); }
  /** Adds a time travel node based on the special syntax in the table identifier. */
  public  scala.Tuple2<org.apache.spark.sql.delta.DeltaTimeTravelSpec, java.lang.String> resolvePath (org.apache.spark.sql.internal.SQLConf conf, java.lang.String identifier)  { throw new RuntimeException(); }
}
