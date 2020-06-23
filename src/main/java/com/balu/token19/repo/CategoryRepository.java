package com.balu.token19.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.balu.token19.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	/*
	 * @Query(nativeQuery = true, value =
	 * "select * from role where role_name =:roleName") Role
	 * findByroleName(@Param("roleName") String roleName);
	 */

}
