package com.eliabe.ut03.repositories;

import com.eliabe.ut03.olaheliabe.entities.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customers, Integer> {



}
