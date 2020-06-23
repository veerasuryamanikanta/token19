package com.balu.token19.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.balu.token19.dto.ErrorObject;
import com.balu.token19.dto.ProductAvailabilityDTO;
import com.balu.token19.dto.ReturnHolder;
import com.balu.token19.service.ProductAvailibilityService;

@RestController
@RequestMapping(value = "/productavailability")
public class PAController {

	@Autowired
	private ProductAvailibilityService productAvailibilityService;

	/*
	 * -----------------SAVE ROLE -------------
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ReturnHolder saveRole(@RequestBody ProductAvailabilityDTO productAvailabilityDTO) {
		ReturnHolder holder = new ReturnHolder();
		try {
			if (productAvailabilityDTO != null) {
				ProductAvailabilityDTO productAvailabilityDTO2 = productAvailibilityService
						.savePA(productAvailabilityDTO);
				if (productAvailabilityDTO2 != null) {
					holder.setResult(productAvailabilityDTO2);
				} else {
					holder = new ReturnHolder(false, new ErrorObject("error", "Data Empty"));
				}
			} else {
				holder = new ReturnHolder(false, new ErrorObject("error", "Data Empty"));
			}

		} catch (Exception e) {
			holder = new ReturnHolder(false, new ErrorObject("error", "Unable to Save." + e));
		}
		return holder;
	}

	/*
	 * -----------------PRODUCT LIST BY ID-------------
	 */
	@RequestMapping(value = "/list/{userId}/{productquantityId}", method = RequestMethod.GET)
	public ReturnHolder getProductsbySubCatId(@PathVariable("productquantityId") Long productquantityId,
			@PathVariable("userId") Long userId) {
		ReturnHolder holder = new ReturnHolder();
		try {
			holder.setResult(productAvailibilityService.isAvailableProduct(productquantityId, userId));
		} catch (Exception e) {
			holder = new ReturnHolder(false, new ErrorObject("error", "Unable to Load." + e));
		}
		return holder;
	}

}
