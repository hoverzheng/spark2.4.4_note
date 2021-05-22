package org.apache.spark.sql.delta.commands;
public  class ConvertToDeltaCommand extends org.apache.spark.sql.delta.commands.ConvertToDeltaCommandBase implements scala.Product, scala.Serializable {
  static public abstract  R apply (T1 v1, T2 v2, T3 v3)  ;
  static public  java.lang.String toString ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.catalyst.TableIdentifier tableIdentifier ()  { throw new RuntimeException(); }
  public  scala.Option<org.apache.spark.sql.types.StructType> partitionSchema ()  { throw new RuntimeException(); }
  public  scala.Option<java.lang.String> deltaPath ()  { throw new RuntimeException(); }
  // not preceding
  public   ConvertToDeltaCommand (org.apache.spark.sql.catalyst.TableIdentifier tableIdentifier, scala.Option<org.apache.spark.sql.types.StructType> partitionSchema, scala.Option<java.lang.String> deltaPath)  { throw new RuntimeException(); }
}
