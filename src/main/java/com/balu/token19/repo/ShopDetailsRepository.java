package com.balu.token19.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.balu.token19.domain.ShopDetails;

public interface ShopDetailsRepository extends JpaRepository<ShopDetails,Long>{
	
	@Query(nativeQuery = true, value = "select * from shop_details where user_id =:userId")
	ShopDetails findByUserId(@Param("userId") Long userId);
	
	@Query(nativeQuery = true, value = "select * from shop_details where pincode =:pincode")
	List<ShopDetails> findByPincode(@Param("pincode") String pincode);
	
}
