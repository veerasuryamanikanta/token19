package com.balu.token19.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.balu.token19.dto.ErrorObject;
import com.balu.token19.dto.ReportCategoryDTO;
import com.balu.token19.dto.ReturnHolder;
import com.balu.token19.service.ReportCategoryService;


@RestController
@RequestMapping(value = "/report")
public class ReportsController {

	@Autowired
	private ReportCategoryService ReportCategoryService;

	

	/*
	 * -----------------SAVE Report CATEGORY -------------
	 */
	@RequestMapping(value = "/category/save", method = RequestMethod.POST)
	public ReturnHolder saveReportCategory(@RequestBody ReportCategoryDTO ReportCategoryDTO) {
		ReturnHolder holder = new ReturnHolder();
		try {
			if (ReportCategoryDTO != null) {
				ReportCategoryDTO savedReportCategoryDTO = ReportCategoryService.saveReportCategory(ReportCategoryDTO);
				holder.setResult(savedReportCategoryDTO);
			} else {
				holder = new ReturnHolder(false, new ErrorObject("error", "Data Empty"));
			}
		} catch (Exception e) {
			holder = new ReturnHolder(false, new ErrorObject("error", "Unable to Save." + e));
		}
		return holder;
	}

	/*
	 * -----------------Report CATEGORY LIST -------------
	 */
	@RequestMapping(value = "/category/list", method = RequestMethod.GET)
	public ReturnHolder getReportCategoryList(@RequestBody ReportCategoryDTO ReportCategoryDTO) {
		ReturnHolder holder = new ReturnHolder();
		try {
			if (ReportCategoryDTO != null) {
				List<ReportCategoryDTO> getReportCategoryDTOList = ReportCategoryService.getReportCategory();
				holder.setResult(getReportCategoryDTOList);
			} else {
				holder = new ReturnHolder(false, new ErrorObject("error", "Data Empty"));
			}
		} catch (Exception e) {
			holder = new ReturnHolder(false, new ErrorObject("error", "Unable to Save." + e));
		}
		return holder;
	}




}
