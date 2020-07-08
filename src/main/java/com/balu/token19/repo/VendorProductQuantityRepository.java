package com.balu.token19.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.balu.token19.domain.VendorProductQuantity;

public interface VendorProductQuantityRepository extends JpaRepository<VendorProductQuantity, Long> {

	@Query(nativeQuery = true, value = "select * from vendorproductquantity where user_id =:userId and "
			+ " productquantity_id =:productquantityId")
	VendorProductQuantity findByUserShopQuantity(@Param("userId") Long userId,
			 @Param("productquantityId") Long productquantityId);

	@Query(nativeQuery = true, value = "select * from vendorproductquantity where user_id =:userId and "
			+ "productquantity_id =:productquantityId")
	VendorProductQuantity findByProductQuantityId(@Param("userId") Long userId,
			@Param("productquantityId") Long productquantityId);

	@Query(nativeQuery = true, value = "select * from vendorproductquantity where " + "shopdetails_id =:shopdetailsId")
	List<VendorProductQuantity> findByUserShopProduct(@Param("shopdetailsId") Long shopdetailsId);
	
	@Query(nativeQuery = true, value = "select * from vendorproductquantity where vendorproduct_id =:vendorproductId")
	List<VendorProductQuantity> findByVendorProductId(@Param("vendorproductId") Long vendorproductId);

}
