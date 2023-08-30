package filters;

import org.example.filters.SimpleFilter;
import org.example.records.Transaction;
import org.example.records.TransactionDeserializer;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.Instant;

public class TestSimpleFilter {

  @Test
  public void TestSimpleFilterReturnTrue() throws Exception {
    SimpleFilter simpleFilter = new SimpleFilter();
    Instant instant = Instant.now();
    Transaction transaction = new Transaction(
        instant, 1L, 1L, BigDecimal.valueOf(15.00), true
    );
    var result = simpleFilter.filter(transaction);

    Assert.assertTrue(result);
  }

  @Test
  public void TestSimpleFilterReturnFalse() throws Exception {
    SimpleFilter simpleFilter = new SimpleFilter();
    Instant instant = Instant.now();
    Transaction transaction = new Transaction(
        instant, 1L, 1L, BigDecimal.valueOf(9.00), true
    );
    var result = simpleFilter.filter(transaction);
    Assert.assertFalse(result);
  }


}
