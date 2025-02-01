package com.mydata.controller;

import com.mydata.entity.Order;
import com.mydata.entity.User;
import com.mydata.exception.UserNotFoundException;
import com.mydata.service.OrderService;
import com.mydata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<List<Order>> getOrdersByUsername(@PathVariable String username) {
        userService.getUserByUsername(username).orElseThrow(() ->
                new UserNotFoundException("User not found with username: " + username));

        List<Order> orders = orderService.getOrdersByUsername(username);
        return ResponseEntity.ok(orders);
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order createdOrder = orderService.createOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder); // 201 Created
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order updatedOrder) {
        Order order = orderService.updateOrder(id, updatedOrder);
        return ResponseEntity.ok(order); // 200 OK
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderById(@PathVariable Long id) {
        orderService.deleteOrderById(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}