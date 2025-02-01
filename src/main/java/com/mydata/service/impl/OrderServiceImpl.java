package com.mydata.service.impl;

import com.mydata.entity.Order;
import com.mydata.entity.User;
import com.mydata.exception.OrderNotFoundException;
import com.mydata.exception.UserNotFoundException;
import com.mydata.repository.OrderRepository;
import com.mydata.repository.UserRepository;
import com.mydata.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Order createOrder(Order order) {
        if (order.getUser() == null || order.getUser().getId() == null) {
            throw new UserNotFoundException("User information is required to create an order.");
        }
        User user = userRepository.findById(order.getUser().getId())
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + order.getUser().getId()));
        order.setUser(user);
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getOrdersByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found with username: " + username));
        return user.getOrders();
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order updateOrder(Long id, Order updatedOrder) {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with ID: " + id));
        existingOrder.setDescription(updatedOrder.getDescription());
        existingOrder.setAmount(updatedOrder.getAmount());
        if (updatedOrder.getUser() != null && updatedOrder.getUser().getId() != null) {
            User user = userRepository.findById(updatedOrder.getUser().getId())
                    .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + updatedOrder.getUser().getId()));
            existingOrder.setUser(user);
        }
        return orderRepository.save(existingOrder);
    }

    @Override
    public void deleteOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with ID: " + id));
        orderRepository.deleteById(id);
    }
}
