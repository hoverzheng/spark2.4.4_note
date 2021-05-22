package org.apache.spark.sql.delta.util;
/**
 * Accumulator to collect distinct elements as a set.
 */
public  class SetAccumulator<T extends java.lang.Object> extends org.apache.spark.util.AccumulatorV2<T, java.util.Set<T>> {
  // not preceding
  // TypeTree().setOriginal(TypeBoundsTree(TypeTree(), TypeTree()))
  public   SetAccumulator ()  { throw new RuntimeException(); }
  public  void add (T v)  { throw new RuntimeException(); }
  public  org.apache.spark.util.AccumulatorV2<T, java.util.Set<T>> copy ()  { throw new RuntimeException(); }
  public  boolean isZero ()  { throw new RuntimeException(); }
  public  void merge (org.apache.spark.util.AccumulatorV2<T, java.util.Set<T>> other)  { throw new RuntimeException(); }
  public  void reset ()  { throw new RuntimeException(); }
  public  java.util.Set<T> value ()  { throw new RuntimeException(); }
}
