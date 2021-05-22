package org.apache.spark.sql.delta.actions;
public  class JobInfo implements scala.Product, scala.Serializable {
  static public  scala.Option<org.apache.spark.sql.delta.actions.JobInfo> fromContext (scala.collection.immutable.Map<java.lang.String, java.lang.String> context)  { throw new RuntimeException(); }
  public  java.lang.String jobId ()  { throw new RuntimeException(); }
  public  java.lang.String jobName ()  { throw new RuntimeException(); }
  public  java.lang.String runId ()  { throw new RuntimeException(); }
  public  java.lang.String jobOwnerId ()  { throw new RuntimeException(); }
  public  java.lang.String triggerType ()  { throw new RuntimeException(); }
  // not preceding
  public   JobInfo (java.lang.String jobId, java.lang.String jobName, java.lang.String runId, java.lang.String jobOwnerId, java.lang.String triggerType)  { throw new RuntimeException(); }
}
