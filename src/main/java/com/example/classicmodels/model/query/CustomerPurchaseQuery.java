package com.example.classicmodels.model.query;

import javax.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CustomerPurchaseQuery {
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
    @Getter
    private LocalDateTime orderDate; 

}
