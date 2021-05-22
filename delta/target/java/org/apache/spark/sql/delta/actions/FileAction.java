package org.apache.spark.sql.delta.actions;
/** Actions pertaining to the addition and removal of files. */
public  interface FileAction extends org.apache.spark.sql.delta.actions.Action {
  public  boolean dataChange ()  ;
  public  java.lang.String path ()  ;
  public  java.net.URI pathAsUri ()  ;
}
