package com.eliabe.ut03.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name="orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_id")
    private Integer orderId;

    @Column(name = "order_total",nullable = false)
    private Double orderTotal;

    @Column(name="order_date",nullable = false)
    private LocalDateTime orderDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id",nullable = false)
    private Customers customer;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private Set<OrderItems> orderItems;


}