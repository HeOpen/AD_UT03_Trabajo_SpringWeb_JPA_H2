package com.eliabe.ut03.services;

import com.eliabe.ut03.entities.Customers;
import com.eliabe.ut03.entities.Wishlists;
import com.eliabe.ut03.repositories.CustomerRepository;
import com.eliabe.ut03.repositories.WishlistRepository;
import com.eliabe.ut03.dto.WishlistCreateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public List<Wishlists> getWishlistsByCustomer(Integer customerId) {
        return wishlistRepository.findByCustomer_CustomerId(customerId);
    }

    @Transactional
    public Wishlists createWishlist(Integer customerId, WishlistCreateDTO dto) {
        Customers customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        Wishlists wishlist = new Wishlists();
        wishlist.setName(dto.getName());
        wishlist.setShared(dto.getShared());
        wishlist.setCustomer(customer);
        return wishlistRepository.save(wishlist);
    }

    @Transactional
    public void deleteWishlist(Integer wishlistId) {
        Wishlists wishlist = wishlistRepository.findById(wishlistId)
                .orElseThrow(() -> new RuntimeException("Lista no encontrada"));

        if (wishlist.getProducts() != null && !wishlist.getProducts().isEmpty()) {
            throw new RuntimeException("No se puede eliminar una lista que contiene productos");
        }
        wishlistRepository.delete(wishlist);
    }
}