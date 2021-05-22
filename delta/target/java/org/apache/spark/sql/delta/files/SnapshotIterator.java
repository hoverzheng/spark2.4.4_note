package org.apache.spark.sql.delta.files;
public  interface SnapshotIterator {
  public  void close (boolean unpersistSnapshot)  ;
  public  scala.collection.Iterator<org.apache.spark.sql.delta.sources.IndexedFile> iterator ()  ;
}
