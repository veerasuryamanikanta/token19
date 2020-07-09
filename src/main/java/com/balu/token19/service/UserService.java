package com.balu.token19.service;

import com.balu.token19.dto.UserDTO;

public interface UserService {
	
	UserDTO saveUser(UserDTO userDTO);
	
	UserDTO updateUser(UserDTO userDTO);

	UserDTO findByUserByNumber(String userNumber);
	
	String activateUser(String usernumber);
			
}
