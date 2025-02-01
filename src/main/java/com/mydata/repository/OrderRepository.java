package com.mydata.repository;

import com.mydata.entity.Order;
import com.mydata.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserUsername(String username);
    long countByUser(User user);

}
