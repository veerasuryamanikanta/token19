package com.balu.token19.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.balu.token19.domain.ProductQuantities;

public interface ProductQuantityRepository extends JpaRepository<ProductQuantities, Long> {

	@Query(nativeQuery = true, value = "select * from productquantities where product_id =:productId")
	List<ProductQuantities> findByProductId(@Param("productId") Long productId);

}
