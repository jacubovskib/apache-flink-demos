package org.example.filters;

import org.apache.flink.api.common.functions.FilterFunction;
import org.example.records.Transaction;

public class SimpleFilter implements FilterFunction<Transaction> {

  @Override
  public boolean filter(Transaction event) throws Exception {
    return event.getAmount().floatValue() > 10.0;
  }
}
