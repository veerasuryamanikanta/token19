package com.balu.token19.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.balu.token19.domain.OrderItems;

public interface OrderItemsRepository extends JpaRepository<OrderItems, Long> {

}
