package com.balu.token19.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {

	private Long cartId;

	private Long userId;

	private Long productId;

	private Long quantityId;

	private String createdDate;

	private String itemQuantity;

	private String updatedOn;

	private Boolean isactive;

}
