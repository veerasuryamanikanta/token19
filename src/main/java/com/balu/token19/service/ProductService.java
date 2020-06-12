package com.balu.token19.service;

import java.util.List;

import com.balu.token19.dto.ProductDTO;

public interface ProductService {

	ProductDTO saveProduct(ProductDTO productDTO);

	List<ProductDTO> findProductsBySubCategryAndShopId(Long subcategoryId,Long shopdetailsId);
	
	List<ProductDTO> findProductsBySubCategryId(Long subcategoryId);

	String saveProductItems(ProductDTO productDTO);

}
