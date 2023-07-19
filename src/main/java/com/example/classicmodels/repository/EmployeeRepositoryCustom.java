package com.example.classicmodels.repository;

import java.util.List;
import java.util.Optional;

import com.example.classicmodels.model.Employee;
import com.example.classicmodels.model.query.EmployeeQuery;

public interface EmployeeRepositoryCustom {
    
    List<EmployeeQuery> findQueryByOfficeCode(String officeCode);

    List<EmployeeQuery> findQueryByReportsTo(Long reportsTo);

    List<EmployeeQuery> findQueryAll();

    Optional<EmployeeQuery> findQueryById(Long employeeNumber);
 
    EmployeeQuery insert(Employee employee);

    EmployeeQuery update(Employee employee);

    void delete(Long employeeNumber);
}
