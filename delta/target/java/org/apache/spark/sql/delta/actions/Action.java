package org.apache.spark.sql.delta.actions;
/**
 * Represents a single change to the state of a Delta table. An order sequence
 * of actions can be replayed using {@link InMemoryLogReplay} to derive the state
 * of the table at a given point in time.
 */
public  interface Action {
  public  org.apache.spark.sql.delta.actions.SingleAction wrap ()  ;
  public  java.lang.String json ()  ;
}
