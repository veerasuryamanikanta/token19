package com.balu.token19.serviceimpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.balu.token19.dto.FirebaseRefreshTokenToIdTokenResponseBean;
import com.balu.token19.dto.FirebaseSignInSignUpResponseBean;
import com.balu.token19.excep.HttpBadRequestException;
import com.balu.token19.excep.HttpNotFoundException;
import com.balu.token19.excep.HttpUnauthorizedException;
import com.balu.token19.service.UserAuthenticationService;
import com.balu.token19.utils.ApiUrlConstants;
import com.balu.token19.utils.StringUtility;

@Component
public class UserAuthenticationServiceImpl implements UserAuthenticationService {

	private String firebaseWebApiKey;
	private StringUtility stringUtility = null;

	public UserAuthenticationServiceImpl() {

		stringUtility = new StringUtility();

		Properties properties = new Properties();
		try {
			File file = ResourceUtils.getFile("classpath:firebase-web-api-key.txt");
			InputStream in = new FileInputStream(file);
			properties.load(in);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		this.firebaseWebApiKey = properties.getProperty("firebase-web-api-key");

	}

	@SuppressWarnings("unchecked")
	@Override
	public FirebaseSignInSignUpResponseBean signInWithEmailAndPassword(String email, String password) {

		HttpEntity<String> request = createPostRequestBodyForEmailAndPassword(email, password);

		// This is the generated URI
		String url = stringUtility.findAndReplaceStringIntoString(ApiUrlConstants.FIREBASE_SIGNIN_EMAIL_AND_PASSWORD,
				ApiUrlConstants.FIREBASE_SIGNIN_EMAIL_AND_PASSWORD_TO_BE_CHANGED_PART, this.firebaseWebApiKey);

		ResponseEntity<FirebaseSignInSignUpResponseBean> responseEntity = (ResponseEntity<FirebaseSignInSignUpResponseBean>) doPostForEntity(
				url, request, FirebaseSignInSignUpResponseBean.class);

		return responseEntity.getBody();
	}

	@Override
	public void deleteUserAccount(String idToken) {

		HttpEntity<String> request = createPostRequestBodyForIdToken(idToken);

		// This is the generated URI
		String url = stringUtility.findAndReplaceStringIntoString(ApiUrlConstants.FIREBASE_DELETE_USER_ACCOUNT,
				ApiUrlConstants.FIREBASE_DELETE_USER_ACCOUNT_TO_BE_CHANGED_PART, this.firebaseWebApiKey);

		doPostForEntity(url, request, null);

	}

	@SuppressWarnings("unchecked")
	@Override
	public FirebaseSignInSignUpResponseBean signUpWithEmailAndPassword(String email, String password) {

		try {
			HttpEntity<String> request = createPostRequestBodyForEmailAndPassword(email, password);
			String url = stringUtility.findAndReplaceStringIntoString(
					ApiUrlConstants.FIREBASE_SIGNUP_EMAIL_AND_PASSWORD,
					ApiUrlConstants.FIREBASE_SIGNUP_EMAIL_AND_PASSWORD_TO_BE_CHANGED_PART, this.firebaseWebApiKey);
			ResponseEntity<FirebaseSignInSignUpResponseBean> responseEntity = (ResponseEntity<FirebaseSignInSignUpResponseBean>) doPostForEntity(
					url, request, FirebaseSignInSignUpResponseBean.class);
			return responseEntity.getBody();
		} catch (Exception e) {
			return null;
		}

		// This is the generated URI

	}

	@SuppressWarnings("unchecked")
	@Override
	public FirebaseRefreshTokenToIdTokenResponseBean exchangeRefreshTokenToIdToken(String refreshToken) {

		try {
			HttpEntity<MultiValueMap<String, String>> request = createPostRequestBodyForRefreshTokenToIdToken(
					refreshToken);
			String url = stringUtility.findAndReplaceStringIntoString(
					ApiUrlConstants.FIREBASE_EXCHANGE_REFRESH_TOKEN_TO_ID_TOKEN,
					ApiUrlConstants.FIREBASE_EXCHANGE_REFRESH_TOKEN_TO_ID_TOKEN_TO_BE_CHANGED_PART,
					this.firebaseWebApiKey);
			ResponseEntity<FirebaseRefreshTokenToIdTokenResponseBean> responseEntity = (ResponseEntity<FirebaseRefreshTokenToIdTokenResponseBean>) doPostForEntity(
					url, request, FirebaseRefreshTokenToIdTokenResponseBean.class);
			return responseEntity.getBody();
		} catch (Exception e) {
			return null;
		}
	}

	private ResponseEntity<?> doPostForEntity(String url, Object request, Class<?> responseType) {

		ResponseEntity<?> responseEntity = null;
		try {
			responseEntity = new RestTemplate().postForEntity(url, request, responseType);
		} catch (HttpStatusCodeException e) {
			if (e.getStatusCode() == HttpStatus.BAD_REQUEST) {
				throw new HttpBadRequestException(e.getResponseBodyAsString());
			} else if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
				throw new HttpNotFoundException(e.getResponseBodyAsString());
			} else if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
				throw new HttpUnauthorizedException(e.getResponseBodyAsString());
			} else {
				throw new RuntimeException();
			}
		}

		return responseEntity;

	}

	private HttpEntity<String> createPostRequestBodyForEmailAndPassword(String email, String password) {

		try {
			JSONObject requestBodyJsonObject = new JSONObject();
			requestBodyJsonObject.put("email", email);
			requestBodyJsonObject.put("password", password);
			requestBodyJsonObject.put("returnSecureToken", "true");
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> requestBody = new HttpEntity<String>(requestBodyJsonObject.toString(), httpHeaders);
			return requestBody;
		} catch (Exception e) {
			return null;
		}

	}

	private HttpEntity<String> createPostRequestBodyForIdToken(String idToken) {
		try {
			JSONObject requestBodyJsonObject = new JSONObject();
			requestBodyJsonObject.put("idToken", idToken);
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> requestBody = new HttpEntity<String>(requestBodyJsonObject.toString(), httpHeaders);
			return requestBody;
		} catch (JSONException e) {
			return null;
		}

	}

	private HttpEntity<MultiValueMap<String, String>> createPostRequestBodyForRefreshTokenToIdToken(
			String refreshToken) {
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("grant_type", "refresh_token");
		map.add("refresh_token", refreshToken);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		HttpEntity<MultiValueMap<String, String>> requestBody = new HttpEntity<MultiValueMap<String, String>>(map,
				httpHeaders);
		return requestBody;
	}

}
