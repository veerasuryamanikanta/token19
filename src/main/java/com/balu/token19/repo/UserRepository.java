package com.balu.token19.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.balu.token19.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

	@Query(nativeQuery = true, value = "select * from user where user_number =:userNumber")
	User findUserByNumber(@Param("userNumber") String userNumber);
	
	@Modifying()
	@Transactional
	@Query(nativeQuery = true, value = "UPDATE user SET isactive =:value where user_number =:user_number")
	void updateUser(@Param("user_number") String user_number, @Param("value") boolean value);
	
}
