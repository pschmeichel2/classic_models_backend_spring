package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.model.Order;
import com.example.demo.model.updates.OrderUpdate;
import com.example.demo.model.query.OrderQuery;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.OrderService;




@CrossOrigin(origins = {"http://localhost:8081", "http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class OrderController {
    
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderService orderService;
    
    @GetMapping("/orders")
    public ResponseEntity<List<OrderQuery>> getAllOrders() {
      List<OrderQuery> orders = new ArrayList<OrderQuery>();
      orderRepository.findQueryAll().forEach(orders::add);
      if (orders.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
  
      return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    
    @GetMapping("/orders/{orderNumber}")
    public ResponseEntity<OrderQuery> getOrderByOrderNumber(@PathVariable("orderNumber") long orderNumber) {
      OrderQuery _order = orderRepository.findQueryById(orderNumber)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order with id = " + orderNumber+ " not found"));
    
      return new ResponseEntity<>(_order, HttpStatus.OK );  
    }

    @GetMapping("/customers/{customerNumber}/orders")
    public ResponseEntity<List<OrderQuery>> getOrdersByCustomerNumber(@PathVariable("customerNumber") long customerNumber) {    
      List<OrderQuery> orders = new ArrayList<OrderQuery>();
      orders = orderRepository.findQueryByCustomerNumber(customerNumber);      
      if (orders.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(orders, HttpStatus.OK);
    }
    
    @PostMapping("/orders")
    public ResponseEntity<Order> createCustomer(@Valid @RequestBody OrderUpdate order) {   
      Order newOrder = orderService.insert(order);
      return new ResponseEntity<>(newOrder, HttpStatus.OK );       
    }
 
    @PutMapping("/orders/{orderNumber}")
    public ResponseEntity<Order> updateOrder(
      @PathVariable("orderNumber") long orderNumber,
      @Valid @RequestBody OrderUpdate order) {
        Order newOrder = orderService.update(order);
        return new ResponseEntity<>(newOrder, HttpStatus.OK );       
      }

}
