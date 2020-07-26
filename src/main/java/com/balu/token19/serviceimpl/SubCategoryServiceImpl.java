package com.balu.token19.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balu.token19.domain.SubCategory;
import com.balu.token19.dto.SubCategoryDTO;
import com.balu.token19.repo.CategoryRepository;
import com.balu.token19.repo.ShopSubCategoryRepository;
import com.balu.token19.repo.SubCategoryRepository;
import com.balu.token19.service.SubCategoryService;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {

	private static final Mapper mapper = new DozerBeanMapper();

	@Autowired
	private SubCategoryRepository subCategoryRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ShopSubCategoryRepository shopsubcategoryRepository;

	/*
	 * -----------------SAVE SUBCATEGORY -------------
	 */
	@Override
	public SubCategoryDTO saveSubCategory(SubCategoryDTO subCategoryDTO) {
		SubCategory subCategory = new SubCategory();
		mapper.map(subCategoryDTO, subCategory);
		subCategory.setCategory(categoryRepository.getOne(subCategoryDTO.getCategoryId()));
		subCategory.setShopsubcategory(shopsubcategoryRepository.getOne(subCategoryDTO.getShopsubcategoryId()));
		SubCategory subCategoryData = subCategoryRepository.save(subCategory);
		SubCategoryDTO subCategoryDtoData = new SubCategoryDTO();
		mapper.map(subCategoryData, subCategoryDtoData);
		subCategoryDtoData.setCategoryId(subCategoryData.getCategory().getCategoryId());
		subCategoryDtoData.setShopsubcategoryId(subCategoryData.getShopsubcategory().getShopsubcategoryId());
		subCategoryDtoData.setShopsubcategoryName(subCategoryData.getShopsubcategory().getShopsubcategoryName());
		return subCategoryDtoData;
	}

	/*
	 * -----------------GET SUB CATEGORIES BY CATEGORY ID-------------
	 */
	@Override
	public List<SubCategoryDTO> findSubCategoriesByCategryId(Long id) {
		List<SubCategory> subCategoryData = subCategoryRepository.findByCategoryId(id);
		List<SubCategoryDTO> subCategoryDtoList = new ArrayList<>();
		if (subCategoryData.size() != 0) {
			for (SubCategory subCategory : subCategoryData) {
				SubCategoryDTO subCategoryDtoData = new SubCategoryDTO();
				mapper.map(subCategory, subCategoryDtoData);
				subCategoryDtoData.setCategoryId(subCategory.getCategory().getCategoryId());
				subCategoryDtoData.setShopsubcategoryId(subCategory.getShopsubcategory().getShopsubcategoryId());
				subCategoryDtoData.setShopsubcategoryName(subCategory.getShopsubcategory().getShopsubcategoryName());
				subCategoryDtoList.add(subCategoryDtoData);
			}
		}
		return subCategoryDtoList;
	}
}
