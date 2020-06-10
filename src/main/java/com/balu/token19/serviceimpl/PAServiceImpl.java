package com.balu.token19.serviceimpl;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balu.token19.domain.ProductsAvailablity;
import com.balu.token19.dto.ProductAvailabilityDTO;
import com.balu.token19.repo.ProductAvailabilityRepository;
import com.balu.token19.repo.ProductQuantityRepository;
import com.balu.token19.repo.ShopDetailsRepository;
import com.balu.token19.repo.SubCategoryRepository;
import com.balu.token19.repo.UserRepository;
import com.balu.token19.service.ProductAvailibilityService;

@Service
public class PAServiceImpl implements ProductAvailibilityService {

	private static final Mapper mapper = new DozerBeanMapper();

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ShopDetailsRepository shopDetailsRepository;

	@Autowired
	private ProductQuantityRepository productQuantityRepository;

	@Autowired
	private SubCategoryRepository subCategoryRepository;

	@Autowired
	private ProductAvailabilityRepository productAvailabilityRepository;

	@Override
	public ProductAvailabilityDTO savePA(ProductAvailabilityDTO productAvailabilityDTO) {
		try {
			ProductsAvailablity padata = productAvailabilityRepository.findByUserid(productAvailabilityDTO.getUserId(),
					productAvailabilityDTO.getProductquantityId());
			if (padata == null) {
				ProductsAvailablity productsAvailablity = new ProductsAvailablity();
				mapper.map(productAvailabilityDTO, productsAvailablity);
				productsAvailablity.setUser(userRepository.getOne(productAvailabilityDTO.getUserId()));
				productsAvailablity
						.setShopdetails(shopDetailsRepository.getOne(productAvailabilityDTO.getShopdetailsId()));
				productsAvailablity.setProductquantities(
						productQuantityRepository.getOne(productAvailabilityDTO.getProductquantityId()));
				productsAvailablity
						.setSubcategory(subCategoryRepository.getOne(productAvailabilityDTO.getSubcategoryId()));

				ProductsAvailablity productavailabilityData = productAvailabilityRepository.save(productsAvailablity);
				ProductAvailabilityDTO productAvailabilitydto = new ProductAvailabilityDTO();
				mapper.map(productavailabilityData, productAvailabilitydto);
				productAvailabilitydto.setUserId(productavailabilityData.getUser().getUserId());
				productAvailabilitydto.setShopdetailsId(productavailabilityData.getShopdetails().getShopdetailsId());
				productAvailabilitydto
						.setProductquantityId(productavailabilityData.getProductquantities().getProductquantityId());
				productAvailabilitydto.setSubcategoryId(productavailabilityData.getProductquantities().getProduct()
						.getSubcategory().getSubcategoryId());
				return productAvailabilitydto;
			} else {
				if (productAvailabilityDTO.getIsavailable()) {
					productAvailabilityRepository.updateAvailibility(productAvailabilityDTO.getUserId(),
							productAvailabilityDTO.getProductquantityId(), true);
					ProductAvailabilityDTO productAvailabilitydto = new ProductAvailabilityDTO();
					mapper.map(padata, productAvailabilitydto);
					productAvailabilitydto.setUserId(padata.getUser().getUserId());
					productAvailabilitydto.setShopdetailsId(padata.getShopdetails().getShopdetailsId());
					productAvailabilitydto.setProductquantityId(padata.getProductquantities().getProductquantityId());
					productAvailabilitydto.setIsavailable(true);
					return productAvailabilitydto;
					
				} else {

					productAvailabilityRepository.updateAvailibility(productAvailabilityDTO.getUserId(),
							productAvailabilityDTO.getProductquantityId(), false);
					ProductAvailabilityDTO productAvailabilitydto = new ProductAvailabilityDTO();
					mapper.map(padata, productAvailabilitydto);
					productAvailabilitydto.setUserId(padata.getUser().getUserId());
					productAvailabilitydto.setShopdetailsId(padata.getShopdetails().getShopdetailsId());
					productAvailabilitydto.setProductquantityId(padata.getProductquantities().getProductquantityId());
					productAvailabilitydto.setIsavailable(false);
					return productAvailabilitydto;
					
				}
			}
		} catch (Exception e) {
			return null;
		}

	}

}
