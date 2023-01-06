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

import com.example.demo.model.Order;
import com.example.demo.repository.OrderRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class OrderController {
    
    @Autowired
    OrderRepository orderRepository;
    
    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders() {
      List<Order> orders = new ArrayList<Order>();
        orderRepository.findAll().forEach(orders::add);
      if (orders.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
  
      return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    
    @GetMapping("/orders/{orderNumber}")
    public ResponseEntity<Order> getOrderByOrderNumber(@PathVariable("orderNumber") long orderNumber) {
      Order _order = orderRepository.findById(orderNumber)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order with id = " + orderNumber+ " not found"));
    
      return new ResponseEntity<>(_order, HttpStatus.OK );  
    }

    @GetMapping("/customers/{customerNumber}/orders")
    public ResponseEntity<List<Order>> getOrdersByCustomerNumber(@PathVariable("customerNumber") long customerNumber) {    
      List<Order> orders = new ArrayList<Order>();
      orders = orderRepository.findByCustomerNumber(customerNumber);      
      if (orders.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(orders, HttpStatus.OK);
    }
    
}
