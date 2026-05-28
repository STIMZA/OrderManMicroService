package com.assesment.ordermanmicroservice.repository;

import com.assesment.ordermanmicroservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {}