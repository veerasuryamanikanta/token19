package com.balu.token19.service;

import java.util.List;

import com.balu.token19.dto.ShopDetailsDTO;

public interface ShopDetailsService {

	ShopDetailsDTO saveShopDetails(ShopDetailsDTO shopDetailsDTO);
	
	ShopDetailsDTO getShopDetails(Long userid);
	
	List<ShopDetailsDTO> getShopsbyPincode(String pincode);
	
	
}
