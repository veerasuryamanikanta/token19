package com.balu.token19.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.balu.token19.domain.Orders;

public interface OrderRepository extends JpaRepository<Orders, Long> {

	@Query(nativeQuery = true, value = "select * from orders where order_id =:orderId")
	Orders findByOrderId(@Param("orderId") String orderId);

}
