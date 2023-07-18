package com.example.classicmodels.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.classicmodels.controller.ResourceNotFoundException;
import com.example.classicmodels.model.Employee;
import com.example.classicmodels.model.query.EmployeeQuery;

public class EmployeeRepositoryCustomImpl implements EmployeeRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    // @Autowired
    // EmployeeRepository employeeRepository;

    static final String baseSelect = """
            select
                e.employeeNumber,
                e.lastName,
                e.firstName,
                e.extension,
                e.email,
                e.officeCode,
                e.reportsTo,
                e.jobTitle,
                o.city as city,
                em.lastName as reportsToName
                from Employees e
                left join Offices o on e.officeCode = o.officeCode
                left join Employees em on e.reportsTo = em.employeeNumber
            """;

    @Override
    public List<EmployeeQuery> findQueryByOfficeCode(String officeCode) {
        String sql = baseSelect + """
                where e.officeCode = :officeCode
                order by e.employeeNumber
                """;
        Query query = entityManager.createNativeQuery(sql, EmployeeQuery.class);
        query.setParameter("officeCode", officeCode);

        @SuppressWarnings("unchecked")
        List<EmployeeQuery> employees = query.getResultList();
        return employees;
    };

    @Override
    public List<EmployeeQuery> findQueryByReportsTo(Long reportsTo) {
        String sql = baseSelect + """
                where e.reportsTo = :reportsTo
                order by e.employeeNumber
                """;
        Query query = entityManager.createNativeQuery(sql, EmployeeQuery.class);
        query.setParameter("reportsTo", reportsTo);

        @SuppressWarnings("unchecked")
        List<EmployeeQuery> employees = query.getResultList();
        return employees;
    }

    @Override
    public List<EmployeeQuery> findQueryAll() {
        String sql = baseSelect + """
                order by e.employeeNumber
                """;
        Query query = entityManager.createNativeQuery(sql, EmployeeQuery.class);

        @SuppressWarnings("unchecked")
        List<EmployeeQuery> employees = query.getResultList();
        return employees;
    }

    @Override
    public Optional<EmployeeQuery> findQueryById(Long employeeNumber) {
        String sql = baseSelect + """
                where e.employeeNumber = :employeeNumber
                """;
        Query query = entityManager.createNativeQuery(sql, EmployeeQuery.class);
        query.setParameter("employeeNumber", employeeNumber);

        @SuppressWarnings("unchecked")
        List<EmployeeQuery> employees = query.getResultList();
        return employees.stream().findFirst();
    }

    @Transactional
    @Override
    public EmployeeQuery insert(Employee employee) {
        entityManager.persist(employee);
        EmployeeQuery newEmployee = findQueryById(employee.getEmployeeNumber()).orElse(null);
        return newEmployee;
    }

    @Transactional
    @Override
    // TODO wrong: also accepts inserts
    public EmployeeQuery update(Employee employee) {
        entityManager.merge(employee);
        EmployeeQuery mergedEmployeeQuery = findQueryById(employee.getEmployeeNumber()).orElse(null);
        return mergedEmployeeQuery;
    }

    @Transactional
    @Override
    public void deleteById(Long employeeNumber) {
        Employee employee = entityManager.find(Employee.class, employeeNumber);
        entityManager.remove(employee);
    }
}
