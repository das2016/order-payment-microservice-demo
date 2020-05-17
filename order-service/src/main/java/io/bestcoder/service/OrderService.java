package io.bestcoder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.bestcoder.common.Payment;
import io.bestcoder.common.TransactionRequest;
import io.bestcoder.common.TransactionResponse;
import io.bestcoder.entity.Order;
import io.bestcoder.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@RefreshScope
@Slf4j
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	@Lazy
	private RestTemplate restTemplate;
	
	@Value("${microservices.payment-service.endpoints.endpoint.uri}")
	private String PAYMENT_URI;

	public TransactionResponse saveOrder(TransactionRequest request) throws JsonProcessingException {
		String response = "";
		Order order = request.getOrder();
		Payment payment = request.getPayment();
		payment.setOrderId(order.getId());
		payment.setAmount(order.getPrice());
		log.info("Orderservice request :{}",new ObjectMapper().writeValueAsString(request));
		// rest call
		Payment paymentResponse = restTemplate.postForObject(PAYMENT_URI, payment, Payment.class);
		
		log.info("Payment-service response from order service rest call : {} ",new ObjectMapper().writeValueAsString(paymentResponse));
		response = "success".equals(paymentResponse.getPaymentStatus())?"payment processing succefull and order placed":"there is a failure in payment api, order added to cart";
		
		orderRepository.save(order);
		
		return new TransactionResponse(order,paymentResponse.getAmount(),paymentResponse.getTransactionId(),response);
	}
}
