package com.eliabe.ut03.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
        name = "cart_items",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {
                        "customer_id","product_id"
                })
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItems {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="cart_item_id")
        private Integer cartItemId;

        @Column(nullable = false)
        private Integer quantity;

        @ManyToOne
        @JoinColumn(name="customer_id",nullable = false)
        private Customers customer;

        @ManyToOne
        @JoinColumn(name="product_id",nullable = false)
        private Products product;

}
