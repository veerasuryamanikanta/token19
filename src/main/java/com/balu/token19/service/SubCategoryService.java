package com.balu.token19.service;

import java.util.List;

import com.balu.token19.dto.SubCategoryDTO;

public interface SubCategoryService {

	SubCategoryDTO saveSubCategory(SubCategoryDTO subCategoryDTO);

	List<SubCategoryDTO> findSubCategoriesByCategryId(Long id);

}
