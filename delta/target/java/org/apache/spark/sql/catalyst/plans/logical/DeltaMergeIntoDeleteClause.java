package org.apache.spark.sql.catalyst.plans.logical;
/** Represents the clause WHEN MATCHED THEN DELETE in MERGE. See {@link DeltaMergeInto}. */
public  class DeltaMergeIntoDeleteClause extends org.apache.spark.sql.catalyst.expressions.Expression implements org.apache.spark.sql.catalyst.plans.logical.DeltaMergeIntoMatchedClause, scala.Product, scala.Serializable {
  static public abstract  R apply (T1 v1)  ;
  static public  java.lang.String toString ()  { throw new RuntimeException(); }
  public  scala.Option<org.apache.spark.sql.catalyst.expressions.Expression> condition ()  { throw new RuntimeException(); }
  // not preceding
  public   DeltaMergeIntoDeleteClause (scala.Option<org.apache.spark.sql.catalyst.expressions.Expression> condition)  { throw new RuntimeException(); }
  public   DeltaMergeIntoDeleteClause (scala.Option<org.apache.spark.sql.catalyst.expressions.Expression> condition, scala.collection.Seq<org.apache.spark.sql.catalyst.plans.logical.DeltaMergeAction> actions)  { throw new RuntimeException(); }
  public  scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Expression> actions ()  { throw new RuntimeException(); }
}
