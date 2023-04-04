package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Payment;
import com.example.demo.model.keys.PaymentPK;

public interface PaymentRepository extends JpaRepository<Payment, PaymentPK> {

    List<Payment> findByCustomerNumber(Long customerNumber);
}
