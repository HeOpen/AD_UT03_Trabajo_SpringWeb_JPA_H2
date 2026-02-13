package com.eliabe.ut03.dto;

import lombok.Data;

@Data
public class ShipmentDTO {
    private String address;
    private String zipCode;
    private String city;
    private String state;
    private String country;
}