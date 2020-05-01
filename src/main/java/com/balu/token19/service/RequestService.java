package com.balu.token19.service;

import java.util.List;

import com.balu.token19.dto.RequestDTO;

public interface RequestService {
	
	RequestDTO saveRequest(RequestDTO requestDTO);
	
	List<RequestDTO> findByShopId(Long shopdetailsId);
		
}
