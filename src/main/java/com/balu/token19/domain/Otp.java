package com.balu.token19.domain;

import javax.persistence.Column;
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
public class Otp extends Root {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "otpSeq")
	@SequenceGenerator(name = "otpSeq", sequenceName = "OTP_ID_SEQ", allocationSize = 1)
	private Long otpId;

	@Column(nullable = false, updatable = true)
	@NotNull
	private String userNumber;

	@Column(nullable = false, unique = true, updatable = true)
	@NotNull
	private String otpCode;

	private String daycount;

}
