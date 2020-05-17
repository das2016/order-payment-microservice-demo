package io.bestcoder;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

/**
 * 
 * @author SKAN
 *
 */
@RestController
@RequestMapping("/")
public class FallBackController {

	@RequestMapping("/orderFallBack")
	public Mono<String> orderServiceFallBack(){
		return Mono.just("order service is taking too long to respond or is down. Please try again later");
	}

	@RequestMapping("paymentFallBack")
	public Mono<String> paymentServiceFallBack(){
		return Mono.just("payment service is taking too long to respond or is down. Please try again later");
	}

}