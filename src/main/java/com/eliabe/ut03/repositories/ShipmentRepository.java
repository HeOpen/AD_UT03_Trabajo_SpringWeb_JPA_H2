package com.eliabe.ut03.repositories;

import com.eliabe.ut03.entities.Shipments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipments, Integer> {

    boolean existsByOrder_OrderId(Integer orderId);
}
