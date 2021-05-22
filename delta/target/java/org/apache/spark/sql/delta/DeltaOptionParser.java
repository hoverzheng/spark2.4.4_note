package org.apache.spark.sql.delta;
public  interface DeltaOptionParser {
  public  org.apache.spark.sql.catalyst.util.CaseInsensitiveMap<java.lang.String> options ()  ;
  public  org.apache.spark.sql.internal.SQLConf sqlConf ()  ;
  public  boolean toBoolean (java.lang.String input, java.lang.String name)  ;
}
