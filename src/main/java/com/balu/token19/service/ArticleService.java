package com.balu.token19.service;

import java.util.List;

import com.balu.token19.dto.ArticleDTO;

public interface ArticleService {

	ArticleDTO saveArticle(ArticleDTO articleDTO);
	
	List<ArticleDTO> getArticleByUserId(Long userId);

}
