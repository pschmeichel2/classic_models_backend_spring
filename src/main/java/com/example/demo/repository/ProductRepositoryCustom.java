package com.example.demo.repository;

import java.util.List;
import com.example.demo.model.Product;

public interface ProductRepositoryCustom {

    List<Product> getProducts(
        String productCode, String productName, 
        String productLine, String productVendor);

    List<String> getProductLines();
    List<String> getProductVendors();
}
