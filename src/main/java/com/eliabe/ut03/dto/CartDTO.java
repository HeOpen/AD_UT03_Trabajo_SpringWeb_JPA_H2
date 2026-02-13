package com.eliabe.ut03.dto;

import lombok.Data;

import java.util.List;

@Data
public class CartDTO {
    private List<CartItemDTO> items;
    private Double totalAmount;
}