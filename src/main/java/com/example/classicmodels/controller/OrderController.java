package com.example.classicmodels.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.classicmodels.model.Order;
import com.example.classicmodels.model.query.CustomerOrderQuery;
import com.example.classicmodels.model.query.OrderQuery;
import com.example.classicmodels.model.updates.OrderUpdate;
import com.example.classicmodels.repository.OrderDetailRepository;
import com.example.classicmodels.repository.OrderRepository;
import com.example.classicmodels.service.OrderService;

@CrossOrigin(origins = "*")
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
        .orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order with id = " + orderNumber + " not found"));

    return new ResponseEntity<>(_order, HttpStatus.OK);
  }

  @GetMapping("/customers/{customerNumber}/orders")
  public ResponseEntity<List<OrderQuery>> getOrdersByCustomerNumber(
      @PathVariable("customerNumber") long customerNumber) {
    List<OrderQuery> orders = new ArrayList<OrderQuery>();
    orders = orderRepository.findQueryByCustomerNumber(customerNumber);
    if (orders.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    return new ResponseEntity<>(orders, HttpStatus.OK);
  }

  @GetMapping("/customers/{customerNumber}/ordertotals")
  public ResponseEntity<List<CustomerOrderQuery>> getOrderTotalsByCustomerNumber(
      @PathVariable("customerNumber") long customerNumber) {
    List<CustomerOrderQuery> orders = new ArrayList<CustomerOrderQuery>();
    orders = orderRepository.findOrderTotalsByCustomerNumber(customerNumber);
    if (orders.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    return new ResponseEntity<>(orders, HttpStatus.OK);
  }

  @PostMapping("/orders")
  public ResponseEntity<Order> createCustomer(@Valid @RequestBody OrderUpdate order) {
    Order newOrder = orderService.insert(order);
    return new ResponseEntity<>(newOrder, HttpStatus.OK);
  }

  @PutMapping("/orders/{orderNumber}")
  public ResponseEntity<Order> updateOrder(
      @PathVariable("orderNumber") long orderNumber,
      @Valid @RequestBody OrderUpdate order) {
    Order newOrder = orderService.update(order);
    return new ResponseEntity<>(newOrder, HttpStatus.OK);
  }

  @DeleteMapping("/orders/{orderNumber}")
  public ResponseEntity<HttpStatus> deleteOrder(@PathVariable("orderNumber") long orderNumber) {
    try {
      orderService.deleteOrder(orderNumber);
    } catch (EmptyResultDataAccessException ex) {
      throw new ResourceNotFoundException(String.format("Order with orderNumber %d not found", orderNumber));
    }
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
