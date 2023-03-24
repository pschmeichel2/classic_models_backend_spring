package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.Order;
import com.example.demo.model.OrderDetail;
import com.example.demo.model.updates.OrderDetailUpdate;
import com.example.demo.model.updates.OrderUpdate;
import com.example.demo.repository.OrderDetailRepository;
import com.example.demo.repository.OrderRepository;

@Component
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    public Order save(OrderUpdate orderUpdate) {
        System.out.println("save");
        Order order = Order.fromOrderUpdate(orderUpdate);
        int orderNumber = orderRepository.getNewOrderNumber();
        order.setOrderNumber(Long.valueOf(orderNumber));
        Order newOrder = orderRepository.save(order);
        long orderLineNumber = 1;
        for( OrderDetailUpdate orderDetailUpdate: orderUpdate.getOrderDetails()) {
            OrderDetail orderDetail = OrderDetail.fromOrderDetailUpdate(orderDetailUpdate);
            orderDetail.setOrderNumber(order.getOrderNumber());
            orderDetail.setOrderLineNumber(orderLineNumber);
            OrderDetail newOrderDetail = orderDetailRepository.save(orderDetail);            
            orderLineNumber++;
        }
        return newOrder;
    }

}
