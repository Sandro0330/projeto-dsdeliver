package com.cursodev.dsdeliver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cursodev.dsdeliver.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
