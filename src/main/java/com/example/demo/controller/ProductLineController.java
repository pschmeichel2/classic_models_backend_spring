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

import com.example.demo.model.ProductLine;
import com.example.demo.repository.ProductLineRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class ProductLineController {
    
    @Autowired
    ProductLineRepository productLineRepository;
    
    @GetMapping("/productLines")
    public ResponseEntity<List<ProductLine>> getAllProductLines() {
      List<ProductLine> productLines = new ArrayList<ProductLine>();
        productLineRepository.findAll().forEach(productLines::add);
      if (productLines.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
  
      return new ResponseEntity<>(productLines, HttpStatus.OK);
    }

    
    @GetMapping("/productLines/{productLine}")
    public ResponseEntity<ProductLine> getProductLineByProductLineNumber(@PathVariable("productLine") String productLine) {
      ProductLine _productLine = productLineRepository.findById(productLine)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ProductLine with id = " + productLine+ " not found"));
    
      return new ResponseEntity<>(_productLine, HttpStatus.OK );  
    }

}
