package com.finseta.service.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.finseta.exception.PaymentValidationException;
import com.finseta.model.Account;
import com.finseta.model.Account.TypeEnum;
import com.finseta.model.CustomPayment;
import com.finseta.model.Payment;
import com.finseta.repository.PaymentRepository;

class PaymentCommandServiceImplTest {

	@Mock
	private PaymentRepository paymentRepository;

	@InjectMocks
	private PaymentCommandServiceImpl paymentService;

	Payment payment = createValidPayment();

	CustomPayment customPayment = new CustomPayment(payment);

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testCreatePayment_ValidPayment() {

		when(paymentRepository.save(customPayment)).thenReturn(customPayment);

		Payment result = paymentService.createPayment(new CustomPayment(payment));

		assertNotNull(result);
		verify(paymentRepository, times(1)).save(customPayment);
	}

	@Test
	void testCreatePayment_InvalidAmount() {
		Payment payment = createValidPayment();
		CustomPayment customPayment = new CustomPayment(payment);
		customPayment.setAmount(BigDecimal.ZERO);

		assertThrows(PaymentValidationException.class, () -> paymentService.createPayment(customPayment));
	}

	@Test
	void testCreatePayment_InvalidCurrency() {
		Payment payment = createValidPayment();
		CustomPayment customPayment = new CustomPayment(payment);
		customPayment.setCurrency(null);

		assertThrows(PaymentValidationException.class, () -> paymentService.createPayment(customPayment));
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
