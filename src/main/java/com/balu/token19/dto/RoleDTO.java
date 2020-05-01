package com.balu.token19.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {
	
	private Long roleId;
	
	private String roleName;
	
	private String roleCode;
	
	private String createdDate;
	
	private String updatedOn;

}
