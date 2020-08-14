package com.balu.token19.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.balu.token19.domain.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

	@Query(nativeQuery = true, value = "select * from cart where user_id =:userId and productquantity_id =:productQtyId")
	Cart findByUserAndProduct(@Param("userId") Long userId, @Param("productQtyId") Long productQtyId);

	@Query(nativeQuery = true, value = "select * from cart where user_id =:userId")
	List<Cart> findByUser(@Param("userId") Long userId);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "delete from cart where user_id =:userId and productquantity_id =:productQtyId")
	void deletItem(@Param("userId") Long userId, @Param("productQtyId") Long productQtyId);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "delete from cart where user_id =:userId")
	void deletAllByUserId(@Param("userId") Long userId);

}
