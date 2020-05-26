package com.balu.token19.controller;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.balu.token19.dto.FcmDTO;
import com.balu.token19.service.FcmService;

@RestController
@RequestMapping(value = "/fcm")
public class FcmController {

	@Autowired
	FcmService fcmservice;

	@RequestMapping(value = "/send", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<String> send(@RequestBody FcmDTO fcmDTO) {
		try {
			CompletableFuture<String> pushNotification = fcmservice.send(fcmDTO);
			CompletableFuture.allOf(pushNotification).join();
			String firebaseResponse = pushNotification.get();
			return new ResponseEntity<>(firebaseResponse, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("Push Notification ERROR!", HttpStatus.BAD_REQUEST);
	}

}
