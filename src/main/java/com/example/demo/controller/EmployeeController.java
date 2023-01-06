package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;
    
    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
      List<Employee> employees = new ArrayList<Employee>();
        employeeRepository.findAll().forEach(employees::add);
      if (employees.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
  
      return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/employees/{employeeNumber}")
    public ResponseEntity<Employee> getEmployeeByEmployeeNumber(@PathVariable("employeeNumber") long employeeNumber) {
      Employee _employee = employeeRepository.findById(employeeNumber)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee with id = " + employeeNumber+ " not found"));
    
      return new ResponseEntity<>(_employee, HttpStatus.OK );  
    }

    @GetMapping("/offices/{officeCode}/employees")
    public ResponseEntity<List<Employee>> getEmployeesByOfficeCode(@PathVariable("officeCode") String officeCode) {    
      List<Employee> employees = new ArrayList<Employee>();
      employees = employeeRepository.findByOfficeCode(officeCode);      
      if (employees.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/employees/{employeeNumber}/employees")
    public ResponseEntity<List<Employee>> getEmployeesByReportsTo(@PathVariable("employeeNumber") Long employeeNumber) {    
      List<Employee> employees = new ArrayList<Employee>();
      employees = employeeRepository.findByReportsTo(employeeNumber);      
      if (employees.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(employees, HttpStatus.OK);
    }


}
