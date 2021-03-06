package com.balu.token19.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.balu.token19.domain.Culture;

public interface CultureRepository extends JpaRepository<Culture,Long>{
	
	@Query(nativeQuery = true, value = "select * from culture where user_id =:userId")
	List<Culture> findByUserId(@Param("userId") Long userId);
	
}
