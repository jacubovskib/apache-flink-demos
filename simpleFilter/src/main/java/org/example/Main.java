package org.example;

import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.connector.source.Source;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.connector.kafka.sink.KafkaSink;
import org.apache.flink.connector.kafka.source.KafkaSource;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.OutputTag;
import org.example.connectors.Kafka;
import org.example.filters.SimpleFilter;
import org.example.records.Transaction;

import java.util.function.Consumer;

public class Main {
  public static void main(String[] args) throws Exception {
    // recupera os parametros necessários para consumir os dados do kafka
    ParameterTool params = ParameterTool.fromArgs(args);
    runJob(params);
  }

  static void runJob(ParameterTool params) throws Exception {
    // cria o ambiente de execução
    StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

    // recupera as conifugrações relacionadas a consumir/produzir dados no Kafka
    String bootstrapServer = params.getRequired("bootstrap-server");
    String sourceTopic = params.getRequired("source-topic");
    String successSinkTopic = params.getRequired("success-sink-topic");
    String suspiciousSinkTopic = params.getRequired("suspicious-sink-topic");

    // Conector para consumir eventos de um Tópico Kafka
    KafkaSource<Transaction> source = Kafka.source(
        bootstrapServer,
        sourceTopic
    );
    // Conector para produzir eventos de um Tópico Kafka
    KafkaSink<Transaction> successSink = Kafka.sink(
        bootstrapServer,
        successSinkTopic
    );

    defineWorkflow(
        env,
        source,
        workflow -> workflow.sinkTo(successSink)
    );

    // executa a aplicação, fornecendo um nome.
    env.execute("Simple Filter Job");

  }

  static void defineWorkflow(
      StreamExecutionEnvironment env,
      Source<Transaction, ?, ?> source,
      Consumer<DataStream<Transaction>> sinkApplier
  ) {
    // DataSet que contém os dados que são consumidos do Tópico do Kafka
    DataStream<Transaction> origin =
        env.fromSource(source, WatermarkStrategy.noWatermarks(), "Kafka Transactions");

    // Aplicação do Filtro com a lógica, originando um novo DataStream
    DataStream<Transaction> dsFiltered = origin.filter(new SimpleFilter());

    //Envia para o destino o resultado do Filtro
    sinkApplier.accept(dsFiltered);
  }
}