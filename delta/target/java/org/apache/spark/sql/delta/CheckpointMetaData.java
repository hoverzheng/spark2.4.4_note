package org.apache.spark.sql.delta;
/**
 * Records information about a checkpoint.
 * <p>
 * param:  version the version of this checkpoint
 * param:  size the number of actions in the checkpoint
 * param:  parts the number of parts when the checkpoint has multiple parts. None if this is a
 *              singular checkpoint
 */
public  class CheckpointMetaData implements scala.Product, scala.Serializable {
  static public abstract  R apply (T1 v1, T2 v2, T3 v3)  ;
  static public  java.lang.String toString ()  { throw new RuntimeException(); }
  public  long version ()  { throw new RuntimeException(); }
  public  long size ()  { throw new RuntimeException(); }
  public  scala.Option<java.lang.Object> parts ()  { throw new RuntimeException(); }
  // not preceding
  public   CheckpointMetaData (long version, long size, scala.Option<java.lang.Object> parts)  { throw new RuntimeException(); }
}
