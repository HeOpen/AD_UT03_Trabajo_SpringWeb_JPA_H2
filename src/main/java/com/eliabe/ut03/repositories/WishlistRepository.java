package com.eliabe.ut03.repositories;

import com.eliabe.ut03.olaheliabe.entities.Wishlists;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlists,Integer>{

    List<Wishlists> findByCustomer_CustomerId(Integer customerId);

}
