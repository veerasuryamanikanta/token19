package com.balu.token19.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balu.token19.domain.CropCategory;
import com.balu.token19.dto.CropCategoryDTO;
import com.balu.token19.repo.CropCategoryRepository;
import com.balu.token19.service.CropCategoryService;
import com.balu.token19.utils.AppConstants;

@Service
public class CropCategoryServiceImpl implements CropCategoryService {

	@Autowired
	private CropCategoryRepository cropCategoryRepository;

	/*
	 * -----------------SAVE Crop CATEGORIE -------------
	 */

	@Override
	public CropCategoryDTO saveCropCategory(CropCategoryDTO cropCategoryDTO) {
		try {
			CropCategory cropCategory = new CropCategory();
			AppConstants.mapper.map(cropCategoryDTO, cropCategory);
			CropCategory savedCropCategory = cropCategoryRepository.save(cropCategory);
			CropCategoryDTO returnCropCategoryDTO = new CropCategoryDTO();
			AppConstants.mapper.map(savedCropCategory, returnCropCategoryDTO);
			return returnCropCategoryDTO;
		} catch (Exception e) {
			return null;
		}
	}

	/*
	 * -----------------GET Crop CATEGORIES -------------
	 */

	@Override
	public List<CropCategoryDTO> getCropCategory() {
		try {
			List<CropCategory> cropCategoryList = cropCategoryRepository.findAll();
			List<CropCategoryDTO> cropCategoryDTOList = new ArrayList<CropCategoryDTO>();
			if (cropCategoryList.size() != 0) {
				for (int i = 0; i < cropCategoryList.size(); i++) {
					CropCategory cropCategory = (CropCategory) cropCategoryList.get(i);
					CropCategoryDTO cropCategoryDTO = new CropCategoryDTO(cropCategory.getCropCategoryId(),
							cropCategory.getCropCategoryName(), cropCategory.getCropCategoryCode(),
							"" + cropCategory.getCreatedDate(), "" + cropCategory.getUpdatedOn(),
							cropCategory.getIsactive());
					cropCategoryDTOList.add(cropCategoryDTO);
				}
				return cropCategoryDTOList;
			} else {
				return cropCategoryDTOList;
			}
		} catch (Exception e) {
			return null;
		}
	}

}
