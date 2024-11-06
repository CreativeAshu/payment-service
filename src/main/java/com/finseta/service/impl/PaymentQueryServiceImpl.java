package com.finseta.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finseta.model.Payment;
import com.finseta.repository.PaymentRepository;
import com.finseta.service.PaymentQueryService;

@Service
public class PaymentQueryServiceImpl implements PaymentQueryService {

	@Autowired
	private PaymentRepository paymentRepository;

	public List<Payment> getPayments(BigDecimal minAmount, List<String> currencies) {

		return paymentRepository.findByCurrencyInAndAmountGreaterThanEqual(currencies,
				minAmount != null ? minAmount : BigDecimal.ZERO);

	}
}
