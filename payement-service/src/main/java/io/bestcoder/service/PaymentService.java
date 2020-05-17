package io.bestcoder.service;

import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.bestcoder.entity.Payment;
import io.bestcoder.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PaymentService {

	@Autowired
	PaymentRepository paymentRepository;

	public Payment doPayment(Payment payment) throws JsonProcessingException {
		payment.setPaymentStatus(paymentProcessing());
		payment.setTransactionId(UUID.randomUUID().toString());
		log.info(" Payment service request {}"+new ObjectMapper().writeValueAsString(payment));
		return paymentRepository.save(payment);
	}

	private String paymentProcessing() {
		// api should be 3rd party payment gateway
		return new Random().nextBoolean() ? "success" : "error";
	}

	public Payment findPaymentByOrderId(int orderId) {
		return paymentRepository.findByOrderId(orderId);
	}
}
