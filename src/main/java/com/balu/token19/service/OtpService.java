package com.balu.token19.service;

import com.balu.token19.dto.OtpDTO;

public interface OtpService {

	String resendOTP(String usernumber);

	String verifyOTP( String usernumber);
	
	OtpDTO saveOtp( OtpDTO otpdto);
	
	String updateOtp( OtpDTO otpdto);
	
}
