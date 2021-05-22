package org.apache.spark.sql.delta.commands;
/**
 * Represents the state of a single merge clause:
 * - merge clause's (optional) predicate
 * - action type (insert, update, delete)
 * - action's expressions
 */
public  class MergeClauseStats implements scala.Product, scala.Serializable {
  static public  org.apache.spark.sql.delta.commands.MergeClauseStats apply (org.apache.spark.sql.catalyst.plans.logical.DeltaMergeIntoClause mergeClause)  { throw new RuntimeException(); }
  public  scala.Option<java.lang.String> condition ()  { throw new RuntimeException(); }
  public  java.lang.String actionType ()  { throw new RuntimeException(); }
  public  scala.collection.Seq<java.lang.String> actionExpr ()  { throw new RuntimeException(); }
  // not preceding
  public   MergeClauseStats (scala.Option<java.lang.String> condition, java.lang.String actionType, scala.collection.Seq<java.lang.String> actionExpr)  { throw new RuntimeException(); }
}
