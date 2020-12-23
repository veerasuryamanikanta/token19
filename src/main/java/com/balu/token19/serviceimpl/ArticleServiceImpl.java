package com.balu.token19.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balu.token19.domain.Article;
import com.balu.token19.dto.ArticleDTO;
import com.balu.token19.repo.ArticleRepository;
import com.balu.token19.repo.UserRepository;
import com.balu.token19.service.ArticleService;
import com.balu.token19.utils.AppConstants;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleRepository articleRepository;

	@Autowired
	private UserRepository userRepository;

	/*
	 * -----------------SAVE ARTICLE -------------
	 */

	@Override
	public ArticleDTO saveArticle(ArticleDTO articleDTO) {
		try {
			Article article = new Article();
			AppConstants.mapper.map(articleDTO, article);
			article.setUser(userRepository.getOne(articleDTO.getUserId()));
			Article saved_article = articleRepository.save(article);
			ArticleDTO returnArticle = new ArticleDTO();
			AppConstants.mapper.map(saved_article, returnArticle);
			return returnArticle;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<ArticleDTO> getArticleByUserId(Long userId) {
		try {
			List<Article> articlesDataList = articleRepository.findByUserId(userId);
			List<ArticleDTO> articlesDtoList = new ArrayList<ArticleDTO>();
			if (articlesDataList.size() != 0) {
				for (int i = 0; i < articlesDataList.size(); i++) {
					Article article = (Article) articlesDataList.get(i);
					ArticleDTO articledto = new ArticleDTO(article.getArticleId(), article.getUser().getUserId(),
							article.getTitle(), article.getContent(), article.getFilepath(), article.getArticleType(),
							"" + article.getCreatedDate(), "" + article.getUpdatedOn(),
							article.isIsactive());
					articlesDtoList.add(articledto);
				}
				return articlesDtoList;
			} else {
				return articlesDtoList;
			}
		} catch (Exception e) {
			return null;
		}
	}

}
