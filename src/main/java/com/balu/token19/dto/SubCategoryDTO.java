package com.balu.token19.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class SubCategoryDTO {

	private Long subcategoryId;

	private Long categoryId;
	
	private Long shopsubcategoryId;

	private String subcategoryName;
	
	private String shopsubcategoryName;

	private String subcategoryImage;

	private String createdDate;

	private String updatedOn;

	private Boolean isactive;

}
