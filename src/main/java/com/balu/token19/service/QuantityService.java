package com.balu.token19.service;

import java.util.List;

import com.balu.token19.dto.QuantityDTO;

public interface QuantityService {

	QuantityDTO saveQuantity(QuantityDTO quantityDTO);

	List<QuantityDTO> findAllQuantities();

}
