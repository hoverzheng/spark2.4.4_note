package org.apache.spark.sql.catalyst.plans.logical;
/**
 * Trait that represents a WHEN clause in MERGE. See {@link DeltaMergeInto}. It extends {@link Expression}
 * so that Catalyst can find all the expressions in the clause implementations.
 */
public  interface DeltaMergeIntoClause extends org.apache.spark.sql.catalyst.plans.logical.DeltaUnevaluable {
  /** Optional condition of the clause */
  public  scala.Option<org.apache.spark.sql.catalyst.expressions.Expression> condition ()  ;
  /**
   * Sequence of actions represented as expressions. Note that this can be only be either
   * UnresolvedStar, or MergeAction.
   * @return (undocumented)
   */
  public  scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Expression> actions ()  ;
  /**
   * Sequence of resolved actions represented as Aliases. Actions, once resolved, must
   * be Aliases and not any other NamedExpressions. So it should be safe to do this casting
   * as long as this is called after the clause has been resolved.
   * @return (undocumented)
   */
  public  scala.collection.Seq<org.apache.spark.sql.catalyst.plans.logical.DeltaMergeAction> resolvedActions ()  ;
  public  java.lang.String clauseType ()  ;
  public  java.lang.String toString ()  ;
  public  boolean nullable ()  ;
  public  org.apache.spark.sql.types.DataType dataType ()  ;
  public  scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Expression> children ()  ;
  /** Verify whether the expressions in the actions are of the right type */
    void verifyActions ()  ;
}
