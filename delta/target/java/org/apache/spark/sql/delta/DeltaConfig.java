package org.apache.spark.sql.delta;
public  class DeltaConfig<T extends java.lang.Object> implements scala.Product, scala.Serializable {
  public  java.lang.String key ()  { throw new RuntimeException(); }
  public  java.lang.String defaultValue ()  { throw new RuntimeException(); }
  public  scala.Function1<java.lang.String, T> fromString ()  { throw new RuntimeException(); }
  public  scala.Function1<T, java.lang.Object> validationFunction ()  { throw new RuntimeException(); }
  public  java.lang.String helpMessage ()  { throw new RuntimeException(); }
  public  scala.Option<org.apache.spark.sql.delta.actions.Protocol> minimumProtocolVersion ()  { throw new RuntimeException(); }
  // not preceding
  public   DeltaConfig (java.lang.String key, java.lang.String defaultValue, scala.Function1<java.lang.String, T> fromString, scala.Function1<T, java.lang.Object> validationFunction, java.lang.String helpMessage, scala.Option<org.apache.spark.sql.delta.actions.Protocol> minimumProtocolVersion)  { throw new RuntimeException(); }
  /**
   * Recover the saved value of this configuration from <code>Metadata</code> or return the default if this
   * value hasn't been changed.
   * @param metadata (undocumented)
   * @return (undocumented)
   */
  public  T fromMetaData (org.apache.spark.sql.delta.actions.Metadata metadata)  { throw new RuntimeException(); }
  /**
   * Validate this configuration and return the key - value pair to save into the metadata.
   * @param value (undocumented)
   * @return (undocumented)
   */
  public  scala.Tuple2<java.lang.String, java.lang.String> apply (java.lang.String value)  { throw new RuntimeException(); }
  /**
   * SQL configuration to set for ensuring that all newly created tables have this table property.
   * @return (undocumented)
   */
  public  java.lang.String defaultTablePropertyKey ()  { throw new RuntimeException(); }
}
