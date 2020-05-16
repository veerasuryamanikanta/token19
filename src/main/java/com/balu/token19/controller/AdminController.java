package com.balu.token19.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.balu.token19.dto.DeviceDTO;
import com.balu.token19.dto.ErrorObject;
import com.balu.token19.dto.ReturnHolder;
import com.balu.token19.dto.RoleDTO;
import com.balu.token19.dto.VersionDTO;
import com.balu.token19.service.DeviceService;
import com.balu.token19.service.RoleService;
import com.balu.token19.service.VersionService;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {

	@Autowired
	private RoleService roleService;

	@Autowired
	private VersionService versionService;

	@Autowired
	private DeviceService deviceService;

	/*
	 * -----------------SAVE ROLE -------------
	 */
	@RequestMapping(value = "/role/save", method = RequestMethod.POST)
	public ReturnHolder saveRole(@RequestBody RoleDTO roleDTO) {
		ReturnHolder holder = new ReturnHolder();
		try {
			if (roleDTO != null) {
				if (roleDTO.getRoleName().equalsIgnoreCase("")) {
					holder = new ReturnHolder(false, new ErrorObject("error", "Role Name Must Not Be Empty"));
				} else if (roleDTO.getRoleName().equalsIgnoreCase("")) {
					holder = new ReturnHolder(false, new ErrorObject("error", "Role Code Must Not Be Empty"));
				} else {
					RoleDTO roleDtodata = roleService.findByRoleName(roleDTO.getRoleName());
					if (roleDtodata != null) {
						holder = new ReturnHolder(false, new ErrorObject("error", "Role Name Already Exists"));
					} else {
						RoleDTO rolesdata = roleService.saveRole(roleDTO);
						holder.setResult(rolesdata);
					}
				}
			} else {
				holder = new ReturnHolder(false, new ErrorObject("error", "Data Empty"));
			}

		} catch (Exception e) {
			holder = new ReturnHolder(false, new ErrorObject("error", "Unable to Save." + e));
		}
		return holder;
	}

	/*
	 * -----------------ROLES LIST -------------
	 */
	@RequestMapping(value = "/role/list", method = RequestMethod.GET)
	public ReturnHolder getRole() {
		ReturnHolder holder = new ReturnHolder();
		try {
			List<RoleDTO> roleDtoList = roleService.findByAllRoles();
			holder.setResult(roleDtoList);
		} catch (Exception e) {
			holder = new ReturnHolder(false, new ErrorObject("error", "Unable to Load." + e));
		}
		return holder;
	}

	/*
	 * -----------------SAVE APP VERSION -------------
	 */
	@RequestMapping(value = "/version/save", method = RequestMethod.POST)
	public ReturnHolder saveVersion(@RequestBody VersionDTO versionDTO) {
		ReturnHolder holder = new ReturnHolder();
		try {
			if (versionDTO != null) {
				VersionDTO versiondto = versionService.saveVersion(versionDTO);
				holder.setResult(versiondto);
			} else {
				holder = new ReturnHolder(false, new ErrorObject("error", "Data Empty"));
			}

		} catch (Exception e) {
			holder = new ReturnHolder(false, new ErrorObject("error", "Unable to Save." + e));
		}
		return holder;
	}

	/*
	 * -----------------APP VERSION INFO -------------
	 */
	@RequestMapping(value = "/app/version", method = RequestMethod.GET)
	public ReturnHolder getLatestVersion() {
		ReturnHolder holder = new ReturnHolder();
		try {
			VersionDTO versiondto = versionService.findLatestVersion();
			holder.setResult(versiondto);
		} catch (Exception e) {
			holder = new ReturnHolder(false, new ErrorObject("error", "Unable to Load." + e));
		}
		return holder;
	}

	/*
	 * -----------------SAVE DEVICE -------------
	 */
	@RequestMapping(value = "/device/save", method = RequestMethod.POST)
	public ReturnHolder saveDevice(@RequestBody DeviceDTO deviceDTO) {
		ReturnHolder holder = new ReturnHolder();
		try {
			if (deviceDTO != null) {
				DeviceDTO devicedto = deviceService.findByNotificationId(deviceDTO.getNotificationId());
				if (devicedto != null) {
					holder = new ReturnHolder(false, new ErrorObject("error", "Device Already Registered."));
				} else {
					if (deviceDTO.getNotificationId().equalsIgnoreCase("")) {
						holder = new ReturnHolder(false,
								new ErrorObject("error", "Notificatoin Id Must Not Be Empty.."));
					} else {
						DeviceDTO deviceDtodata = deviceService.saveDevice(deviceDTO);
						holder.setResult(deviceDtodata);
					}
				}

			} else {
				holder = new ReturnHolder(false, new ErrorObject("error", "Data Empty.."));
			}

		} catch (Exception e) {
			holder = new ReturnHolder(false, new ErrorObject("error", "Unable to Load." + e));
		}
		return holder;
	}

}
