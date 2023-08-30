package org.example.records;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;
@Getter
@Setter
public class Transaction {
  @JsonFormat(
      shape = JsonFormat.Shape.STRING,
      pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS",
      timezone = "UTC"
  )
  public Instant created_dt;
  public long id;
  public long customerId;
  public BigDecimal amount;
  public Boolean isValid;
  public Transaction() {}
  public Transaction(Instant created_dt, Long id, Long customerId, BigDecimal amount, Boolean isValid) {
    this.created_dt = created_dt;
    this.id = id;
    this.customerId = customerId;
    this.amount = amount;
    this.isValid = isValid;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Transaction that = (Transaction) o;
    return id == that.id &&
        customerId == that.customerId &&
        Objects.equals(created_dt, that.created_dt) &&
        Objects.equals(amount, that.amount) &&
        Objects.equals(isValid, that.isValid);
  }

  @Override
  public String toString() {
    return "Transaction{" +
        "created_dt=" + created_dt +
        ", id=" + id +
        ", customerId=" + customerId +
        ", amount=" + amount +
        ", isValid=" + isValid +
        '}';
  }

  @Override
  public int hashCode() {
    return Objects.hash(created_dt, id, customerId, amount, isValid);
  }
}
