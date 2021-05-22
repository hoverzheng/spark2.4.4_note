package org.apache.spark.sql.delta.catalog;
public  class WriteIntoDeltaBuilder implements org.apache.spark.sql.connector.write.WriteBuilder, org.apache.spark.sql.connector.write.V1WriteBuilder, org.apache.spark.sql.connector.write.SupportsOverwrite, org.apache.spark.sql.connector.write.SupportsTruncate {
  public   WriteIntoDeltaBuilder (org.apache.spark.sql.delta.DeltaLog log, org.apache.spark.sql.util.CaseInsensitiveStringMap writeOptions)  { throw new RuntimeException(); }
  public  org.apache.spark.sql.sources.InsertableRelation buildForV1Write ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.connector.write.WriteBuilder overwrite (org.apache.spark.sql.sources.Filter[] filters)  { throw new RuntimeException(); }
  public  org.apache.spark.sql.delta.catalog.WriteIntoDeltaBuilder truncate ()  { throw new RuntimeException(); }
}
