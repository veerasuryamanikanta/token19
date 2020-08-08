package com.balu.token19.serviceimpl;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.balu.token19.dto.FcmDTO;
import com.balu.token19.excep.HeaderRequestInterceptor;
import com.balu.token19.service.FcmService;

@Service
public class FcmServiceImpl implements FcmService {

	private static final String FIREBASE_SERVER_KEY = "AAAAgW5NQ9E:APA91bHMNM0N7CN5yW1-j164opvpswxpA6tySq78jkJbjF"
			+ "ckZpD5ts94ggIGYXPMx7mF4Cem8_mKcUHmBhXiEbAU1mNV39Hez88iEly_zfeCJqxDGCGBmbP2lHtY5AYRFV1Jf4HE8hIT";
	private static final String FIREBASE_API_URL = "https://fcm.googleapis.com/fcm/send";

	@Override
	public CompletableFuture<String> send(FcmDTO fcmDTO) {
		RestTemplate restTemplate = new RestTemplate();
		ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
		interceptors.add(new HeaderRequestInterceptor("Authorization", "key=" + FIREBASE_SERVER_KEY));
		interceptors.add(new HeaderRequestInterceptor("Content-Type", "application/json"));
		restTemplate.setInterceptors(interceptors);
		JSONObject data = new JSONObject();
		try {
			data.put("title", fcmDTO.getTitle());
			data.put("body", fcmDTO.getBody());
			data.put("image", fcmDTO.getImage());
			data.put("requestId", fcmDTO.getRequestdto().getRequestId());
			data.put("requestPath", fcmDTO.getRequestdto().getRequestPath());
			data.put("requestStatus", fcmDTO.getRequestdto().getRequestStatus());
			data.put("requestType", fcmDTO.getRequestdto().getRequestType());
			JSONObject body = new JSONObject();
			body.put("to", fcmDTO.getTo());
			body.put("data", data);
			HttpEntity<String> request = new HttpEntity<>(body.toString());
			String firebaseResponse = restTemplate.postForObject(FIREBASE_API_URL, request, String.class);
			return CompletableFuture.completedFuture(firebaseResponse);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			return null;
		}

	}

}
