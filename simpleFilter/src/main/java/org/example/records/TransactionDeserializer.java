package org.example.records;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.flink.api.common.serialization.AbstractDeserializationSchema;

import java.io.IOException;
import java.io.Serial;

public class TransactionDeserializer extends AbstractDeserializationSchema<Transaction> {
  @Serial
  private final static long serialVersionUID = 1L;
  private transient ObjectMapper objectMapper;

  @Override
  public void open(InitializationContext context) throws Exception {
    objectMapper = JsonMapper.builder().build().registerModule(new JavaTimeModule());
  }

  @Override
  public Transaction deserialize(byte[] bytes) throws IOException {
    return objectMapper.readValue(bytes, Transaction.class);
  }
}
