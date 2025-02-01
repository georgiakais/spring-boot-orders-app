package com.mydata.dto;

import com.mydata.entity.Order;
import lombok.Data;
import java.util.List;

@Data
public class DashboardData {
    private List<Order> orders;
    private double totalAmount;
    private long clientCount;
    private int quantity;

}
