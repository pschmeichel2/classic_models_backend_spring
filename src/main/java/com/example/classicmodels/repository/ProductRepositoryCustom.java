package com.example.classicmodels.repository;

import java.util.List;

import com.example.classicmodels.model.Product;

public interface ProductRepositoryCustom {

    List<Product> getProducts(
            String productCode, String productName,
            String productLine, String productVendor);

    List<String> getProductLines();

    List<String> getProductVendors();
}
