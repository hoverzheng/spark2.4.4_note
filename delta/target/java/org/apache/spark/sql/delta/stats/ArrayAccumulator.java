package org.apache.spark.sql.delta.stats;
/**
 * An accumulator that keeps arrays of counts. Counts from multiple partitions
 * are merged by index. -1 indicates a null and is handled using TVL (-1 + N = -1)
 */
public  class ArrayAccumulator extends org.apache.spark.util.AccumulatorV2<scala.Tuple2<java.lang.Object, java.lang.Object>, long[]> {
  // not preceding
  public   ArrayAccumulator (int size)  { throw new RuntimeException(); }
  public  void add (scala.Tuple2<java.lang.Object, java.lang.Object> v)  { throw new RuntimeException(); }
  public  org.apache.spark.util.AccumulatorV2<scala.Tuple2<java.lang.Object, java.lang.Object>, long[]> copy ()  { throw new RuntimeException(); }
  protected  long[] counts ()  { throw new RuntimeException(); }
  public  boolean isZero ()  { throw new RuntimeException(); }
  public  void merge (org.apache.spark.util.AccumulatorV2<scala.Tuple2<java.lang.Object, java.lang.Object>, long[]> o)  { throw new RuntimeException(); }
  public  void reset ()  { throw new RuntimeException(); }
  public  int size ()  { throw new RuntimeException(); }
  public  long[] value ()  { throw new RuntimeException(); }
}
