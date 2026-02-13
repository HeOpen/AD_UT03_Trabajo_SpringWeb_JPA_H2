package com.eliabe.ut03.repositories;

import com.eliabe.ut03.entities.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository {

    List<CartItems> findByCustomer_CustomerIdOrderByProduct_NameAsc(Integer customerId);

    //El nombre de los metodos son largos y tediosos.
    Optional<CartItems> findByCustomer_CustomerIdAndProduct_ProductId(Integer customerId, Integer productId);

    void deleteByCustomer_CustomerId(Integer customerId);

}
