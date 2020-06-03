package com.balu.token19.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategoryDTO {

	private Long productcategoryId;

	private String productcategoryName;

	private String productcategoryImage;

	private String createdDate;

	private String updatedOn;

	private Boolean isactive;

}
