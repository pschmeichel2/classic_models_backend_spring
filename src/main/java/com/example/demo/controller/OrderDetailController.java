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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.model.query.OrderDetailQuery;
import com.example.demo.model.OrderDetail;
import com.example.demo.repository.OrderDetailRepository;


@CrossOrigin(origins = {"http://localhost:8081", "http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class OrderDetailController {
    
    @Autowired
    OrderDetailRepository orderDetailRepository;
    
    @GetMapping("/orderDetails")
    public ResponseEntity<List<OrderDetailQuery>> getAllOrderDetails() {
      List<OrderDetailQuery> orderDetails = new ArrayList<OrderDetailQuery>();
        orderDetailRepository.findQueryAll().forEach(orderDetails::add);
      if (orderDetails.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
  
      return new ResponseEntity<>(orderDetails, HttpStatus.OK);
    }

    
    @GetMapping("/orderDetails/{orderNumber}/{productCode}")
    public ResponseEntity<OrderDetailQuery> getOrderDetailByOrderDetailNumber(
      @PathVariable("orderNumber") long orderNumber,
      @PathVariable("productCode") String productCode) {
      
        OrderDetailQuery orderDetail = orderDetailRepository.findQueryByOrderNumberAndProductCode(orderNumber, productCode)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, 
        "OrderDetail with id = " + orderNumber + "/" + productCode + " not found"));
    
      return new ResponseEntity<>(orderDetail, HttpStatus.OK );  
    }

    @GetMapping("/orders/{orderNumber}/orderDetails")
    public ResponseEntity<List<OrderDetailQuery>> getOrderDetailsByCustomerNumber(@PathVariable("orderNumber") long orderNumber) {    
      List<OrderDetailQuery> orderDetails = new ArrayList<OrderDetailQuery>();
      orderDetails = orderDetailRepository.findQueryByOrderNumber(orderNumber);      
      if (orderDetails.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(orderDetails, HttpStatus.OK);
    }

    @GetMapping("/products/{productCode}/orderDetails")
    public ResponseEntity<List<OrderDetailQuery>> getOrderDetailsByProductCode(@PathVariable("productCode") String productCode) {    
      List<OrderDetailQuery> orderDetails = new ArrayList<OrderDetailQuery>();
      orderDetails = orderDetailRepository.findQueryByProductCode(productCode);      
      if (orderDetails.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(orderDetails, HttpStatus.OK);
    }

    @PostMapping("/orderDetails")
    public ResponseEntity<OrderDetail> createOrderDetail(@Valid @RequestBody OrderDetail orderDetail) {   
      return new ResponseEntity<>(orderDetailRepository.save(orderDetail), HttpStatus.OK ); 
    }

}
