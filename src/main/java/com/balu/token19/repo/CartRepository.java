package com.balu.token19.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.balu.token19.domain.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

	@Query(nativeQuery = true, value = "select * from cart where user_id =:userId and product_id =:productId")
	Cart findByUserAndProduct(@Param("userId") Long userId, @Param("productId") Long productId);

	@Query(nativeQuery = true, value = "select * from cart where user_id =:userId")
	List<Cart> findByUser(@Param("userId") Long userId);
	
	
	@Query(nativeQuery = true, value = "delete from cart where user_id =:userId and product_id =:productId")
	void deletItem(@Param("userId") Long userId, @Param("productId") Long productId);

}
