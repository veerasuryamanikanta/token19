package com.balu.token19.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.balu.token19.domain.SubCategory;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {

	@Query(nativeQuery = true, value = "select * from subcategory where category_id =:categoryId")
	List<SubCategory> findByCategoryId(@Param("categoryId") Long categoryId);

}
