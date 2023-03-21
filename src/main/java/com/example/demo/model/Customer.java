package com.example.demo.model;

import javax.persistence.*;

import org.springframework.data.annotation.ReadOnlyProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customers")
public class Customer {
    @Id @Getter 
    private Long customerNumber; // int(11) NOT NULL,
    @Getter 
    private String customerName; // varchar(50) NOT NULL,
    @Getter 
    private String contactLastName; // varchar(50) NOT NULL,
    @Getter 
    private String contactFirstName; // varchar(50) NOT NULL,
    @Getter 
    private String phone; // varchar(50) NOT NULL,
    @Getter 
    private String addressLine1; // varchar(50) NOT NULL,
    @Getter 
    private String addressLine2; // varchar(50) DEFAULT NULL,
    @Getter 
    private String city; // varchar(50) NOT NULL,
    @Getter 
    private String state; // varchar(50) DEFAULT NULL,
    @Getter 
    private String postalCode; // varchar(15) DEFAULT NULL,
    @Getter 
    private String country; // varchar(50) NOT NULL,
    @Getter 
    private Long salesRepEmployeeNumber; // int(11) DEFAULT NULL,
    @Getter 
    private Float creditLimit; // decimal(10,2) DEFAULT NULL,

    @Getter @ReadOnlyProperty
    private String salesRepEmployeeName;

    public static Customer from(String customerName) {
        Customer customer = new Customer();
        customer.customerName = customerName;
        customer.salesRepEmployeeName = "";
        return customer;
    }
}
