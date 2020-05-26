package com.balu.token19.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

	private Long userId;

	private Long roleId;

	private String userName;

	private String userNumber;

	private String userEmail;

	private String userAddress;

	private String userImage;

	private String uniqueID;
	
	private String createdDate;

	private boolean isactive;

}
