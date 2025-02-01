package com.mydata.service;

import com.mydata.entity.Order;

import java.util.List;


public interface OrderService {
    Order createOrder(Order order);
    List<Order> getAllOrders();
    List<Order> getOrdersByUsername(String username);
    Order updateOrder(Long id,Order order);
    void deleteOrderById(Long id);
}
