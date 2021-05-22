package org.apache.spark.sql.delta.stats;
/**
 * Note: Please don't add any new constructor to this class. <code>jackson-module-scala</code> always picks up
 * the first constructor returned by <code>Class.getConstructors</code> but the order of the constructors list
 * is non-deterministic. (SC-13343)
 */
public  class DataSize implements scala.Product, scala.Serializable {
  static public  org.apache.spark.sql.delta.stats.DataSize apply (org.apache.spark.sql.delta.stats.ArrayAccumulator a)  { throw new RuntimeException(); }
  public  scala.Option<java.lang.Object> bytesCompressed ()  { throw new RuntimeException(); }
  public  scala.Option<java.lang.Object> rows ()  { throw new RuntimeException(); }
  // not preceding
  public   DataSize (scala.Option<java.lang.Object> bytesCompressed, scala.Option<java.lang.Object> rows)  { throw new RuntimeException(); }
}
