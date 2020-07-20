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
public class OrderItemDTO {

	private Long orderItemId;

	private Long id;
	
	private Long userId;

	private Long productId;

	private Long productQuantityId;

	private String productName;

	private String productDescription;

	private List<ProductImagesDTO> productImages;

	private String productMrp;
	
	private String sellingPrice;

	private String productDiscount;

	private String specialOffer;
	
	private String quantity;

	private String quantityName;

	private String createdDate;

	private String itemQuantity;

	private String updatedOn;

	private Boolean isactive;

}
