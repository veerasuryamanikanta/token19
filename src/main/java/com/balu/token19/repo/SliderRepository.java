package com.balu.token19.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.balu.token19.domain.Sliders;

public interface SliderRepository extends JpaRepository<Sliders, Long> {
	
	@Query(nativeQuery = true, value = "select slider_path from sliders where slider_view_type ='n' ")
	String findNotificationImage();

}
