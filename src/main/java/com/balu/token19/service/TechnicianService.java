package com.balu.token19.service;

import java.util.List;

import com.balu.token19.dto.TechnicianDTO;

public interface TechnicianService {

	TechnicianDTO saveTechnician(TechnicianDTO technicianDTO);

	TechnicianDTO getTechnicianByUserId(Long userId);
	
	List<TechnicianDTO> getTechnicians();

}
