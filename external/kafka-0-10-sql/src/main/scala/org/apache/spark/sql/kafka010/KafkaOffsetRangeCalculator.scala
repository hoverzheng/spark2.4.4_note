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

package org.apache.spark.sql.kafka010

import org.apache.kafka.common.TopicPartition

import org.apache.spark.sql.sources.v2.DataSourceOptions


/**
 * Class to calculate offset ranges to process based on the the from and until offsets, and
 * the configured `minPartitions`.
 */
// 计算offset的范围，基于offset和配置的minPartitions的值。
private[kafka010] class KafkaOffsetRangeCalculator(val minPartitions: Option[Int]) {
  require(minPartitions.isEmpty || minPartitions.get > 0)

  /**
   * Calculate the offset ranges that we are going to process this batch. If `minPartitions`
   * is not set or is set less than or equal the number of `topicPartitions` that we're going to
   * consume, then we fall back to a 1-1 mapping of Spark tasks to Kafka partitions. If
   * `numPartitions` is set higher than the number of our `topicPartitions`, then we will split up
   * the read tasks of the skewed partitions to multiple Spark tasks.
   * The number of Spark tasks will be *approximately* `numPartitions`. It can be less or more
   * depending on rounding errors or Kafka partitions that didn't receive any new data.
   *
   * Empty ranges (`KafkaOffsetRange.size <= 0`) will be dropped.
   */
  // 计算本批次需要处理的offset范围。若minPartitions没有被设置，或则<=topicPartitions，
  // 将会开始消费，那么我们会对每个kafka的分区创建一个map任务。
  // 若minPartitions > topicPartitions，则将会把倾斜的分区拆分成多个spark任务。
  // spark的任务数，大约等于：numPartitions数。
  // 但有可能小于或大于这个数，这个决定于是否发生错误，或没有接收到任何新数据的kafka分区。
  def getRanges(
      fromOffsets: PartitionOffsetMap,
      untilOffsets: PartitionOffsetMap,
      executorLocations: Seq[String] = Seq.empty): Seq[KafkaOffsetRange] = {
    val partitionsToRead = untilOffsets.keySet.intersect(fromOffsets.keySet)

    val offsetRanges = partitionsToRead.toSeq.map { tp =>
      KafkaOffsetRange(tp, fromOffsets(tp), untilOffsets(tp), preferredLoc = None)
    }.filter(_.size > 0)

    // If minPartitions not set or there are enough partitions to satisfy minPartitions
    if (minPartitions.isEmpty || offsetRanges.size > minPartitions.get) {
      // Assign preferred executor locations to each range such that the same topic-partition is
      // preferentially read from the same executor and the KafkaConsumer can be reused.
      offsetRanges.map { range =>
        range.copy(preferredLoc = getLocation(range.topicPartition, executorLocations))
      }
    } else {

      // Splits offset ranges with relatively large amount of data to smaller ones.
      val totalSize = offsetRanges.map(_.size).sum
      offsetRanges.flatMap { range =>
        val tp = range.topicPartition
        val size = range.size
        // number of partitions to divvy up this topic partition to
        val parts = math.max(math.round(size.toDouble / totalSize * minPartitions.get), 1).toInt
        var remaining = size
        var startOffset = range.fromOffset
        (0 until parts).map { part =>
          // Fine to do integer division. Last partition will consume all the round off errors
          val thisPartition = remaining / (parts - part)
          remaining -= thisPartition
          val endOffset = math.min(startOffset + thisPartition, range.untilOffset)
          val offsetRange = KafkaOffsetRange(tp, startOffset, endOffset, None)
          startOffset = endOffset
          offsetRange
        }
      }.filter(_.size > 0)
    }
  }

  private def getLocation(tp: TopicPartition, executorLocations: Seq[String]): Option[String] = {
    def floorMod(a: Long, b: Int): Int = ((a % b).toInt + b) % b

    val numExecutors = executorLocations.length
    if (numExecutors > 0) {
      // This allows cached KafkaConsumers in the executors to be re-used to read the same
      // partition in every batch.
      Some(executorLocations(floorMod(tp.hashCode, numExecutors)))
    } else None
  }
}

private[kafka010] object KafkaOffsetRangeCalculator {

  def apply(options: DataSourceOptions): KafkaOffsetRangeCalculator = {
    val optionalValue = Option(options.get("minPartitions").orElse(null)).map(_.toInt)
    new KafkaOffsetRangeCalculator(optionalValue)
  }
}

private[kafka010] case class KafkaOffsetRange(
    topicPartition: TopicPartition,
    fromOffset: Long,
    untilOffset: Long,
    preferredLoc: Option[String]) {
  lazy val size: Long = untilOffset - fromOffset
}
