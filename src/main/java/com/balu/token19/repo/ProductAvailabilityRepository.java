package com.balu.token19.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.balu.token19.domain.ProductsAvailablity;

public interface ProductAvailabilityRepository extends JpaRepository<ProductsAvailablity, Long> {

	@Query(nativeQuery = true, value = "select * from products_availablity where user_id =:userId"
			+ " and productquantity_id =:productquantityId")
	ProductsAvailablity findByUserid(@Param("userId") Long userId, @Param("productquantityId") Long productquantityId);

	@Modifying()
	@Transactional
	@Query(nativeQuery = true, value = "UPDATE products_availablity SET isavailable =:value where user_id =:userId and "
			+ "productquantity_id =:productquantityId")
	void updateAvailibility(@Param("userId") Long userId, @Param("productquantityId") Long productquantityId,
			@Param("value") boolean value);

	@Query(nativeQuery = true, value = "select * from products_availablity where user_id =:userId"
			+ " and productquantity_id =:productquantityId and shopdetails_id =1 and subcategory_id =1;")
	List<ProductsAvailablity> findByAvaialbility(@Param("userId") Long userId,
			@Param("productquantityId") Long productquantityId);

	@Query(nativeQuery = true, value = "select * from products_availablity where user_id =:userId"
			+ " and productquantity_id =:productquantityId and isavailable =:value")
	ProductsAvailablity findByIsAvailable(@Param("userId") Long userId,
			@Param("productquantityId") Long productquantityId, @Param("value") boolean value);

}
