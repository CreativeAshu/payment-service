package com.finseta.service.impl;

import java.math.BigDecimal;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finseta.exception.PaymentValidationException;
import com.finseta.model.CustomPayment;
import com.finseta.model.Payment;
import com.finseta.repository.PaymentRepository;
import com.finseta.service.PaymentCommandService;

@Service
public class PaymentCommandServiceImpl implements PaymentCommandService {

	@Autowired
	private PaymentRepository paymentRepository;

	public Payment createPayment(CustomPayment payment) {
		validatePayment(payment);
		return paymentRepository.save(payment);
	}

	private void validatePayment(Payment payment) {

		if (payment.getAmount() == null || payment.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
			throw new PaymentValidationException("Amount must be greater than 0");
		}

		if (payment.getCurrency() == null || payment.getCurrency().isEmpty()) {
			throw new PaymentValidationException("Currency is required");
		}

		if (payment.getCounterparty() == null) {
			throw new PaymentValidationException("Counterparty details are required");
		}

		String sortCode = payment.getCounterparty().getSortCode();
		String accountNumber = payment.getCounterparty().getAccountNumber();

		Pattern sortCodePattern = Pattern.compile("\\d{6}");
		Pattern accountNumberPattern = Pattern.compile("\\d{8}");
		if (!sortCodePattern.matcher(sortCode).matches()) {
			throw new PaymentValidationException("Sort code must be 6 numeric characters");
		}
		if (!accountNumberPattern.matcher(accountNumber).matches()) {
			throw new PaymentValidationException("Account number must be 8 numeric characters");
		}
	}
}
