package com.balu.token19.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balu.token19.domain.ShopDetails;
import com.balu.token19.dto.ShopDetailsDTO;
import com.balu.token19.repo.ShopDetailsRepository;
import com.balu.token19.repo.UserRepository;
import com.balu.token19.service.ShopDetailsService;

@Service
public class ShopDetailsServiceImpl implements ShopDetailsService {

	private static final Mapper mapper = new DozerBeanMapper();

	@Autowired
	private ShopDetailsRepository shopDetailsRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public ShopDetailsDTO saveShopDetails(ShopDetailsDTO shopDetailsDTO) {
		ShopDetails shopdetails = new ShopDetails();
		mapper.map(shopDetailsDTO, shopdetails);
		shopdetails.setUser(userRepository.getOne(shopDetailsDTO.getUserId()));
		ShopDetails shopDetailsData = shopDetailsRepository.save(shopdetails);
		ShopDetailsDTO shopdetailsDtoData = new ShopDetailsDTO();
		mapper.map(shopDetailsData, shopdetailsDtoData);
		shopdetailsDtoData.setUserId(shopDetailsData.getUser().getUserId());
		return shopdetailsDtoData;
	}

	@Override
	public ShopDetailsDTO getShopDetails(Long userid) {
		ShopDetails shopdetails = shopDetailsRepository.findByUserId(userid);
		if (shopdetails != null) {
			ShopDetailsDTO shopdetailsDtoData = new ShopDetailsDTO();
			shopdetailsDtoData.setUserId(shopdetails.getUser().getUserId());
			mapper.map(shopdetails, shopdetailsDtoData);
			return shopdetailsDtoData;
		} else {
			return null;
		}

	}

	@Override
	public List<ShopDetailsDTO> getShopsbyPincode(String pincode) {
		List<ShopDetails> shopdetailsList = shopDetailsRepository.findByPincode(pincode);
		List<ShopDetailsDTO> shopdetailsDtoList = new ArrayList<ShopDetailsDTO>();
		if (shopdetailsList.size() != 0) {
			for (int i = 0; i < shopdetailsList.size(); i++) {
				ShopDetails shopdetails = (ShopDetails)shopdetailsList.get(i);
				ShopDetailsDTO shopdetailsDtoData = new ShopDetailsDTO();
				shopdetailsDtoData.setUserId(shopdetails.getUser().getUserId());
				mapper.map(shopdetails, shopdetailsDtoData);
				shopdetailsDtoList.add(shopdetailsDtoData);
			}
		}
		return shopdetailsDtoList;
	}

}
