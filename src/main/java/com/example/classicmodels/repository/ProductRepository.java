package com.example.classicmodels.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.classicmodels.model.Product;

public interface ProductRepository extends JpaRepository<Product, String>, ProductRepositoryCustom {

    List<Product> findByProductLine(String productLine);
}
