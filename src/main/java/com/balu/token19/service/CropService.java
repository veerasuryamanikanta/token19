package com.balu.token19.service;

import java.util.List;

import com.balu.token19.dto.CropDTO;

public interface CropService {

	CropDTO saveCrop(CropDTO cropDTO);
	
	List<CropDTO> getCropByUserId(Long userId);

}
