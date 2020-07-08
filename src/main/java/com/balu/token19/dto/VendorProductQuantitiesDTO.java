package com.balu.token19.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class VendorProductQuantitiesDTO {

	private Long vpqId;

	private Long userId;
	
	private Long shopdetailsId;
	
	private Long productquantityId;
	
	private Long productId;
	
	private Long subCategoryId;

	private String description;
	
	private String mrpprice;

	private String discount;

	private String sellingprice;

	private Boolean isavailable;

	private String createdDate;

	private String updatedOn;

	private Boolean isactive;

}
