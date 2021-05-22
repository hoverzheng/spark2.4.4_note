package org.apache.spark.sql.delta.schema;
public  class SchemaUtils {
  static public  scala.Function2<java.lang.String, java.lang.String, java.lang.Object> DELTA_COL_RESOLVER ()  { throw new RuntimeException(); }
  /**
   * Finds <code>StructField</code>s that match a given check <code>f</code>. Returns the path to the column, and the
   * field.
   * <p>
   * @param checkComplexTypes While <code>StructType</code> is also a complex type, since we're returning
   *                          StructFields, we definitely recurse into StructTypes. This flag
   *                          defines whether we should recurse into ArrayType and MapType.
   * @param schema (undocumented)
   * @param f (undocumented)
   * @return (undocumented)
   */
  static public  scala.collection.Seq<scala.Tuple2<scala.collection.Seq<java.lang.String>, org.apache.spark.sql.types.StructField>> filterRecursively (org.apache.spark.sql.types.StructType schema, boolean checkComplexTypes, scala.Function1<org.apache.spark.sql.types.StructField, java.lang.Object> f)  { throw new RuntimeException(); }
  /** Copied over from DataType for visibility reasons. */
  static public  boolean typeExistsRecursively (org.apache.spark.sql.types.DataType dt, scala.Function1<org.apache.spark.sql.types.DataType, java.lang.Object> f)  { throw new RuntimeException(); }
  /** Turns the data types to nullable in a recursive manner for nested columns. */
  static public  org.apache.spark.sql.types.DataType typeAsNullable (org.apache.spark.sql.types.DataType dt)  { throw new RuntimeException(); }
  /**
   * Drops null types from the DataFrame if they exist. We don't have easy ways of generating types
   * such as MapType and ArrayType, therefore if these types contain NullType in their elements,
   * we will throw an AnalysisException.
   * @param df (undocumented)
   * @return (undocumented)
   */
  static public  org.apache.spark.sql.Dataset<org.apache.spark.sql.Row> dropNullTypeColumns (org.apache.spark.sql.Dataset<org.apache.spark.sql.Row> df)  { throw new RuntimeException(); }
  /**
   * Returns all column names in this schema as a flat list. For example, a schema like:
   *   | - a
   *   | | - 1
   *   | | - 2
   *   | - b
   *   | - c
   *   | | - nest
   *   |   | - 3
   *   will get flattened to: "a", "a.1", "a.2", "b", "c", "c.nest", "c.nest.3"
   * @param schema (undocumented)
   * @return (undocumented)
   */
  static public  scala.collection.Seq<java.lang.String> explodeNestedFieldNames (org.apache.spark.sql.types.StructType schema)  { throw new RuntimeException(); }
  /**
   * Checks if input column names have duplicate identifiers. This throws an exception if
   * the duplication exists.
   * <p>
   * @param schema the schema to check for duplicates
   * @param colType column type name, used in an exception message
   */
  static public  void checkColumnNameDuplication (org.apache.spark.sql.types.StructType schema, java.lang.String colType)  { throw new RuntimeException(); }
  /**
   * Rewrite the query field names according to the table schema. This method assumes that all
   * schema validation checks have been made and this is the last operation before writing into
   * Delta.
   * @param baseSchema (undocumented)
   * @param data (undocumented)
   * @return (undocumented)
   */
  static public  org.apache.spark.sql.Dataset<org.apache.spark.sql.Row> normalizeColumnNames (org.apache.spark.sql.types.StructType baseSchema, org.apache.spark.sql.Dataset<?> data)  { throw new RuntimeException(); }
  /**
   * As the Delta snapshots update, the schema may change as well. This method defines whether the
   * new schema of a Delta table can be used with a previously analyzed LogicalPlan. Our
   * rules are to return false if:
   *   - Dropping any column that was present in the DataFrame schema
   *   - Converting nullable=false to nullable=true for any column
   *   - Any change of datatype
   * @param existingSchema (undocumented)
   * @param readSchema (undocumented)
   * @return (undocumented)
   */
  static public  boolean isReadCompatible (org.apache.spark.sql.types.StructType existingSchema, org.apache.spark.sql.types.StructType readSchema)  { throw new RuntimeException(); }
  /**
   * Compare an existing schema to a specified new schema and
   * return a message describing the first difference found, if any:
   *   - different field name or datatype
   *   - different metadata
   * @param existingSchema (undocumented)
   * @param specifiedSchema (undocumented)
   * @return (undocumented)
   */
  static public  scala.collection.Seq<java.lang.String> reportDifferences (org.apache.spark.sql.types.StructType existingSchema, org.apache.spark.sql.types.StructType specifiedSchema)  { throw new RuntimeException(); }
  /**
   * Returns the given column's ordinal within the given <code>schema</code> and the size of the last schema
   * size. The length of the returned position will be as long as how nested the column is.
   * <p>
   * For ArrayType: accessing the array's element adds a position 0 to the position list.
   * e.g. accessing a.element.y would have the result -> Seq(..., positionOfA, 0, positionOfY)
   * <p>
   * For MapType: accessing the map's key adds a position 0 to the position list.
   * e.g. accessing m.key.y would have the result -> Seq(..., positionOfM, 0, positionOfY)
   * <p>
   * For MapType: accessing the map's value adds a position 1 to the position list.
   * e.g. accessing m.key.y would have the result -> Seq(..., positionOfM, 1, positionOfY)
   * <p>
   * @param column The column to search for in the given struct. If the length of <code>column</code> is
   *               greater than 1, we expect to enter a nested field.
   * @param schema The current struct we are looking at.
   * @param resolver The resolver to find the column.
   * @return (undocumented)
   */
  static public  scala.Tuple2<scala.collection.Seq<java.lang.Object>, java.lang.Object> findColumnPosition (scala.collection.Seq<java.lang.String> column, org.apache.spark.sql.types.StructType schema, scala.Function2<java.lang.String, java.lang.String, java.lang.Object> resolver)  { throw new RuntimeException(); }
  /**
   * Pretty print the column path passed in.
   * @param columnPath (undocumented)
   * @return (undocumented)
   */
  static public  java.lang.String prettyFieldName (scala.collection.Seq<java.lang.String> columnPath)  { throw new RuntimeException(); }
  /**
   * Add <code>column</code> to the specified <code>position</code> in <code>schema</code>.
   * @param position A Seq of ordinals on where this column should go. It is a Seq to denote
   *                 positions in nested columns (0-based). For example:
   * <p>
   *                 tableSchema: <a:STRUCT<a1,a2,a3>, b,c:STRUCT<c1,c3>>
   *                 column: c2
   *                 position: Seq(2, 1)
   *                 will return
   *                 result: <a:STRUCT<a1,a2,a3>, b,c:STRUCT<c1,**c2**,c3>>
   * @param schema (undocumented)
   * @param column (undocumented)
   * @return (undocumented)
   */
  static public  org.apache.spark.sql.types.StructType addColumn (org.apache.spark.sql.types.StructType schema, org.apache.spark.sql.types.StructField column, scala.collection.Seq<java.lang.Object> position)  { throw new RuntimeException(); }
  /**
   * Drop from the specified <code>position</code> in <code>schema</code> and return with the original column.
   * @param position A Seq of ordinals on where this column should go. It is a Seq to denote
   *                 positions in nested columns (0-based). For example:
   * <p>
   *                 tableSchema: <a:STRUCT<a1,a2,a3>, b,c:STRUCT<c1,c2,c3>>
   *                 position: Seq(2, 1)
   *                 will return
   *                 result: <a:STRUCT<a1,a2,a3>, b,c:STRUCT<c1,c3>>
   * @param schema (undocumented)
   * @return (undocumented)
   */
  static public  scala.Tuple2<org.apache.spark.sql.types.StructType, org.apache.spark.sql.types.StructField> dropColumn (org.apache.spark.sql.types.StructType schema, scala.collection.Seq<java.lang.Object> position)  { throw new RuntimeException(); }
  /**
   * Check if the two data types can be changed.
   * <p>
   * @return None if the data types can be changed, otherwise Some(err) containing the reason.
   * @param from (undocumented)
   * @param to (undocumented)
   * @param resolver (undocumented)
   * @param columnPath (undocumented)
   */
  static public  scala.Option<java.lang.String> canChangeDataType (org.apache.spark.sql.types.DataType from, org.apache.spark.sql.types.DataType to, scala.Function2<java.lang.String, java.lang.String, java.lang.Object> resolver, scala.collection.Seq<java.lang.String> columnPath)  { throw new RuntimeException(); }
  /**
   * Copy the nested data type between two data types.
   * @param from (undocumented)
   * @param to (undocumented)
   * @param resolver (undocumented)
   * @return (undocumented)
   */
  static public  org.apache.spark.sql.types.DataType changeDataType (org.apache.spark.sql.types.DataType from, org.apache.spark.sql.types.DataType to, scala.Function2<java.lang.String, java.lang.String, java.lang.Object> resolver)  { throw new RuntimeException(); }
  /**
   * Check whether we can write to the Delta table, which has <code>tableSchema</code>, using a query that has
   * <code>dataSchema</code>. Our rules are that:
   *   - <code>dataSchema</code> may be missing columns or have additional columns
   *   - We don't trust the nullability in <code>dataSchema</code>. Assume fields are nullable.
   *   - We only allow nested StructType expansions. For all other complex types, we check for
   *     strict equality
   *   - <code>dataSchema</code> can't have duplicate column names. Columns that only differ by case are also
   *     not allowed.
   * The following merging strategy is
   * applied:
   *  - The name of the current field is used.
   *  - The data types are merged by calling this function.
   *  - We respect the current field's nullability.
   *  - The metadata is current field's metadata.
   * <p>
   * Schema merging occurs in a case insensitive manner. Hence, column names that only differ
   * by case are not accepted in the <code>dataSchema</code>.
   * @param tableSchema (undocumented)
   * @param dataSchema (undocumented)
   * @return (undocumented)
   */
  static public  org.apache.spark.sql.types.StructType mergeSchemas (org.apache.spark.sql.types.StructType tableSchema, org.apache.spark.sql.types.StructType dataSchema)  { throw new RuntimeException(); }
  /**
   * Transform (nested) columns in a schema.
   * <p>
   * @param schema to transform.
   * @param tf function to apply.
   * @return the transformed schema.
   */
  static public  org.apache.spark.sql.types.StructType transformColumns (org.apache.spark.sql.types.StructType schema, scala.Function3<scala.collection.Seq<java.lang.String>, org.apache.spark.sql.types.StructField, scala.Function2<java.lang.String, java.lang.String, java.lang.Object>, org.apache.spark.sql.types.StructField> tf)  { throw new RuntimeException(); }
  /**
   * Transform (nested) columns in a schema. Runs the transform function on all nested StructTypes
   * <p>
   * @param schema to transform.
   * @param tf function to apply on the StructType.
   * @return the transformed schema.
   * @param colName (undocumented)
   */
  static public  org.apache.spark.sql.types.StructType transformColumnsStructs (org.apache.spark.sql.types.StructType schema, java.lang.String colName, scala.Function3<scala.collection.Seq<java.lang.String>, org.apache.spark.sql.types.StructType, scala.Function2<java.lang.String, java.lang.String, java.lang.Object>, scala.collection.Seq<org.apache.spark.sql.types.StructField>> tf)  { throw new RuntimeException(); }
  /**
   * Transform (nested) columns in a schema using the given path and parameter pairs. The transform
   * function is only invoked when a field's path matches one of the input paths.
   * <p>
   * @param schema to transform
   * @param input paths and parameter pairs. The paths point to fields we want to transform. The
   *              parameters will be passed to the transform function for a matching field.
   * @param tf function to apply per matched field. This function takes the field path, the field
   *           itself and the input names and payload pairs that matched the field name. It should
   *           return a new field.
   * @tparam E the type of the payload used for transforming fields.
   * @return the transformed schema.
   */
  static public <E extends java.lang.Object> org.apache.spark.sql.types.StructType transformColumns (org.apache.spark.sql.types.StructType schema, scala.collection.Seq<scala.Tuple2<scala.collection.Seq<java.lang.String>, E>> input, scala.Function3<scala.collection.Seq<java.lang.String>, org.apache.spark.sql.types.StructField, scala.collection.Seq<scala.Tuple2<scala.collection.Seq<java.lang.String>, E>>, org.apache.spark.sql.types.StructField> tf)  { throw new RuntimeException(); }
  /**
   * Verifies that the column names are acceptable by Parquet and henceforth Delta. Parquet doesn't
   * accept the characters ' ,;{}()\n\t'. We ensure that neither the data columns nor the partition
   * columns have these characters.
   * @param names (undocumented)
   */
  static public  void checkFieldNames (scala.collection.Seq<java.lang.String> names)  { throw new RuntimeException(); }
  /**
   * Go through the schema to look for unenforceable NOT NULL constraints. By default we'll throw
   * when they're encountered, but if this is suppressed through SQLConf they'll just be silently
   * removed.
   * <p>
   * Note that this should only be applied to schemas created from explicit user DDL - in other
   * scenarios, the nullability information may be inaccurate and Delta should always coerce the
   * nullability flag to true.
   * @param schema (undocumented)
   * @param conf (undocumented)
   * @return (undocumented)
   */
  static public  org.apache.spark.sql.types.StructType removeUnenforceableNotNullConstraints (org.apache.spark.sql.types.StructType schema, org.apache.spark.sql.internal.SQLConf conf)  { throw new RuntimeException(); }
}
