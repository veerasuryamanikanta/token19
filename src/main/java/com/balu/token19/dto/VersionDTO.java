package com.balu.token19.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
public class VersionDTO {
	
	private Long versionId;
	
	private String versionName;
	
	private String versionCode;
	
	private String sourceType;
	
	private String updatePath;
	
	private String updateMessage;
	
	private Boolean isMandatory;
	
	private String createdDate;
	
	private String updatedOn;

}
