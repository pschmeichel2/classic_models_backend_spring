package com.example.classicmodels.repository;

import java.util.List;
import java.util.Optional;

import com.example.classicmodels.model.query.OrderDetailQuery;

public interface OrderDetailRepositoryCustom {

    List<OrderDetailQuery> findQueryByProductCode(String productCode);

    List<OrderDetailQuery> findQueryByOrderNumber(Long orderNumber);

    List<OrderDetailQuery> findQueryAll();

    Optional<OrderDetailQuery> findQueryByOrderNumberAndProductCode(Long orderNumber, String productCode);

}
