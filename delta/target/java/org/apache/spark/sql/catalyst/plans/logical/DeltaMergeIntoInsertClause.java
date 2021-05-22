package org.apache.spark.sql.catalyst.plans.logical;
/** Represents the clause WHEN NOT MATCHED THEN INSERT in MERGE. See {@link DeltaMergeInto}. */
public  class DeltaMergeIntoInsertClause extends org.apache.spark.sql.catalyst.expressions.Expression implements org.apache.spark.sql.catalyst.plans.logical.DeltaMergeIntoClause, scala.Product, scala.Serializable {
  static public abstract  R apply (T1 v1, T2 v2)  ;
  static public  java.lang.String toString ()  { throw new RuntimeException(); }
  public  scala.Option<org.apache.spark.sql.catalyst.expressions.Expression> condition ()  { throw new RuntimeException(); }
  public  scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Expression> actions ()  { throw new RuntimeException(); }
  // not preceding
  public   DeltaMergeIntoInsertClause (scala.Option<org.apache.spark.sql.catalyst.expressions.Expression> condition, scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Expression> actions)  { throw new RuntimeException(); }
  public   DeltaMergeIntoInsertClause (scala.Option<org.apache.spark.sql.catalyst.expressions.Expression> cond, scala.collection.Seq<org.apache.spark.sql.catalyst.analysis.UnresolvedAttribute> cols, scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Expression> exprs)  { throw new RuntimeException(); }
}
