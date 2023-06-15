package com.example.classicmodels.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.classicmodels.model.ProductLine;

public interface ProductLineRepository extends JpaRepository<ProductLine, String> {

}
