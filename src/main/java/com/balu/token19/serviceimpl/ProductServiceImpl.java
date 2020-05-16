package com.balu.token19.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balu.token19.domain.Product;
import com.balu.token19.dto.ProductDTO;
import com.balu.token19.repo.ProductRepository;
import com.balu.token19.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	private static final Mapper mapper = new DozerBeanMapper();

	@Autowired
	private ProductRepository productRepository;

	/*
	 * -----------------SAVE SUBCATEGORY -------------
	 */
	@Override
	public ProductDTO saveProduct(ProductDTO productDTO) {
		Product product = new Product();
		mapper.map(productDTO, product);
		Product productData = productRepository.save(product);
		ProductDTO productDtoData = new ProductDTO();
		mapper.map(productData, productDtoData);
		return productDtoData;
	}

	/*
	 * -----------------GET PRODUCTS BY SUBCATEGORY ID-------------
	 */
	@Override
	public List<ProductDTO> findProductsBySubCategryId(Long id) {
		List<Product> productData = productRepository.findProductBySubCategoryId(id);
		List<ProductDTO> productDtoList = new ArrayList<>();
		if (productData.size() != 0) {
			for (Product product : productData) {
				ProductDTO productDtoData = new ProductDTO();
				mapper.map(product, productDtoData);
				productDtoList.add(productDtoData);
			}
		}
		return productDtoList;
	}

	@Override
	public List<ProductDTO> findAllProducts() {
		List<Product> productData = productRepository.findAll();
		List<ProductDTO> productDtoList = new ArrayList<>();
		if (productData.size() != 0) {
			for (Product product : productData) {
				ProductDTO productDtoData = new ProductDTO();
				mapper.map(product, productDtoData);
				productDtoList.add(productDtoData);
			}
		}
		return productDtoList;
	}

}
