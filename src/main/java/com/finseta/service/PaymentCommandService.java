package com.finseta.service;

import com.finseta.exception.PaymentValidationException;
import com.finseta.model.CustomPayment;
import com.finseta.model.Payment;

public interface PaymentCommandService {

	public Payment createPayment(CustomPayment payment) throws PaymentValidationException;

}
