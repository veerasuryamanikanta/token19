package com.balu.token19.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class TechnicianDTO {

	private Long technicianId;

	private Long userId;

	private String title;

	private String description;

	private String blogurl;

	private String experience;

	private String createdDate;

	private String updatedOn;

	private Boolean isactive;

}
