package org.apache.spark.sql.delta.util;
/**
 * Forked from {@link org.apache.spark.sql.catalyst.util.DateTimeFormatterHelper}
 */
public  interface DateTimeFormatterHelper {
  public  java.time.Instant toInstantWithZoneId (java.time.temporal.TemporalAccessor temporalAccessor, java.time.ZoneId zoneId)  ;
  public  java.time.format.DateTimeFormatter getOrCreateFormatter (java.lang.String pattern, java.util.Locale locale)  ;
}
