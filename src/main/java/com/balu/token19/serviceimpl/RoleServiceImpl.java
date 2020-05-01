package com.balu.token19.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balu.token19.domain.Role;
import com.balu.token19.dto.RoleDTO;
import com.balu.token19.repo.RoleRepository;
import com.balu.token19.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
	
	private static final Mapper mapper = new DozerBeanMapper();

	@Autowired
	private RoleRepository roleRepository;
	
	
	/*
	 * -----------------SAVE ROLE -------------
	 */
	@Override
	public RoleDTO saveRole(RoleDTO roleDTO) {
		Role role = new Role();
		mapper.map(roleDTO, role);
		Role roleData = roleRepository.save(role);
		RoleDTO roleDtoData = new RoleDTO();
		mapper.map(roleData, roleDtoData);
		return roleDtoData;
	}

	
	/*
	 * -----------------GET ROLE BY NAME -------------
	 */
	@Override
	public RoleDTO findByRoleName(String roleName) {
		Role roleData = roleRepository.findByroleName(roleName);
		if(roleData!=null) {
			RoleDTO roleDtoData = new RoleDTO();
			mapper.map(roleData, roleDtoData);
			return roleDtoData;
		}else {
			return null;
		}
	}

	
	/*
	 * -----------------GET ALL ROLES -------------
	 */
	@Override
	public List<RoleDTO> findByAllRoles() {
		List<Role> roleDataList = roleRepository.findAll();
		List<RoleDTO> roleDtoList = new ArrayList<RoleDTO>();
		if(roleDataList.size()!=0) {
			for(int i=0;i<roleDataList.size();i++) {
				Role role = (Role)roleDataList.get(i);
				RoleDTO roledto = new RoleDTO(role.getRoleId(),role.getRoleName(),role.getRoleCode(),""+role.getCreatedDate(),
						""+role.getUpdatedOn());
				roleDtoList.add(roledto);
			}
			return 	roleDtoList;
		}else {
			return roleDtoList;
		}
		
	}

}
