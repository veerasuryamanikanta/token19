package com.balu.token19.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.balu.token19.domain.ShopSubCategory;

public interface ShopSubCategoryRepository extends JpaRepository<ShopSubCategory, Long> {

	@Query(nativeQuery = true, value = "select * from shopsubcategory where shopcategory_id =:shopcategoryId")
	List<ShopSubCategory> findByShopCategoryId(@Param("shopcategoryId") Long shopcategoryId);

}
