package com.balu.token19.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.balu.token19.dto.ErrorObject;
import com.balu.token19.dto.ReturnHolder;
import com.balu.token19.dto.TechnicianDTO;
import com.balu.token19.service.TechnicianService;

@RestController
@RequestMapping(value = "/techsupport")
public class TechSupportController {

	@Autowired
	private TechnicianService technicianService;

	/*
	 * -----------------SAVE TECHNICIAN -------------
	 */
	@RequestMapping(value = "/technician/save", method = RequestMethod.POST)
	public ReturnHolder saveTechnician(@RequestBody TechnicianDTO technicianDTO) {
		ReturnHolder holder = new ReturnHolder();
		try {
			if (technicianDTO != null) {
				TechnicianDTO technicandto = technicianService.saveTechnician(technicianDTO);
				holder.setResult(technicandto);
			} else {
				holder = new ReturnHolder(false, new ErrorObject("error", "Data Empty"));
			}

		} catch (Exception e) {
			holder = new ReturnHolder(false, new ErrorObject("error", "Unable to Save." + e));
		}
		return holder;
	}

	/*
	 * -----------------GET TECHNICIAN DATA BY USERID -------------
	 */
	@RequestMapping(value = "/technician", method = RequestMethod.POST)
	public ReturnHolder getTechnician(@RequestBody TechnicianDTO technicianDTO) {
		ReturnHolder holder = new ReturnHolder();
		try {
			if (technicianDTO != null) {
				TechnicianDTO technicandto = technicianService.getTechnicianByUserId(technicianDTO.getUserId());
				holder.setResult(technicandto);
			} else {
				holder = new ReturnHolder(false, new ErrorObject("error", "Data Empty"));
			}

		} catch (Exception e) {
			holder = new ReturnHolder(false, new ErrorObject("error", "Unable to Save." + e));
		}
		return holder;
	}
	
	/*
	 * -----------------GET TECHNICIAN DATA BY USERID -------------
	 */
	@RequestMapping(value = "/technicians", method = RequestMethod.GET)
	public ReturnHolder getAllTechnicians() {
		ReturnHolder holder = new ReturnHolder();
		try {
			List<TechnicianDTO> technicianDtoList = technicianService.getTechnicians();
			if (technicianDtoList != null) {
				holder.setResult(technicianDtoList);
			}else {
				holder = new ReturnHolder(false, new ErrorObject("error", "Unable to Save."));
			}
			
		} catch (Exception e) {
			holder = new ReturnHolder(false, new ErrorObject("error", "Unable to Save." + e));
		}
		return holder;
	}

}
