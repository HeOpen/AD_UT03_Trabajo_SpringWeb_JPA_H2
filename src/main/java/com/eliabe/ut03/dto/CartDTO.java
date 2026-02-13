package com.eliabe.ut03.dto;

import lombok.Data;
import java.util.List;

@Data
public class CartDTO {
    private List<CartItemDTO> items;
    private Double totalAmount;
}

@Data
class CartItemDTO {
    private String productName;
    private Integer quantity;
    private Double unitPrice;
    private Double subtotal; // cantidad * precio
}