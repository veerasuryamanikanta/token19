package com.balu.token19.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class RequestDTO {

	private Long requestId;

	private Long userId;

	private Long shopdetailsId;

	private String requestPath;

	private String requestStatus;

	private String recieveTime;

	private String tokenNumber;

}
