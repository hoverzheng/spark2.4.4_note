package org.apache.spark.sql.delta.util;
/**
 * Machinary that caches the reconstructed state of a Delta table
 * using the RDD cache. The cache is designed so that the first access
 * will materialize the results.  However once uncache is called,
 * all data will be flushed and will not be cached again.
 */
public  interface StateCache {
  public  class CachedDS<A extends java.lang.Object> {
    // not preceding
    // TypeTree().setOriginal(TypeBoundsTree(TypeTree(), TypeTree()))
    public   CachedDS (org.apache.spark.sql.Dataset<A> ds, java.lang.String name)  { throw new RuntimeException(); }
    /**
     * Get the DS from the cache.
     * <p>
     * If a RDD cache is available,
     * - return the cached DS if called from the same session in which the cached DS is created, or
     * - reconstruct the DS using the RDD cache if called from a different session.
     * <p>
     * If no RDD cache is available,
     * - return a copy of the original DS with updated spark session.
     * <p>
     * Since a cached DeltaLog can be accessed from multiple Spark sessions, this interface makes
     * sure that the original Spark session in the cached DS does not leak into the current active
     * sessions.
     * @return (undocumented)
     */
    public  org.apache.spark.sql.Dataset<A> getDS ()  { throw new RuntimeException(); }
  }
  /**
   * Create a CachedDS instance for the given Dataset and the name.
   * @param ds (undocumented)
   * @param name (undocumented)
   * @return (undocumented)
   */
  public <A extends java.lang.Object> org.apache.spark.sql.delta.util.StateCache.CachedDS<A> cacheDS (org.apache.spark.sql.Dataset<A> ds, java.lang.String name)  ;
  public  org.apache.spark.sql.SparkSession spark ()  ;
  /** Drop any cached data for this {@link Snapshot}. */
  public  void uncache ()  ;
}
