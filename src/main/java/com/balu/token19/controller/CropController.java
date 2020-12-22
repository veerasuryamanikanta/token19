package com.balu.token19.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.balu.token19.dto.CropCategoryDTO;
import com.balu.token19.dto.CropDTO;
import com.balu.token19.dto.ErrorObject;
import com.balu.token19.dto.ReturnHolder;
import com.balu.token19.service.CropCategoryService;
import com.balu.token19.service.CropService;

@RestController
@RequestMapping(value = "/crop")
public class CropController {

	@Autowired
	private CropCategoryService cropCategoryService;

	@Autowired
	private CropService cropService;

	/*
	 * -----------------SAVE STORE CATEGORY -------------
	 */
	@RequestMapping(value = "/category/save", method = RequestMethod.POST)
	public ReturnHolder saveCropCategory(@RequestBody CropCategoryDTO cropCategoryDTO) {
		ReturnHolder holder = new ReturnHolder();
		try {
			if (cropCategoryDTO != null) {
				CropCategoryDTO savedCropCategoryDTO = cropCategoryService.saveCropCategory(cropCategoryDTO);
				holder.setResult(savedCropCategoryDTO);
			} else {
				holder = new ReturnHolder(false, new ErrorObject("error", "Data Empty"));
			}
		} catch (Exception e) {
			holder = new ReturnHolder(false, new ErrorObject("error", "Unable to Save." + e));
		}
		return holder;
	}

	/*
	 * -----------------STORE CATEGORY LIST -------------
	 */
	@RequestMapping(value = "/category/list", method = RequestMethod.GET)
	public ReturnHolder getCropCategoryList(@RequestBody CropCategoryDTO cropCategoryDTO) {
		ReturnHolder holder = new ReturnHolder();
		try {
			if (cropCategoryDTO != null) {
				List<CropCategoryDTO> getCropCategoryDTOList = cropCategoryService.getCropCategory();
				holder.setResult(getCropCategoryDTOList);
			} else {
				holder = new ReturnHolder(false, new ErrorObject("error", "Data Empty"));
			}
		} catch (Exception e) {
			holder = new ReturnHolder(false, new ErrorObject("error", "Unable to Save." + e));
		}
		return holder;
	}

	/*
	 * -----------------SAVE STORE -------------
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ReturnHolder saveCrop(@RequestBody CropDTO cropDTO) {
		ReturnHolder holder = new ReturnHolder();
		try {
			if (cropDTO != null) {
				CropDTO savedStoreDTO = cropService.saveCrop(cropDTO);
				holder.setResult(savedStoreDTO);
			} else {
				holder = new ReturnHolder(false, new ErrorObject("error", "Data Empty"));
			}
		} catch (Exception e) {
			holder = new ReturnHolder(false, new ErrorObject("error", "Unable to Save." + e));
		}
		return holder;
	}

	/*
	 * -----------------CROPS LIST -------------
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public ReturnHolder getCropList(@RequestBody CropDTO cropDTO) {
		ReturnHolder holder = new ReturnHolder();
		try {
			if (cropDTO != null) {
				List<CropDTO> getCropDTOList = cropService.getCropByUserId(cropDTO.getCultureId());
				holder.setResult(getCropDTOList);
			} else {
				holder = new ReturnHolder(false, new ErrorObject("error", "Data Empty"));
			}
		} catch (Exception e) {
			holder = new ReturnHolder(false, new ErrorObject("error", "Unable to Save." + e));
		}
		return holder;
	}

}
