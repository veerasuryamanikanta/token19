package com.balu.token19.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class ServicesDTO {

	private Long serviceId;

	private Long userId;

	private Long shopdetailsId;

	private String serviceName;

	private String serviceDesc;

	private String servicePrice;

	private String serviceImagePath;

}
