package com.example.classicmodels.repository;

import java.util.List;
import java.util.Optional;

import com.example.classicmodels.model.query.CustomerOrderQuery;
import com.example.classicmodels.model.query.OrderQuery;

public interface OrderRepositoryCustom {
    Optional<OrderQuery> findQueryById(Long orderNumber);

    List<OrderQuery> findQueryByCustomerNumber(Long customerNumber);

    List<OrderQuery> findQueryAll();

    List<CustomerOrderQuery> findOrderTotalsByCustomerNumber(Long customerNumber);
}
