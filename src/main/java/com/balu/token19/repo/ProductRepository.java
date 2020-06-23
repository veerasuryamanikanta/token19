package com.balu.token19.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.balu.token19.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query(nativeQuery = true, value = "select * from product where subcategory_id =:subcategoryId")
	List<Product> findProductBySubCategoryId(@Param("subcategoryId") Long subcategoryId);

	@Query(nativeQuery = true, value = "select * from product where product_name =:productName")
	Product findProductByName(@Param("productName") String productName);

}
