package com.finseta.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finseta.model.CustomPayment;
import com.finseta.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<CustomPayment, Long> {
	List<Payment> findByCurrencyInAndAmountGreaterThanEqual(List<String> currencies, BigDecimal minAmount);
}