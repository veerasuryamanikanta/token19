package com.balu.token19.dto;

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

	private String productName;

	private String productDescription;

	private String productImagePath;

	private String productMrp;

	private String productDiscount;

	private String specialOffer;

	private String createdDate;

	private String updatedOn;

	private Boolean isactive;

}
