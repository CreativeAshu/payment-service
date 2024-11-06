package com.finseta.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.finseta.model.Account;
import com.finseta.model.Account.TypeEnum;
import com.finseta.model.CustomPayment;
import com.finseta.model.Payment;
import com.finseta.repository.PaymentRepository;

class PaymentQueryServiceImplTest {

	@Mock
	private PaymentRepository paymentRepository;

	@InjectMocks
	private PaymentQueryServiceImpl paymentService;

	Payment payment = createValidPayment();

	CustomPayment customPayment = new CustomPayment(payment);

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testGetPayments_WithFilters() {
		List<Payment> payments = Arrays.asList(createValidPayment(), createValidPayment());
		when(paymentRepository.findByCurrencyInAndAmountGreaterThanEqual(any(), any())).thenReturn(payments);

		List<Payment> result = paymentService.getPayments(new BigDecimal("10"), List.of("GBP"));

		assertEquals(2, result.size());
		verify(paymentRepository, times(1)).findByCurrencyInAndAmountGreaterThanEqual(any(), any());
	}

	private Payment createValidPayment() {
		Account counterparty = new Account();
		counterparty.setType(TypeEnum.SORT_CODE_ACCOUNT_NUMBER);
		counterparty.setAccountNumber("12345678");
		counterparty.setSortCode("123456");

		Payment payment = new Payment();
		payment.setAmount(new BigDecimal("100.00"));
		payment.setCurrency("GBP");
		payment.setCounterparty(counterparty);

		return payment;
	}
}
