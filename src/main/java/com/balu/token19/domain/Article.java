package com.balu.token19.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Article extends Root {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "articleSeq")
	@SequenceGenerator(name = "articleSeq", sequenceName = "ARTICLE_ID_SEQ", allocationSize = 1)
	private Long articleId;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	public User user;

	private String title;

	private String content;

	private String filepath;

	private String articleType;

}
