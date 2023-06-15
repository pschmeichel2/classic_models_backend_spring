package com.example.classicmodels.model.query;

import javax.persistence.*;
import java.time.LocalDateTime;
import org.springframework.data.annotation.ReadOnlyProperty;

import com.example.classicmodels.model.keys.OrderDetailPK;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CustomerOrderQuery {
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

    @Getter
    @ReadOnlyProperty
    private String customerName;
    @Getter
    @ReadOnlyProperty
    private float totalOrderPrice;
    @Getter
    @ReadOnlyProperty
    private float recommendedOrderPrice;
    @Getter
    @ReadOnlyProperty
    private float buyPrice;
}
