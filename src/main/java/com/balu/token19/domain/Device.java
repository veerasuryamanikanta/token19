package com.balu.token19.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import com.sun.istack.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Device extends Root{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "deviceSeq")
	@SequenceGenerator(name = "deviceSeq", sequenceName = "DEVICE_ID_SEQ", allocationSize = 1)
	private Long deviceId;
	
	private String deviceName;
	
	@NotNull
	public int deviceVersion;

	@NotNull
	public String uniqueID;

	@NotNull
	public String notificationId;

	public String devicetype;
	
}
