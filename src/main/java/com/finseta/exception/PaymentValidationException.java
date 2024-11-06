package com.finseta.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class PaymentValidationException extends RuntimeException {

	private static final long serialVersionUID = 4207624091070356672L;

	public PaymentValidationException(String message) {
		super(message);
	}
}