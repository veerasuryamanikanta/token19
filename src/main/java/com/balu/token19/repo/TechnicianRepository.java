package com.balu.token19.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.balu.token19.domain.Technician;

public interface TechnicianRepository extends JpaRepository<Technician,Long>{
	
	@Query(nativeQuery = true, value = "select * from technician where user_id =:userId")
	Technician findByUserId(@Param("userId") Long userId);
	
}
