package com.balu.token19.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.balu.token19.domain.Crop;

public interface CropRepository extends JpaRepository<Crop, Long> {

	@Query(nativeQuery = true, value = "select * from crop where culture_id =:cultureId")
	List<Crop> findByCultureId(@Param("cultureId") Long userId);

}
