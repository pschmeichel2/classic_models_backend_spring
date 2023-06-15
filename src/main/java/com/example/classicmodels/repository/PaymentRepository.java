package com.example.classicmodels.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.classicmodels.model.Payment;
import com.example.classicmodels.model.keys.PaymentPK;

public interface PaymentRepository extends JpaRepository<Payment, PaymentPK> {

    List<Payment> findByCustomerNumber(Long customerNumber);
}
