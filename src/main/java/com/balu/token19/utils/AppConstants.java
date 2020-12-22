package com.balu.token19.utils;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

public class AppConstants {

	// response strings
	public static String success = "Sucess";
	public static String saved = "saved successfully";
	public static String updated = "updated successfully";
	public static String deleted = "deleted successfull";
	public static String failed = "Failed";
	public static String invalidrole = "Invalid Role Entered";
	public static String invalidotp = "Invalid OTP Entered";
	public static String alreadytank = "Tank altready registered with this Name.";
	public static String alreadyuser = "User altready registered with this Number.";
	public static String alreadyculture = "Culture altready registered.";
	public static String invalidcredentials = "Invalid Credentials";

	public static String alreadysaved = "Already Title Registered With this One.";

	// message strings
	public static String apikey = "apikey=";
	public static String utf = "UTF-8";
	public static String secret = "&secret=";
	public static String usetype = "&usetype=";
	public static String phone = "&phone=";
	public static String message = "&message=";
	public static String senderid = "&senderid=";
	public static String url = "https://www.smsstriker.com/API/sms.php?";
	public static String username = "username=";
	public static String password = "&password=";
	public static String from = "&from=";
	public static String to = "&to=";
	public static String msg = "&msg=";
	public static String type = "&type=1";
	public static String otpMessage = " is your one time password to proceed on token19.co.in it is valid for next 10 miniutes.";
	public static String AK_VALUE = "56U29Q98DU6FIH73HTUI68SQ1CWIJNCQ";
	public static String SECRET_VALUE = "BDHG5HFWJALXB1VV";
	public static String SENDER_ID = "StAqua";
	public static String STAGE = "prod";
	
	public static final Mapper mapper = new DozerBeanMapper();

}
