package com.balu.token19.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class ShopDetailsDTO {

	private Long shopdetailsId;

	private Long userId;

	private Long shopsubcategoryId;

	private String categoryName;

	private String shopName;

	private String userNumber;

	private String ownerName;

	private String areaname;

	private String cityname;

	private String pincode;

	private String shopAddress;

	private String shopLatitude;

	private String shopLongitude;

	private String shopImage;

	private boolean ishomeDelivery;

	private boolean isSelfPickup;
}
