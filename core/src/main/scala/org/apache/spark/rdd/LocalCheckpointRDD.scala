/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.spark.rdd

import scala.reflect.ClassTag

import org.apache.spark.{Partition, SparkContext, SparkException, TaskContext}
import org.apache.spark.storage.RDDBlockId

/**
 * A dummy CheckpointRDD that exists to provide informative error messages during failures.
 *
 * This is simply a placeholder because the original checkpointed RDD is expected to be
 * fully cached. Only if an executor fails or if the user explicitly unpersists the original
 * RDD will Spark ever attempt to compute this CheckpointRDD. When this happens, however,
 * we must provide an informative error message.
 *
 * @param sc the active SparkContext
 * @param rddId the ID of the checkpointed RDD
 * @param numPartitions the number of partitions in the checkpointed RDD
 */
// 这是一个简单的占位符，因为原始被checkpointed的RDD期望被完全的cache。
// 仅当executor失败或用户明确unpersists时，Spark才会尝试计算该CheckpointRDD。
// (1)sc是SparkContext；(2)rddID是被checkpointed的RDD的Id；(3)numPartitions是checkpointed RDD的分区数。
private[spark] class LocalCheckpointRDD[T: ClassTag](
    sc: SparkContext,
    rddId: Int,
    numPartitions: Int)
  extends CheckpointRDD[T](sc) {

  def this(rdd: RDD[T]) {
    this(rdd.context, rdd.id, rdd.partitions.length)
  }

  // 获取该RDD的分区标识，这是一个数组
  protected override def getPartitions: Array[Partition] = {
    (0 until numPartitions).toArray.map { i => new CheckpointRDDPartition(i) }
  }

  /**
   * Throw an exception indicating that the relevant block is not found.
   *
   * This should only be called if the original RDD is explicitly unpersisted or if an
   * executor is lost. Under normal circumstances, however, the original RDD (our child)
   * is expected to be fully cached and so all partitions should already be computed and
   * available in the block storage.
   */
  // 对于LocalCheckpointRDD来说，该方法在executor失败和明确调用unpersist后才会被调用。
  // todo: xh??
  override def compute(partition: Partition, context: TaskContext): Iterator[T] = {
    throw new SparkException(
      s"Checkpoint block ${RDDBlockId(rddId, partition.index)} not found! Either the executor " +
      s"that originally checkpointed this partition is no longer alive, or the original RDD is " +
      s"unpersisted. If this problem persists, you may consider using `rdd.checkpoint()` " +
      s"instead, which is slower than local checkpointing but more fault-tolerant.")
  }

}
