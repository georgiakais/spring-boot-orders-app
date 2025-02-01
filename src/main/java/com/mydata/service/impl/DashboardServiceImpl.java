package com.mydata.service.impl;

import com.mydata.dto.DashboardData;
import com.mydata.entity.Order;
import com.mydata.entity.User;
import com.mydata.exception.UserNotFoundException;
import com.mydata.service.DashboardService;
import com.mydata.service.OrderService;
import com.mydata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardServiceImpl implements DashboardService {

    private final OrderService orderService;
    private final UserService userService;

    @Autowired
    public DashboardServiceImpl(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @Override
    public DashboardData getDashboardData(String username) {
        User user = userService.getUserByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        return "ADMIN".equals(user.getRole())
                ? getAdminDashboardData()
                : getUserDashboardData(username);
    }

    private DashboardData getAdminDashboardData() {
        DashboardData data = new DashboardData();
        List<Order> allOrders = orderService.getAllOrders();
        data.setOrders(allOrders);
        data.setTotalAmount(calculateTotalAmount(allOrders));
        data.setClientCount(userService.getAllUsers().stream()
                .filter(u -> "USER".equals(u.getRole()))
                .count());
        return data;
    }

    private DashboardData getUserDashboardData(String username) {
        DashboardData data = new DashboardData();
        List<Order> userOrders = orderService.getOrdersByUsername(username);
        data.setOrders(userOrders);
        data.setTotalAmount(calculateTotalAmount(userOrders));
        return data;
    }

    private double calculateTotalAmount(List<Order> orders) {
        return orders.stream()
                .mapToDouble(Order::getAmount)
                .sum();
    }
}