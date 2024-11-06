package com.finseta.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.finseta.model.Account;
import java.math.BigDecimal;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Payment
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-11-06T08:04:20.125272500Z[Europe/London]", comments = "Generator version: 7.8.0")
public class Payment {

  private String currency;

  private BigDecimal amount;

  private Account counterparty;

  public Payment() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Payment(String currency, BigDecimal amount, Account counterparty) {
    this.currency = currency;
    this.amount = amount;
    this.counterparty = counterparty;
  }

  public Payment currency(String currency) {
    this.currency = currency;
    return this;
  }

  /**
   * The three letter ISO 4217 code
   * @return currency
   */
  @NotNull @Size(min = 3, max = 3) 
  @Schema(name = "currency", example = "GBP", description = "The three letter ISO 4217 code", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("currency")
  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public Payment amount(BigDecimal amount) {
    this.amount = amount;
    return this;
  }

  /**
   * Get amount
   * @return amount
   */
  @NotNull @Valid 
  @Schema(name = "amount", example = "10.0", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("amount")
  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public Payment counterparty(Account counterparty) {
    this.counterparty = counterparty;
    return this;
  }

  /**
   * Get counterparty
   * @return counterparty
   */
  @NotNull @Valid 
  @Schema(name = "counterparty", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("counterparty")
  public Account getCounterparty() {
    return counterparty;
  }

  public void setCounterparty(Account counterparty) {
    this.counterparty = counterparty;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Payment payment = (Payment) o;
    return Objects.equals(this.currency, payment.currency) &&
        Objects.equals(this.amount, payment.amount) &&
        Objects.equals(this.counterparty, payment.counterparty);
  }

  @Override
  public int hashCode() {
    return Objects.hash(currency, amount, counterparty);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Payment {\n");
    sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    counterparty: ").append(toIndentedString(counterparty)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

