package com.example.classicmodels.model;

import javax.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "ProductLines")
public class ProductLine {
    @Id
    @Getter
    private String productLine; // varchar(50) NOT NULL,
    @Getter
    private String textDescription; // varchar(4000) DEFAULT NULL,
    @Getter
    private String htmlDescription; // mediumtext,
    @Getter
    private String image; // mediumblob;
}
