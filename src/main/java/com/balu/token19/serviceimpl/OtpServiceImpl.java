package com.balu.token19.serviceimpl;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balu.token19.domain.Otp;
import com.balu.token19.dto.OtpDTO;
import com.balu.token19.repo.OtpRepository;
import com.balu.token19.service.OtpService;
import com.balu.token19.utils.AppConstants;
import com.balu.token19.utils.Helper;

@Service
public class OtpServiceImpl implements OtpService {

	private static final Mapper mapper = new DozerBeanMapper();

	@Autowired
	private OtpRepository otpRepository;

	/*
	 * ----------------RESEND OTP--------------------
	 */

	@Override
	public String resendOTP(String usernumber) {
		String response;
		String otp = otpRepository.findOTPByNumber(usernumber);
		if (otp != null) {
			long rndNumber = Helper.createRandomInteger(55, 579026);
			if (Helper.sendOTP(usernumber, rndNumber, AppConstants.AK_VALUE, AppConstants.SECRET_VALUE,
					AppConstants.STAGE, AppConstants.SENDER_ID, AppConstants.otpMessage)) {
				try {
					otpRepository.updateOTP(usernumber, String.valueOf(rndNumber));
					response = "success";
				} catch (Exception e) {
					response = "failed";
				}
			} else {
				response = "failed";
			}
		} else {
			response = "failed";
		}
		return response;
	}

	/*
	 * ----------------VERIFY OTP--------------------
	 */
	@Override
	public String verifyOTP(String usernumber) {
		String db_otpcode = otpRepository.findOTPByNumber(usernumber);
		if (db_otpcode != null) {
			return db_otpcode;
		} else {
			return null;
		}

	}

	/*
	 * ----------------SAVE OTP--------------------
	 */
	@Override
	public OtpDTO saveOtp(OtpDTO otpdto) {
		Otp otp = new Otp();
		mapper.map(otpdto, otp);
		Otp otpData = otpRepository.save(otp);
		OtpDTO otpDtoData = new OtpDTO();
		mapper.map(otpData, otpDtoData);
		return otpDtoData;
	}

	/*
	 * ----------------UPDATE OTP--------------------
	 */
	@Override
	public String updateOtp(OtpDTO otpdto) {
		try {
			otpRepository.updateOTP(otpdto.getUserNumber(), String.valueOf(otpdto.getOtpCode()));
			return "success";
		} catch (Exception e) {
			return "failed";
		}
	}

}
