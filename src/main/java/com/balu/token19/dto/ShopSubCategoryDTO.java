package com.balu.token19.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class ShopSubCategoryDTO {

	private Long shopsubcategoryId;

	private Long shopcategoryId;

	private String shopsubcategoryName;

	private String shopsubcategoryImage;

	private String createdDate;

	private String updatedOn;

	private Boolean isactive;

}
