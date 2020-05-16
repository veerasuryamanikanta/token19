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

	/*
	 * ----------------SAVE SHOP DETAILS--------------------
	 */
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

	/*
	 * ----------------SHOPS LIST BY USERID--------------------
	 */
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

	/*
	 * ----------------SHOPS LIST BY PINCODE--------------------
	 */
	@Override
	public List<ShopDetailsDTO> getShopsbyPincode(String pincode) {
		List<ShopDetails> shopdetailsList = shopDetailsRepository.findByPincode(pincode);
		List<ShopDetailsDTO> shopdetailsDtoList = new ArrayList<ShopDetailsDTO>();
		if (shopdetailsList.size() != 0) {
			for (int i = 0; i < shopdetailsList.size(); i++) {
				ShopDetails shopdetails = (ShopDetails) shopdetailsList.get(i);
				ShopDetailsDTO shopdetailsDtoData = new ShopDetailsDTO();
				shopdetailsDtoData.setUserId(shopdetails.getUser().getUserId());
				mapper.map(shopdetails, shopdetailsDtoData);
				shopdetailsDtoList.add(shopdetailsDtoData);
			}
		}
		return shopdetailsDtoList;
	}

	/*
	 * ----------------UPDATE SHOP DETAILS--------------------
	 */
	@Override
	public ShopDetailsDTO updateShopDetails(ShopDetailsDTO shopDetailsDTO) {
		ShopDetails shopDetailsById = shopDetailsRepository.getOne(shopDetailsDTO.getShopdetailsId());
		if (shopDetailsById != null) {
			ShopDetails shopdetails = new ShopDetails();
			mapper.map(shopDetailsDTO, shopdetails);
			shopdetails.setUser(userRepository.getOne(shopDetailsDTO.getUserId()));

			if (shopDetailsDTO.getShopName() == null) {
				shopdetails.setShopName(shopDetailsById.getShopName());
			}

			if (shopDetailsDTO.getOwnerName() == null) {
				shopdetails.setOwnerName(shopDetailsById.getOwnerName());
			}

			if (shopDetailsDTO.getPincode() == null) {
				shopdetails.setPincode(shopDetailsById.getPincode());
			}

			if (shopDetailsDTO.getShopLatitude() == null) {
				shopdetails.setShopLatitude(shopDetailsById.getShopLatitude());
			}

			if (shopDetailsDTO.getShopLongitude() == null) {
				shopdetails.setShopLongitude(shopDetailsById.getShopLongitude());
			}

			if (shopDetailsDTO.getShopAddress() == null) {
				shopdetails.setShopAddress(shopDetailsById.getShopAddress());
			}

			if (shopDetailsDTO.getShopImage() == null) {
				shopdetails.setShopImage(shopDetailsById.getShopImage());
			}
			ShopDetails shopDetailsData = shopDetailsRepository.save(shopdetails);
			ShopDetailsDTO shopdetailsDtoData = new ShopDetailsDTO();
			mapper.map(shopDetailsData, shopdetailsDtoData);
			shopdetailsDtoData.setUserId(shopDetailsData.getUser().getUserId());
			return shopdetailsDtoData;
		} else {
			return null;
		}

	}

}
