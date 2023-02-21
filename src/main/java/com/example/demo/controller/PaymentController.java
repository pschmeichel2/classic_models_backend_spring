package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.model.Payment;
import com.example.demo.model.keys.PaymentPK;
import com.example.demo.repository.PaymentRepository;

@CrossOrigin(origins = {"http://localhost:8081", "http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class PaymentController {
    
    @Autowired
    PaymentRepository paymentRepository;
    
    @GetMapping("/payments")
    public ResponseEntity<List<Payment>> getAllPayments() {
      List<Payment> payments = new ArrayList<Payment>();
        paymentRepository.findAll().forEach(payments::add);
      if (payments.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
  
      return new ResponseEntity<>(payments, HttpStatus.OK);
    }

    
    @GetMapping("/payments/{customerNumber}/{checkNumber}")
    public ResponseEntity<Payment> getPaymentByCustomerNumberAndCheckNumber(
      @PathVariable("customerNumber") long customerNumber,
      @PathVariable("checkNumber") String checkNumber) {
      PaymentPK paymentPk = new PaymentPK(customerNumber, checkNumber);
      Payment _payment = paymentRepository.findById(paymentPk)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, 
        "Payment with id = " + customerNumber+ "/" + checkNumber + " not found"));
    
      return new ResponseEntity<>(_payment, HttpStatus.OK );  
    }

    @GetMapping("/customers/{customerNumber}/payments")
    public ResponseEntity<List<Payment>> getOrdersByCustomerNumber(@PathVariable("customerNumber") long customerNumber) {    
      List<Payment> payments = new ArrayList<Payment>();
      payments = paymentRepository.findByCustomerNumber(customerNumber);      
      if (payments.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(payments, HttpStatus.OK);
    }
    

}
