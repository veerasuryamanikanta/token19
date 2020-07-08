package com.balu.token19.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.balu.token19.domain.VendorProduct;

public interface VendorProductRepository extends JpaRepository<VendorProduct, Long> {

	@Query(nativeQuery = true, value = "select * from vendor_product where user_id =:userId and "
			+ "subcategory_id =:subcategoryId and product_id =:productId and shopdetails_id =:shopdetailsId")
	VendorProduct findByUserAndSubCategory(@Param("userId") Long userId, @Param("subcategoryId") Long subcategoryId,
			@Param("productId") Long productId, @Param("shopdetailsId") Long shopdetailsId);

	@Query(nativeQuery = true, value = "select * from vendor_product where user_id =:userId and "
			+ "subcategory_id =:subcategoryId")
	List<VendorProduct> findBySubCategory(@Param("userId") Long userId, @Param("subcategoryId") Long subcategoryId);

	@Query(nativeQuery = true, value = "select * from vendor_product where shopdetails_id =:shopdetailsId and "
			+ "subcategory_id =:subcategoryId")
	List<VendorProduct> findByShopAndSubCategory(@Param("shopdetailsId") Long shopdetailsId,
			@Param("subcategoryId") Long subcategoryId);

}
