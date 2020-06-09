package com.balu.token19.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.balu.token19.dto.ErrorObject;
import com.balu.token19.dto.ReturnHolder;
import com.balu.token19.dto.ServicesDTO;
import com.balu.token19.service.AppService;

@RestController
@RequestMapping(value = "/services")
public class ServiceController {

	@Autowired
	private AppService appService;

	/*
	 * -----------------SAVE SERVICE -------------
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ReturnHolder saveCategory(@RequestBody ServicesDTO servicesDTO) {
		ReturnHolder holder = new ReturnHolder();
		try {
			if (servicesDTO != null) {
				ServicesDTO serviceData = appService.saveService(servicesDTO);
				holder.setResult(serviceData);
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
	@RequestMapping(value = "/list/{shopdetailsId}", method = RequestMethod.GET)
	public ReturnHolder getServicesbyId(@PathVariable("shopdetailsId") Long shopdetailsId) {
		ReturnHolder holder = new ReturnHolder();
		try {
			List<ServicesDTO> servicesDTOList = appService.findByShopId(shopdetailsId);
			holder.setResult(servicesDTOList);
		} catch (Exception e) {
			holder = new ReturnHolder(false, new ErrorObject("error", "Unable to Load." + e));
		}
		return holder;
	}

	/*
	 * -----------------GET SERVICES-------------
	 */
	@RequestMapping(value = "/serviceslist", method = RequestMethod.GET)
	public ReturnHolder getServicesbySerrviceId(@RequestParam List<Long> values) {
		ReturnHolder holder = new ReturnHolder();
		try {
			List<ServicesDTO> servicesDTOList = appService.findByServicesId(values);
			holder.setResult(servicesDTOList);
		} catch (Exception e) {
			holder = new ReturnHolder(false, new ErrorObject("error", "Unable to Load." + e));
		}
		return holder;
	}

}
