package com.balu.token19.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class CropDTO {

	private Long cropId;

	private Long cultureId;
	
	private Long cropCategoryId;

	private String cropname;

	private String description;
	
	private String imagepath;

	private String createdDate;

	private String updatedOn;

	private Boolean isactive;

}
