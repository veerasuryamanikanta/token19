package com.balu.token19.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.balu.token19.dto.ErrorObject;
import com.balu.token19.dto.LanguageDTO;
import com.balu.token19.dto.ReturnHolder;
import com.balu.token19.service.LanguageService;

@RestController
@RequestMapping(value = "/language")
public class LanguageController {

	@Autowired
	private LanguageService languageService;

	/*
	 * -----------------SAVE APP ARTICLE -------------
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ReturnHolder saveArticle(@RequestBody LanguageDTO languageDTO) {
		ReturnHolder holder = new ReturnHolder();
		try {
			if (languageDTO != null) {
				LanguageDTO savedlanguageDTO = languageService.saveLanguage(languageDTO);
				holder.setResult(savedlanguageDTO);
			} else {
				holder = new ReturnHolder(false, new ErrorObject("error", "Data Empty"));
			}
		} catch (Exception e) {
			holder = new ReturnHolder(false, new ErrorObject("error", "Unable to Save." + e));
		}
		return holder;
	}

	/*
	 * -----------------ARTICLE LIST -------------
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ReturnHolder getArticleList() {
		ReturnHolder holder = new ReturnHolder();
		try {

			List<LanguageDTO> getArticleDTOList = languageService.getLanguages();
			holder.setResult(getArticleDTOList);

		} catch (Exception e) {
			holder = new ReturnHolder(false, new ErrorObject("error", "Unable to Save." + e));
		}
		return holder;
	}

}
