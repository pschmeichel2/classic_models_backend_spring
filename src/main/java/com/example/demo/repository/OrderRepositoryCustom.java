package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.query.CustomerOrderQuery;
import com.example.demo.model.query.OrderQuery;

public interface OrderRepositoryCustom {
    Optional<OrderQuery> findQueryById(Long orderNumber);

    List<OrderQuery> findQueryByCustomerNumber(Long customerNumber);

    List<OrderQuery> findQueryAll();

    List<CustomerOrderQuery> findOrderTotalsByCustomerNumber(Long customerNumber);
}
