package com.example.demo.model;

import java.time.LocalDateTime;

import javax.persistence.*;

import com.example.demo.model.keys.PaymentPK;

import lombok.Getter;

@Entity
@IdClass(PaymentPK.class)
@Table(name = "Payments")
public class Payment {
    @Id
    @Getter
    private Long customerNumber; // int(11) NOT NULL,
    @Id
    @Getter
    private String checkNumber; // varchar(50) NOT NULL,
    @Getter
    private LocalDateTime paymentDate; // date NOT NULL,
    @Getter
    private Float amount; // decimal(10,2) NOT NULL,

}
