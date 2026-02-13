package com.eliabe.ut03.olaheliabe.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
        name="order_items",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {
                        "order_id","product_id"
                })
        }

)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Integer orderItemId;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false,name = "unit_price")
    private Double unitPrice;

    @ManyToOne(optional = false)
    @JoinColumn(name = "order_id",nullable = false)
    private Orders order;

    @ManyToOne(optional = false)
    @JoinColumn(name="product_id",nullable = false)
    private Products product;

}