package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.ProductLine;

public interface ProductLineRepository extends JpaRepository<ProductLine, String> {

}
