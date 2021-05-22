package org.apache.spark.sql.delta;
public  interface DeltaReadOptions extends org.apache.spark.sql.delta.DeltaOptionParser {
  public  scala.Option<scala.util.matching.Regex> excludeRegex ()  ;
  public  boolean failOnDataLoss ()  ;
  public  boolean ignoreChanges ()  ;
  public  boolean ignoreDeletes ()  ;
  public  boolean ignoreFileDeletion ()  ;
  public  scala.Option<java.lang.Object> maxBytesPerTrigger ()  ;
  public  scala.Option<java.lang.Object> maxFilesPerTrigger ()  ;
  public  void provideOneStartingOption ()  ;
  public  scala.Option<java.lang.String> startingTimestamp ()  ;
  public  scala.Option<org.apache.spark.sql.delta.DeltaStartingVersion> startingVersion ()  ;
}
