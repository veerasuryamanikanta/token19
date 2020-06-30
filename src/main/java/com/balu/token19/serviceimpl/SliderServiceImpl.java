package com.balu.token19.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balu.token19.domain.Sliders;
import com.balu.token19.dto.SlidersDTO;
import com.balu.token19.repo.SliderRepository;
import com.balu.token19.service.SliderService;

@Service
public class SliderServiceImpl implements SliderService {

	private static final Mapper mapper = new DozerBeanMapper();

	@Autowired
	private SliderRepository sliderRepository;

	@Override
	public SlidersDTO saveSliders(SlidersDTO slidersDto) {
		try {
			Sliders sliders = new Sliders();
			mapper.map(slidersDto, sliders);
			Sliders slidersData = sliderRepository.save(sliders);
			SlidersDTO slidersdto = new SlidersDTO();
			mapper.map(slidersData, slidersdto);
			return slidersdto;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<SlidersDTO> slidersList() {
		List<SlidersDTO> sliderdtoList = new ArrayList<SlidersDTO>();
		try {
			List<Sliders> slidersList = sliderRepository.findAll();
			for (Sliders sliders : slidersList) {
				SlidersDTO slidersdto = new SlidersDTO();
				mapper.map(sliders, slidersdto);
				sliderdtoList.add(slidersdto);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return sliderdtoList;
	}

}
