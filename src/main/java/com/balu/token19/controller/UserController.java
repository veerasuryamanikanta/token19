package com.balu.token19.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.balu.token19.dto.ErrorObject;
import com.balu.token19.dto.OtpDTO;
import com.balu.token19.dto.ReturnHolder;
import com.balu.token19.dto.UserDTO;
import com.balu.token19.service.OtpService;
import com.balu.token19.service.UserService;
import com.balu.token19.utils.AppConstants;
import com.balu.token19.utils.Helper;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private OtpService otpService;

	/*
	 * -----------------SAVE USER -------------
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ReturnHolder saveUser(@RequestBody UserDTO userDTO) {
		ReturnHolder holder = new ReturnHolder();
		try {
			if (userDTO != null) {
				if (userDTO.getUserNumber().equalsIgnoreCase("")) {
					holder = new ReturnHolder(false, new ErrorObject("error", "User Number Must Not Be Empty"));
				} else if (userDTO.getRoleId() == null) {
					holder = new ReturnHolder(false, new ErrorObject("error", "UserType Must Not Be Empty"));
				} else {

					UserDTO userDtodata = userService.findByUserByNumber(userDTO.getUserNumber());
					if (userDtodata != null) {
						if (userDtodata.getUniqueID() == null) {
							userDtodata.setUniqueID(userDTO.getUniqueID());
							userDtodata.setCreatedDate(userDtodata.getCreatedDate());
							userDtodata = userService.saveUser(userDtodata);
						} else {
							if (!userDtodata.getUniqueID().equalsIgnoreCase(userDTO.getUniqueID())) {
								userDtodata.setUniqueID(userDTO.getUniqueID());
								userDtodata.setCreatedDate(userDtodata.getCreatedDate());
								userDtodata = userService.saveUser(userDtodata);
							}
						}
					} else {
						userDtodata = userService.saveUser(userDtodata);
					}

					long rndNumber = Helper.createRandomInteger(111, 579026);
					if (Helper.sendOTP(userDtodata.getUserNumber(), rndNumber, AppConstants.AK_VALUE,
							AppConstants.SECRET_VALUE, AppConstants.STAGE, AppConstants.SENDER_ID,
							AppConstants.otpMessage)) {
						OtpDTO otpdto = new OtpDTO();
						otpdto.setUserNumber(userDtodata.getUserNumber());
						otpdto.setOtpCode("" + rndNumber);
						String otpdtodaata = otpService.updateOtp(otpdto);
						if (otpdtodaata.equalsIgnoreCase("success")) {
							holder.setResult(userDtodata);
						} else {
							holder = new ReturnHolder(false, new ErrorObject("error", "Otp Sent Failed"));
						}
					} else {
						holder = new ReturnHolder(false, new ErrorObject("error", "Otp Sent Failed"));
					}
				}
			} else {
				holder = new ReturnHolder(false, new ErrorObject("error", "Data Empty"));
			}

		} catch (Exception e) {
			holder = new ReturnHolder(false, new ErrorObject("error", "Unable to Save." + e));
		}
		return holder;
	}

	/*
	 * -----------------VERIFY OTP -------------
	 */
	@RequestMapping(value = "/verify/{otpcode}/{usernumber}", method = RequestMethod.GET)
	public ReturnHolder verifyOTP(@PathVariable("otpcode") String otpcode,
			@PathVariable("usernumber") String usernumber) {
		ReturnHolder holder = new ReturnHolder();
		try {
			String db_otpcode = otpService.verifyOTP(usernumber);
			if (db_otpcode != null) {
				if (db_otpcode.equalsIgnoreCase(otpcode)) {
					String status = userService.activateUser(usernumber);
					if (status.equalsIgnoreCase("success")) {
						holder.setResult("success");
					} else {
						holder = new ReturnHolder(false, new ErrorObject("error", "Unable To Update"));
					}
				} else {
					holder = new ReturnHolder(false, new ErrorObject("error", "Invalid OTP"));
				}
			} else {
				holder = new ReturnHolder(false, new ErrorObject("error", "Invalid Mobile Number"));
			}
		} catch (Exception e) {
			holder = new ReturnHolder(false, new ErrorObject("error", "Unable to Check."));
		}
		return holder;
	}

	/*
	 * -----------------RESEND OTP -------------
	 */
	@RequestMapping(value = "/resend/{usernumber}", method = RequestMethod.GET)
	public ReturnHolder resendOTP(@PathVariable("usernumber") String usernumber) {
		ReturnHolder holder = new ReturnHolder();
		try {
			String response = otpService.resendOTP(usernumber);
			if (response.equalsIgnoreCase("success")) {
				holder.setResult("success");
			} else {
				holder = new ReturnHolder(false, new ErrorObject("error", "Invalid Mobile Number"));
			}
		} catch (Exception e) {
			holder = new ReturnHolder(false, new ErrorObject("error", "Unable to Check."));
		}
		return holder;
	}
}
