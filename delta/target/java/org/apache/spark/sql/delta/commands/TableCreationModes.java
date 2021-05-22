package org.apache.spark.sql.delta.commands;
public  class TableCreationModes {
  static public  class Create$ implements org.apache.spark.sql.delta.commands.TableCreationModes.CreationMode, scala.Product, scala.Serializable {
    /**
     * Static reference to the singleton instance of this Scala object.
     */
    public static final Create$ MODULE$ = null;
    public   Create$ ()  { throw new RuntimeException(); }
    public  org.apache.spark.sql.SaveMode mode ()  { throw new RuntimeException(); }
  }
  static public  class CreateOrReplace$ implements org.apache.spark.sql.delta.commands.TableCreationModes.CreationMode, scala.Product, scala.Serializable {
    /**
     * Static reference to the singleton instance of this Scala object.
     */
    public static final CreateOrReplace$ MODULE$ = null;
    public   CreateOrReplace$ ()  { throw new RuntimeException(); }
    public  org.apache.spark.sql.SaveMode mode ()  { throw new RuntimeException(); }
  }
  static public  class Replace$ implements org.apache.spark.sql.delta.commands.TableCreationModes.CreationMode, scala.Product, scala.Serializable {
    /**
     * Static reference to the singleton instance of this Scala object.
     */
    public static final Replace$ MODULE$ = null;
    public   Replace$ ()  { throw new RuntimeException(); }
    public  org.apache.spark.sql.SaveMode mode ()  { throw new RuntimeException(); }
  }
  static public  interface CreationMode {
    public  org.apache.spark.sql.SaveMode mode ()  ;
  }
}
