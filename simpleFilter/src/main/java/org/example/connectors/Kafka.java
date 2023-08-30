package org.example.connectors;

import org.apache.flink.api.common.serialization.SerializationSchema;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.connector.kafka.sink.KafkaRecordSerializationSchema;
import org.apache.flink.connector.kafka.sink.KafkaSink;
import org.apache.flink.connector.kafka.source.KafkaSource;
import org.apache.flink.connector.kafka.source.enumerator.initializer.OffsetsInitializer;
import org.example.records.Transaction;
import org.example.records.TransactionDeserializer;

public class Kafka {

  public static KafkaSource<Transaction> source(String bootstrapServer, String topic) {
    return KafkaSource.<Transaction>builder()
        .setBootstrapServers(bootstrapServer)
        .setTopics(topic)
        .setStartingOffsets(OffsetsInitializer.latest())
        .setValueOnlyDeserializer(new TransactionDeserializer())
        .build();
  }

  public static KafkaSink<Transaction> sink(String bootstrapServer, String topic) {
    return KafkaSink.<Transaction>builder()
        .setBootstrapServers(bootstrapServer)
        .setRecordSerializer(
            KafkaRecordSerializationSchema.builder()
                .setTopic(topic)
                .setValueSerializationSchema(
                    (SerializationSchema<Transaction>) el -> el.toString().getBytes()
                ).build()
        )
        .build();
  }
}
