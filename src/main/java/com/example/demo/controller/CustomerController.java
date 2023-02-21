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
import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;

@CrossOrigin(origins = {"http://localhost:8081", "http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;
    
    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
      List<Customer> customers = new ArrayList<Customer>();
      customerRepository.findAll().forEach(customers::add);
      if (customers.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }  
      return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/customers/{customerNumber}")
    public ResponseEntity<Customer> getCustomerByCustomerNumber(@PathVariable("customerNumber") long customerNumber) {
      Customer _customer = customerRepository.findById(customerNumber)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer with id = " + customerNumber+ " not found"));
    
      return new ResponseEntity<>(_customer, HttpStatus.OK );  
    }

    @GetMapping("/employees/{employeeNumber}/customers")
    public ResponseEntity<List<Customer>> getCustomersByEmployeeNumber(@PathVariable("employeeNumber") long employeeNumber) {    
      List<Customer> customers = new ArrayList<Customer>();
      customers = customerRepository.findBySalesRepEmployeeNumber(employeeNumber);      
      if (customers.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(customers, HttpStatus.OK);
    }

}
