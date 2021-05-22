package org.apache.spark.sql.delta.hooks;
/**
 * Post commit hook to generate hive-style manifests for Delta table. This is useful for
 * compatibility with Presto / Athena.
 */
public  class GenerateSymlinkManifest$ implements org.apache.spark.sql.delta.hooks.GenerateSymlinkManifestImpl {
  /**
   * Static reference to the singleton instance of this Scala object.
   */
  public static final GenerateSymlinkManifest$ MODULE$ = null;
  public  java.lang.String CONFIG_NAME_ROOT ()  { throw new RuntimeException(); }
  public  java.lang.String MANIFEST_LOCATION ()  { throw new RuntimeException(); }
  public  java.lang.String OP_TYPE_ROOT ()  { throw new RuntimeException(); }
  public  java.lang.String FULL_MANIFEST_OP_TYPE ()  { throw new RuntimeException(); }
  public  java.lang.String INCREMENTAL_MANIFEST_OP_TYPE ()  { throw new RuntimeException(); }
  public  java.lang.String name ()  { throw new RuntimeException(); }
  public  org.apache.spark.sql.delta.hooks.GenerateSymlinkManifestImpl.SymlinkManifestStats$ SymlinkManifestStats ()  { throw new RuntimeException(); }
  public   GenerateSymlinkManifest$ ()  { throw new RuntimeException(); }
}
