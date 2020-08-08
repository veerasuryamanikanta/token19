package com.balu.token19.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balu.token19.domain.Request;
import com.balu.token19.domain.ShopDetails;
import com.balu.token19.domain.User;
import com.balu.token19.dto.FcmDTO;
import com.balu.token19.dto.FirebaseSignInSignUpResponseBean;
import com.balu.token19.dto.RequestDTO;
import com.balu.token19.dto.ShopDetailsDTO;
import com.balu.token19.dto.UserDTO;
import com.balu.token19.repo.DeviceRepository;
import com.balu.token19.repo.RequestRepository;
import com.balu.token19.repo.ShopDetailsRepository;
import com.balu.token19.repo.SliderRepository;
import com.balu.token19.repo.UserRepository;
import com.balu.token19.service.FcmService;
import com.balu.token19.service.RequestService;
import com.balu.token19.service.UserAuthenticationService;

@Service
public class RequestServiceImpl implements RequestService {

	private static final Mapper mapper = new DozerBeanMapper();

	@Autowired
	private RequestRepository requestRepository;

	@Autowired
	private FcmService fcmservice;

	@Autowired
	UserAuthenticationService userAuthenticationService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ShopDetailsRepository shopdetailsRepository;

	@Autowired
	private DeviceRepository deviceRepository;

	@Autowired
	private SliderRepository sliderRepository;

	private String token;

