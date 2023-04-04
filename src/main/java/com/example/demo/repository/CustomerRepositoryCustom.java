package com.example.demo.repository;

import java.util.List;
import com.example.demo.model.Customer;
import com.example.demo.model.query.CustomerBalanceLineQuery;

public interface CustomerRepositoryCustom {
    List<Customer> getCustomers(
            String customerName, String contactLastName,
            String contactFirstName, String country);

    List<String> getCountries();
    List<CustomerBalanceLineQuery> getCustomerBalanceLines(Long customerNumber);

}
