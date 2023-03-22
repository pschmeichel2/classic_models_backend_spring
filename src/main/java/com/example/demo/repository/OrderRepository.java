package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    
    @Query("select new com.example.demo.model.Order("+
    "o.orderNumber, "+
    "o.orderDate, "+
    "o.requiredDate, "+
    "o.shippedDate, "+
    "o.status, "+
    "o.comments, "+
    "o.customerNumber, "+
    "c.customerName) "+
    "from Order o "+
    "left join Customer c on o.customerNumber = c.customerNumber " +    
    "where o.orderNumber = ?1")  // this is JPQL so use classnames
    Optional<Order> findById(Long orderNumber);

    @Query("select new com.example.demo.model.Order("+
    "o.orderNumber, "+
    "o.orderDate, "+
    "o.requiredDate, "+
    "o.shippedDate, "+
    "o.status, "+
    "o.comments, "+
    "o.customerNumber, "+
    "c.customerName) "+
    "from Order o "+
    "left join Customer c on o.customerNumber = c.customerNumber " + 
    "where o.customerNumber = ?1 "+   
    "order by o.orderNumber")  // this is JPQL so use classnames
    List<Order> findByCustomerNumber(Long customerNumber);

    @Query("select new com.example.demo.model.Order("+
        "o.orderNumber, "+
        "o.orderDate, "+
        "o.requiredDate, "+
        "o.shippedDate, "+
        "o.status, "+
        "o.comments, "+
        "o.customerNumber, "+
        "c.customerName) "+
        "from Order o "+
        "left join Customer c on o.customerNumber = c.customerNumber " +    
        "order by o.orderNumber")  // this is JPQL so use classnames
    List<Order> findAll();

    @Query("select max(convert(o.orderNumber, unsigned))+1 from Order o")  // this is JPQL so use classnames
    int getNewOrderNumber();

}

