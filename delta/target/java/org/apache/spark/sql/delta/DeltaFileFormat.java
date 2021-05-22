package org.apache.spark.sql.delta;
public  interface DeltaFileFormat {
  /** Return the underlying Spark `FileFormat` of the Delta table. */
  public  org.apache.spark.sql.execution.datasources.FileFormat fileFormat ()  ;
}
