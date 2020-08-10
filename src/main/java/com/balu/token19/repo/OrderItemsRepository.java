package com.balu.token19.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.balu.token19.domain.OrderItems;

public interface OrderItemsRepository extends JpaRepository<OrderItems, Long> {

	@Query(nativeQuery = true, value = "select * from order_items where id =:id")
	List<OrderItems> findByOrderId(@Param("id") Long id);

	@Modifying()
	@Transactional
	@Query(nativeQuery = true, value = "UPDATE order_items SET isactive =:value where order_item_id =:orderItemId")
	void updateOrder(@Param("orderItemId") Long orderItemId, @Param("value") boolean value);

	@Modifying()
	@Transactional
	@Query(nativeQuery = true, value = "UPDATE order_items SET update_quantity =:updateQuantity where order_item_id =:orderItemId")
	void updateOrderCount(@Param("orderItemId") Long orderItemId, @Param("updateQuantity") String updateQuantity);

}
