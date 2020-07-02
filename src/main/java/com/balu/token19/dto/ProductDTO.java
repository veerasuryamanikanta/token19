package com.balu.token19.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

	private Long productId;

	private Long subcategoryId;

	private Long productcategoryId;

	private List<ProductQuantitiesDTO> productQuantitiesDTOs;

	private String productName;
	
	private String productCategoryName;

	private String shortDescription;

	private String longDescription;

	private String createdDate;

	private String updatedOn;

	private Boolean isactive;

}
