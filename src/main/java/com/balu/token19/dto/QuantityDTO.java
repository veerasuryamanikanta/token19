package com.balu.token19.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class QuantityDTO {

	private Long quantityId;

	private String quantityName;
	
	private String quantityCode;

	private String createdDate;

	private String updatedOn;

	private Boolean isactive;

}
