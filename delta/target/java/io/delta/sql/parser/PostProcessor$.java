package io.delta.sql.parser;
/**
 * Fork from <code>org.apache.spark.sql.catalyst.parser.PostProcessor</code>.
 * <p>
 * @see https://github.com/apache/spark/blob/v2.4.4/sql/catalyst/src/main/scala/org/apache/spark/sql/catalyst/parser/ParseDriver.scala#L248
 */
public  class PostProcessor$ extends io.delta.sql.parser.DeltaSqlBaseBaseListener implements scala.Product, scala.Serializable {
  /**
   * Static reference to the singleton instance of this Scala object.
   */
  public static final PostProcessor$ MODULE$ = null;
  public   PostProcessor$ ()  { throw new RuntimeException(); }
  /** Remove the back ticks from an Identifier. */
  public  void exitQuotedIdentifier (io.delta.sql.parser.DeltaSqlBaseParser.QuotedIdentifierContext ctx)  { throw new RuntimeException(); }
  /** Treat non-reserved keywords as Identifiers. */
  public  void exitNonReserved (io.delta.sql.parser.DeltaSqlBaseParser.NonReservedContext ctx)  { throw new RuntimeException(); }
}
