package com.balu.token19.service;

import java.util.concurrent.CompletableFuture;

import com.balu.token19.dto.FcmDTO;

public interface FcmService {

	public CompletableFuture<String> send(FcmDTO fcmDTO);

}
