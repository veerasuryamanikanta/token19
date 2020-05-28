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
import com.balu.token19.dto.ShopCategoryDTO;
import com.balu.token19.dto.ShopDetailsDTO;
import com.balu.token19.dto.ShopSubCategoryDTO;
import com.balu.token19.service.FileStorageService;
import com.balu.token19.service.ShopDetailsService;

@RestController
@RequestMapping(value = "/shop")
public class ShopController {

	@Autowired
	private ShopDetailsService shopDetailsService;

	@Autowired
	private FileStorageService fileStorageService;

	/*
	 * -----------------SAVE SHOP DETAILS -------------
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ReturnHolder saveShopdetails(@RequestBody ShopDetailsDTO shopdetailsDTO) {
		ReturnHolder holder = new ReturnHolder();
		try {
			if (shopdetailsDTO != null) {
				if (shopdetailsDTO.getShopName().equalsIgnoreCase("")) {
					holder = new ReturnHolder(false, new ErrorObject("error", "Shop Name Should Not Be Empty."));
				} else if (shopdetailsDTO.getOwnerName().equalsIgnoreCase("")) {
					holder = new ReturnHolder(false, new ErrorObject("error", "Owner Name Should Not Be Empty."));
				} else {
					ShopDetailsDTO shopDto = shopDetailsService.getShopDetails(shopdetailsDTO.getUserId());
					if (shopDto != null) {
						holder = new ReturnHolder(false, new ErrorObject("error", "Shop Alreay Registered."));
					} else {
						ShopDetailsDTO shopdetailsDtoData = shopDetailsService.saveShopDetails(shopdetailsDTO);
						holder.setResult(shopdetailsDtoData);
					}

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
	 * -----------------UPDATE SHOP DETAILS -------------
	 */
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ShopDetailsDTO updateShopdetails(@RequestBody ShopDetailsDTO shopDetailsDTO) {
		ReturnHolder holder = new ReturnHolder();
		try {
			if (shopDetailsDTO != null) {
				ShopDetailsDTO shopdetailsDtoData = shopDetailsService.updateShopDetails(shopDetailsDTO);
				holder.setResult(shopdetailsDtoData);
			} else {
				holder = new ReturnHolder(false, new ErrorObject("error", "Data Empty."));
			}

		} catch (Exception e) {
			holder = new ReturnHolder(false, new ErrorObject("error", "Data Empty." + e));
		}
		return shopDetailsDTO;
	}

	/*
	 * -----------------SHOP LIST BY USERID -------------
	 */
	@RequestMapping(value = "/list/{userId}", method = RequestMethod.GET)
	public ReturnHolder getShopdetails(@PathVariable("userId") Long userId) {
		ReturnHolder holder = new ReturnHolder();
		try {
			if (userId != null) {
				ShopDetailsDTO shopDto = shopDetailsService.getShopDetails(userId);
				if (shopDto != null) {
					holder.setResult(shopDto);
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
	 * -----------------SHOP LIST BY PINCODE -------------
	 */
	@RequestMapping(value = "/shoplist/{pincode}", method = RequestMethod.GET)
	public ReturnHolder getShopdetailsByPincode(@PathVariable("pincode") String pincode) {
		ReturnHolder holder = new ReturnHolder();
		try {
			if (pincode != null) {
				List<ShopDetailsDTO> shopDtoList = shopDetailsService.getShopsbyPincode(pincode);
				if (shopDtoList.size() != 0) {
					holder.setResult(shopDtoList);
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
	 * -----------------SAVE CATEGORY -------------
	 */
	@RequestMapping(value = "/category/save", method = RequestMethod.POST)
	public ReturnHolder saveCategory(@RequestBody ShopCategoryDTO shopcategoryDTO) {
		ReturnHolder holder = new ReturnHolder();
		try {
			if (shopcategoryDTO != null) {
				ShopCategoryDTO shopcategoryDTOData = shopDetailsService.saveShopCategory(shopcategoryDTO);
				holder.setResult(shopcategoryDTOData);
			} else {
				holder = new ReturnHolder(false, new ErrorObject("error", "Data Empty"));
			}

		} catch (Exception e) {
			holder = new ReturnHolder(false, new ErrorObject("error", "Unable to Save." + e));
		}
		return holder;
	}

	/*
	 * -----------------CATEGORY LIST -------------
	 */
	@RequestMapping(value = "/category/list", method = RequestMethod.GET)
	public ReturnHolder getCategories() {
		ReturnHolder holder = new ReturnHolder();
		try {
			List<ShopCategoryDTO> shopcategoryDTOList = shopDetailsService.findAllShopCategories();
			holder.setResult(shopcategoryDTOList);
		} catch (Exception e) {
			holder = new ReturnHolder(false, new ErrorObject("error", "Unable to Load." + e));
		}
		return holder;
	}

	/*
	 * -----------------SAVE SUBCATEGORY -------------
	 */
	@RequestMapping(value = "/subcategory/save", method = RequestMethod.POST)
	public ReturnHolder saveSubCategory(@RequestBody ShopSubCategoryDTO shopsubCategoryDTO) {
		ReturnHolder holder = new ReturnHolder();
		try {
			if (shopsubCategoryDTO != null) {
				ShopSubCategoryDTO shopsubCategoryDTOData = shopDetailsService.saveShopSubCategory(shopsubCategoryDTO);
				holder.setResult(shopsubCategoryDTOData);
			} else {
				holder = new ReturnHolder(false, new ErrorObject("error", "Data Empty"));
			}

		} catch (Exception e) {
			holder = new ReturnHolder(false, new ErrorObject("error", "Unable to Save." + e));
		}
		return holder;
	}

	/*
	 * -----------------SUBCATEGORY LIST BY ID-------------
	 */
	@RequestMapping(value = "/subcategory/list/{shopcategoryId}", method = RequestMethod.GET)
	public ReturnHolder getSubCategoriesbyId(@PathVariable("shopcategoryId") Long shopcategoryId) {
		ReturnHolder holder = new ReturnHolder();
		try {
			List<ShopSubCategoryDTO> shopsubCategoryDTOList = shopDetailsService
					.findShopSubCategoriesByShopCategryId(shopcategoryId);
			holder.setResult(shopsubCategoryDTOList);
		} catch (Exception e) {
			holder = new ReturnHolder(false, new ErrorObject("error", "Unable to Load." + e));
		}
		return holder;
	}

	/*
	 * -----------------UPLOAD SHOP IMAGE -------------
	 */
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public ReturnHolder uploadFile(@RequestParam("file") MultipartFile file) {

		ReturnHolder holder = new ReturnHolder();
		try {
			if (file != null) {
				String fileName = fileStorageService.storeFile(file);
				String fileDownloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
						.path("/shop/downloadFile/").path(fileName).toUriString();
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
	 * -----------------DOWNLOAD SHOP IMAGE -------------
	 */
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
