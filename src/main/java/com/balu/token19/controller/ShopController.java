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
import com.balu.token19.dto.ReturnHolder;
import com.balu.token19.dto.ShopDetailsDTO;
import com.balu.token19.service.FileStorageService;
import com.balu.token19.service.ShopDetailsService;

@RestController
@RequestMapping(value = "/shop")
public class ShopController {
	
	@Autowired
	private ShopDetailsService shopDetailsService;
	
	@Autowired
	private FileStorageService fileStorageService;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ReturnHolder saveRole(@RequestBody ShopDetailsDTO shopdetailsDTO) {
		ReturnHolder holder = new ReturnHolder();
		try {
			if(shopdetailsDTO!=null) {
				if(shopdetailsDTO.getShopName().equalsIgnoreCase("")) {
					holder = new ReturnHolder(false, new ErrorObject("error", "Shop Name Should Not Be Empty."));
				}else if(shopdetailsDTO.getOwnerName().equalsIgnoreCase("")) {
					holder = new ReturnHolder(false, new ErrorObject("error", "Owner Name Should Not Be Empty."));
				}else {
					ShopDetailsDTO shopDto = shopDetailsService.getShopDetails(shopdetailsDTO.getUserId());
					if(shopDto!=null) {
						holder = new ReturnHolder(false, new ErrorObject("error", "Shop Alreay Registered."));
					}else {
						ShopDetailsDTO shopdetailsDtoData = shopDetailsService.saveShopDetails(shopdetailsDTO);
						holder.setResult(shopdetailsDtoData);
					}
					
				}
			}else {
				holder = new ReturnHolder(false, new ErrorObject("error", "Data Empty."));
			}
			
		}catch (Exception e) {
			holder = new ReturnHolder(false, new ErrorObject("error", "Data Empty."));
		}
		return holder;
	}
	
	@RequestMapping(value = "/list/{userId}", method = RequestMethod.GET)
	public ReturnHolder getShopdetails(@PathVariable("userId") Long userId) {
		ReturnHolder holder = new ReturnHolder();
		try {
			if(userId!=null) {
				ShopDetailsDTO shopDto = shopDetailsService.getShopDetails(userId);
				if(shopDto!=null) {
					holder.setResult(shopDto);
				}else {
					holder = new ReturnHolder(false, new ErrorObject("error", "Data Empty."));
				}
			}else {
				holder = new ReturnHolder(false, new ErrorObject("error", "Data Empty."));
			}
			
		}catch (Exception e) {
			holder = new ReturnHolder(false, new ErrorObject("error", "Data Empty."));
		}
		return holder;
	}
	
	
	@RequestMapping(value = "/shoplist/{pincode}", method = RequestMethod.GET)
	public ReturnHolder getShopdetailsByPincode(@PathVariable("pincode") String pincode) {
		ReturnHolder holder = new ReturnHolder();
		try {
			if(pincode!=null) {
				List<ShopDetailsDTO> shopDtoList = shopDetailsService.getShopsbyPincode(pincode);
				if(shopDtoList.size()!=0) {
					holder.setResult(shopDtoList);
				}else {
					holder = new ReturnHolder(false, new ErrorObject("error", "Data Empty."));
				}
			}else {
				holder = new ReturnHolder(false, new ErrorObject("error", "Data Empty."));
			}
			
		}catch (Exception e) {
			holder = new ReturnHolder(false, new ErrorObject("error", "Data Empty."));
		}
		return holder;
	}
	
	
	
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public ReturnHolder uploadFile(@RequestParam("file") MultipartFile file) {

		ReturnHolder holder = new ReturnHolder();
		try {
			if(file!=null) {
				String fileName = fileStorageService.storeFile(file);
				String fileDownloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
						.path("/shop/downloadFile/").path(fileName).toUriString();
				holder.setResult(fileDownloadUrl);
			}else {
				holder = new ReturnHolder(false, new ErrorObject("error", "File Path Empty."));
			}
			
		}catch (Exception e) {
			holder = new ReturnHolder(false, new ErrorObject("error", "File Path Empty."));
		}
		return holder;
	}
	

	@RequestMapping(value = "/downloadFile/{fileName:.+}", method = RequestMethod.GET)
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
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
