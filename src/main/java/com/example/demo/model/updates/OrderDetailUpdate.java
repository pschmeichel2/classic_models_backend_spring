package com.example.demo.model.updates;

import javax.persistence.*;

import org.springframework.data.annotation.ReadOnlyProperty;

import com.example.demo.model.keys.OrderDetailPK;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@IdClass(OrderDetailPK.class)

public class OrderDetailUpdate {
    @Id @Getter @Setter
    private Long orderNumber; // int(11) NOT NULL,
    @Id @Getter @Setter
    private String productCode; // varchar(15) NOT NULL,
    @Getter 
    private Long quantityOrdered; // int(11) NOT NULL,
    @Getter 
    private Float priceEach; // decimal(10,2) NOT NULL,
    @Getter 
    private Long orderLineNumber; // smallint(6) NOT NULL,

    public OrderDetailPK getPk() {
        return new OrderDetailPK(orderNumber, productCode);
    }
}


