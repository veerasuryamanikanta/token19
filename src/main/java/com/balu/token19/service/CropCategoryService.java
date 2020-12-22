package com.balu.token19.service;

import java.util.List;

import com.balu.token19.dto.CropCategoryDTO;

public interface CropCategoryService {

	CropCategoryDTO saveCropCategory(CropCategoryDTO cropCategoryDTO);
	
	List<CropCategoryDTO> getCropCategory();

}
