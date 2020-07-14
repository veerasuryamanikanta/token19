package com.balu.token19.service;

import java.util.List;

import com.balu.token19.dto.ProductDTO;
import com.balu.token19.dto.ProductImagesDTO;

public interface ProductService {

	ProductDTO saveProduct(ProductDTO productDTO);

	List<ProductDTO> findProductsBySubCategryAndShopId(Long subcategoryId,Long shopdetailsId);
	
	List<ProductDTO> availableProductsBySubCategryAndShopId(Long subcategoryId,Long shopdetailsId);
	
	List<ProductDTO> findProductsBySubCategryId(Long subcategoryId,Long userId);

	String saveProductItems(ProductDTO productDTO);
	
	String deleteQuantityImage(Long productimageId);
	
	String deleteQuantity(Long productquantityId);
	
	List<ProductImagesDTO> getProductImages(Long productId);
	

}
