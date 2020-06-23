package com.balu.token19.service;

import com.balu.token19.dto.ProductAvailabilityDTO;

public interface ProductAvailibilityService {

	ProductAvailabilityDTO savePA(ProductAvailabilityDTO productAvailabilityDTO);
	
	String isAvailableProduct(Long productquantityId,Long userId);

}
