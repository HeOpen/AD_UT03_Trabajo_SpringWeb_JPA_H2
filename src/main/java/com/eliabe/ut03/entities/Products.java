package com.eliabe.ut03.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name="products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id")
    private Integer productId;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false, length = 32)
    private Integer stock;
    @Column(nullable = false)
    private String sku;
    @Column(nullable = false, length = 100)
    private String name;
    @Column(length = 1000)
    private String description;

    @ManyToMany
    @JoinTable(
            name="products_categories",
            joinColumns = @JoinColumn(name="product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Categories> categories;

}
