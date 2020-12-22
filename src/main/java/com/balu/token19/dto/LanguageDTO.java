package com.balu.token19.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class LanguageDTO {

	private Long languageId;

	private String languageMotherName;

	private String languageEnglishName;

	private String createdDate;

	private String updatedOn;

	private Boolean isactive;

}
