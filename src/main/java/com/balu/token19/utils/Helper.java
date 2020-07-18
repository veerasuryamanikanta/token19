package com.balu.token19.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Random;

public class Helper {

	public static boolean sendOTP(String mobilenumber, Long rndNumber, String akvalue, String skvalue, String utype,
			String senderValue, String message) {

		try {
			// String token = AppConstants.apikey + URLEncoder.encode(akvalue,
			// AppConstants.utf);
			// String secret = AppConstants.secret + URLEncoder.encode(skvalue,
			// AppConstants.utf);
			// String usetype = AppConstants.usetype + URLEncoder.encode(utype,
			// AppConstants.utf);
			// String mobile = AppConstants.phone + URLEncoder.encode(mobilenumber,
			// AppConstants.utf);
			// String messageText = AppConstants.message + URLEncoder.encode(rndNumber +
			// message, AppConstants.utf);
			// String senderId = AppConstants.senderid + URLEncoder.encode(senderValue,
			// AppConstants.utf);

			String userId = "UserID =odospl";
			String Password = "Password=ewan2628EW";
			String SenderID = "SenderID=MTOKEN";
			String Phno = "Phno=" + mobilenumber;
			String Msg = "Msg=" + message;

			String username = AppConstants.username + "suryaaa";
			String password = AppConstants.password + "Surya@7891";
			String from = AppConstants.from + "TOKENS";
			String to = AppConstants.to + URLEncoder.encode(mobilenumber, AppConstants.utf);
			String messageText = AppConstants.msg + URLEncoder.encode(rndNumber + message, AppConstants.utf);
			String type = AppConstants.type + "1";

			// URL obj = new URL(AppConstants.url + token + secret + usetype + mobile +
			// messageText + senderId);

//			URL obj = new URL(AppConstants.url + username + password + from + to + messageText + type);
			
			URL obj = new URL(AppConstants.url + userId + Password + SenderID + Phno + Msg);

			HttpURLConnection httpConnection = (HttpURLConnection) obj.openConnection();
			httpConnection.setDoOutput(true);
			BufferedReader bufferedReader = null;
			if (httpConnection.getResponseCode() == 200) {
				bufferedReader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
			} else {
				bufferedReader = new BufferedReader(new InputStreamReader(httpConnection.getErrorStream()));
			}

			StringBuilder content = new StringBuilder();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				content.append(line).append("\n");
			}
			bufferedReader.close();
			return true;
		} catch (Exception ex) {
			return false;

		}
	}

	public static long createRandomInteger(int aStart, long aEnd) {
		Random r = new Random();
		if (aStart > aEnd) {
			throw new IllegalArgumentException("Start cannot exceed End.");
		}
		long range = aEnd - (long) aStart + 1;
		long fraction = (long) (range * r.nextDouble());
		long randomNumber = fraction + (long) aStart;
		return randomNumber;

	}

}
