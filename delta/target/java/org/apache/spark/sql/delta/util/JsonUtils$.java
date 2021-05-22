package org.apache.spark.sql.delta.util;
/** Useful json functions used around the Delta codebase. */
public  class JsonUtils$ {
  /**
   * Static reference to the singleton instance of this Scala object.
   */
  public static final JsonUtils$ MODULE$ = null;
  public   JsonUtils$ ()  { throw new RuntimeException(); }
  /** Used to convert between classes and JSON. */
  public  com.fasterxml.jackson.databind.ObjectMapper mapper ()  { throw new RuntimeException(); }
  public <T extends java.lang.Object> java.lang.String toJson (T obj, scala.reflect.Manifest<T> evidence$1)  { throw new RuntimeException(); }
  public <T extends java.lang.Object> java.lang.String toPrettyJson (T obj, scala.reflect.Manifest<T> evidence$2)  { throw new RuntimeException(); }
  public <T extends java.lang.Object> T fromJson (java.lang.String json, scala.reflect.Manifest<T> evidence$3)  { throw new RuntimeException(); }
}
