package com.example.classicmodels.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.classicmodels.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>, EmployeeRepositoryCustom {
}
