package com.balu.token19.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.balu.token19.domain.Request;

public interface RequestRepository extends JpaRepository<Request, Long> {

	@Query(nativeQuery = true, value = "select * from request where shopdetails_id =:shopdetailsId")
	List<Request> findByShopId(@Param("shopdetailsId") Long shopdetailsId);

	@Query(nativeQuery = true, value = "select * from request where user_id =:userId")
	List<Request> findByUserId(@Param("userId") Long userId);

	@Query(nativeQuery = true, value = "select count(*) from request where shopdetails_id =:shopdetailsId "
			+ "and DATE(created_date) = CURDATE() and and request_status ='confirm' ")
	int findTodayRequestByShopId(@Param("shopdetailsId") Long shopdetailsId);

}
