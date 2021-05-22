package org.apache.spark.sql.delta.storage;
/**
 * Turn a <code>Reader</code> to <code>ClosableIterator</code> which can be read on demand. Each element is
 * a trimmed line.
 */
public  class LineClosableIterator implements org.apache.spark.sql.delta.storage.ClosableIterator<java.lang.String> {
  public   LineClosableIterator (java.io.Reader _reader)  { throw new RuntimeException(); }
  public  void close ()  { throw new RuntimeException(); }
  public  boolean hasNext ()  { throw new RuntimeException(); }
  public  java.lang.String next ()  { throw new RuntimeException(); }
}
