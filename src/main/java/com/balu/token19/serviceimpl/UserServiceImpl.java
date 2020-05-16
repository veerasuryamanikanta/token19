package com.balu.token19.serviceimpl;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balu.token19.domain.User;
import com.balu.token19.dto.UserDTO;
import com.balu.token19.repo.RoleRepository;
import com.balu.token19.repo.UserRepository;
import com.balu.token19.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private static final Mapper mapper = new DozerBeanMapper();

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	/*
	 * -----------------SAVE USER -------------
	 */
	@Override
	public UserDTO saveUser(UserDTO userDTO) {
		
		User user = new User();
		mapper.map(userDTO, user);
		if(userDTO.getRoleId()!=null) {
			user.setRole(roleRepository.getOne(userDTO.getRoleId()));
		}
		User userData = userRepository.save(user);
		UserDTO userDtoData = new UserDTO();
		mapper.map(userData, userDtoData);
		userDtoData.setRoleId(userData.getRole().getRoleId());
		return userDtoData;
	}

	/*
	 * -----------------GET USER BY NUMBER -------------
	 */
	@Override
	public UserDTO findByUserByNumber(String userNumber) {
		User userData = userRepository.findUserByNumber(userNumber);
		if (userData != null) {
			UserDTO userDtoData = new UserDTO();
			mapper.map(userData, userDtoData);
			userDtoData.setRoleId(userData.getRole().getRoleId());
			return userDtoData;
		} else {
			return null;
		}
	}

	/*
	 * ----------------ACTIVATE USER--------------------
	 */
	@Override
	public String activateUser(String usernumber) {
		try {
			userRepository.updateUser(usernumber, true);
			return "success";
		} catch (Exception e) {
			return "failed";
		}

	}

}
