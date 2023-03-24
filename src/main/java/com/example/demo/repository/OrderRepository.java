package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>, OrderRepositoryCustom {
    
    @Query("select max(convert(o.orderNumber, unsigned))+1 from Order o")  // this is JPQL so use classnames
    int getNewOrderNumber();

}

