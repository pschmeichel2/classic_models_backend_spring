package com.example.classicmodels.model.query;

import java.time.LocalDateTime;

import javax.persistence.*;

import org.springframework.data.annotation.ReadOnlyProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor

public class OrderQuery {
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
    /*
     * @Getter
     * 
     * @ManyToOne(fetch=FetchType.EAGER)
     * 
     * @JoinColumn(name="customerNumber", referencedColumnName="customerNumber",
     * insertable=false, updatable=false)
     * private Customer customer;
     */
    @Getter
    @ReadOnlyProperty
    private String customerName;

}
