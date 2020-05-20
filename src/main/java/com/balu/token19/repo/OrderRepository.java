package com.balu.token19.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.balu.token19.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

	@Query(nativeQuery = true, value = "select * from order where order_id =:orderId")
	Order findByOrderId(@Param("orderId") String orderId);

}
