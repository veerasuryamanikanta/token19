package com.balu.token19.service;

import java.util.List;

import com.balu.token19.dto.ReportCategoryDTO;

public interface ReportCategoryService {

	ReportCategoryDTO saveReportCategory(ReportCategoryDTO reportCategoryDTO);
	
	List<ReportCategoryDTO> getReportCategory();

}
