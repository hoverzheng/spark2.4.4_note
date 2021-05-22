package org.apache.spark.sql.delta;
public  interface DocsPath {
  public  void assertValidCallingFunction ()  ;
  /**
   * The URL for the base path of Delta's docs. When changing this path, ensure that the new path
   * works with the error messages below.
   * @param conf (undocumented)
   * @return (undocumented)
   */
  public  java.lang.String baseDocsPath (org.apache.spark.SparkConf conf)  ;
  /**
   * List of error function names for all errors that have URLs. When adding your error to this list
   * remember to also add it to the list of errors in DeltaErrorsSuite
   * <p>
   * @note add your error to DeltaErrorsSuiteBase after adding it to this list so that the url can
   *       be tested
   * @return (undocumented)
   */
  public  scala.collection.Seq<java.lang.String> errorsWithDocsLinks ()  ;
  /**
   * Get the link to the docs for the given relativePath. Validates that the error generating the
   * link is added to docsLinks.
   * <p>
   * @param relativePath the relative path after the base url to access.
   * @param skipValidation whether to validate that the function generating the link is
   *                       in the allowlist.
   * @return The entire URL of the documentation link
   * @param conf (undocumented)
   */
  public  java.lang.String generateDocsLink (org.apache.spark.SparkConf conf, java.lang.String relativePath, boolean skipValidation)  ;
}
