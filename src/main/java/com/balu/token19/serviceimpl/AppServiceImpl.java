package com.balu.token19.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balu.token19.domain.Services;
import com.balu.token19.dto.ServicesDTO;
import com.balu.token19.repo.ServiceRepository;
import com.balu.token19.repo.ShopDetailsRepository;
import com.balu.token19.repo.UserRepository;
import com.balu.token19.service.AppService;

@Service
public class AppServiceImpl implements AppService {

	private static final Mapper mapper = new DozerBeanMapper();

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ServiceRepository serviceRepository;

	@Autowired
	private ShopDetailsRepository shopdetailsRepository;

	@Override
	public ServicesDTO saveService(ServicesDTO servicesDTO) {
		try {
			Services service = new Services();
			mapper.map(servicesDTO, service);
			service.setUser(userRepository.getOne(servicesDTO.getUserId()));
			service.setShopdetails(shopdetailsRepository.getOne(servicesDTO.getShopdetailsId()));
			Services serviceData = serviceRepository.save(service);
			ServicesDTO servicedto = new ServicesDTO();
			mapper.map(serviceData, servicedto);
			return servicedto;
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public List<ServicesDTO> findByShopId(Long shopdetailsId) {
		List<ServicesDTO> servicsdtoList = new ArrayList<ServicesDTO>();
		try {
			List<Services> servicsList = serviceRepository.findByShopId(shopdetailsId);
			for (Services services : servicsList) {
				ServicesDTO servicedto = new ServicesDTO();
				mapper.map(services, servicedto);
				servicedto.setUserId(services.getUser().getUserId());
				servicedto.setShopdetailsId(services.getShopdetails().getShopdetailsId());
				servicsdtoList.add(servicedto);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return servicsdtoList;
	}

}
