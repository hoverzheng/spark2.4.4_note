package org.apache.spark.sql.delta.storage;
/**
 * General interface for all critical file system operations required to read and write the
 * {@link DeltaLog}. The correctness of the {@link DeltaLog} is predicated on the atomicity and
 * durability guarantees of the implementation of this interface. Specifically,
 * <p>
 * 1. Atomic visibility of files: Any file written through this store must
 *    be made visible atomically. In other words, this should not generate partial files.
 * <p>
 * 2. Mutual exclusion: Only one writer must be able to create (or rename) a file at the final
 *    destination.
 * <p>
 * 3. Consistent listing: Once a file has been written in a directory, all future listings for
 *    that directory must return that file.
 */
public  interface LogStore {
  /**
   * Load the given file and return a <code>Seq</code> of lines. The line break will be removed from each
   * line. This method will load the entire file into the memory. Call <code>readAsIterator</code> if possible
   * as its implementation may be more efficient.
   * @param path (undocumented)
   * @return (undocumented)
   */
  public  scala.collection.Seq<java.lang.String> read (java.lang.String path)  ;
  /**
   * Load the given file and return a <code>Seq</code> of lines. The line break will be removed from each
   * line. This method will load the entire file into the memory. Call <code>readAsIterator</code> if possible
   * as its implementation may be more efficient.
   * @param path (undocumented)
   * @return (undocumented)
   */
  public  scala.collection.Seq<java.lang.String> read (org.apache.hadoop.fs.Path path)  ;
  /**
   * Load the given file and return an iterator of lines. The line break will be removed from each
   * line. The default implementation calls <code>read</code> to load the entire file into the memory.
   * An implementation should provide a more efficient approach if possible. For example, the file
   * content can be loaded on demand.
   * @param path (undocumented)
   * @return (undocumented)
   */
  public  org.apache.spark.sql.delta.storage.ClosableIterator<java.lang.String> readAsIterator (java.lang.String path)  ;
  /**
   * Load the given file and return an iterator of lines. The line break will be removed from each
   * line. The default implementation calls <code>read</code> to load the entire file into the memory.
   * An implementation should provide a more efficient approach if possible. For example, the file
   * content can be loaded on demand.
   * <p>
   * Note: the returned {@link ClosableIterator} should be closed when it's no longer used to avoid
   * resource leak.
   * @param path (undocumented)
   * @return (undocumented)
   */
  public  org.apache.spark.sql.delta.storage.ClosableIterator<java.lang.String> readAsIterator (org.apache.hadoop.fs.Path path)  ;
  /**
   * Write the given <code>actions</code> to the given <code>path</code> without overwriting any existing file.
   * Implementation must throw {@link java.nio.file.FileAlreadyExistsException} exception if the file
   * already exists. Furthermore, implementation must ensure that the entire file is made
   * visible atomically, that is, it should not generate partial files.
   * @param path (undocumented)
   * @param actions (undocumented)
   */
  public  void write (java.lang.String path, scala.collection.Iterator<java.lang.String> actions)  ;
  /**
   * Write the given <code>actions</code> to the given <code>path</code> with or without overwrite as indicated.
   * Implementation must throw {@link java.nio.file.FileAlreadyExistsException} exception if the file
   * already exists and overwrite = false. Furthermore, implementation must ensure that the
   * entire file is made visible atomically, that is, it should not generate partial files.
   * @param path (undocumented)
   * @param actions (undocumented)
   * @param overwrite (undocumented)
   */
  public  void write (org.apache.hadoop.fs.Path path, scala.collection.Iterator<java.lang.String> actions, boolean overwrite)  ;
  /**
   * List the paths in the same directory that are lexicographically greater or equal to
   * (UTF-8 sorting) the given <code>path</code>. The result should also be sorted by the file name.
   * @param path (undocumented)
   * @return (undocumented)
   */
  public  scala.collection.Iterator<org.apache.hadoop.fs.FileStatus> listFrom (java.lang.String path)  ;
  /**
   * List the paths in the same directory that are lexicographically greater or equal to
   * (UTF-8 sorting) the given <code>path</code>. The result should also be sorted by the file name.
   * @param path (undocumented)
   * @return (undocumented)
   */
  public  scala.collection.Iterator<org.apache.hadoop.fs.FileStatus> listFrom (org.apache.hadoop.fs.Path path)  ;
  /** Invalidate any caching that the implementation may be using */
  public  void invalidateCache ()  ;
  /** Resolve the fully qualified path for the given `path`. */
  public  org.apache.hadoop.fs.Path resolvePathOnPhysicalStorage (org.apache.hadoop.fs.Path path)  ;
  /**
   * Whether a partial write is visible when writing to <code>path</code>.
   * <p>
   * As this depends on the underlying file system implementations, we require the input of <code>path</code>
   * here in order to identify the underlying file system, even though in most cases a log store
   * only deals with one file system.
   * <p>
   * The default value is only provided here for legacy reasons, which will be removed.
   * Any LogStore implementation should override this instead of relying on the default.
   * @param path (undocumented)
   * @return (undocumented)
   */
  public  boolean isPartialWriteVisible (org.apache.hadoop.fs.Path path)  ;
}
