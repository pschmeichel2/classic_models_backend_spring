package com.example.demo.model;

import java.time.LocalDateTime;

import javax.persistence.*;

import org.springframework.data.annotation.ReadOnlyProperty;

import com.example.demo.model.updates.OrderUpdate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Orders")
public class Order {
    @Id
    @Getter
    @Setter
    private Long orderNumber; // int(11) NOT NULL,
    @Getter
    private LocalDateTime orderDate; // date NOT NULL,
    @Getter
    private LocalDateTime requiredDate; // date NOT NULL,
    @Getter
    private LocalDateTime shippedDate; // date DEFAULT NULL,
    @Getter
    private String status; // varchar(15) NOT NULL,
    @Getter
    private String comments; // text,
    @Getter
    private Long customerNumber; // int(11) NOT NULL,

    public static Order fromOrderUpdate(OrderUpdate orderUpdate) {
        return new Order(
                orderUpdate.getOrderNumber(),
                orderUpdate.getOrderDate(),
                orderUpdate.getRequiredDate(),
                orderUpdate.getShippedDate(),
                orderUpdate.getStatus(),
                orderUpdate.getComments(),
                orderUpdate.getCustomerNumber());

    }

}
