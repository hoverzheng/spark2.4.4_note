package org.apache.spark.sql.delta.util;
/**
 * The formatter parses/formats timestamps according to the pattern <code>yyyy-MM-dd HH:mm:ss.[..fff..]</code>
 * where <code>[..fff..]</code> is a fraction of second up to microsecond resolution. The formatter does not
 * output trailing zeros in the fraction. For example, the timestamp <code>2019-03-05 15:00:01.123400</code> is
 * formatted as the string <code>2019-03-05 15:00:01.1234</code>.
 * <p>
 * param:  timeZone the time zone in which the formatter parses or format timestamps
 */
public  class FractionTimestampFormatter extends org.apache.spark.sql.delta.util.Iso8601TimestampFormatter {
  public   FractionTimestampFormatter (java.util.TimeZone timeZone)  { throw new RuntimeException(); }
  // not preceding
  protected  java.time.format.DateTimeFormatter formatter ()  { throw new RuntimeException(); }
}
