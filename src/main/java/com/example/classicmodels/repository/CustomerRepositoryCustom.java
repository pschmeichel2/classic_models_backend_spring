package com.example.classicmodels.repository;

import java.util.List;

import com.example.classicmodels.model.Customer;
import com.example.classicmodels.model.query.CustomerBalanceLineQuery;
import com.example.classicmodels.model.query.CustomerPurchaseAggregateQuery;
import com.example.classicmodels.model.query.CustomerPurchaseQuery;

public interface CustomerRepositoryCustom {
    List<Customer> getCustomers(
            String customerName, String contactLastName,
            String contactFirstName, String country);

    List<String> getCountries();

    List<CustomerBalanceLineQuery> getCustomerBalanceLines(Long customerNumber);

    List<CustomerPurchaseQuery> getCustomerPurchases(Long customerNumber);

    List<CustomerPurchaseAggregateQuery> getCustomerPurchaseAggregates(Long customerNumber);
}
