package com.example.demo.model;

import javax.persistence.*;

import org.springframework.data.annotation.ReadOnlyProperty;

import com.example.demo.model.keys.OrderDetailPK;
import com.example.demo.model.updates.OrderDetailUpdate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@IdClass(OrderDetailPK.class)
@Table(name = "orderdetails")
public class OrderDetail {
    @Id @Getter @Setter
    private Long orderNumber; // int(11) NOT NULL,
    @Id @Getter @Setter
    private String productCode; // varchar(15) NOT NULL,
    @Getter 
    private Long quantityOrdered; // int(11) NOT NULL,
    @Getter 
    private Float priceEach; // decimal(10,2) NOT NULL,
    @Getter @Setter
    private Long orderLineNumber; // smallint(6) NOT NULL,

    public static OrderDetail fromOrderDetailUpdate(OrderDetailUpdate orderDetailUpdate ) {
        return new OrderDetail(
            orderDetailUpdate.getOrderNumber(),
            orderDetailUpdate.getProductCode(),
            orderDetailUpdate.getQuantityOrdered(),
            orderDetailUpdate.getPriceEach(),
            orderDetailUpdate.getOrderLineNumber()
        );
    }

}


