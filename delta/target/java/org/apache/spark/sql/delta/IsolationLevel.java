package org.apache.spark.sql.delta;
/**
 * Trait that defines the level consistency guarantee is going to be provided by
 * <code>OptimisticTransaction.commit()</code>. {@link Serializable} is the most
 * strict level and {@link SnapshotIsolation} is the least strict one.
 * <p>
 * @see {@link IsolationLevel.allLevelsInDescOrder} for all the levels in the descending order
 *       of strictness and {@link IsolationLevel.DEFAULT} for the default table isolation level.
 */
public  interface IsolationLevel {
  public  java.lang.String toString ()  ;
}