	/*
	 * -----------------SAVE REQUEST -------------
	 */
	@Override
	public RequestDTO saveRequest(RequestDTO requestDTO) {
		Request request = new Request();
		mapper.map(requestDTO, request);
		request.setUser(userRepository.getOne(requestDTO.getUserId()));
		request.setShopdetails(shopdetailsRepository.getOne(requestDTO.getShopDetailsId()));
		Request requestData = requestRepository.save(request);
		if (requestData != null) {
			String userEmail = requestData.getRequestType() + "_" + requestData.getRequestId() + "_user@token19.com";
			FirebaseSignInSignUpResponseBean userUIDData = userAuthenticationService
					.signUpWithEmailAndPassword(userEmail, userEmail);
			String userUID = userUIDData.getLocalId();
			String providerEmail = requestData.getRequestType() + "_" + requestData.getRequestId()
					+ "_provider@token19.com";
			FirebaseSignInSignUpResponseBean providerUIDData = userAuthenticationService
					.signUpWithEmailAndPassword(providerEmail, providerEmail);
			String providerUID = providerUIDData.getLocalId();
			Request updaterequest = new Request();
			mapper.map(requestDTO, updaterequest);
			updaterequest.setRequestId(requestData.getRequestId());
			updaterequest.setUser(userRepository.getOne(requestDTO.getUserId()));
			updaterequest.setShopdetails(shopdetailsRepository.getOne(requestDTO.getShopDetailsId()));
			updaterequest.setUserUid(userUID);
			updaterequest.setProviderUid(providerUID);
			updaterequest.setSenderUid(userUID);
			Request updateRequestData = requestRepository.save(updaterequest);
			if (updateRequestData != null) {
				RequestDTO requestDtoData = new RequestDTO();
				mapper.map(updateRequestData, requestDtoData);
				requestDtoData.setUserId(updateRequestData.getUser().getUserId());
				requestDtoData.setShopDetailsId(updateRequestData.getShopdetails().getShopdetailsId());
				User user = updateRequestData.getUser();
				UserDTO userdto = new UserDTO();
				mapper.map(user, userdto);
				requestDtoData.setUserDTO(userdto);
				ShopDetails shopDetails = updateRequestData.getShopdetails();
				ShopDetailsDTO shopDetailsDTO = new ShopDetailsDTO();
				mapper.map(shopDetails, shopDetailsDTO);
				requestDtoData.setShopDetailsDTO(shopDetailsDTO);
				if (requestData.getUser().getUniqueID() != null) {
					String notif_token = deviceRepository
							.findByuniqueId(updateRequestData.getShopdetails().getUser().getUniqueID());
					if (notif_token != null) {
						token = notif_token;
					} else {
						token = "";
					}
				} else {
					token = "";
				}
				try {
					String not_imagepath = sliderRepository.findNotificationImage();
					FcmDTO fcmdto = new FcmDTO();
					fcmdto.setTo(token);
					fcmdto.setTitle("HEY VENDOR we have a request from " + requestData.getUser().getUserNumber());
					fcmdto.setBody("Send Time Slot By Confirm Order.");
					fcmdto.setImage(not_imagepath);
					fcmdto.setRequestdto(requestDtoData);
					CompletableFuture<String> pushNotification = fcmservice.send(fcmdto);
					CompletableFuture.allOf(pushNotification).join();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return requestDtoData;
			} else {
				return null;
			}

		} else {
			return null;
		}

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
				requestdto.setShopDetailsId(requestDtoData.getShopdetails().getShopdetailsId());
				requestdto.setUserUID(requestDtoData.getUserUid());
				requestdto.setSenderUID(requestDtoData.getSenderUid());
				requestdto.setProviderUID(requestDtoData.getProviderUid());
				User user = requestDtoData.getUser();
				UserDTO userdto = new UserDTO();
				mapper.map(user, userdto);
				requestdto.setUserDTO(userdto);
				ShopDetails shopDetails = requestDtoData.getShopdetails();
				ShopDetailsDTO shopDetailsDTO = new ShopDetailsDTO();
				mapper.map(shopDetails, shopDetailsDTO);
				requestdto.setShopDetailsDTO(shopDetailsDTO);
				requestDtoList.add(requestdto);
			}
		}
		return requestDtoList;
	}

	/*
	 * -----------------GET REQUEST INFO BY USER ID -------------
	 */
	@Override
	public List<RequestDTO> findByUserId(Long userId) {
		List<Request> requestData = requestRepository.findByUserId(userId);
		List<RequestDTO> requestDtoList = new ArrayList<RequestDTO>();
		if (requestData.size() != 0) {
			for (int i = 0; i < requestData.size(); i++) {
				Request requestDtoData = (Request) requestData.get(i);
				RequestDTO requestdto = new RequestDTO();
				mapper.map(requestDtoData, requestdto);
				requestdto.setUserId(requestDtoData.getUser().getUserId());
				requestdto.setShopDetailsId(requestDtoData.getShopdetails().getShopdetailsId());
				requestdto.setUserUID(requestDtoData.getUserUid());
				requestdto.setSenderUID(requestDtoData.getSenderUid());
				requestdto.setProviderUID(requestDtoData.getProviderUid());
				User user = requestDtoData.getUser();
				UserDTO userdto = new UserDTO();
				mapper.map(user, userdto);
				requestdto.setUserDTO(userdto);
				ShopDetails shopDetails = requestDtoData.getShopdetails();
				ShopDetailsDTO shopDetailsDTO = new ShopDetailsDTO();
				mapper.map(shopDetails, shopDetailsDTO);
				requestdto.setShopDetailsDTO(shopDetailsDTO);
				requestDtoList.add(requestdto);
			}
		}
		return requestDtoList;
	}

	/*
	 * -----------------UPDATE REQUEST INFO BY SHOP ID -------------
	 */
	@Override
	public RequestDTO updateRequest(RequestDTO requestDTO) {
		Request requestData = requestRepository.getOne(requestDTO.getRequestId());
		if (requestData != null) {
			Request request = new Request();
			mapper.map(requestDTO, request);
			request.setUser(userRepository.getOne(requestData.getUser().getUserId()));
			request.setShopdetails(shopdetailsRepository.getOne(requestData.getShopdetails().getShopdetailsId()));

			if (requestDTO.getRequestPath() == null) {
				request.setRequestPath(requestData.getRequestPath());
			}

			if (requestDTO.getCreatedDate() == null) {
				request.setCreatedDate(requestData.getCreatedDate());
			}

			if (requestDTO.getRecieveTime() == null) {
				request.setRecieveTime(requestData.getRecieveTime());
			}

			if (requestDTO.getRequestStatus() == null) {
				request.setRequestStatus(requestData.getRequestStatus());
			}

			try {
				int tokennumber = requestRepository
						.findTodayRequestByShopId(requestData.getShopdetails().getShopdetailsId());
				request.setTokenNumber(String.valueOf(tokennumber + 1));
			} catch (Exception e) {
				request.setTokenNumber(requestData.getTokenNumber());
			}

			Request request_save_data = requestRepository.save(request);
			RequestDTO requestDtoData = new RequestDTO();
			mapper.map(request_save_data, requestDtoData);
			User user = request_save_data.getUser();
			requestDtoData.setUserId(request_save_data.getUser().getUserId());
			requestDtoData.setShopDetailsId(request_save_data.getShopdetails().getShopdetailsId());
			UserDTO userdto = new UserDTO();
			mapper.map(user, userdto);
			requestDtoData.setUserDTO(userdto);
			ShopDetails shopDetails = request_save_data.getShopdetails();
			ShopDetailsDTO shopDetailsDTO = new ShopDetailsDTO();
			mapper.map(shopDetails, shopDetailsDTO);
			requestDtoData.setShopDetailsDTO(shopDetailsDTO);
			return requestDtoData;
		} else {
			return null;
		}
	}

}
