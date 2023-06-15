package com.example.classicmodels.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.classicmodels.model.OrderDetail;
import com.example.classicmodels.model.keys.OrderDetailPK;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailPK>, OrderDetailRepositoryCustom {

    List<OrderDetail> findByOrderNumber(long orderNumber);

    void deleteByOrderNumber(long orderNumber);
}
