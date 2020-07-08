package com.balu.token19.serviceimpl;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balu.token19.domain.VendorProduct;
import com.balu.token19.domain.VendorProductQuantity;
import com.balu.token19.dto.VendorProductQuantitiesDTO;
import com.balu.token19.repo.ProductQuantityRepository;
import com.balu.token19.repo.ProductRepository;
import com.balu.token19.repo.ShopDetailsRepository;
import com.balu.token19.repo.SubCategoryRepository;
import com.balu.token19.repo.UserRepository;
import com.balu.token19.repo.VendorProductQuantityRepository;
import com.balu.token19.repo.VendorProductRepository;
import com.balu.token19.service.VendorProductQtyService;

@Service
public class VendorProductServiceImpl implements VendorProductQtyService {

	private static final Mapper mapper = new DozerBeanMapper();

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ShopDetailsRepository shopDetailsRepository;
	
	@Autowired
	private SubCategoryRepository subCategoryRepository;
	
	
	@Autowired
	private ProductQuantityRepository productQuantityRepository;
	
	@Autowired
	private VendorProductQuantityRepository vendorProductQuantityRepository;
	
	@Autowired
	private VendorProductRepository vendorProductRepository;
	
	
	
	@Override
	public VendorProductQuantitiesDTO saveVPQ(VendorProductQuantitiesDTO vendorProductQuantitiesDTO) {
		try {
			VendorProduct vendorProductData = vendorProductRepository.findByUserAndSubCategory(vendorProductQuantitiesDTO.getUserId(),
					vendorProductQuantitiesDTO.getSubCategoryId(), vendorProductQuantitiesDTO.getProductId(),
					vendorProductQuantitiesDTO.getShopdetailsId());
			Long vendorProductId;
			if(vendorProductData!=null) {
				 vendorProductId = vendorProductData.getVendorproductId();
			}else {
				VendorProduct vendorproduct = new VendorProduct();
				vendorproduct.setUser(userRepository.getOne(vendorProductQuantitiesDTO.getUserId()));
				vendorproduct.setSubcategory(subCategoryRepository.getOne(vendorProductQuantitiesDTO.getSubCategoryId()));
				vendorproduct.setProduct(productRepository.getOne(vendorProductQuantitiesDTO.getProductId()));
				vendorproduct.setShopdetails(shopDetailsRepository.getOne(vendorProductQuantitiesDTO.getShopdetailsId()));
				VendorProduct myVendorProductData  = vendorProductRepository.save(vendorproduct);
				vendorProductId = myVendorProductData.getVendorproductId();	
			}
			VendorProductQuantity existingVendorProductQuantity = vendorProductQuantityRepository.
					findByUserShopQuantity(vendorProductQuantitiesDTO.getUserId(),
							vendorProductQuantitiesDTO.getProductquantityId());
			if(existingVendorProductQuantity!=null) {
				VendorProductQuantity vendorProductQuantity = new VendorProductQuantity();
				mapper.map(vendorProductQuantitiesDTO, vendorProductQuantity);
				vendorProductQuantity.setUser(userRepository.getOne(vendorProductQuantitiesDTO.getUserId()));
				vendorProductQuantity.setVendorproduct(vendorProductRepository.getOne(vendorProductId));
				vendorProductQuantity.setVpqId(existingVendorProductQuantity.getVpqId());
				vendorProductQuantity.setProductquantities(productQuantityRepository.getOne(vendorProductQuantitiesDTO.
						getProductquantityId()));
				VendorProductQuantity vendorProductQuantityData = vendorProductQuantityRepository.save(vendorProductQuantity);
				VendorProductQuantitiesDTO vendorProductQuantitiesDTOData = new VendorProductQuantitiesDTO();
				mapper.map(vendorProductQuantityData, vendorProductQuantitiesDTOData);
				return vendorProductQuantitiesDTOData;
			}else {
				VendorProductQuantity vendorProductQuantity = new VendorProductQuantity();
				mapper.map(vendorProductQuantitiesDTO, vendorProductQuantity);
				vendorProductQuantity.setUser(userRepository.getOne(vendorProductQuantitiesDTO.getUserId()));
				vendorProductQuantity.setVendorproduct(vendorProductRepository.getOne(vendorProductId));
				vendorProductQuantity.setProductquantities(productQuantityRepository.getOne(vendorProductQuantitiesDTO.
						getProductquantityId()));
				VendorProductQuantity vendorProductQuantityData = vendorProductQuantityRepository.save(vendorProductQuantity);
				VendorProductQuantitiesDTO vendorProductQuantitiesDTOData = new VendorProductQuantitiesDTO();
				mapper.map(vendorProductQuantityData, vendorProductQuantitiesDTOData);
				return vendorProductQuantitiesDTOData;
			}
		} catch (Exception e) {
			return null;
		}
	}

	

}
