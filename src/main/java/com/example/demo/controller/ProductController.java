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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;

@CrossOrigin(origins = { "http://localhost:8081", "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class ProductController {

  @Autowired
  ProductRepository productRepository;

  @GetMapping("/products")
  public ResponseEntity<List<Product>> getAllProducts(
      @RequestParam(required = false) String productCode,
      @RequestParam(required = false) String productName,
      @RequestParam(required = false) String productLine,
      @RequestParam(required = false) String productVendor) {

    List<Product> products = new ArrayList<Product>();

    if (!isNullOrBlank(productCode) ||
        !isNullOrBlank(productName) ||
        !isNullOrBlank(productLine) ||
        !isNullOrBlank(productVendor)) {
      productRepository.getProducts(productCode, productName, productLine, productVendor).forEach(products::add);
    } else {
      productRepository.findAll().forEach(products::add);
    }

    if (products.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    return new ResponseEntity<>(products, HttpStatus.OK);
  }

  @GetMapping("/products/{productCode}")
  public ResponseEntity<Product> getProductByProductNumber(@PathVariable("productCode") String productCode) {
    Product _product = productRepository.findById(productCode)
        .orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with id = " + productCode + " not found"));

    return new ResponseEntity<>(_product, HttpStatus.OK);
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

  @GetMapping("/productVendors")
  public ResponseEntity<List<String>> getProductVendors() {

    List<String> productVendors = new ArrayList<String>();
    productRepository.getProductVendors().forEach(productVendors::add);

    if (productVendors.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<List<String>>(productVendors, HttpStatus.OK);
  }

  @GetMapping("/productLineNames")
  public ResponseEntity<List<String>> getProductLineNames() {

    List<String> productLines = new ArrayList<String>();
    productRepository.getProductLines().forEach(productLines::add);

    if (productLines.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<List<String>>(productLines, HttpStatus.OK);
  }

  static boolean isNullOrBlank(String s) {
    return (s == null || s.isBlank());
  }

}
