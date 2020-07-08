package com.balu.token19.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.balu.token19.domain.ProductQuantities;

public interface ProductQuantityRepository extends JpaRepository<ProductQuantities, Long> {

	@Query(nativeQuery = true, value = "select * from productquantities where product_id =:productId")
	List<ProductQuantities> findByProductId(@Param("productId") Long productId);

	@Query(nativeQuery = true, value = "select * from productquantities where product_id =:productId "
			+ "and productquantity_id not in (select productquantity_id from products_availablity where shopdetails_id =:shopdetailsId "
			+ "and subcategory_id =:subcategoryId and isavailable =false)")
	List<ProductQuantities> findByAvailableProduct(@Param("productId") Long productId,
			@Param("shopdetailsId") Long shopdetailsId, @Param("subcategoryId") Long subcategoryId);

	@Query(nativeQuery = true, value = "select * from productquantities where product_id =:productId "
			+ "and productquantity_id not in (select productquantity_id from vendorproductquantity where shopdetails_id =:shopdetailsId"
			+ " and isavailable =false)")
	List<ProductQuantities> findByVendorProduct(@Param("productId") Long productId,
			@Param("shopdetailsId") Long shopdetailsId);

	@Query(nativeQuery = true, value = "select * from productquantities where product_id =:productId and description =:description")
	ProductQuantities findByProductIdAnddescription(@Param("productId") Long productId,
			@Param("description") String description);
}
