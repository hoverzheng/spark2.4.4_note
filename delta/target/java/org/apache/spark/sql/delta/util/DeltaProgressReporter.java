package org.apache.spark.sql.delta.util;
public  interface DeltaProgressReporter extends org.apache.spark.internal.Logging {
  /**
   * Report a log to indicate some command is running.
   * @param statusCode (undocumented)
   * @param defaultMessage (undocumented)
   * @param data (undocumented)
   * @param body (undocumented)
   * @return (undocumented)
   */
  public <T extends java.lang.Object> T withStatusCode (java.lang.String statusCode, java.lang.String defaultMessage, scala.collection.immutable.Map<java.lang.String, java.lang.Object> data, scala.Function0<T> body)  ;
}
