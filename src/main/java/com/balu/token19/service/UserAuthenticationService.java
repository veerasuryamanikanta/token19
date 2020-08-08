package com.balu.token19.service;

import com.balu.token19.dto.FirebaseRefreshTokenToIdTokenResponseBean;
import com.balu.token19.dto.FirebaseSignInSignUpResponseBean;

public interface UserAuthenticationService {

    public FirebaseSignInSignUpResponseBean signUpWithEmailAndPassword(String email, String password);

    public FirebaseSignInSignUpResponseBean signInWithEmailAndPassword(String email, String password);

    public void deleteUserAccount(String idToken);

    public FirebaseRefreshTokenToIdTokenResponseBean exchangeRefreshTokenToIdToken(String refreshToken);

}
