package com.balu.token19.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.balu.token19.domain.ProductImages;

public interface ProductImagesRepository extends JpaRepository<ProductImages, Long> {

	@Query(nativeQuery = true, value = "select * from productimages where productquantity_id =:productquantityId")
	List<ProductImages> findByProductQuantityId(@Param("productquantityId") Long productquantityId);

}
