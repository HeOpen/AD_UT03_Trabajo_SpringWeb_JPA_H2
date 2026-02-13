package com.eliabe.ut03.controllers;

import com.eliabe.ut03.dto.CartAddDTO;
import com.eliabe.ut03.dto.CartDTO;
import com.eliabe.ut03.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/{customerId}")
    public ResponseEntity<CartDTO> getCart(@PathVariable Integer customerId) {
        return ResponseEntity.ok(cartService.getCartDTO(customerId)); // [cite: 213, 218]
    }

    @PostMapping("/{customerId}")
    public ResponseEntity<CartDTO> addProduct(@PathVariable Integer customerId, @RequestBody CartAddDTO dto) {
        cartService.addProductToCart(customerId, dto.getProductId(), dto.getQuantity()); // [cite: 219, 221, 223]
        return ResponseEntity.ok(cartService.getCartDTO(customerId));
    }

    @PostMapping("/empty/{customerId}")
    public ResponseEntity<CartDTO> emptyCart(@PathVariable Integer customerId) {
        cartService.clearCart(customerId); // [cite: 229, 232]
        return ResponseEntity.ok(cartService.getCartDTO(customerId));
    }
}