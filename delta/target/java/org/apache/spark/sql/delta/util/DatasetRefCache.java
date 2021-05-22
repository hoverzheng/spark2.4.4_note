package org.apache.spark.sql.delta.util;
/**
 * A {@link Dataset} reference cache to automatically create new {@link Dataset} objects when the active
 * {@link SparkSession} changes. This is useful when sharing objects holding {@link Dataset} references
 * cross multiple sessions. Without this, using a {@link Dataset} that holds a stale session may change
 * the active session and cause multiple issues (e.g., if we switch to a stale session coming from a
 * notebook that has been detached, we may not be able to use built-in functions because those are
 * cleaned up).
 * <p>
 * The <code>creator</code> function will be called to create a new {@link Dataset} object when the old one has a
 * different session than the current active session.
 * <p>
 * Unlike {@link StateCache}, this class only caches the {@link Dataset} reference and doesn't cache the
 * underlying <code>RDD</code>.
 * <p>
 * param:  creator a function to create {@link Dataset}.
 */
public  class DatasetRefCache<T extends java.lang.Object> {
  // not preceding
  // TypeTree().setOriginal(TypeBoundsTree(TypeTree(), TypeTree()))
  public   DatasetRefCache (scala.Function0<org.apache.spark.sql.Dataset<T>> creator)  { throw new RuntimeException(); }
  public  org.apache.spark.sql.Dataset<T> get ()  { throw new RuntimeException(); }
}
