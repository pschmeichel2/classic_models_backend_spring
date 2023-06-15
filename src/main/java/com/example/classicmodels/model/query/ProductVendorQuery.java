package com.example.classicmodels.model.query;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ProductVendorQuery {
    @Id
    @Getter
    private String productVendor; // int(11) NOT NULL,

}
