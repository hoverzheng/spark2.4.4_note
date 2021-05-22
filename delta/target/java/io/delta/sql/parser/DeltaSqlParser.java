package io.delta.sql.parser;
/**
 * A SQL parser that tries to parse Delta commands. If failng to parse the SQL text, it will
 * forward the call to <code>delegate</code>.
 */
public  class DeltaSqlParser implements org.apache.spark.sql.catalyst.parser.ParserInterface {
  // not preceding
  public   DeltaSqlParser (org.apache.spark.sql.catalyst.parser.ParserInterface delegate)  { throw new RuntimeException(); }
  public  org.apache.spark.sql.catalyst.parser.ParserInterface delegate ()  { throw new RuntimeException(); }
  /**
   * Fork from <code>org.apache.spark.sql.catalyst.parser.AbstractSqlParser#parse(java.lang.String, scala.Function1)</code>.
   * <p>
   * @see https://github.com/apache/spark/blob/v2.4.4/sql/catalyst/src/main/scala/org/apache/spark/sql/catalyst/parser/ParseDriver.scala#L81
   * @param command (undocumented)
   * @param toResult (undocumented)
   * @return (undocumented)
   */
  protected <T extends java.lang.Object> T parse (java.lang.String command, scala.Function1<io.delta.sql.parser.DeltaSqlBaseParser, T> toResult)  { throw new RuntimeException(); }
  public  org.apache.spark.sql.types.DataType parseDataType (java.lang.String sqlText)  { throw new RuntimeException(); }
  public  org.apache.spark.sql.catalyst.expressions.Expression parseExpression (java.lang.String sqlText)  { throw new RuntimeException(); }
  public  org.apache.spark.sql.catalyst.FunctionIdentifier parseFunctionIdentifier (java.lang.String sqlText)  { throw new RuntimeException(); }
  public  scala.collection.Seq<java.lang.String> parseMultipartIdentifier (java.lang.String sqlText)  { throw new RuntimeException(); }
  public  org.apache.spark.sql.catalyst.plans.logical.LogicalPlan parsePlan (java.lang.String sqlText)  { throw new RuntimeException(); }
  public  org.apache.spark.sql.types.DataType parseRawDataType (java.lang.String sqlText)  { throw new RuntimeException(); }
  public  org.apache.spark.sql.catalyst.TableIdentifier parseTableIdentifier (java.lang.String sqlText)  { throw new RuntimeException(); }
  public  org.apache.spark.sql.types.StructType parseTableSchema (java.lang.String sqlText)  { throw new RuntimeException(); }
}
