package com.balu.token19.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDTO {

	private Long articleId;

	private Long userId;

	private String title;

	private String content;

	private String filepath;

	private String articleType;

	private String createdDate;

	private String updatedOn;

	private Boolean isactive;

}
