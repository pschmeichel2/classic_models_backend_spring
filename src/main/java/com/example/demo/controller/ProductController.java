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

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;

@CrossOrigin(origins = {"http://localhost:8081", "http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ProductController {
    
    @Autowired
    ProductRepository productRepository;
    
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
      List<Product> products = new ArrayList<Product>();
        productRepository.findAll().forEach(products::add);
      if (products.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
  
      return new ResponseEntity<>(products, HttpStatus.OK);
    }

    
    @GetMapping("/products/{productCode}")
    public ResponseEntity<Product> getProductByProductNumber(@PathVariable("productCode") String productCode) {
      Product _product = productRepository.findById(productCode)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with id = " + productCode+ " not found"));
    
      return new ResponseEntity<>(_product, HttpStatus.OK );  
    }

    @GetMapping("/productLines/{productLine}/products")
    public ResponseEntity<List<Product>> getProductsByProductLine(@PathVariable("productLine") String productLine) {    
      List<Product> products = new ArrayList<Product>();
      products = productRepository.findByProductLine(productLine);      
      if (products.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(products, HttpStatus.OK);
    }

}
