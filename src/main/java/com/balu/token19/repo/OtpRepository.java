package com.balu.token19.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.balu.token19.domain.Otp;

public interface OtpRepository extends JpaRepository<Otp,Long>{
	
	@Query(nativeQuery = true, value = "select otp_code from otp where user_number =:user_number")
	String findOTPByNumber(@Param("user_number") String user_number);

	@Modifying()
	@Transactional
	@Query(nativeQuery = true, value = "UPDATE otp SET otp_code =:otp_code where user_number =:user_number")
	void updateOTP(@Param("user_number") String user_number, @Param("otp_code") String otp_code);
}
