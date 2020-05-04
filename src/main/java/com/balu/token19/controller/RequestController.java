package com.balu.token19.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.balu.token19.dto.ErrorObject;
import com.balu.token19.dto.RequestDTO;
import com.balu.token19.dto.ReturnHolder;
import com.balu.token19.service.FileStorageService;
import com.balu.token19.service.RequestService;

@RestController
@RequestMapping(value = "/request")
public class RequestController {

	@Autowired
	private FileStorageService fileStorageService;

	@Autowired
	private RequestService requestService;

	
	/*
	 * -----------------SAVE REQUEST -------------
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ReturnHolder saveRequest(@RequestBody RequestDTO requestDTO) {

		ReturnHolder holder = new ReturnHolder();
		try {
			if (requestDTO != null) {
				RequestDTO requestDtoData = requestService.saveRequest(requestDTO);
				holder.setResult(requestDtoData);
			} else {
				holder = new ReturnHolder(false, new ErrorObject("error", "Data Empty."));
			}

		} catch (Exception e) {
			holder = new ReturnHolder(false, new ErrorObject("error", "Data Empty."));
		}
		return holder;
	}

	
	/*
	 * -----------------GET REQUEST INFO BY SHOP ID -------------
	 */
	@RequestMapping(value = "/list/{shopdetailsId}", method = RequestMethod.GET)
	public ReturnHolder getRequests(@PathVariable("shopdetailsId") Long shopdetailsId) {
		ReturnHolder holder = new ReturnHolder();
		try {
			if (shopdetailsId != null) {
				List<RequestDTO> requestDTOList = requestService.findByShopId(shopdetailsId);
				if (requestDTOList.size() != 0) {
					holder.setResult(requestDTOList);
				} else {
					holder = new ReturnHolder(false, new ErrorObject("error", "Data Empty."));
				}
			} else {
				holder = new ReturnHolder(false, new ErrorObject("error", "Data Empty."));
			}

		} catch (Exception e) {
			holder = new ReturnHolder(false, new ErrorObject("error", "Data Empty."));
		}
		return holder;
	}
	
	
	/*
	 * -----------------UPDATE REQUEST INFO BY SHOP ID -------------
	 */
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ReturnHolder updateRequest(@RequestBody RequestDTO requestDTO) {
		ReturnHolder holder = new ReturnHolder();
		try {
			if (requestDTO != null) {
				RequestDTO requestDtoData = requestService.updateRequest(requestDTO);
				holder.setResult(requestDtoData);
			} else {
				holder = new ReturnHolder(false, new ErrorObject("error", "Data Empty."));
			}

		} catch (Exception e) {
			holder = new ReturnHolder(false, new ErrorObject("error", "Data Empty."));
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
