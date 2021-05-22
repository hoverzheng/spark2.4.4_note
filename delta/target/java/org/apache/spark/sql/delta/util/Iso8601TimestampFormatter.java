package org.apache.spark.sql.delta.util;
public  class Iso8601TimestampFormatter implements org.apache.spark.sql.delta.util.TimestampFormatter, org.apache.spark.sql.delta.util.DateTimeFormatterHelper {
  public   Iso8601TimestampFormatter (java.lang.String pattern, java.util.TimeZone timeZone, java.util.Locale locale)  { throw new RuntimeException(); }
  public  java.lang.String format (long us)  { throw new RuntimeException(); }
  // not preceding
  protected  java.time.format.DateTimeFormatter formatter ()  { throw new RuntimeException(); }
  public  long parse (java.lang.String s)  { throw new RuntimeException(); }
}
