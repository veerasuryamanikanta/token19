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
public class ProductQuantitiesDTO {

	private Long productquantityId;

	private Long quantityId;

	private String description;
	
	private String imagePath;

	private String mrpprice;

	private String discount;

	private String sellingprice;

	private Boolean isavailable;

	private List<ProductImagesDTO> productImagesDTOs;

	private String createdDate;

	private String updatedOn;

	private Boolean isactive;

}
