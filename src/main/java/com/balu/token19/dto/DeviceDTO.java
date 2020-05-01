package com.balu.token19.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
public class DeviceDTO {
	
	private Long deviceId;
	
	private String deviceName;
	
	private String deviceVersion;
	
	private String uniqueID;
	
	private String notificationId;
	
	private String devicetype;
	
	private String createdDate;
	
	private String updatedOn;

}
