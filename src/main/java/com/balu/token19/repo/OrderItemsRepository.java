package com.balu.token19.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.balu.token19.domain.OrderItems;

public interface OrderItemsRepository extends JpaRepository<OrderItems, Long> {

	@Query(nativeQuery = true, value = "select * from order_items where id =:id")
	List<OrderItems> findByOrderId(@Param("id") Long id);
	
}
