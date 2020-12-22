package com.balu.token19.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.balu.token19.dto.ArticleDTO;
import com.balu.token19.dto.ErrorObject;
import com.balu.token19.dto.ReturnHolder;
import com.balu.token19.service.ArticleService;

@RestController
@RequestMapping(value = "/article")
public class ArticleController {

	@Autowired
	private ArticleService articleService;

	/*
	 * -----------------SAVE APP ARTICLE -------------
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ReturnHolder saveArticle(@RequestBody ArticleDTO articleDTO) {
		ReturnHolder holder = new ReturnHolder();
		try {
			if (articleDTO != null) {
				ArticleDTO savedArticleDTO = articleService.saveArticle(articleDTO);
				holder.setResult(savedArticleDTO);
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
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public ReturnHolder getArticleList(@RequestBody ArticleDTO articleDTO) {
		ReturnHolder holder = new ReturnHolder();
		try {
			if (articleDTO != null) {
				List<ArticleDTO> getArticleDTOList = articleService.getArticleByUserId(articleDTO.getUserId());
				holder.setResult(getArticleDTOList);
			} else {
				holder = new ReturnHolder(false, new ErrorObject("error", "Data Empty"));
			}
		} catch (Exception e) {
			holder = new ReturnHolder(false, new ErrorObject("error", "Unable to Save." + e));
		}
		return holder;
	}

}
