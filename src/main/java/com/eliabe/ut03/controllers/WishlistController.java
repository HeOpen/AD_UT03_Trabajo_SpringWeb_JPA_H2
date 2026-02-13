package com.eliabe.ut03.controllers;

import com.eliabe.ut03.dto.WishlistCreateDTO;
import com.eliabe.ut03.entities.Wishlists;
import com.eliabe.ut03.services.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/wishlists")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    @GetMapping("/list/{customerId}")
    public ResponseEntity<List<Wishlists>> listWishlists(@PathVariable Integer customerId) {
        return ResponseEntity.ok(wishlistService.getWishlistsByCustomer(customerId)); // [cite: 240, 244]
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<Wishlists> create(@PathVariable Integer customerId, @RequestBody WishlistCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(wishlistService.createWishlist(customerId, dto)); // [cite: 245, 247, 188]
    }

    @DeleteMapping("/{wishlistId}")
    public ResponseEntity<Void> delete(@PathVariable Integer wishlistId) {
        wishlistService.deleteWishlist(wishlistId); // [cite: 248, 253]
        return ResponseEntity.noContent().build();
    }
}