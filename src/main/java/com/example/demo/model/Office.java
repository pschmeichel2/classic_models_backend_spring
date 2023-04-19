package com.example.demo.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Offices")
public class Office {
    @Id
    @Getter
    @Setter
    private String officeCode; // varchar(10) NOT NULL,

    @Getter
    @Setter
    @NotBlank(message = "city is mandatory")
    // @NotEmpty(message = "city musn't be empty")
    private String city; // varchar(50) NOT NULL,

    @Getter
    @Setter
    @Size(min = 1, message = "min length 1 char")
    @Size(max = 50, message = "max length 50 char")
    private String phone; // varchar(50) NOT NULL,

    @Getter
    @Setter
    @NotBlank(message = "addressLine1 is mandatory")
    @Size(min = 1, message = "min length 1 char")
    @Size(max = 50, message = "max length 50 char")
    private String addressLine1; // varchar(50) NOT NULL,

    @Getter
    @Setter
    @Size(max = 50, message = "max length 50 char")
    private String addressLine2; // varchar(50) DEFAULT NULL,

    @Getter
    @Setter
    @Size(max = 50, message = "max length 50 char")
    private String state; // varchar(50) DEFAULT NULL,

    @Getter
    @Setter
    @NotBlank(message = "country is mandatory")
    @Size(max = 50, message = "max length 50 char")
    private String country; // varchar(50) NOT NULL,

    @Getter
    @Setter
    @NotBlank(message = "postalCode is mandatory")
    @Size(max = 50, message = "max length 15 char")
    private String postalCode; // varchar(15) NOT NULL,

    @Getter
    @Setter
    @Size(max = 50, message = "max length 10 char")
    private String territory; // varchar(10) NOT NULL,
}
