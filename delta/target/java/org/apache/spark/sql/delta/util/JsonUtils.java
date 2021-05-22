package org.apache.spark.sql.delta.util;
/** Useful json functions used around the Delta codebase. */
public  class JsonUtils {
  /** Used to convert between classes and JSON. */
  static public  com.fasterxml.jackson.databind.ObjectMapper mapper ()  { throw new RuntimeException(); }
  static public <T extends java.lang.Object> java.lang.String toJson (T obj, scala.reflect.Manifest<T> evidence$1)  { throw new RuntimeException(); }
  static public <T extends java.lang.Object> java.lang.String toPrettyJson (T obj, scala.reflect.Manifest<T> evidence$2)  { throw new RuntimeException(); }
  static public <T extends java.lang.Object> T fromJson (java.lang.String json, scala.reflect.Manifest<T> evidence$3)  { throw new RuntimeException(); }
}
