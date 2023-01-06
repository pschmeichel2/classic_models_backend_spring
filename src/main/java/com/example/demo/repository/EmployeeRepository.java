package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    
    @Query("select new com.example.demo.model.Employee("+
        "e.employeeNumber, "+
        "e.lastName, "+
        "e.firstName, "+
        "e.extension, "+
        "e.email, "+
        "e.officeCode, "+
        "e.reportsTo, "+        
        "e.jobTitle, "+
        "o.city, "+
        "em.lastName) " +
    "from Employee e "+
    "left join Office o on e.officeCode = o.officeCode " +
    "left join Employee em on e.reportsTo = em.employeeNumber " +
    "where e.officeCode = ?1 "+
    "order by e.employeeNumber")  // this is JPQL so use classnames
    List<Employee> findByOfficeCode(String officeCode);

    @Query("select new com.example.demo.model.Employee("+
        "e.employeeNumber, "+
        "e.lastName, "+
        "e.firstName, "+
        "e.extension, "+
        "e.email, "+
        "e.officeCode, "+
        "e.reportsTo, "+        
        "e.jobTitle, "+
        "o.city, "+
        "em.lastName) " +
    "from Employee e "+
    "left join Office o on e.officeCode = o.officeCode " +
    "left join Employee em on e.reportsTo = em.employeeNumber " +
    "where e.reportsTo = ?1 "+
    "order by e.employeeNumber")  // this is JPQL so use classnames
    List<Employee> findByReportsTo(Long reportsTo);

    @Query("select new com.example.demo.model.Employee("+
    "e.employeeNumber, "+
    "e.lastName, "+
    "e.firstName, "+
    "e.extension, "+
    "e.email, "+
    "e.officeCode, "+
    "e.reportsTo, "+        
    "e.jobTitle, "+
    "o.city, "+
    "em.lastName) " +
    "from Employee e "+
    "left join Office o on e.officeCode = o.officeCode " +
    "left join Employee em on e.reportsTo = em.employeeNumber " +
    "order by e.employeeNumber")  // this is JPQL so use classnames
    List<Employee> findAll();

    @Query("select new com.example.demo.model.Employee("+
        "e.employeeNumber, "+
        "e.lastName, "+
        "e.firstName, "+
        "e.extension, "+
        "e.email, "+
        "e.officeCode, "+
        "e.reportsTo, "+        
        "e.jobTitle, "+
        "o.city, "+
        "em.lastName) " +
    "from Employee e "+
    "left join Office o on e.officeCode = o.officeCode " +
    "left join Employee em on e.reportsTo = em.employeeNumber " +
    "where e.employeeNumber = ?1 ")  // this is JPQL so use classnames
    Optional<Employee> findById(Long employeeNumber);
}
