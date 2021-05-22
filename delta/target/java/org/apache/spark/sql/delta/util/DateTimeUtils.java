package org.apache.spark.sql.delta.util;
/**
 * Helper functions for converting between internal and external date and time representations.
 * Dates are exposed externally as java.sql.Date and are represented internally as the number of
 * dates since the Unix epoch (1970-01-01). Timestamps are exposed externally as java.sql.Timestamp
 * and are stored internally as longs, which are capable of storing timestamps with microsecond
 * precision.
 */
public  class DateTimeUtils {
  static public final  long NANOS_PER_MICROS ()  { throw new RuntimeException(); }
  static public final  long MICROS_PER_MILLIS ()  { throw new RuntimeException(); }
  static public final  long MILLIS_PER_SECOND ()  { throw new RuntimeException(); }
  static public final  long SECONDS_PER_DAY ()  { throw new RuntimeException(); }
  static public final  long MICROS_PER_SECOND ()  { throw new RuntimeException(); }
  static public final  long NANOS_PER_MILLIS ()  { throw new RuntimeException(); }
  static public final  long NANOS_PER_SECOND ()  { throw new RuntimeException(); }
  static public final  long MICROS_PER_DAY ()  { throw new RuntimeException(); }
  static public final  long MILLIS_PER_MINUTE ()  { throw new RuntimeException(); }
  static public final  long MILLIS_PER_HOUR ()  { throw new RuntimeException(); }
  static public final  long MILLIS_PER_DAY ()  { throw new RuntimeException(); }
  static public  java.util.TimeZone defaultTimeZone ()  { throw new RuntimeException(); }
  static public  java.util.TimeZone getTimeZone (java.lang.String timeZoneId)  { throw new RuntimeException(); }
  static public  java.lang.String timestampToString (org.apache.spark.sql.delta.util.TimestampFormatter tf, long us)  { throw new RuntimeException(); }
  static public  long instantToMicros (java.time.Instant instant)  { throw new RuntimeException(); }
  static public  java.time.Instant microsToInstant (long us)  { throw new RuntimeException(); }
  static public  int instantToDays (java.time.Instant instant)  { throw new RuntimeException(); }
  /**
   * Returns the number of micros since epoch from java.sql.Timestamp.
   * @param t (undocumented)
   * @return (undocumented)
   */
  static public  long fromJavaTimestamp (java.sql.Timestamp t)  { throw new RuntimeException(); }
}
