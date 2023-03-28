package com.example.demo.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.Order;
import com.example.demo.model.OrderDetail;
import com.example.demo.model.keys.OrderDetailPK;
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

    @Transactional
    public Order insert(OrderUpdate orderUpdate) {
        System.out.println("insert");
        int orderNumber = orderRepository.getNewOrderNumber();
        return insertOrder(orderUpdate, orderNumber);
    }

    @Transactional
    public Order update(OrderUpdate orderUpdate) {
        System.out.println("update");
        deleteOrder(orderUpdate);
        return insertOrder(orderUpdate, orderUpdate.getOrderNumber());
    }

    private Order insertOrder(OrderUpdate orderUpdate, long orderNumber) {        
        Order order = Order.fromOrderUpdate(orderUpdate);        
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

    private void deleteOrder(OrderUpdate orderUpdate) {    
        for( OrderDetailUpdate orderDetailUpdate: orderUpdate.getOrderDetails()) {
            OrderDetailPK pkToDelete = orderDetailUpdate.getPk();
            orderDetailRepository.deleteById(pkToDelete);
        }
        orderRepository.deleteById(orderUpdate.getOrderNumber());        
    }

}
