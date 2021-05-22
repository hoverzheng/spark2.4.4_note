package org.apache.spark.sql.delta.util;
/**
 * Forked from {@link org.apache.spark.sql.catalyst.util.DateFormatter}
 */
public  interface DateFormatter extends scala.Serializable {
  public  int parse (java.lang.String s)  ;
  public  java.lang.String format (int days)  ;
}
