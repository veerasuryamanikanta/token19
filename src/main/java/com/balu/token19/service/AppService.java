package com.balu.token19.service;

import java.util.List;

import com.balu.token19.dto.ServicesDTO;

public interface AppService {

	ServicesDTO saveService(ServicesDTO servicesDTO);

	List<ServicesDTO> findByShopId(Long shopdetailsId);

}
