package com.balu.token19.dto;

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

	private Long orderId;
	
	private Long userId;

	private Long productId;

	private Long quantityId;

	private String productName;

	private String productDescription;

	private String productImagePath;

	private String productMrp;

	private String productDiscount;

	private String specialOffer;

	private String quantityName;

	private String createdDate;

	private String itemQuantity;

	private String updatedOn;

	private Boolean isactive;

}
