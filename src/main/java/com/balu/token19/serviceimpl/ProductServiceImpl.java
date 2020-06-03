package com.balu.token19.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balu.token19.domain.Product;
import com.balu.token19.domain.ProductImages;
import com.balu.token19.domain.ProductQuantities;
import com.balu.token19.dto.ProductDTO;
import com.balu.token19.dto.ProductImagesDTO;
import com.balu.token19.dto.ProductQuantitiesDTO;
import com.balu.token19.repo.ProductCategoryRepository;
import com.balu.token19.repo.ProductImagesRepository;
import com.balu.token19.repo.ProductQuantityRepository;
import com.balu.token19.repo.ProductRepository;
import com.balu.token19.repo.QuantityRepository;
import com.balu.token19.repo.SubCategoryRepository;
import com.balu.token19.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	private static final Mapper mapper = new DozerBeanMapper();

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private SubCategoryRepository subCategoryRepository;

	@Autowired
	private QuantityRepository quantityRepository;

	@Autowired
	private ProductQuantityRepository productquantityRepository;

	@Autowired
	private ProductCategoryRepository productcategoryRepository;

	@Autowired
	private ProductImagesRepository productImagesRepository;

	/*
	 * -----------------SAVE SUBCATEGORY -------------
	 */
	@Override
	public ProductDTO saveProduct(ProductDTO productDTO) {

		List<ProductQuantitiesDTO> productQuantitiesDTOs = productDTO.getProductQuantitiesDTOs();
		if (productQuantitiesDTOs.size() != 0) {
			Product product = new Product();
			mapper.map(productDTO, product);
			product.setSubcategory(subCategoryRepository.getOne(productDTO.getSubcategoryId()));
			product.setProductcategory(productcategoryRepository.getOne(productDTO.getProductcategoryId()));
			Product productData = productRepository.save(product);
			Long productId = productData.getProductId();
			for (ProductQuantitiesDTO productQuantitiesDTO : productQuantitiesDTOs) {
				ProductQuantities productQuantities = new ProductQuantities();
				mapper.map(productQuantitiesDTO, productQuantities);
				productQuantities.setQuantity(quantityRepository.getOne(productQuantitiesDTO.getQuantityId()));
				productQuantities.setProduct(productRepository.getOne(productId));
				ProductQuantities productQuantitiesData = productquantityRepository.save(productQuantities);
				Long producttQuantityId = productQuantitiesData.getProductquantityId();

				List<ProductImagesDTO> productImagesDTOs = productQuantitiesDTO.getProductImagesDTOs();
				if (productImagesDTOs.size() != 0) {
					for (ProductImagesDTO productImagesDTO : productImagesDTOs) {
						ProductImages productimage = new ProductImages();
						mapper.map(productImagesDTO, productimage);
						productimage.setProductquantities(productquantityRepository.getOne(producttQuantityId));
						productImagesRepository.save(productimage);
					}
				}
			}
			ProductDTO return_productdto = new ProductDTO();
			mapper.map(productData, return_productdto);
			return_productdto.setSubcategoryId(productData.getSubcategory().getSubcategoryId());
			return_productdto.setProductcategoryId(productData.getProductcategory().getProductcategoryId());
			return return_productdto;
		} else {
			return null;
		}
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
				productDtoData.setSubcategoryId(product.getSubcategory().getSubcategoryId());
				productDtoData.setProductcategoryId(product.getProductcategory().getProductcategoryId());

				List<ProductQuantitiesDTO> productQuantitiesDTOList = new ArrayList<>();

				List<ProductQuantities> productQuantitiesList = productquantityRepository
						.findByProductId(product.getProductId());

				for (ProductQuantities productQuantities : productQuantitiesList) {
					ProductQuantitiesDTO productQuantitiesDTO = new ProductQuantitiesDTO();
					mapper.map(productQuantities, productQuantitiesDTO);
					productQuantitiesDTO.setQuantityId(productQuantities.getQuantity().getQuantityId());

					List<ProductImagesDTO> productImagesDTOList = new ArrayList<>();
					List<ProductImages> productImagesList = productImagesRepository
							.findByProductQuantityId(productQuantities.getProductquantityId());

					for (ProductImages productImages : productImagesList) {
						ProductImagesDTO ProductImagesDto = new ProductImagesDTO();
						mapper.map(productImages, ProductImagesDto);
						productImagesDTOList.add(ProductImagesDto);
					}
					productQuantitiesDTO.setProductImagesDTOs(productImagesDTOList);
					productQuantitiesDTOList.add(productQuantitiesDTO);
				}
				productDtoData.setProductQuantitiesDTOs(productQuantitiesDTOList);
				productDtoList.add(productDtoData);
			}
		}
		return productDtoList;
	}

}
