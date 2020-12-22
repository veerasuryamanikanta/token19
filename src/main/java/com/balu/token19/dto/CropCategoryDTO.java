package com.balu.token19.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class CropCategoryDTO {

	private Long cropCategoryId;

	private String cropCategoryName;

	private String cropCategoryCode;

	private String createdDate;

	private String updatedOn;

	private Boolean isactive;

}
