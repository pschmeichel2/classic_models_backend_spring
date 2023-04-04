package com.example.demo.model;

import javax.persistence.*;

import org.springframework.data.annotation.ReadOnlyProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employees")
public class Employee {
    @Id
    @Getter
    private Long employeeNumber; // int(11) NOT NULL,
    @Getter
    private String lastName; // varchar(50) NOT NULL,
    @Getter
    private String firstName; // varchar(50) NOT NULL,
    @Getter
    private String extension; // varchar(10) NOT NULL,
    @Getter
    private String email; // varchar(100) NOT NULL,
    @Getter
    private String officeCode; // varchar(10) NOT NULL,
    @Getter
    private Long reportsTo; // int(11) DEFAULT NULL,
    @Getter
    private String jobTitle; // varchar(50) NOT NULL,

    @Getter
    @ReadOnlyProperty
    private String city;
    @Getter
    @ReadOnlyProperty
    private String reportsToName;

}
