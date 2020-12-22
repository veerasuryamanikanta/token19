package com.balu.token19.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.balu.token19.domain.Article;

public interface ArticleRepository extends JpaRepository<Article,Long>{
	
	@Query(nativeQuery = true, value = "select * from article where user_id =:userId")
	List<Article> findByUserId(@Param("userId") Long userId);
	
}
