package com.example.demo.repository;

import java.util.List;
import com.example.demo.model.Customer;

public interface CustomerRepositoryCustom {
    List<Customer> getCustomers(
        String customerName, String contactLastName, 
        String contactFirstName, String country);

    List<String> getCountries();
            
}
