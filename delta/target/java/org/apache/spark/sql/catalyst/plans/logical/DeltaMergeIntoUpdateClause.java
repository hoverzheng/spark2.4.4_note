package org.apache.spark.sql.catalyst.plans.logical;
/** Represents the clause WHEN MATCHED THEN UPDATE in MERGE. See {@link DeltaMergeInto}. */
public  class DeltaMergeIntoUpdateClause extends org.apache.spark.sql.catalyst.expressions.Expression implements org.apache.spark.sql.catalyst.plans.logical.DeltaMergeIntoMatchedClause, scala.Product, scala.Serializable {
  static public abstract  R apply (T1 v1, T2 v2)  ;
  static public  java.lang.String toString ()  { throw new RuntimeException(); }
  public  scala.Option<org.apache.spark.sql.catalyst.expressions.Expression> condition ()  { throw new RuntimeException(); }
  public  scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Expression> actions ()  { throw new RuntimeException(); }
  // not preceding
  public   DeltaMergeIntoUpdateClause (scala.Option<org.apache.spark.sql.catalyst.expressions.Expression> condition, scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Expression> actions)  { throw new RuntimeException(); }
  public   DeltaMergeIntoUpdateClause (scala.Option<org.apache.spark.sql.catalyst.expressions.Expression> cond, scala.collection.Seq<org.apache.spark.sql.catalyst.analysis.UnresolvedAttribute> cols, scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Expression> exprs)  { throw new RuntimeException(); }
}
