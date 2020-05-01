package com.balu.token19.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balu.token19.domain.Request;
import com.balu.token19.dto.RequestDTO;
import com.balu.token19.repo.RequestRepository;
import com.balu.token19.repo.ShopDetailsRepository;
import com.balu.token19.repo.UserRepository;
import com.balu.token19.service.RequestService;

@Service
public class RequestServiceImpl implements RequestService {

	private static final Mapper mapper = new DozerBeanMapper();

	@Autowired
	private RequestRepository requestRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ShopDetailsRepository shopdetailsRepository;

	/*
	 * -----------------SAVE REQUEST -------------
	 */
	@Override
	public RequestDTO saveRequest(RequestDTO requestDTO) {
		Request request = new Request();
		mapper.map(requestDTO, request);
		request.setUser(userRepository.getOne(requestDTO.getUserId()));
		request.setShopdetails(shopdetailsRepository.getOne(requestDTO.getShopdetailsId()));
		Request requestData = requestRepository.save(request);
		RequestDTO requestDtoData = new RequestDTO();
		mapper.map(requestData, requestDtoData);
		requestDtoData.setUserId(requestData.getUser().getUserId());
		requestDtoData.setShopdetailsId(requestData.getShopdetails().getShopdetailsId());
		return requestDtoData;
	}

	/*
	 * -----------------GET REQUEST INFO BY SHOP ID -------------
	 */
	@Override
	public List<RequestDTO> findByShopId(Long shopdetailsId) {
		List<Request> requestData = requestRepository.findByShopId(shopdetailsId);
		List<RequestDTO> requestDtoList = new ArrayList<RequestDTO>();
		if (requestData.size() != 0) {
			for (int i = 0; i < requestData.size(); i++) {
				Request requestDtoData = (Request) requestData.get(i);
				RequestDTO requestdto = new RequestDTO();
				mapper.map(requestDtoData, requestdto);
				requestdto.setUserId(requestDtoData.getUser().getUserId());
				requestdto.setShopdetailsId(requestDtoData.getShopdetails().getShopdetailsId());
				requestDtoList.add(requestdto);
			}
		}
		return requestDtoList;
	}

}
