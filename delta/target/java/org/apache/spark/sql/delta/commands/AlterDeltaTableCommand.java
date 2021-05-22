package org.apache.spark.sql.delta.commands;
/**
 * A super trait for alter table commands that modify Delta tables.
 */
public  interface AlterDeltaTableCommand extends org.apache.spark.sql.delta.commands.DeltaCommand {
  public  org.apache.spark.sql.delta.OptimisticTransaction startTransaction ()  ;
  public  org.apache.spark.sql.delta.catalog.DeltaTableV2 table ()  ;
}
