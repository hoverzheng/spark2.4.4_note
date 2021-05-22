package io.delta.sql.parser;
/**
 * Define how to convert an AST generated from <code>DeltaSqlBase.g4</code> to a <code>LogicalPlan</code>. The parent
 * class <code>DeltaSqlBaseBaseVisitor</code> defines all visitXXX methods generated from <code>#</code> instructions in
 * <code>DeltaSqlBase.g4</code> (such as <code>#vacuumTable</code>).
 */
public  class DeltaSqlAstBuilder extends io.delta.sql.parser.DeltaSqlBaseBaseVisitor<java.lang.Object> {
  public   DeltaSqlAstBuilder ()  { throw new RuntimeException(); }
  protected <T extends java.lang.Object> T typedVisit (org.antlr.v4.runtime.tree.ParseTree ctx)  { throw new RuntimeException(); }
  public  org.apache.spark.sql.catalyst.plans.logical.LogicalPlan visitAddTableConstraint (io.delta.sql.parser.DeltaSqlBaseParser.AddTableConstraintContext ctx)  { throw new RuntimeException(); }
  public  org.apache.spark.sql.types.StructField visitColType (io.delta.sql.parser.DeltaSqlBaseParser.ColTypeContext ctx)  { throw new RuntimeException(); }
  public  scala.collection.Seq<org.apache.spark.sql.types.StructField> visitColTypeList (io.delta.sql.parser.DeltaSqlBaseParser.ColTypeListContext ctx)  { throw new RuntimeException(); }
  public  org.apache.spark.sql.catalyst.plans.logical.LogicalPlan visitConvert (io.delta.sql.parser.DeltaSqlBaseParser.ConvertContext ctx)  { throw new RuntimeException(); }
  public  org.apache.spark.sql.catalyst.plans.logical.LogicalPlan visitDescribeDeltaDetail (io.delta.sql.parser.DeltaSqlBaseParser.DescribeDeltaDetailContext ctx)  { throw new RuntimeException(); }
  public  org.apache.spark.sql.catalyst.plans.logical.LogicalPlan visitDescribeDeltaHistory (io.delta.sql.parser.DeltaSqlBaseParser.DescribeDeltaHistoryContext ctx)  { throw new RuntimeException(); }
  public  org.apache.spark.sql.catalyst.plans.logical.LogicalPlan visitDropTableConstraint (io.delta.sql.parser.DeltaSqlBaseParser.DropTableConstraintContext ctx)  { throw new RuntimeException(); }
  public  org.apache.spark.sql.catalyst.plans.logical.LogicalPlan visitGenerate (io.delta.sql.parser.DeltaSqlBaseParser.GenerateContext ctx)  { throw new RuntimeException(); }
  public  org.apache.spark.sql.catalyst.plans.logical.LogicalPlan visitPassThrough (io.delta.sql.parser.DeltaSqlBaseParser.PassThroughContext ctx)  { throw new RuntimeException(); }
  public  org.apache.spark.sql.types.DataType visitPrimitiveDataType (io.delta.sql.parser.DeltaSqlBaseParser.PrimitiveDataTypeContext ctx)  { throw new RuntimeException(); }
  public  org.apache.spark.sql.catalyst.plans.logical.LogicalPlan visitSingleStatement (io.delta.sql.parser.DeltaSqlBaseParser.SingleStatementContext ctx)  { throw new RuntimeException(); }
  protected  org.apache.spark.sql.catalyst.TableIdentifier visitTableIdentifier (io.delta.sql.parser.DeltaSqlBaseParser.QualifiedNameContext ctx)  { throw new RuntimeException(); }
  /**
   * Create a {@link VacuumTableCommand} logical plan. Example SQL:
   * <pre><code>
   *   VACUUM ('/path/to/dir' | delta.`/path/to/dir`) [RETAIN number HOURS] [DRY RUN];
   * </code></pre>
   * @param ctx (undocumented)
   * @return (undocumented)
   */
  public  java.lang.Object visitVacuumTable (io.delta.sql.parser.DeltaSqlBaseParser.VacuumTableContext ctx)  { throw new RuntimeException(); }
}
