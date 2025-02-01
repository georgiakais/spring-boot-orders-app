package com.mydata.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private Double quantity;

    @Column(nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User user;
}
