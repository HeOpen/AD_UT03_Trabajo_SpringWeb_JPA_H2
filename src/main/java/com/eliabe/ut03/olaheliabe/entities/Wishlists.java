package com.eliabe.ut03.olaheliabe.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name="wishlists")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Wishlists {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="wishlist_id")
    private Integer wishlistId;

    @Column(nullable = false)
    private Boolean shared;

    @Column(nullable = false, length = 100)
    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(name="customer_id",nullable = false)
    private Customers customer;

    @ManyToMany
    @JoinTable(
            name="wishlist_products",
            joinColumns = @JoinColumn(name = "wishlist_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<Products>products;

}
