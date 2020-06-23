package com.balu.token19.service;

import java.util.List;

import com.balu.token19.dto.CategoryDTO;
import com.balu.token19.dto.ProductCategoryDTO;

public interface CategoryService {

	CategoryDTO saveCategory(CategoryDTO categoryDTO);

	List<CategoryDTO> findAllCategories();

	ProductCategoryDTO saveProductCategory(ProductCategoryDTO productcategoryDTO);

	List<ProductCategoryDTO> findAllProductCategories();

}
