package com.balu.token19.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.balu.token19.dto.CommonDTO;
import com.balu.token19.dto.CultureDTO;
import com.balu.token19.dto.ErrorObject;
import com.balu.token19.dto.ReturnHolder;
import com.balu.token19.service.CultureService;

@RestController
@RequestMapping(value = "/culture")
public class CultureController {

	@Autowired
	private CultureService cultureService;

	/*
	 * -----------------SAVE CULTURE -------------
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ReturnHolder saveCulture(@RequestBody CultureDTO cultureDTO) {
		ReturnHolder holder = new ReturnHolder();
		try {
			if (cultureDTO != null) {
				CultureDTO savedCultureDTO = cultureService.saveCulture(cultureDTO);
				holder.setResult(savedCultureDTO);
			} else {
				holder = new ReturnHolder(false, new ErrorObject("error", "Data Empty"));
			}
		} catch (Exception e) {
			holder = new ReturnHolder(false, new ErrorObject("error", "Unable to Save." + e));
		}
		return holder;
	}
	
	/*
	 * -----------------SAVE ALL CULTURE -------------
	 */
	@RequestMapping(value = "/saveAll", method = RequestMethod.POST)
	public ReturnHolder saveAllCultures(@RequestBody CommonDTO commonDTO) {
		ReturnHolder holder = new ReturnHolder();
		try {
			if (commonDTO != null) {
				List<CultureDTO> savedCultureDTO = cultureService.saveAllCultures(commonDTO);
				holder.setResult(savedCultureDTO);
			} else {
				holder = new ReturnHolder(false, new ErrorObject("error", "Data Empty"));
			}
		} catch (Exception e) {
			holder = new ReturnHolder(false, new ErrorObject("error", "Unable to Save." + e));
		}
		return holder;
	}

	/*
	 * -----------------GET CULTURE -------------
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public ReturnHolder getCultureList(@RequestBody CultureDTO cultureDTO) {
		ReturnHolder holder = new ReturnHolder();
		try {
			if (cultureDTO != null) {
				List<CultureDTO> getCultureDTOList = cultureService.getCultureByUserId(cultureDTO.getUserId());
				holder.setResult(getCultureDTOList);
			} else {
				holder = new ReturnHolder(false, new ErrorObject("error", "Data Empty"));
			}
		} catch (Exception e) {
			holder = new ReturnHolder(false, new ErrorObject("error", "Unable to Save." + e));
		}
		return holder;
	}

}
