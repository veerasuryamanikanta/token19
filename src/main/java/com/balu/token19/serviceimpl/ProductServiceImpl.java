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
import com.balu.token19.domain.ProductsAvailablity;
import com.balu.token19.dto.ProductDTO;
import com.balu.token19.dto.ProductImagesDTO;
import com.balu.token19.dto.ProductQuantitiesDTO;
import com.balu.token19.repo.ProductAvailabilityRepository;
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

	@Autowired
	private ProductAvailabilityRepository productAvailabilityRepository;

	@Override
	public String saveProductItems(ProductDTO productDTO) {
		try {
			List<ProductQuantitiesDTO> productQuantitiesDTOs = productDTO.getProductQuantitiesDTOs();
			if (productQuantitiesDTOs.size() != 0) {
				for (ProductQuantitiesDTO productQuantitiesDTO : productQuantitiesDTOs) {

					String quantity = productQuantitiesDTO.getDescription();
					ProductQuantities productQuantities = new ProductQuantities();
					ProductQuantities exists_productQuantitiesData = productquantityRepository
							.findByProductIdAnddescription(productDTO.getProductId(), quantity);

					if (exists_productQuantitiesData == null) {
						mapper.map(productQuantitiesDTO, productQuantities);
						productQuantities.setQuantity(quantityRepository.getOne(productQuantitiesDTO.getQuantityId()));
						productQuantities.setProduct(productRepository.getOne(productDTO.getProductId()));
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
				}
			}
			return "success";
		} catch (Exception e) {
			return "failed";
		}

	}

	/*
	 * -----------------SAVE PRODUCT GROUP -------------
	 */
	@Override
	public ProductDTO saveProduct(ProductDTO productDTO) {
		try {
			ProductDTO return_productdto = new ProductDTO();
			Product product = new Product();
			String productName = productDTO.getProductName();
			Product exists_product = productRepository.findProductByName(productName);
			if (exists_product != null) {
				mapper.map(exists_product, return_productdto);
				return_productdto.setSubcategoryId(exists_product.getSubcategory().getSubcategoryId());
				return_productdto.setProductcategoryId(exists_product.getProductcategory().getProductcategoryId());
			} else {
				mapper.map(productDTO, product);
				product.setSubcategory(subCategoryRepository.getOne(productDTO.getSubcategoryId()));
				product.setProductcategory(productcategoryRepository.getOne(productDTO.getProductcategoryId()));
				Product productData = productRepository.save(product);
				mapper.map(productData, return_productdto);
				return_productdto.setSubcategoryId(productData.getSubcategory().getSubcategoryId());
				return_productdto.setProductcategoryId(productData.getProductcategory().getProductcategoryId());
			}
			return return_productdto;
		} catch (Exception e) {
			return null;
		}
	}

	/*
	 * -----------------GET PRODUCTS BY SUBCATEGORY ID-------------
	 */
	@Override
	public List<ProductDTO> findProductsBySubCategryAndShopId(Long subcategoryId, Long shopdetailsId) {
		List<Product> productData = productRepository.findProductBySubCategoryId(subcategoryId);
		List<ProductDTO> productDtoList = new ArrayList<>();
		if (productData.size() != 0) {
			for (Product product : productData) {
				ProductDTO productDtoData = new ProductDTO();
				mapper.map(product, productDtoData);
				productDtoData.setSubcategoryId(product.getSubcategory().getSubcategoryId());
				productDtoData.setProductcategoryId(product.getProductcategory().getProductcategoryId());
				List<ProductQuantitiesDTO> productQuantitiesDTOList = new ArrayList<>();

				List<ProductQuantities> productQuantitiesList = productquantityRepository
						.findByAvailableProduct(product.getProductId(), shopdetailsId, subcategoryId);

				if (productQuantitiesList.size() != 0) {
					for (ProductQuantities productQuantities : productQuantitiesList) {
						ProductQuantitiesDTO productQuantitiesDTO = new ProductQuantitiesDTO();
						mapper.map(productQuantities, productQuantitiesDTO);
						productQuantitiesDTO.setQuantityId(productQuantities.getQuantity().getQuantityId());
						productQuantitiesDTO.setDescription(productQuantities.getDescription());
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
		}
		return productDtoList;
	}

	@Override
	public List<ProductDTO> findProductsBySubCategryId(Long subcategoryId, Long userId) {
		List<Product> productData = productRepository.findProductBySubCategoryId(subcategoryId);
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

				//if (productQuantitiesList.size() != 0) {
					for (ProductQuantities productQuantities : productQuantitiesList) {
						ProductQuantitiesDTO productQuantitiesDTO = new ProductQuantitiesDTO();
						mapper.map(productQuantities, productQuantitiesDTO);
						productQuantitiesDTO.setQuantityId(productQuantities.getQuantity().getQuantityId());
						productQuantitiesDTO.setDescription(productQuantities.getDescription());
						ProductsAvailablity pavailability = productAvailabilityRepository.findByIsAvailable(userId,
								productQuantities.getProductquantityId());

						if (pavailability != null) {

							productQuantitiesDTO.setIsavailable(pavailability.getIsavailable());
						} else {

							productQuantitiesDTO.setIsavailable(true);
						}

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
				//}

			}
		}
		return productDtoList;
	}

	@Override
	public String deleteQuantityImage(Long productimageId) {
		try {
			productImagesRepository.deleteById(productimageId);
			return "success";
		}catch (Exception e) {
			return null;
		}
		
	}
	
	@Override
	public String deleteQuantity(Long productquantityId) {
		try {
			productquantityRepository.deleteById(productquantityId);
			return "success";
		}catch (Exception e) {
			return null;
		}
		
	}

}
