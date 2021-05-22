package org.apache.spark.sql.delta.util;
/**
 * Helper functions for converting between internal and external date and time representations.
 * Dates are exposed externally as java.sql.Date and are represented internally as the number of
 * dates since the Unix epoch (1970-01-01). Timestamps are exposed externally as java.sql.Timestamp
 * and are stored internally as longs, which are capable of storing timestamps with microsecond
 * precision.
 */
public  class DateTimeUtils$ {
  /**
   * Static reference to the singleton instance of this Scala object.
   */
  public static final DateTimeUtils$ MODULE$ = null;
  public   DateTimeUtils$ ()  { throw new RuntimeException(); }
  public final  long NANOS_PER_MICROS ()  { throw new RuntimeException(); }
  public final  long MICROS_PER_MILLIS ()  { throw new RuntimeException(); }
  public final  long MILLIS_PER_SECOND ()  { throw new RuntimeException(); }
  public final  long SECONDS_PER_DAY ()  { throw new RuntimeException(); }
  public final  long MICROS_PER_SECOND ()  { throw new RuntimeException(); }
  public final  long NANOS_PER_MILLIS ()  { throw new RuntimeException(); }
  public final  long NANOS_PER_SECOND ()  { throw new RuntimeException(); }
  public final  long MICROS_PER_DAY ()  { throw new RuntimeException(); }
  public final  long MILLIS_PER_MINUTE ()  { throw new RuntimeException(); }
  public final  long MILLIS_PER_HOUR ()  { throw new RuntimeException(); }
  public final  long MILLIS_PER_DAY ()  { throw new RuntimeException(); }
  public  java.util.TimeZone defaultTimeZone ()  { throw new RuntimeException(); }
  public  java.util.TimeZone getTimeZone (java.lang.String timeZoneId)  { throw new RuntimeException(); }
  public  java.lang.String timestampToString (org.apache.spark.sql.delta.util.TimestampFormatter tf, long us)  { throw new RuntimeException(); }
  public  long instantToMicros (java.time.Instant instant)  { throw new RuntimeException(); }
  public  java.time.Instant microsToInstant (long us)  { throw new RuntimeException(); }
  public  int instantToDays (java.time.Instant instant)  { throw new RuntimeException(); }
  /**
   * Returns the number of micros since epoch from java.sql.Timestamp.
   * @param t (undocumented)
   * @return (undocumented)
   */
  public  long fromJavaTimestamp (java.sql.Timestamp t)  { throw new RuntimeException(); }
}
