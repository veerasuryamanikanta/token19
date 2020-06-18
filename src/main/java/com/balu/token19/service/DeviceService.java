package com.balu.token19.service;

import com.balu.token19.dto.DeviceDTO;

public interface DeviceService {
	
	DeviceDTO saveDevice(DeviceDTO deviceDTO);
	
	DeviceDTO findByNotificationId(String notificationId);
	
	
	DeviceDTO findByDeviceId(String deviceId);
		
}
