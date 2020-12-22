package com.balu.token19.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class CultureDTO {

	private Long cultureId;

	private Long userId;

	private String title;

	private String description;

	private String createdDate;

	private String updatedOn;

	private Boolean isactive;

}
