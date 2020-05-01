package com.balu.token19.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
public class OtpDTO {

	private Long otpId;
	
	private String userNumber;
	
	private String otpCode;
	
	private String dayCount;
	
}
