package org.apache.spark.sql.delta;
/**
 * The specification to time travel a Delta Table to the given <code>timestamp</code> or <code>version</code>.
 * param:  timestamp An expression that can be evaluated into a timestamp. The expression cannot
 *                  be a subquery.
 * param:  version The version of the table to time travel to. Must be >= 0.
 * param:  creationSource The API used to perform time travel, e.g. <code>atSyntax</code>, <code>dfReader</code> or SQL
 */
public  class DeltaTimeTravelSpec implements org.apache.spark.sql.delta.metering.DeltaLogging, scala.Product, scala.Serializable {
  /** Returns whether the given table identifier may contain time travel syntax. */
  static public  boolean isApplicable (org.apache.spark.sql.internal.SQLConf conf, java.lang.String identifier)  { throw new RuntimeException(); }
  /** Adds a time travel node based on the special syntax in the table identifier. */
  static public  scala.Tuple2<org.apache.spark.sql.delta.DeltaTimeTravelSpec, java.lang.String> resolvePath (org.apache.spark.sql.internal.SQLConf conf, java.lang.String identifier)  { throw new RuntimeException(); }
  public  scala.Option<org.apache.spark.sql.catalyst.expressions.Expression> timestamp ()  { throw new RuntimeException(); }
  public  scala.Option<java.lang.Object> version ()  { throw new RuntimeException(); }
  public  scala.Option<java.lang.String> creationSource ()  { throw new RuntimeException(); }
  // not preceding
  public   DeltaTimeTravelSpec (scala.Option<org.apache.spark.sql.catalyst.expressions.Expression> timestamp, scala.Option<java.lang.Object> version, scala.Option<java.lang.String> creationSource)  { throw new RuntimeException(); }
  /**
   * Compute the timestamp to use for time travelling the relation from the given expression for
   * the given time zone.
   * @param timeZone (undocumented)
   * @return (undocumented)
   */
  public  java.sql.Timestamp getTimestamp (java.lang.String timeZone)  { throw new RuntimeException(); }
}
