package com.example.demo.model;

import javax.persistence.*;
import lombok.Getter;


@Entity
@Table(name = "products")
public class Product {
    @Id @Getter 
    private String productCode; // varchar(15) NOT NULL,
    @Getter 
    private String productName; // varchar(70) NOT NULL,
    @Getter 
    private String productLine; // varchar(50) NOT NULL,
    @Getter 
    private String productScale; // varchar(10) NOT NULL,
    @Getter 
    private String productVendor; // varchar(50) NOT NULL,
    @Getter 
    private String productDescription; // text NOT NULL,
    @Getter 
    private Integer quantityInStock; // smallint(6) NOT NULL,
    @Getter 
    private Float buyPrice; // decimal(10,2) NOT NULL,
    @Getter 
    private Float MSRP; // decimal(10,2) NOT NULL,
}
