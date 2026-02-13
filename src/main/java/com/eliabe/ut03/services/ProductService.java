package com.eliabe.ut03.services;

import com.eliabe.ut03.entities.Products;
import com.eliabe.ut03.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Page<Products> getProductsPaged(int page, int pageSize){
        Pageable pageable = PageRequest.of(page,pageSize, Sort.by("name").ascending());
        return productRepository.findAll(pageable);
    }

    public Page<Products> searchProductsPaged(String query, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("name").ascending());
        return productRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
                query, query, (java.awt.print.Pageable) pageable
        );
    }



}
