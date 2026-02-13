package com.eliabe.ut03.controllers;

import com.eliabe.ut03.entities.Products;
import com.eliabe.ut03.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // GET /api/products/{page}/{pageSize}
    @GetMapping("/{page}/{pageSize}")
    public ResponseEntity<Page<Products>> getProducts(
            @PathVariable int page,
            @PathVariable int pageSize) {
        return ResponseEntity.ok(productService.getProductsPaged(page, pageSize));
    }

    // GET /api/products/search/{query}/{page}/{pageSize}
    @GetMapping("/search/{query}/{page}/{pageSize}")
    public ResponseEntity<Page<Products>> searchProducts(
            @PathVariable String query,
            @PathVariable int page,
            @PathVariable int pageSize) {
        return ResponseEntity.ok(productService.searchProductsPaged(query, page, pageSize));
    }
}