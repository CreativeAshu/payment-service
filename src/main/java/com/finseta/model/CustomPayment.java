package com.finseta.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "payments")
public class CustomPayment extends Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 3)
	private String currency;

	@Column(nullable = false)
	private BigDecimal amount;

	@Embedded
	private Account counterparty;

	public CustomPayment() {
	}

	public CustomPayment(String currency, BigDecimal amount, Account counterparty) {
		this.currency = currency;
		this.amount = amount;
		this.counterparty = counterparty;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Account getCounterparty() {
		return counterparty;
	}

	public void setCounterparty(Account counterparty) {
		this.counterparty = counterparty;
	}

	public CustomPayment(Payment payment) {
		super();
		this.amount = payment.getAmount();
		this.counterparty = payment.getCounterparty();
		this.currency = payment.getCurrency();
	}

}
