package com.balu.token19.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balu.token19.domain.Crop;
import com.balu.token19.dto.CropDTO;
import com.balu.token19.repo.CropCategoryRepository;
import com.balu.token19.repo.CropRepository;
import com.balu.token19.repo.CultureRepository;
import com.balu.token19.service.CropService;
import com.balu.token19.utils.AppConstants;

@Service
public class CropServiceImpl implements CropService {

	@Autowired
	private CropRepository cropRepository;

	@Autowired
	private CultureRepository cultureRepository;

	@Autowired
	private CropCategoryRepository cropCategoryRepository;

	/*
	 * -----------------SAVE Crop -------------
	 */

	@Override
	public CropDTO saveCrop(CropDTO cropDTO) {
		try {
			Crop crop = new Crop();
			AppConstants.mapper.map(cropDTO, crop);
			crop.setCulture(cultureRepository.getOne(cropDTO.getCultureId()));
			crop.setCropcategory(cropCategoryRepository.getOne(cropDTO.getCropCategoryId()));
			Crop savedCrop = cropRepository.save(crop);
			CropDTO returnCropDTO = new CropDTO();
			AppConstants.mapper.map(savedCrop, returnCropDTO);
			returnCropDTO.setCultureId(crop.getCulture().getCultureId());
			returnCropDTO.setCropCategoryId(crop.getCropcategory().getCropCategoryId());
			return returnCropDTO;
		} catch (Exception e) {
			return null;
		}
	}

	/*
	 * -----------------GET Crop LISt-------------
	 */
	@Override
	public List<CropDTO> getCropByUserId(Long cultureId) {
		try {
			List<Crop> CropDataList = cropRepository.findByCultureId(cultureId);
			List<CropDTO> CropDtoList = new ArrayList<CropDTO>();
			if (CropDataList.size() != 0) {
				for (int i = 0; i < CropDataList.size(); i++) {
					Crop crop = (Crop) CropDataList.get(i);
					CropDTO articledto = new CropDTO(crop.getCropId(), crop.getCulture().getCultureId(),
							crop.getCropcategory().getCropCategoryId(), crop.getCropname(), crop.getDescription(),
							crop.getImagepath(), "" + crop.getCreatedDate(), "" + crop.getUpdatedOn(),
							crop.getIsactive());
					CropDtoList.add(articledto);
				}
				return CropDtoList;
			} else {
				return CropDtoList;
			}
		} catch (Exception e) {
			return null;
		}
	}

}
