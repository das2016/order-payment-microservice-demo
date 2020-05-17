package io.bestcoder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.bestcoder.entity.Payment;
import io.bestcoder.service.PaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentController {

	@Autowired
	PaymentService paymentService;
	
	@PostMapping("/doPayment")
	public Payment doPayment(@RequestBody Payment payment) throws JsonProcessingException {
		return paymentService.doPayment(payment);
	}
	
	@GetMapping("/{orderId}")
	public Payment findPaymentByOrderId(@PathVariable int orderId) {
		return paymentService.findPaymentByOrderId(orderId);
	}
}
