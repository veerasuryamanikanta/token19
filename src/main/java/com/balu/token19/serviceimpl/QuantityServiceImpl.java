package com.balu.token19.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balu.token19.domain.Quantity;
import com.balu.token19.dto.QuantityDTO;
import com.balu.token19.repo.QuantityRepository;
import com.balu.token19.service.QuantityService;

@Service
public class QuantityServiceImpl implements QuantityService {

	private static final Mapper mapper = new DozerBeanMapper();

	@Autowired
	private QuantityRepository quantityRepository;

	/*
	 * -----------------GET QUANTITIES -------------
	 */
	@Override
	public List<QuantityDTO> findAllQuantities() {
		List<Quantity> quantityData = quantityRepository.findAll();
		List<QuantityDTO> quantityDtoList = new ArrayList<>();
		if (quantityData.size() != 0) {
			for (Quantity category : quantityData) {
				QuantityDTO categoryDtoData = new QuantityDTO();
				mapper.map(category, categoryDtoData);
				quantityDtoList.add(categoryDtoData);
			}
		}
		return quantityDtoList;
	}

	/*
	 * -----------------SAVE QUANTITY -------------
	 */
	@Override
	public QuantityDTO saveQuantity(QuantityDTO quantityDTO) {
		Quantity quantity = new Quantity();
		mapper.map(quantityDTO, quantity);
		Quantity qty = quantityRepository.save(quantity);
		QuantityDTO qtyDTO = new QuantityDTO();
		mapper.map(qty, qtyDTO);
		return qtyDTO;
	}

}
