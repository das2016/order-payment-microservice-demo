package io.bestcoder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.bestcoder.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

	Payment findByOrderId(int orderId);

}
