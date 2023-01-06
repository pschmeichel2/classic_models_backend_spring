package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.util.StringBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.model.OrderDetail;
import com.example.demo.model.keys.OrderDetailPK;
import com.example.demo.repository.OrderDetailRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class OrderDetailController {
    
    @Autowired
    OrderDetailRepository orderDetailRepository;
    
    @GetMapping("/orderDetails")
    public ResponseEntity<List<OrderDetail>> getAllOrderDetails() {
      List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
        orderDetailRepository.findAll().forEach(orderDetails::add);
      if (orderDetails.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
  
      return new ResponseEntity<>(orderDetails, HttpStatus.OK);
    }

    
    @GetMapping("/orderDetails/{orderNumber}/{productCode}")
    public ResponseEntity<OrderDetail> getOrderDetailByOrderDetailNumber(
      @PathVariable("orderNumber") long orderNumber,
      @PathVariable("productCode") String productCode) {
      
      OrderDetail orderDetail = orderDetailRepository.findByOrderNumberAndProductCode(orderNumber, productCode)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, 
        "OrderDetail with id = " + orderNumber + "/" + productCode + " not found"));
    
      return new ResponseEntity<>(orderDetail, HttpStatus.OK );  
    }

    @GetMapping("/orders/{orderNumber}/orderDetails")
    public ResponseEntity<List<OrderDetail>> getOrderDetailsByCustomerNumber(@PathVariable("orderNumber") long orderNumber) {    
      List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
      orderDetails = orderDetailRepository.findByOrderNumber(orderNumber);      
      if (orderDetails.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(orderDetails, HttpStatus.OK);
    }

    @GetMapping("/products/{productCode}/orderDetails")
    public ResponseEntity<List<OrderDetail>> getOrderDetailsByProductCode(@PathVariable("productCode") String productCode) {    
      List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
      orderDetails = orderDetailRepository.findByProductCode(productCode);      
      if (orderDetails.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(orderDetails, HttpStatus.OK);
    }

}
