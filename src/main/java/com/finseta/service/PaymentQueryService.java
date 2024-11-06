package com.finseta.service;

import java.math.BigDecimal;
import java.util.List;

import com.finseta.model.Payment;

public interface PaymentQueryService {

	public List<Payment> getPayments(BigDecimal minAmount, List<String> currencies);

}
