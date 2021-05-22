package org.apache.spark.sql.delta.actions;
/**
 * Adds a new file to the table. When multiple {@link AddFile} file actions
 * are seen with the same <code>path</code> only the metadata from the last one is
 * kept.
 */
public  class AddFile implements org.apache.spark.sql.delta.actions.FileAction, scala.Product, scala.Serializable {
  /**
   * Misc file-level metadata.
   * <p>
   * The convention is that clients may safely ignore any/all of these tags and this should never
   * have an impact on correctness.
   * <p>
   * Otherwise, the information should go as a field of the AddFile action itself and the Delta
   * protocol version should be bumped.
   */
  static public  class Tags$ {
    /**
     * Static reference to the singleton instance of this Scala object.
     */
    public static final Tags$ MODULE$ = null;
    /** {@link ZCUBE_ID}: identifier of the OPTIMIZE ZORDER BY job that this file was produced by */
    public  class ZCUBE_ID {
      static public  java.lang.String name ()  { throw new RuntimeException(); }
    }
    /** {@link ZCUBE_ID}: identifier of the OPTIMIZE ZORDER BY job that this file was produced by */
    public  class ZCUBE_ID$ extends org.apache.spark.sql.delta.actions.AddFile.Tags.KeyType {
      /**
       * Static reference to the singleton instance of this Scala object.
       */
      public static final ZCUBE_ID$ MODULE$ = null;
      public   ZCUBE_ID$ ()  { throw new RuntimeException(); }
    }
    /** {@link ZCUBE_ZORDER_BY}: ZOrdering of the corresponding ZCube */
    public  class ZCUBE_ZORDER_BY {
      static public  java.lang.String name ()  { throw new RuntimeException(); }
    }
    /** {@link ZCUBE_ZORDER_BY}: ZOrdering of the corresponding ZCube */
    public  class ZCUBE_ZORDER_BY$ extends org.apache.spark.sql.delta.actions.AddFile.Tags.KeyType {
      /**
       * Static reference to the singleton instance of this Scala object.
       */
      public static final ZCUBE_ZORDER_BY$ MODULE$ = null;
      public   ZCUBE_ZORDER_BY$ ()  { throw new RuntimeException(); }
    }
    /** {@link ZCUBE_ZORDER_CURVE}: Clustering strategy of the corresponding ZCube */
    public  class ZCUBE_ZORDER_CURVE {
      static public  java.lang.String name ()  { throw new RuntimeException(); }
    }
    /** {@link ZCUBE_ZORDER_CURVE}: Clustering strategy of the corresponding ZCube */
    public  class ZCUBE_ZORDER_CURVE$ extends org.apache.spark.sql.delta.actions.AddFile.Tags.KeyType {
      /**
       * Static reference to the singleton instance of this Scala object.
       */
      public static final ZCUBE_ZORDER_CURVE$ MODULE$ = null;
      public   ZCUBE_ZORDER_CURVE$ ()  { throw new RuntimeException(); }
    }
    public abstract class KeyType {
      // not preceding
      public   KeyType (java.lang.String name)  { throw new RuntimeException(); }
      public  java.lang.String name ()  { throw new RuntimeException(); }
    }
    public   Tags$ ()  { throw new RuntimeException(); }
  }
  /** Convert a {@link Tags.KeyType} to a string to be used in the AddMap.tags Map[String, String]. */
  static public  java.lang.String tag (org.apache.spark.sql.delta.actions.AddFile.Tags.KeyType tagKey)  { throw new RuntimeException(); }
  public  java.net.URI pathAsUri ()  { throw new RuntimeException(); }
  public  java.lang.String path ()  { throw new RuntimeException(); }
  public  scala.collection.immutable.Map<java.lang.String, java.lang.String> partitionValues ()  { throw new RuntimeException(); }
  public  long size ()  { throw new RuntimeException(); }
  public  long modificationTime ()  { throw new RuntimeException(); }
  public  boolean dataChange ()  { throw new RuntimeException(); }
  public  java.lang.String stats ()  { throw new RuntimeException(); }
  public  scala.collection.immutable.Map<java.lang.String, java.lang.String> tags ()  { throw new RuntimeException(); }
  // not preceding
  public   AddFile (java.lang.String path, scala.collection.immutable.Map<java.lang.String, java.lang.String> partitionValues, long size, long modificationTime, boolean dataChange, java.lang.String stats, scala.collection.immutable.Map<java.lang.String, java.lang.String> tags)  { throw new RuntimeException(); }
  public  org.apache.spark.sql.delta.actions.SingleAction wrap ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.delta.actions.RemoveFile remove ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.delta.actions.RemoveFile removeWithTimestamp (long timestamp, boolean dataChange)  { throw new RuntimeException(); }
}
