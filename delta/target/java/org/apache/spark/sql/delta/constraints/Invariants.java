package org.apache.spark.sql.delta.constraints;
/**
 * List of invariants that can be defined on a Delta table that will allow us to perform
 * validation checks during changes to the table.
 */
public  class Invariants {
  /** Used for columns that should never be null. */
  static public  class NotNull$ implements org.apache.spark.sql.delta.constraints.Invariants.Rule, scala.Product, scala.Serializable {
    /**
     * Static reference to the singleton instance of this Scala object.
     */
    public static final NotNull$ MODULE$ = null;
    public   NotNull$ ()  { throw new RuntimeException(); }
    public  java.lang.String name ()  { throw new RuntimeException(); }
  }
  /** Rules that are persisted in the metadata field of a schema. */
  static public  class PersistedRule implements scala.Product, scala.Serializable {
    public  org.apache.spark.sql.delta.constraints.Invariants.PersistedExpression expression ()  { throw new RuntimeException(); }
    // not preceding
    public   PersistedRule (org.apache.spark.sql.delta.constraints.Invariants.PersistedExpression expression)  { throw new RuntimeException(); }
    public  org.apache.spark.sql.delta.constraints.Invariants.RulePersistedInMetadata unwrap ()  { throw new RuntimeException(); }
  }
  static public  class PersistedRule$ extends scala.runtime.AbstractFunction1<org.apache.spark.sql.delta.constraints.Invariants.PersistedExpression, org.apache.spark.sql.delta.constraints.Invariants.PersistedRule> implements scala.Serializable {
    /**
     * Static reference to the singleton instance of this Scala object.
     */
    public static final PersistedRule$ MODULE$ = null;
    public   PersistedRule$ ()  { throw new RuntimeException(); }
  }
  /** A SQL expression to check for when writing out data. */
  static public  class ArbitraryExpression implements org.apache.spark.sql.delta.constraints.Invariants.Rule, scala.Product, scala.Serializable {
    public  org.apache.spark.sql.catalyst.expressions.Expression expression ()  { throw new RuntimeException(); }
    // not preceding
    public   ArbitraryExpression (org.apache.spark.sql.catalyst.expressions.Expression expression)  { throw new RuntimeException(); }
    public  java.lang.String name ()  { throw new RuntimeException(); }
  }
  static public  class ArbitraryExpression$ implements scala.Serializable {
    /**
     * Static reference to the singleton instance of this Scala object.
     */
    public static final ArbitraryExpression$ MODULE$ = null;
    public   ArbitraryExpression$ ()  { throw new RuntimeException(); }
    public  org.apache.spark.sql.delta.constraints.Invariants.ArbitraryExpression apply (org.apache.spark.sql.SparkSession sparkSession, java.lang.String exprString)  { throw new RuntimeException(); }
  }
  /** Persisted companion of the ArbitraryExpression rule. */
  static public  class PersistedExpression implements org.apache.spark.sql.delta.constraints.Invariants.RulePersistedInMetadata, scala.Product, scala.Serializable {
    public  java.lang.String expression ()  { throw new RuntimeException(); }
    // not preceding
    public   PersistedExpression (java.lang.String expression)  { throw new RuntimeException(); }
    public  org.apache.spark.sql.delta.constraints.Invariants.PersistedRule wrap ()  { throw new RuntimeException(); }
  }
  static public  class PersistedExpression$ extends scala.runtime.AbstractFunction1<java.lang.String, org.apache.spark.sql.delta.constraints.Invariants.PersistedExpression> implements scala.Serializable {
    /**
     * Static reference to the singleton instance of this Scala object.
     */
    public static final PersistedExpression$ MODULE$ = null;
    public   PersistedExpression$ ()  { throw new RuntimeException(); }
  }
  static public  interface Rule {
    public  java.lang.String name ()  ;
  }
  static public  interface RulePersistedInMetadata {
    public  java.lang.String json ()  ;
    public  org.apache.spark.sql.delta.constraints.Invariants.PersistedRule wrap ()  ;
  }
  /** Extract invariants from the given schema */
  static public  scala.collection.Seq<org.apache.spark.sql.delta.constraints.Constraint> getFromSchema (org.apache.spark.sql.types.StructType schema, org.apache.spark.sql.SparkSession spark)  { throw new RuntimeException(); }
  static public  java.lang.String INVARIANTS_FIELD ()  { throw new RuntimeException(); }
}
