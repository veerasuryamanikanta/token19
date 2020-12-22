package com.balu.token19.service;

import java.util.List;

import com.balu.token19.dto.CommonDTO;
import com.balu.token19.dto.CultureDTO;


public interface CultureService {

	CultureDTO saveCulture(CultureDTO cultureDTO);
	
	List<CultureDTO> saveAllCultures(CommonDTO commonDTO);
	
	List<CultureDTO> getCultureByUserId(Long userId);

}
