package com.balu.token19.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class ProductAvailabilityDTO {

	private Long paId;

	private Long userId;
	
	private Long shopdetailsId;
	
	private Long subcategoryId;
	
	private Long productquantityId;

	public String description;

	public String mrpprice;

	public String discount;

	public String sellingprice;

	public Boolean isavailable;

	private String createdDate;

	private String updatedOn;

	private Boolean isactive;

}
