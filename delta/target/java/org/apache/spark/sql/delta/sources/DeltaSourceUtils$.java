package org.apache.spark.sql.delta.sources;
public  class DeltaSourceUtils$ {
  /**
   * Static reference to the singleton instance of this Scala object.
   */
  public static final DeltaSourceUtils$ MODULE$ = null;
  public   DeltaSourceUtils$ ()  { throw new RuntimeException(); }
  public  java.lang.String NAME ()  { throw new RuntimeException(); }
  public  java.lang.String ALT_NAME ()  { throw new RuntimeException(); }
  public  java.lang.String PARTITIONING_COLUMNS_KEY ()  { throw new RuntimeException(); }
  public  boolean isDeltaDataSourceName (java.lang.String name)  { throw new RuntimeException(); }
  /** Check whether this table is a Delta table based on information from the Catalog. */
  public  boolean isDeltaTable (scala.Option<java.lang.String> provider)  { throw new RuntimeException(); }
  /** Translates the public Spark Filter APIs into Spark internal expressions. */
  public  org.apache.spark.sql.catalyst.expressions.Expression translateFilters (org.apache.spark.sql.sources.Filter[] filters)  { throw new RuntimeException(); }
}
