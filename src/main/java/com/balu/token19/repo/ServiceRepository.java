package com.balu.token19.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.balu.token19.domain.Services;

public interface ServiceRepository extends JpaRepository<Services, Long> {

	@Query(nativeQuery = true, value = "select * from services where shopdetails_id =:shopdetailsId")
	List<Services> findByShopId(@Param("shopdetailsId") Long shopdetailsId);

}
