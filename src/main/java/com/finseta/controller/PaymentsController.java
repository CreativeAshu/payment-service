package com.finseta.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finseta.api.PaymentsApi;
import com.finseta.exception.PaymentValidationException;
import com.finseta.model.CustomPayment;
import com.finseta.model.Payment;
import com.finseta.service.PaymentCommandService;
import com.finseta.service.PaymentQueryService;

@RestController
public class PaymentsController implements PaymentsApi {

	@GetMapping("/")
	public String sayHello() {
		return "hello";
	}

	@Autowired
	PaymentQueryService paymentQueryService;

	@Autowired
	PaymentCommandService paymentCommandService;

	@Override
	public ResponseEntity<List<Payment>> paymentsGet(BigDecimal minAmount, List<String> currencies) {
		List<Payment> payments = paymentQueryService.getPayments(minAmount, currencies);
		return ResponseEntity.ok(payments);
	}

	@Override
	public ResponseEntity<Payment> paymentsPost(Payment payment) {
		if (payment.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
			throw new PaymentValidationException("Amount must be greater than 0");
		}
		Payment createdPayment = paymentCommandService.createPayment(new CustomPayment(payment));
		return ResponseEntity.status(HttpStatus.CREATED).body(createdPayment);
	}

}
