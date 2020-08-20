package com.balu.token19.service;

import java.util.List;

import com.balu.token19.dto.ShopCategoryDTO;
import com.balu.token19.dto.ShopDetailsDTO;
import com.balu.token19.dto.ShopSubCategoryDTO;

public interface ShopDetailsService {

	ShopDetailsDTO saveShopDetails(ShopDetailsDTO shopDetailsDTO);
	
	ShopDetailsDTO updateShopDetails(ShopDetailsDTO shopDetailsDTO);
	
	ShopDetailsDTO getShopDetails(Long userid);
	
	List<ShopDetailsDTO> getShopsbyPincode(String pincode);
	
	List<ShopDetailsDTO> getAllShopsList();
	
	List<ShopDetailsDTO> getAllShopsListByDetails(ShopDetailsDTO shopDetailsDTO);
	
	ShopCategoryDTO saveShopCategory(ShopCategoryDTO shopcategoryDTO);

	List<ShopCategoryDTO> findAllShopCategories();
	
	ShopSubCategoryDTO saveShopSubCategory(ShopSubCategoryDTO shopsubCategoryDTO);

	List<ShopSubCategoryDTO> findShopSubCategoriesByShopCategryId(Long id);
	
}
