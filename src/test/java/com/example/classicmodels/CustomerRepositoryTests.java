package com.example.classicmodels;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.classicmodels.model.Customer;
import com.example.classicmodels.model.Employee;
import com.example.classicmodels.repository.CustomerRepository;
import com.example.classicmodels.repository.EmployeeRepository;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class CustomerRepositoryTests {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void findByIdTest() {
        final Long customerNumber1 = 1225L;
        Customer customer = new Customer();
        customer.setCustomerNumber(customerNumber1);
        Customer savedCustomer = customerRepository.save(customer);
        Optional<Customer> foundCustomer = customerRepository.findById(customerNumber1);
        assertNotNull(foundCustomer);
        assertEquals(savedCustomer.getCustomerNumber(), customerNumber1);
        assertEquals(foundCustomer.get().getCustomerNumber(), customerNumber1);
    }

    @Test
    void findBySalesRepEmployeeNumberTest() {
        final Long customerNumber1 = 1235L;
        final Long customerNumber2 = 1236L;
        final Long employeeNumber1 = 1237L;
        final Long employeeNumber2 = 1238L;

        Customer customer = new Customer();
        customer.setCustomerNumber(customerNumber1);
        customer.setSalesRepEmployeeNumber(employeeNumber1);
        customerRepository.save(customer);

        customer = new Customer();
        customer.setCustomerNumber(customerNumber2);
        customer.setSalesRepEmployeeNumber(employeeNumber2);
        customerRepository.save(customer);
        
        List<Customer> foundCustomers = customerRepository.findBySalesRepEmployeeNumber(employeeNumber1);
        assertEquals(foundCustomers.size(), 1);
        assertEquals(foundCustomers.get(0).getSalesRepEmployeeNumber(), employeeNumber1);
    }

    @Test
    void findBySalesRepEmployeeNumberWithEmployeeNameTest() {
        final Long customerNumber1 = 1245L;
        final Long customerNumber2 = 1246L;
        final Long employeeNumber1 = 1247L;
        final Long employeeNumber2 = 1248L;
        final String employeeName1 = "Employee1";
        final String employeeName2 = "Employee2";

        Employee employee = new Employee();
        employee.setEmployeeNumber(employeeNumber1);
        employee.setLastName(employeeName1);
        employeeRepository.save(employee);

        employee = new Employee();
        employee.setEmployeeNumber(employeeNumber2);
        employee.setLastName(employeeName2);
        employeeRepository.save(employee);

        Customer customer = new Customer();
        customer.setCustomerNumber(customerNumber1);
        customer.setSalesRepEmployeeNumber(employeeNumber1);
        customerRepository.save(customer);

        customer = new Customer();
        customer.setCustomerNumber(customerNumber2);
        customer.setSalesRepEmployeeNumber(employeeNumber2);
        customerRepository.save(customer);

        List<Customer> foundCustomers = customerRepository.findBySalesRepEmployeeNumber(employeeNumber1);
        assertEquals(foundCustomers.size(), 1);
        assertEquals(foundCustomers.get(0).getSalesRepEmployeeNumber(), employeeNumber1);
        assertEquals(foundCustomers.get(0).getSalesRepEmployeeName(), employeeName1);
    }

    @Test
    void getCountriesTest() {
        final Long customerNumber1 = 1255L;
        final Long customerNumber2 = 1256L;
        final Long customerNumber3 = 1257L;
        final String country1 = "country1";
        final String country2 = "country2";

        Customer customer = new Customer();
        customer.setCustomerNumber(customerNumber1);
        customer.setCountry(country1);
        customerRepository.save(customer);

        customer = new Customer();
        customer.setCustomerNumber(customerNumber2);
        customer.setCountry(country1);
        customerRepository.save(customer);

        customer = new Customer();
        customer.setCustomerNumber(customerNumber3);
        customer.setCountry(country2);
        customerRepository.save(customer);

        List<String> countries = customerRepository.getCountries();
        assertEquals(countries.size(), 2);
        assertEquals(countries.get(0), country1);
        assertEquals(countries.get(1), country2);
    }

}