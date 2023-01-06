package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Customer;


public interface CustomerRepository extends JpaRepository<Customer, Long> {
    
    @Query("select new com.example.demo.model.Customer("+
    "c.customerNumber, "+
    "c.customerName, "+
    "c.contactLastName, "+
    "c.contactFirstName, "+
    "c.phone, "+
    "c.addressLine1, "+
    "c.addressLine2, "+
    "c.city, "+
    "c.state, "+
    "c.postalCode, "+
    "c.country, "+
    "c.salesRepEmployeeNumber, "+
    "c.creditLimit, "+
    "e.lastName) "+
    "from Customer c "+
    "left join Employee e on c.salesRepEmployeeNumber = e.employeeNumber " +
    "where c.salesRepEmployeeNumber = ?1 " +
    "order by c.customerNumber")  // this is JPQL so use classnames
    List<Customer> findBySalesRepEmployeeNumber(Long employeeNumber);

    @Query("select new com.example.demo.model.Customer("+
    "c.customerNumber, "+
    "c.customerName, "+
    "c.contactLastName, "+
    "c.contactFirstName, "+
    "c.phone, "+
    "c.addressLine1, "+
    "c.addressLine2, "+
    "c.city, "+
    "c.state, "+
    "c.postalCode, "+
    "c.country, "+
    "c.salesRepEmployeeNumber, "+
    "c.creditLimit, "+
    "e.lastName) "+
    "from Customer c "+
    "left join Employee e on c.salesRepEmployeeNumber = e.employeeNumber " +
    "where c.customerNumber = ?1 ")  // this is JPQL so use classnames
    Optional<Customer> findById(Long customerNumber);

    @Query("select new com.example.demo.model.Customer("+
        "c.customerNumber, "+
        "c.customerName, "+
        "c.contactLastName, "+
        "c.contactFirstName, "+
        "c.phone, "+
        "c.addressLine1, "+
        "c.addressLine2, "+
        "c.city, "+
        "c.state, "+
        "c.postalCode, "+
        "c.country, "+
        "c.salesRepEmployeeNumber, "+
        "c.creditLimit, "+
        "e.lastName) "+
        "from Customer c "+
        "left join Employee e on c.salesRepEmployeeNumber = e.employeeNumber " +
        "order by c.customerNumber")  // this is JPQL so use classnames
    List<Customer> findAll();


}
