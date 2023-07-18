package com.example.classicmodels.model.query;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.data.annotation.ReadOnlyProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor

public class EmployeeQuery {
    @Id
    @Getter
    @Setter
    private Long employeeNumber; // int(11) NOT NULL,
    @Getter
    @Setter
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
