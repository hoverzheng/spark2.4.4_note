package org.apache.spark.sql.delta.sources;
/**
 * A case class to help with <code>Dataset</code> operations regarding Offset indexing, representing AddFile
 * actions in a Delta log. For proper offset tracking (SC-19523), there are also special sentinel
 * values with index = -1 and add = null.
 * <p>
 * This class is not designed to be persisted in offset logs or such.
 * <p>
 * param:  version The version of the Delta log containing this AddFile.
 * param:  index The index of this AddFile in the Delta log.
 * param:  add The AddFile.
 * param:  isLast A flag to indicate whether this is the last AddFile in the version. This is used
 *               to resolve an off-by-one issue in the streaming offset interface; once we've read
 *               to the end of a log version file, we check this flag to advance immediately to the
 *               next one in the persisted offset. Without this special case we would re-read the
 *               already completed log file.
 */
  class IndexedFile implements scala.Product, scala.Serializable {
  static public abstract  R apply (T1 v1, T2 v2, T3 v3, T4 v4)  ;
  static public  java.lang.String toString ()  { throw new RuntimeException(); }
  public  long version ()  { throw new RuntimeException(); }
  public  long index ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.delta.actions.AddFile add ()  { throw new RuntimeException(); }
  public  boolean isLast ()  { throw new RuntimeException(); }
  // not preceding
  public   IndexedFile (long version, long index, org.apache.spark.sql.delta.actions.AddFile add, boolean isLast)  { throw new RuntimeException(); }
}
