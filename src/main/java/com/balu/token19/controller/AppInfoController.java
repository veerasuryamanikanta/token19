package com.balu.token19.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.balu.token19.dto.DeviceDTO;
import com.balu.token19.dto.ErrorObject;
import com.balu.token19.dto.ReturnHolder;
import com.balu.token19.dto.VersionDTO;
import com.balu.token19.service.DeviceService;
import com.balu.token19.service.FileStorageService;
import com.balu.token19.service.VersionService;

@RestController
@RequestMapping(value = "/app")
public class AppInfoController {

	@Autowired
	private VersionService versionService;

	@Autowired
	private DeviceService deviceService;

	@Autowired
	private FileStorageService fileStorageService;

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
	@RequestMapping(value = "/version", method = RequestMethod.GET)
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
				DeviceDTO devicedto = deviceService.findByDeviceId(deviceDTO.getUniqueID());
				if (devicedto != null) {
					String notificationId = devicedto.getNotificationId();
					if (notificationId.equalsIgnoreCase(deviceDTO.getNotificationId())) {
						holder = new ReturnHolder(false, new ErrorObject("error", "Device Already Registered."));
					} else {
						if (deviceDTO.getNotificationId().equalsIgnoreCase("")) {
							holder = new ReturnHolder(false,
									new ErrorObject("error", "Notificatoin Id Must Not Be Empty.."));
						} else {
							devicedto.setCreatedDate("");
							devicedto.setUpdatedOn("");
							devicedto.setNotificationId(deviceDTO.getNotificationId());
							DeviceDTO deviceDtodata = deviceService.saveDevice(devicedto);
							holder.setResult(deviceDtodata);
						}
					}

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

	/*
	 * -----------------UPLOAD FILE -------------
	 */
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public ReturnHolder uploadFile(@RequestParam("file") MultipartFile file) {

		ReturnHolder holder = new ReturnHolder();
		try {
			if (file != null) {
				String fileName = fileStorageService.storeFile(file);
				String fileDownloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/")
						.path(fileName).toUriString();
				holder.setResult(fileDownloadUrl);
			} else {
				holder = new ReturnHolder(false, new ErrorObject("error", "File Path Empty."));
			}

		} catch (Exception e) {
			holder = new ReturnHolder(false, new ErrorObject("error", "File Path Empty."));
		}
		return holder;
	}

	/*
	 * -----------------DOWNLOAD FILE -------------
	 */
	@RequestMapping(value = "/downloadFile/{fileName:.+}", method = RequestMethod.GET)
	public ResponseEntity<Resource> downloadFile(String fileName, HttpServletRequest request) {
		Resource resource = fileStorageService.loadFileAsResource(fileName);
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException ex) {
			contentType = null;
		}

		if (contentType == null) {
			contentType = "application/octet-stream";
		}

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}

}
