package com.balu.token19.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.balu.token19.domain.Request;

public interface RequestRepository extends JpaRepository<Request,Long>{
	
	@Query(nativeQuery = true, value = "select * from request where shopdetails_id =:shopdetailsId")
	List<Request> findByShopId(@Param("shopdetailsId") Long shopdetailsId);
	
}
