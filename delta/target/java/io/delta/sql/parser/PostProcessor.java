package io.delta.sql.parser;
/**
 * Fork from <code>org.apache.spark.sql.catalyst.parser.PostProcessor</code>.
 * <p>
 * @see https://github.com/apache/spark/blob/v2.4.4/sql/catalyst/src/main/scala/org/apache/spark/sql/catalyst/parser/ParseDriver.scala#L248
 */
public  class PostProcessor {
  /** Remove the back ticks from an Identifier. */
  static public  void exitQuotedIdentifier (io.delta.sql.parser.DeltaSqlBaseParser.QuotedIdentifierContext ctx)  { throw new RuntimeException(); }
  /** Treat non-reserved keywords as Identifiers. */
  static public  void exitNonReserved (io.delta.sql.parser.DeltaSqlBaseParser.NonReservedContext ctx)  { throw new RuntimeException(); }
  static public  void enterSingleStatement (io.delta.sql.parser.DeltaSqlBaseParser.SingleStatementContext ctx)  { throw new RuntimeException(); }
  static public  void exitSingleStatement (io.delta.sql.parser.DeltaSqlBaseParser.SingleStatementContext ctx)  { throw new RuntimeException(); }
  static public  void enterVacuumTable (io.delta.sql.parser.DeltaSqlBaseParser.VacuumTableContext ctx)  { throw new RuntimeException(); }
  static public  void exitVacuumTable (io.delta.sql.parser.DeltaSqlBaseParser.VacuumTableContext ctx)  { throw new RuntimeException(); }
  static public  void enterDescribeDeltaDetail (io.delta.sql.parser.DeltaSqlBaseParser.DescribeDeltaDetailContext ctx)  { throw new RuntimeException(); }
  static public  void exitDescribeDeltaDetail (io.delta.sql.parser.DeltaSqlBaseParser.DescribeDeltaDetailContext ctx)  { throw new RuntimeException(); }
  static public  void enterGenerate (io.delta.sql.parser.DeltaSqlBaseParser.GenerateContext ctx)  { throw new RuntimeException(); }
  static public  void exitGenerate (io.delta.sql.parser.DeltaSqlBaseParser.GenerateContext ctx)  { throw new RuntimeException(); }
  static public  void enterDescribeDeltaHistory (io.delta.sql.parser.DeltaSqlBaseParser.DescribeDeltaHistoryContext ctx)  { throw new RuntimeException(); }
  static public  void exitDescribeDeltaHistory (io.delta.sql.parser.DeltaSqlBaseParser.DescribeDeltaHistoryContext ctx)  { throw new RuntimeException(); }
  static public  void enterConvert (io.delta.sql.parser.DeltaSqlBaseParser.ConvertContext ctx)  { throw new RuntimeException(); }
  static public  void exitConvert (io.delta.sql.parser.DeltaSqlBaseParser.ConvertContext ctx)  { throw new RuntimeException(); }
  static public  void enterAddTableConstraint (io.delta.sql.parser.DeltaSqlBaseParser.AddTableConstraintContext ctx)  { throw new RuntimeException(); }
  static public  void exitAddTableConstraint (io.delta.sql.parser.DeltaSqlBaseParser.AddTableConstraintContext ctx)  { throw new RuntimeException(); }
  static public  void enterDropTableConstraint (io.delta.sql.parser.DeltaSqlBaseParser.DropTableConstraintContext ctx)  { throw new RuntimeException(); }
  static public  void exitDropTableConstraint (io.delta.sql.parser.DeltaSqlBaseParser.DropTableConstraintContext ctx)  { throw new RuntimeException(); }
  static public  void enterPassThrough (io.delta.sql.parser.DeltaSqlBaseParser.PassThroughContext ctx)  { throw new RuntimeException(); }
  static public  void exitPassThrough (io.delta.sql.parser.DeltaSqlBaseParser.PassThroughContext ctx)  { throw new RuntimeException(); }
  static public  void enterQualifiedName (io.delta.sql.parser.DeltaSqlBaseParser.QualifiedNameContext ctx)  { throw new RuntimeException(); }
  static public  void exitQualifiedName (io.delta.sql.parser.DeltaSqlBaseParser.QualifiedNameContext ctx)  { throw new RuntimeException(); }
  static public  void enterUnquotedIdentifier (io.delta.sql.parser.DeltaSqlBaseParser.UnquotedIdentifierContext ctx)  { throw new RuntimeException(); }
  static public  void exitUnquotedIdentifier (io.delta.sql.parser.DeltaSqlBaseParser.UnquotedIdentifierContext ctx)  { throw new RuntimeException(); }
  static public  void enterQuotedIdentifierAlternative (io.delta.sql.parser.DeltaSqlBaseParser.QuotedIdentifierAlternativeContext ctx)  { throw new RuntimeException(); }
  static public  void exitQuotedIdentifierAlternative (io.delta.sql.parser.DeltaSqlBaseParser.QuotedIdentifierAlternativeContext ctx)  { throw new RuntimeException(); }
  static public  void enterQuotedIdentifier (io.delta.sql.parser.DeltaSqlBaseParser.QuotedIdentifierContext ctx)  { throw new RuntimeException(); }
  static public  void enterColTypeList (io.delta.sql.parser.DeltaSqlBaseParser.ColTypeListContext ctx)  { throw new RuntimeException(); }
  static public  void exitColTypeList (io.delta.sql.parser.DeltaSqlBaseParser.ColTypeListContext ctx)  { throw new RuntimeException(); }
  static public  void enterColType (io.delta.sql.parser.DeltaSqlBaseParser.ColTypeContext ctx)  { throw new RuntimeException(); }
  static public  void exitColType (io.delta.sql.parser.DeltaSqlBaseParser.ColTypeContext ctx)  { throw new RuntimeException(); }
  static public  void enterPrimitiveDataType (io.delta.sql.parser.DeltaSqlBaseParser.PrimitiveDataTypeContext ctx)  { throw new RuntimeException(); }
  static public  void exitPrimitiveDataType (io.delta.sql.parser.DeltaSqlBaseParser.PrimitiveDataTypeContext ctx)  { throw new RuntimeException(); }
  static public  void enterDecimalLiteral (io.delta.sql.parser.DeltaSqlBaseParser.DecimalLiteralContext ctx)  { throw new RuntimeException(); }
  static public  void exitDecimalLiteral (io.delta.sql.parser.DeltaSqlBaseParser.DecimalLiteralContext ctx)  { throw new RuntimeException(); }
  static public  void enterIntegerLiteral (io.delta.sql.parser.DeltaSqlBaseParser.IntegerLiteralContext ctx)  { throw new RuntimeException(); }
  static public  void exitIntegerLiteral (io.delta.sql.parser.DeltaSqlBaseParser.IntegerLiteralContext ctx)  { throw new RuntimeException(); }
  static public  void enterBigIntLiteral (io.delta.sql.parser.DeltaSqlBaseParser.BigIntLiteralContext ctx)  { throw new RuntimeException(); }
  static public  void exitBigIntLiteral (io.delta.sql.parser.DeltaSqlBaseParser.BigIntLiteralContext ctx)  { throw new RuntimeException(); }
  static public  void enterSmallIntLiteral (io.delta.sql.parser.DeltaSqlBaseParser.SmallIntLiteralContext ctx)  { throw new RuntimeException(); }
  static public  void exitSmallIntLiteral (io.delta.sql.parser.DeltaSqlBaseParser.SmallIntLiteralContext ctx)  { throw new RuntimeException(); }
  static public  void enterTinyIntLiteral (io.delta.sql.parser.DeltaSqlBaseParser.TinyIntLiteralContext ctx)  { throw new RuntimeException(); }
  static public  void exitTinyIntLiteral (io.delta.sql.parser.DeltaSqlBaseParser.TinyIntLiteralContext ctx)  { throw new RuntimeException(); }
  static public  void enterDoubleLiteral (io.delta.sql.parser.DeltaSqlBaseParser.DoubleLiteralContext ctx)  { throw new RuntimeException(); }
  static public  void exitDoubleLiteral (io.delta.sql.parser.DeltaSqlBaseParser.DoubleLiteralContext ctx)  { throw new RuntimeException(); }
  static public  void enterBigDecimalLiteral (io.delta.sql.parser.DeltaSqlBaseParser.BigDecimalLiteralContext ctx)  { throw new RuntimeException(); }
  static public  void exitBigDecimalLiteral (io.delta.sql.parser.DeltaSqlBaseParser.BigDecimalLiteralContext ctx)  { throw new RuntimeException(); }
  static public  void enterCheckConstraint (io.delta.sql.parser.DeltaSqlBaseParser.CheckConstraintContext ctx)  { throw new RuntimeException(); }
  static public  void exitCheckConstraint (io.delta.sql.parser.DeltaSqlBaseParser.CheckConstraintContext ctx)  { throw new RuntimeException(); }
  static public  void enterCheckExprToken (io.delta.sql.parser.DeltaSqlBaseParser.CheckExprTokenContext ctx)  { throw new RuntimeException(); }
  static public  void exitCheckExprToken (io.delta.sql.parser.DeltaSqlBaseParser.CheckExprTokenContext ctx)  { throw new RuntimeException(); }
  static public  void enterNonReserved (io.delta.sql.parser.DeltaSqlBaseParser.NonReservedContext ctx)  { throw new RuntimeException(); }
  static public  void enterEveryRule (org.antlr.v4.runtime.ParserRuleContext ctx)  { throw new RuntimeException(); }
  static public  void exitEveryRule (org.antlr.v4.runtime.ParserRuleContext ctx)  { throw new RuntimeException(); }
  static public  void visitTerminal (org.antlr.v4.runtime.tree.TerminalNode node)  { throw new RuntimeException(); }
  static public  void visitErrorNode (org.antlr.v4.runtime.tree.ErrorNode node)  { throw new RuntimeException(); }
  static public abstract  boolean canEqual (Object that)  ;
  static public abstract  boolean equals (Object that)  ;
  static public abstract  Object productElement (int n)  ;
  static public abstract  int productArity ()  ;
  static public  scala.collection.Iterator<java.lang.Object> productIterator ()  { throw new RuntimeException(); }
  static public  java.lang.String productPrefix ()  { throw new RuntimeException(); }
}
