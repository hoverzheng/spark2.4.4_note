package org.apache.spark.sql.catalyst.plans.logical;
public  class DeltaMergeIntoClause$ {
  /**
   * Static reference to the singleton instance of this Scala object.
   */
  public static final DeltaMergeIntoClause$ MODULE$ = null;
  public   DeltaMergeIntoClause$ ()  { throw new RuntimeException(); }
  /**
   * Convert the parsed columns names and expressions into action for MergeInto. Note:
   * - Size of column names and expressions must be the same.
   * - If the sizes are zeros and <code>emptySeqIsStar</code> is true, this function assumes
   *   that query had <code>*</code> as an action, and therefore generates a single action
   *   with <code>UnresolvedStar</code>. This will be expanded later during analysis.
   * - Otherwise, this will convert the names and expressions to MergeActions.
   * @param colNames (undocumented)
   * @param exprs (undocumented)
   * @param isEmptySeqEqualToStar (undocumented)
   * @return (undocumented)
   */
  public  scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Expression> toActions (scala.collection.Seq<org.apache.spark.sql.catalyst.analysis.UnresolvedAttribute> colNames, scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Expression> exprs, boolean isEmptySeqEqualToStar)  { throw new RuntimeException(); }
  public  scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Expression> toActions (scala.collection.Seq<org.apache.spark.sql.catalyst.plans.logical.Assignment> assignments)  { throw new RuntimeException(); }
}
