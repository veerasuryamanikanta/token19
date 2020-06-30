package com.balu.token19.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.balu.token19.dto.ErrorObject;
import com.balu.token19.dto.ReturnHolder;
import com.balu.token19.dto.SlidersDTO;
import com.balu.token19.service.SliderService;

@RestController
@RequestMapping(value = "/sliders")
public class SlidersController {

	@Autowired
	private SliderService slidersService;

	/*
	 * -----------------SAVE SERVICE -------------
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ReturnHolder saveCategory(@RequestBody SlidersDTO slidersdto) {
		ReturnHolder holder = new ReturnHolder();
		try {
			if (slidersdto != null) {
				SlidersDTO slidersdtoData = slidersService.saveSliders(slidersdto);
				holder.setResult(slidersdtoData);
			} else {
				holder = new ReturnHolder(false, new ErrorObject("error", "Data Empty"));
			}

		} catch (Exception e) {
			holder = new ReturnHolder(false, new ErrorObject("error", "Unable to Save." + e));
		}
		return holder;
	}

	/*
	 * -----------------GET SERVICES-------------
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ReturnHolder getServicesbyId() {
		ReturnHolder holder = new ReturnHolder();
		try {
			List<SlidersDTO> sliderDtoList = slidersService.slidersList();
			holder.setResult(sliderDtoList);
		} catch (Exception e) {
			holder = new ReturnHolder(false, new ErrorObject("error", "Unable to Load." + e));
		}
		return holder;
	}

}
