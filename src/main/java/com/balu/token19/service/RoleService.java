package com.balu.token19.service;

import java.util.List;

import com.balu.token19.dto.RoleDTO;

public interface RoleService {
	
	RoleDTO saveRole(RoleDTO roleDTO);
	
	RoleDTO findByRoleName(String roleName);
	
	List<RoleDTO> findByAllRoles();

}
