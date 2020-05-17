package io.bestcoder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.bestcoder.common.Payment;
import io.bestcoder.common.TransactionRequest;
import io.bestcoder.common.TransactionResponse;
import io.bestcoder.entity.Order;
import io.bestcoder.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@PostMapping("/bookOrder")
	public TransactionResponse bookOrder(@RequestBody TransactionRequest request) throws JsonProcessingException {
		return orderService.saveOrder(request);
	}
}
