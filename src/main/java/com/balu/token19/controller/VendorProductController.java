package com.balu.token19.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.balu.token19.dto.ErrorObject;
import com.balu.token19.dto.ReturnHolder;
import com.balu.token19.dto.VendorProductQuantitiesDTO;
import com.balu.token19.service.VendorProductQtyService;

@RestController
@RequestMapping(value = "/vendorproducts")
public class VendorProductController {

	@Autowired
	private VendorProductQtyService vendorProductQtyService;

	/*
	 * -----------------SAVE VENDOR PRODUCT -------------
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ReturnHolder saveCategory(@RequestBody VendorProductQuantitiesDTO vendorProductQuantitiesDTO) {
		ReturnHolder holder = new ReturnHolder();
		try {
			if (vendorProductQuantitiesDTO != null) {
				VendorProductQuantitiesDTO vendorProductQuantitiesDTOData = vendorProductQtyService
						.saveVPQ(vendorProductQuantitiesDTO);
				holder.setResult(vendorProductQuantitiesDTOData);
			} else {
				holder = new ReturnHolder(false, new ErrorObject("error", "Data Empty"));
			}

		} catch (Exception e) {
			holder = new ReturnHolder(false, new ErrorObject("error", "Unable to Save." + e));
		}
		return holder;
	}
}
