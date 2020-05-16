package com.balu.token19.service;

import java.util.List;

import com.balu.token19.dto.ProductDTO;

public interface ProductService {

	ProductDTO saveProduct(ProductDTO productDTO);

	List<ProductDTO> findProductsBySubCategryId(Long id);

	List<ProductDTO> findAllProducts();

}
