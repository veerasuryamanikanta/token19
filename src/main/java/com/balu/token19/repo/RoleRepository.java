package com.balu.token19.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.balu.token19.domain.Role;

public interface RoleRepository extends JpaRepository<Role,Long>{
	
	@Query(nativeQuery = true, value = "select * from role where role_name =:roleName")
	Role findByroleName(@Param("roleName") String roleName);
	
}
