package com.balu.token19.service;

import java.util.List;

import com.balu.token19.dto.CategoryDTO;

public interface CategoryService {

	CategoryDTO saveCategory(CategoryDTO categoryDTO);

	List<CategoryDTO> findAllCategories();

}
