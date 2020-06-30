package com.balu.token19.service;

import java.util.List;

import com.balu.token19.dto.SlidersDTO;

public interface SliderService {

	SlidersDTO saveSliders(SlidersDTO slidersDto);
	
	List<SlidersDTO> slidersList();

}
