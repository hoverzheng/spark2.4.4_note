package org.apache.spark.sql.delta.catalog;
/**
 * A trait for handling table access through delta.<code>/some/path</code>. This is a stop-gap solution
 * until PathIdentifiers are implemented in Apache Spark.
 */
public  interface SupportsPathIdentifier extends org.apache.spark.sql.connector.catalog.TableCatalog {
  public  org.apache.spark.sql.catalyst.catalog.SessionCatalog catalog ()  ;
  public  boolean hasDeltaNamespace (org.apache.spark.sql.connector.catalog.Identifier ident)  ;
  public  boolean isPathIdentifier (org.apache.spark.sql.connector.catalog.Identifier ident)  ;
  public  boolean isPathIdentifier (org.apache.spark.sql.catalyst.catalog.CatalogTable table)  ;
  public  boolean supportSQLOnFile ()  ;
  public  boolean tableExists (org.apache.spark.sql.connector.catalog.Identifier ident)  ;
}
