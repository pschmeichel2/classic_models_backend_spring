package com.example.classicmodels.model.query;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CustomerPurchaseAggregateQuery {
    @Id
    @Getter
    @Setter
    private String id; 
    @Getter
    private Float total; 
    @Getter
    private String productCode; 
    @Getter
    private String productName; 
    @Getter
    private String productLine;
}
