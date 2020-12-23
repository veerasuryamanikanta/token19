package com.balu.token19.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balu.token19.domain.ReportCategory;
import com.balu.token19.dto.ReportCategoryDTO;
import com.balu.token19.repo.ReportCategoryRepository;
import com.balu.token19.service.ReportCategoryService;
import com.balu.token19.utils.AppConstants;

@Service
public class ReportCategoryServiceImpl implements ReportCategoryService {

	@Autowired
	private ReportCategoryRepository reportCategoryRepository;

	/*
	 * -----------------SAVE Report CATEGORIE -------------
	 */

	@Override
	public ReportCategoryDTO saveReportCategory(ReportCategoryDTO reportCategoryDTO) {
		try {
			ReportCategory reportCategory = new ReportCategory();
			AppConstants.mapper.map(reportCategoryDTO, reportCategory);
			ReportCategory savedReportCategory = reportCategoryRepository.save(reportCategory);
			ReportCategoryDTO returnReportCategoryDTO = new ReportCategoryDTO();
			AppConstants.mapper.map(savedReportCategory, returnReportCategoryDTO);
			return returnReportCategoryDTO;
		} catch (Exception e) {
			return null;
		}
	}

	/*
	 * -----------------GET Report CATEGORIES -------------
	 */

	@Override
	public List<ReportCategoryDTO> getReportCategory() {
		try {
			List<ReportCategory> reportCategoryList = reportCategoryRepository.findAll();
			List<ReportCategoryDTO> ReportCategoryDTOList = new ArrayList<ReportCategoryDTO>();
			if (reportCategoryList.size() != 0) {
				for (int i = 0; i < reportCategoryList.size(); i++) {
					ReportCategory reportCategory = (ReportCategory) reportCategoryList.get(i);
					ReportCategoryDTO reportCategoryDTO = new ReportCategoryDTO(reportCategory.getReportCategoryId(),
							reportCategory.getReportCategoryName(), reportCategory.getReportCategoryCode(),
							"" + reportCategory.getCreatedDate(), "" + reportCategory.getUpdatedOn(),
							reportCategory.isIsactive());
					ReportCategoryDTOList.add(reportCategoryDTO);
				}
				return ReportCategoryDTOList;
			} else {
				return ReportCategoryDTOList;
			}
		} catch (Exception e) {
			return null;
		}
	}

}
