package com.example.classicmodels.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.classicmodels.model.Order;
import com.example.classicmodels.model.OrderDetail;
import com.example.classicmodels.model.updates.OrderDetailUpdate;
import com.example.classicmodels.model.updates.OrderUpdate;
import com.example.classicmodels.repository.OrderDetailRepository;
import com.example.classicmodels.repository.OrderRepository;

@Component
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Transactional
    public Order insert(OrderUpdate orderUpdate) {
        System.out.println("insert");
        int orderNumber = orderRepository.getNewOrderNumber();
        return insertOrder(orderUpdate, orderNumber);
    }

    @Transactional
    public Order update(OrderUpdate orderUpdate) {
        System.out.println("update");
        deleteOrder(orderUpdate.getOrderNumber());
        Order retOrder = insertOrder(orderUpdate, orderUpdate.getOrderNumber());
        return retOrder;
    }

    private Order insertOrder(OrderUpdate orderUpdate, long orderNumber) {
        Order order = Order.fromOrderUpdate(orderUpdate);
        order.setOrderNumber(Long.valueOf(orderNumber));
        Order newOrder = orderRepository.save(order);
        long orderLineNumber = 1;
        for (OrderDetailUpdate orderDetailUpdate : orderUpdate.getOrderDetails()) {
            OrderDetail orderDetail = OrderDetail.fromOrderDetailUpdate(orderDetailUpdate);
            orderDetail.setOrderNumber(order.getOrderNumber());
            orderDetail.setOrderLineNumber(orderLineNumber);
            orderDetailRepository.save(orderDetail);
            orderLineNumber++;
        }
        return newOrder;
    }

    @Transactional
    public void deleteOrder(long orderNumber) {
        orderDetailRepository.deleteByOrderNumber(orderNumber);
        orderRepository.deleteById(orderNumber);
    }
}
