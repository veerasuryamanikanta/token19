package com.balu.token19.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class SlidersDTO {

	private Long sliderId;

	private String sliderName;

	private String sliderPath;

	private String sliderViewType;

	private String createdDate;

	private String updatedOn;

	private Boolean isactive;

}
