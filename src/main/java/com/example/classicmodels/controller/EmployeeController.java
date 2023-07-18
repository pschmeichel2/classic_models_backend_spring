package com.example.classicmodels.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.classicmodels.model.Employee;
import com.example.classicmodels.model.query.EmployeeQuery;
import com.example.classicmodels.repository.EmployeeRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class EmployeeController {

  @Autowired
  EmployeeRepository employeeRepository;

  @GetMapping("/employees")
  public ResponseEntity<List<EmployeeQuery>> getAllEmployees() {
    List<EmployeeQuery> employees = new ArrayList<EmployeeQuery>();
    employeeRepository.findQueryAll().forEach(employees::add);
    if (employees.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    return new ResponseEntity<>(employees, HttpStatus.OK);
  }

  @GetMapping("/employees/{employeeNumber}")
  public ResponseEntity<EmployeeQuery> getEmployeeByEmployeeNumber(
      @PathVariable("employeeNumber") long employeeNumber) {
    EmployeeQuery _employee = employeeRepository.findQueryById(employeeNumber)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
            "Employee with id = " + employeeNumber + " not found"));

    return new ResponseEntity<>(_employee, HttpStatus.OK);
  }

  @GetMapping("/offices/{officeCode}/employees")
  public ResponseEntity<List<EmployeeQuery>> getEmployeesByOfficeCode(@PathVariable("officeCode") String officeCode) {
    List<EmployeeQuery> employees = new ArrayList<EmployeeQuery>();
    employees = employeeRepository.findQueryByOfficeCode(officeCode);
    if (employees.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    return new ResponseEntity<>(employees, HttpStatus.OK);
  }

  @GetMapping("/employees/{employeeNumber}/employees")
  public ResponseEntity<List<EmployeeQuery>> getEmployeesByReportsTo(
      @PathVariable("employeeNumber") Long employeeNumber) {
    List<EmployeeQuery> employees = new ArrayList<EmployeeQuery>();
    employees = employeeRepository.findQueryByReportsTo(employeeNumber);
    if (employees.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    return new ResponseEntity<>(employees, HttpStatus.OK);
  }

  @PostMapping("/employees")
  public ResponseEntity<EmployeeQuery> createEmployee(@Valid @RequestBody Employee employee) {
    EmployeeQuery newEmployee = employeeRepository.insert(employee);
    return new ResponseEntity<>(newEmployee, HttpStatus.OK);
  }

  @PutMapping("/employees/{employeeNumber}")
  public ResponseEntity<EmployeeQuery> updateEmployee(@PathVariable("employeeNumber") long orderNumber,
      @Valid @RequestBody Employee employee) {
    EmployeeQuery newOrder = employeeRepository.update(employee);
    return new ResponseEntity<>(newOrder, HttpStatus.OK);
  }

  @DeleteMapping("/employees/{employeeNumber}")
  public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable("employeeNumber") long employeeNumber) {
    try {
      employeeRepository.deleteById(employeeNumber);
    } catch (EmptyResultDataAccessException ex) {
      throw new ResourceNotFoundException(String.format("Employee with employeeNumber %s not found", employeeNumber));
    }
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
