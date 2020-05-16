package com.balu.token19.serviceimpl;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balu.token19.domain.Device;
import com.balu.token19.dto.DeviceDTO;
import com.balu.token19.repo.DeviceRepository;
import com.balu.token19.service.DeviceService;

@Service
public class DeviceServiceImpl implements DeviceService {

	private static final Mapper mapper = new DozerBeanMapper();

	@Autowired
	private DeviceRepository deviceRepository;

	/*
	 * -----------------SAVE DEVICE -------------
	 */
	@Override
	public DeviceDTO saveDevice(DeviceDTO deviceDTO) {
		Device device = new Device();
		mapper.map(deviceDTO, device);
		Device deviceData = deviceRepository.save(device);
		DeviceDTO deviceDtoData = new DeviceDTO();
		mapper.map(deviceData, deviceDtoData);
		return deviceDtoData;
	}

	/*
	 * -----------------GET DEVICE INFO BY NOTIFICAION ID -------------
	 */
	@Override
	public DeviceDTO findByNotificationId(String notificationId) {
		Device deviceData = deviceRepository.findByNotificationId(notificationId);
		if (deviceData != null) {
			DeviceDTO deviceDtoData = new DeviceDTO();
			mapper.map(deviceData, deviceDtoData);
			return deviceDtoData;
		} else {
			return null;
		}
	}

}
