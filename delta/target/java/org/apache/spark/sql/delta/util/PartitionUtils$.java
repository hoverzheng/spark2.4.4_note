package org.apache.spark.sql.delta.util;
public  class PartitionUtils$ {
  /**
   * Static reference to the singleton instance of this Scala object.
   */
  public static final PartitionUtils$ MODULE$ = null;
  public   PartitionUtils$ ()  { throw new RuntimeException(); }
  public  java.lang.String timestampPartitionPattern ()  { throw new RuntimeException(); }
  /**
   * Given a group of qualified paths, tries to parse them and returns a partition specification.
   * For example, given:
   * <pre><code>
   *   hdfs://&lt;host&gt;:&lt;port&gt;/path/to/partition/a=1/b=hello/c=3.14
   *   hdfs://&lt;host&gt;:&lt;port&gt;/path/to/partition/a=2/b=world/c=6.28
   * </code></pre>
   * it returns:
   * <pre><code>
   *   PartitionSpec(
   *     partitionColumns = StructType(
   *       StructField(name = "a", dataType = IntegerType, nullable = true),
   *       StructField(name = "b", dataType = StringType, nullable = true),
   *       StructField(name = "c", dataType = DoubleType, nullable = true)),
   *     partitions = Seq(
   *       Partition(
   *         values = Row(1, "hello", 3.14),
   *         path = "hdfs://&lt;host&gt;:&lt;port&gt;/path/to/partition/a=1/b=hello/c=3.14"),
   *       Partition(
   *         values = Row(2, "world", 6.28),
   *         path = "hdfs://&lt;host&gt;:&lt;port&gt;/path/to/partition/a=2/b=world/c=6.28")))
   * </code></pre>
   * @param paths (undocumented)
   * @param typeInference (undocumented)
   * @param basePaths (undocumented)
   * @param userSpecifiedSchema (undocumented)
   * @param caseSensitive (undocumented)
   * @param validatePartitionColumns (undocumented)
   * @param timeZoneId (undocumented)
   * @return (undocumented)
   */
  public  org.apache.spark.sql.delta.util.PartitionSpec parsePartitions (scala.collection.Seq<org.apache.hadoop.fs.Path> paths, boolean typeInference, scala.collection.immutable.Set<org.apache.hadoop.fs.Path> basePaths, scala.Option<org.apache.spark.sql.types.StructType> userSpecifiedSchema, boolean caseSensitive, boolean validatePartitionColumns, java.lang.String timeZoneId)  { throw new RuntimeException(); }
  public  org.apache.spark.sql.delta.util.PartitionSpec parsePartitions (scala.collection.Seq<org.apache.hadoop.fs.Path> paths, boolean typeInference, scala.collection.immutable.Set<org.apache.hadoop.fs.Path> basePaths, scala.Option<org.apache.spark.sql.types.StructType> userSpecifiedSchema, boolean caseSensitive, boolean validatePartitionColumns, java.util.TimeZone timeZone)  { throw new RuntimeException(); }
  /**
   * Parses a single partition, returns column names and values of each partition column, also
   * the path when we stop partition discovery.  For example, given:
   * <pre><code>
   *   path = hdfs://&lt;host&gt;:&lt;port&gt;/path/to/partition/a=42/b=hello/c=3.14
   * </code></pre>
   * it returns the partition:
   * <pre><code>
   *   PartitionValues(
   *     Seq("a", "b", "c"),
   *     Seq(
   *       Literal.create(42, IntegerType),
   *       Literal.create("hello", StringType),
   *       Literal.create(3.14, DoubleType)))
   * </code></pre>
   * and the path when we stop the discovery is:
   * <pre><code>
   *   hdfs://&lt;host&gt;:&lt;port&gt;/path/to/partition
   * </code></pre>
   * @param path (undocumented)
   * @param typeInference (undocumented)
   * @param basePaths (undocumented)
   * @param userSpecifiedDataTypes (undocumented)
   * @param validatePartitionColumns (undocumented)
   * @param timeZone (undocumented)
   * @param dateFormatter (undocumented)
   * @param timestampFormatter (undocumented)
   * @return (undocumented)
   */
  public  scala.Tuple2<scala.Option<org.apache.spark.sql.delta.util.PartitionUtils.PartitionValues>, scala.Option<org.apache.hadoop.fs.Path>> parsePartition (org.apache.hadoop.fs.Path path, boolean typeInference, scala.collection.immutable.Set<org.apache.hadoop.fs.Path> basePaths, scala.collection.immutable.Map<java.lang.String, org.apache.spark.sql.types.DataType> userSpecifiedDataTypes, boolean validatePartitionColumns, java.util.TimeZone timeZone, org.apache.spark.sql.delta.util.DateFormatter dateFormatter, org.apache.spark.sql.delta.util.TimestampFormatter timestampFormatter)  { throw new RuntimeException(); }
  /**
   * Given a partition path fragment, e.g. <code>fieldOne=1/fieldTwo=2</code>, returns a parsed spec
   * for that fragment as a <code>TablePartitionSpec</code>, e.g. <code>Map(("fieldOne", "1"), ("fieldTwo", "2"))</code>.
   * @param pathFragment (undocumented)
   * @return (undocumented)
   */
  public  scala.collection.immutable.Map<java.lang.String, java.lang.String> parsePathFragment (java.lang.String pathFragment)  { throw new RuntimeException(); }
  /**
   * Given a partition path fragment, e.g. <code>fieldOne=1/fieldTwo=2</code>, returns a parsed spec
   * for that fragment as a <code>Seq[(String, String)]</code>, e.g.
   * <code>Seq(("fieldOne", "1"), ("fieldTwo", "2"))</code>.
   * @param pathFragment (undocumented)
   * @return (undocumented)
   */
  public  scala.collection.Seq<scala.Tuple2<java.lang.String, java.lang.String>> parsePathFragmentAsSeq (java.lang.String pathFragment)  { throw new RuntimeException(); }
  /**
   * This is the inverse of parsePathFragment().
   * @param spec (undocumented)
   * @param partitionSchema (undocumented)
   * @return (undocumented)
   */
  public  java.lang.String getPathFragment (scala.collection.immutable.Map<java.lang.String, java.lang.String> spec, org.apache.spark.sql.types.StructType partitionSchema)  { throw new RuntimeException(); }
  public  java.lang.String getPathFragment (scala.collection.immutable.Map<java.lang.String, java.lang.String> spec, scala.collection.Seq<org.apache.spark.sql.catalyst.expressions.Attribute> partitionColumns)  { throw new RuntimeException(); }
  /**
   * Normalize the column names in partition specification, w.r.t. the real partition column names
   * and case sensitivity. e.g., if the partition spec has a column named <code>monTh</code>, and there is a
   * partition column named <code>month</code>, and it's case insensitive, we will normalize <code>monTh</code> to
   * <code>month</code>.
   * @param partitionSpec (undocumented)
   * @param partColNames (undocumented)
   * @param tblName (undocumented)
   * @param resolver (undocumented)
   * @return (undocumented)
   */
  public <T extends java.lang.Object> scala.collection.immutable.Map<java.lang.String, T> normalizePartitionSpec (scala.collection.immutable.Map<java.lang.String, T> partitionSpec, scala.collection.Seq<java.lang.String> partColNames, java.lang.String tblName, scala.Function2<java.lang.String, java.lang.String, java.lang.Object> resolver)  { throw new RuntimeException(); }
  /**
   * Resolves possible type conflicts between partitions by up-casting "lower" types using
   * {@link findWiderTypeForPartitionColumn}.
   * @param pathsWithPartitionValues (undocumented)
   * @param caseSensitive (undocumented)
   * @param timeZone (undocumented)
   * @return (undocumented)
   */
  public  scala.collection.Seq<org.apache.spark.sql.delta.util.PartitionUtils.PartitionValues> resolvePartitions (scala.collection.Seq<scala.Tuple2<org.apache.hadoop.fs.Path, org.apache.spark.sql.delta.util.PartitionUtils.PartitionValues>> pathsWithPartitionValues, boolean caseSensitive, java.util.TimeZone timeZone)  { throw new RuntimeException(); }
  public  java.lang.String listConflictingPartitionColumns (scala.collection.Seq<scala.Tuple2<org.apache.hadoop.fs.Path, org.apache.spark.sql.delta.util.PartitionUtils.PartitionValues>> pathWithPartitionValues)  { throw new RuntimeException(); }
  /**
   * Converts a string to a {@link Literal} with automatic type inference. Currently only supports
   * {@link NullType}, {@link IntegerType}, {@link LongType}, {@link DoubleType}, {@link DecimalType}, {@link DateType}
   * {@link TimestampType}, and {@link StringType}.
   * <p>
   * When resolving conflicts, it follows the table below:
   * <p>
   * +--------------------+-------------------+-------------------+-------------------+--------------------+------------+---------------+---------------+------------+
   * | InputA \ InputB    | NullType          | IntegerType       | LongType          | DecimalType(38,0)* | DoubleType | DateType      | TimestampType | StringType |
   * +--------------------+-------------------+-------------------+-------------------+--------------------+------------+---------------+---------------+------------+
   * | NullType           | NullType          | IntegerType       | LongType          | DecimalType(38,0)  | DoubleType | DateType      | TimestampType | StringType |
   * | IntegerType        | IntegerType       | IntegerType       | LongType          | DecimalType(38,0)  | DoubleType | StringType    | StringType    | StringType |
   * | LongType           | LongType          | LongType          | LongType          | DecimalType(38,0)  | StringType | StringType    | StringType    | StringType |
   * | DecimalType(38,0)* | DecimalType(38,0) | DecimalType(38,0) | DecimalType(38,0) | DecimalType(38,0)  | StringType | StringType    | StringType    | StringType |
   * | DoubleType         | DoubleType        | DoubleType        | StringType        | StringType         | DoubleType | StringType    | StringType    | StringType |
   * | DateType           | DateType          | StringType        | StringType        | StringType         | StringType | DateType      | TimestampType | StringType |
   * | TimestampType      | TimestampType     | StringType        | StringType        | StringType         | StringType | TimestampType | TimestampType | StringType |
   * | StringType         | StringType        | StringType        | StringType        | StringType         | StringType | StringType    | StringType    | StringType |
   * +--------------------+-------------------+-------------------+-------------------+--------------------+------------+---------------+---------------+------------+
   * Note that, for DecimalType(38,0)*, the table above intentionally does not cover all other
   * combinations of scales and precisions because currently we only infer decimal type like
   * <code>BigInteger</code>/<code>BigInt</code>. For example, 1.1 is inferred as double type.
   * @param raw (undocumented)
   * @param typeInference (undocumented)
   * @param timeZone (undocumented)
   * @param dateFormatter (undocumented)
   * @param timestampFormatter (undocumented)
   * @return (undocumented)
   */
  public  org.apache.spark.sql.catalyst.expressions.Literal inferPartitionColumnValue (java.lang.String raw, boolean typeInference, java.util.TimeZone timeZone, org.apache.spark.sql.delta.util.DateFormatter dateFormatter, org.apache.spark.sql.delta.util.TimestampFormatter timestampFormatter)  { throw new RuntimeException(); }
  public  void validatePartitionColumn (org.apache.spark.sql.types.StructType schema, scala.collection.Seq<java.lang.String> partitionColumns, boolean caseSensitive)  { throw new RuntimeException(); }
  public  org.apache.spark.sql.types.StructType partitionColumnsSchema (org.apache.spark.sql.types.StructType schema, scala.collection.Seq<java.lang.String> partitionColumns, boolean caseSensitive)  { throw new RuntimeException(); }
  public  scala.Tuple2<org.apache.spark.sql.types.StructType, scala.collection.immutable.Map<java.lang.String, org.apache.spark.sql.types.StructField>> mergeDataAndPartitionSchema (org.apache.spark.sql.types.StructType dataSchema, org.apache.spark.sql.types.StructType partitionSchema, boolean caseSensitive)  { throw new RuntimeException(); }
  public  java.lang.String getColName (org.apache.spark.sql.types.StructField f, boolean caseSensitive)  { throw new RuntimeException(); }
  /**
   * Checks if input column names have duplicate identifiers. This throws an exception if
   * the duplication exists.
   * <p>
   * @param columnNames column names to check
   * @param colType column type name, used in an exception message
   * @param resolver resolver used to determine if two identifiers are equal
   */
  public  void checkColumnNameDuplication (scala.collection.Seq<java.lang.String> columnNames, java.lang.String colType, scala.Function2<java.lang.String, java.lang.String, java.lang.Object> resolver)  { throw new RuntimeException(); }
  /**
   * Checks if input column names have duplicate identifiers. This throws an exception if
   * the duplication exists.
   * <p>
   * @param columnNames column names to check
   * @param colType column type name, used in an exception message
   * @param caseSensitiveAnalysis whether duplication checks should be case sensitive or not
   */
  public  void checkColumnNameDuplication (scala.collection.Seq<java.lang.String> columnNames, java.lang.String colType, boolean caseSensitiveAnalysis)  { throw new RuntimeException(); }
}
