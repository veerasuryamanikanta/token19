package com.balu.token19.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balu.token19.domain.Category;
import com.balu.token19.dto.CategoryDTO;
import com.balu.token19.repo.CategoryRepository;
import com.balu.token19.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	private static final Mapper mapper = new DozerBeanMapper();

	@Autowired
	private CategoryRepository categoryRepository;

	/*
	 * -----------------SAVE CATEGORY -------------
	 */
	@Override
	public CategoryDTO saveCategory(CategoryDTO categoryDTO) {
		Category category = new Category();
		mapper.map(categoryDTO, category);
		Category categoryData = categoryRepository.save(category);
		CategoryDTO categoryDtoData = new CategoryDTO();
		mapper.map(categoryData, categoryDtoData);
		return categoryDtoData;
	}

	/*
	 * -----------------GET CATEGORIES -------------
	 */
	@Override
	public List<CategoryDTO> findAllCategories() {
		List<Category> categoryData = categoryRepository.findAll();
		List<CategoryDTO> categoryDtoList = new ArrayList<>();
		if (categoryData.size() != 0) {
			for (Category category : categoryData) {
				CategoryDTO categoryDtoData = new CategoryDTO();
				mapper.map(category, categoryDtoData);
				categoryDtoList.add(categoryDtoData);
			}
		}
		return categoryDtoList;
	}

}
