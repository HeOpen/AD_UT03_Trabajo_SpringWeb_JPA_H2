package com.eliabe.ut03.olaheliabe.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="shipments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Shipments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="shipment_id")
    private Integer shipmentId;

    @Column(nullable = false, name="shipment_date",length = 6)
    private LocalDateTime shipmentDate;

    @Column(nullable = false, name="zip_code",length = 10)
    private String zipCode;

    @Column(nullable = false, length = 50)
    private String city;

    @Column(nullable = false, length = 50)
    private String country;

    @Column(nullable = false, length = 50)
    private String state;

    @Column(nullable = false,length = 250)
    private String address;

    @OneToOne(optional = false)
    @JoinColumn(name="order_id",nullable = false,unique = true)
    private Orders order;

}
