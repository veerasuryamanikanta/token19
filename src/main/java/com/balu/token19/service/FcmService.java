package com.balu.token19.service;

import java.util.concurrent.CompletableFuture;

import com.balu.token19.dto.FcmDTO;

public interface FcmService {

	CompletableFuture<String> send(FcmDTO fcmDTO);

}
