package com.balu.token19.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.balu.token19.domain.Device;

public interface DeviceRepository extends JpaRepository<Device,Long>{
	
	@Query(nativeQuery = true, value = "select * from device where notification_id =:notificationId")
	Device findByNotificationId(@Param("notificationId") String notificationId);
	
}
