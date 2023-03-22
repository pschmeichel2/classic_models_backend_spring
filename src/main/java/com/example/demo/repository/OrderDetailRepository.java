package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.keys.OrderDetailPK;
import com.example.demo.model.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailPK> {
    
    
    @Query("select new com.example.demo.model.OrderDetail(d.orderNumber, "+
            "d.productCode, "+
            "d.quantityOrdered, "+ 
            "d.priceEach, "+ 
            "d.orderLineNumber, "+
            "p.productName, "+
            "c.customerName) " +
        "from OrderDetail d "+
        "left join Product p on d.productCode = p.productCode " +
        "left join Order o on d.orderNumber = o.orderNumber " +        
        "left join Customer c on c.customerNumber = o.customerNumber " +        
        "where d.productCode = ?1 "+
        "order by d.orderNumber, d.orderLineNumber")  // this is JPQL so use classnames
    List<OrderDetail> findByProductCode(String productCode);

    @Query("select new com.example.demo.model.OrderDetail(d.orderNumber, "+
            "d.productCode, "+
            "d.quantityOrdered, "+ 
            "d.priceEach, "+ 
            "d.orderLineNumber, "+
            "p.productName, "+
            "c.customerName) " +
        "from OrderDetail d "+
        "left join Product p on d.productCode = p.productCode " +
        "left join Order o on d.orderNumber = o.orderNumber " +        
        "left join Customer c on c.customerNumber = o.customerNumber " +        
        "where d.orderNumber = ?1 "+
        "order by d.orderLineNumber")  // this is JPQL so use classnames
    List<OrderDetail> findByOrderNumber(Long orderNumber);

    @Query("select new com.example.demo.model.OrderDetail(d.orderNumber, "+
            "d.productCode, "+
            "d.quantityOrdered, "+ 
            "d.priceEach, "+ 
            "d.orderLineNumber, "+
            "p.productName, "+
            "c.customerName) " +
        "from OrderDetail d "+
        "left join Product p on d.productCode = p.productCode " +        
        "left join Order o on d.orderNumber = o.orderNumber " +        
        "left join Customer c on c.customerNumber = o.customerNumber " +        
        "order by d.orderNumber, d.orderLineNumber")  
    List<OrderDetail> findAll();


    @Query("select new com.example.demo.model.OrderDetail(d.orderNumber, "+
        "d.productCode, "+
        "d.quantityOrdered, "+ 
        "d.priceEach, "+ 
        "d.orderLineNumber, "+
        "p.productName, "+
        "c.customerName) " +
        "from OrderDetail d "+
        "left join Product p on d.productCode = p.productCode " +        
        "left join Order o on d.orderNumber = o.orderNumber " +        
        "left join Customer c on c.customerNumber = o.customerNumber " +        
        "where d.orderNumber = ?1 and d.productCode = ?2")  
    Optional<OrderDetail> findByOrderNumberAndProductCode(Long orderNumber, String productCode);

}

