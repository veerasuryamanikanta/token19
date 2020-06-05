package com.balu.token19.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.balu.token19.dto.CategoryDTO;
import com.balu.token19.dto.ErrorObject;
import com.balu.token19.dto.ProductCategoryDTO;
import com.balu.token19.dto.ProductDTO;
import com.balu.token19.dto.QuantityDTO;
import com.balu.token19.dto.ReturnHolder;
import com.balu.token19.dto.SubCategoryDTO;
import com.balu.token19.service.CategoryService;
import com.balu.token19.service.ProductService;
import com.balu.token19.service.QuantityService;
import com.balu.token19.service.SubCategoryService;

@RestController
@RequestMapping(value = "/store")
public class StoreController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private SubCategoryService subCategoryService;

	@Autowired
	private ProductService productService;

	@Autowired
	private QuantityService quantityService;

	/*
	 * -----------------SAVE CATEGORY -------------
	 */
	@RequestMapping(value = "/category/save", method = RequestMethod.POST)
	public ReturnHolder saveCategory(@RequestBody CategoryDTO categoryDTO) {
		ReturnHolder holder = new ReturnHolder();
		try {
			if (categoryDTO != null) {
				CategoryDTO categoryDTOData = categoryService.saveCategory(categoryDTO);
				holder.setResult(categoryDTOData);
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
			List<CategoryDTO> categoryDTOList = categoryService.findAllCategories();
			holder.setResult(categoryDTOList);
		} catch (Exception e) {
			holder = new ReturnHolder(false, new ErrorObject("error", "Unable to Load." + e));
		}
		return holder;
	}

	/*
	 * -----------------SAVE CATEGORY -------------
	 */
	@RequestMapping(value = "/productcategory/save", method = RequestMethod.POST)
	public ReturnHolder saveProductCategory(@RequestBody ProductCategoryDTO productcategoryDTO) {
		ReturnHolder holder = new ReturnHolder();
		try {
			if (productcategoryDTO != null) {
				ProductCategoryDTO productcategoryDTOData = categoryService.saveProductCategory(productcategoryDTO);
				holder.setResult(productcategoryDTOData);
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
	@RequestMapping(value = "/productcategory/list", method = RequestMethod.GET)
	public ReturnHolder getProductCategories() {
		ReturnHolder holder = new ReturnHolder();
		try {
			List<ProductCategoryDTO> productcategoryDTOList = categoryService.findAllProductCategories();
			holder.setResult(productcategoryDTOList);
		} catch (Exception e) {
			holder = new ReturnHolder(false, new ErrorObject("error", "Unable to Load." + e));
		}
		return holder;
	}

	/*
	 * -----------------SAVE SUBCATEGORY -------------
	 */
	@RequestMapping(value = "/subcategory/save", method = RequestMethod.POST)
	public ReturnHolder saveSubCategory(@RequestBody SubCategoryDTO subCategoryDTO) {
		ReturnHolder holder = new ReturnHolder();
		try {
			if (subCategoryDTO != null) {
				SubCategoryDTO subCategoryDTOData = subCategoryService.saveSubCategory(subCategoryDTO);
				holder.setResult(subCategoryDTOData);
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
	@RequestMapping(value = "/subcategory/list/{categoryId}", method = RequestMethod.GET)
	public ReturnHolder getSubCategoriesbyId(@PathVariable("categoryId") Long categoryId) {
		ReturnHolder holder = new ReturnHolder();
		try {
			List<SubCategoryDTO> subCategoryDTOList = subCategoryService.findSubCategoriesByCategryId(categoryId);
			holder.setResult(subCategoryDTOList);
		} catch (Exception e) {
			holder = new ReturnHolder(false, new ErrorObject("error", "Unable to Load." + e));
		}
		return holder;
	}

	/*
	 * -----------------SAVE PRODUCT -------------
	 */
	@RequestMapping(value = "/product/group/save", method = RequestMethod.POST)
	public ReturnHolder saveProductGroup(@RequestBody ProductDTO productDTO) {
		ReturnHolder holder = new ReturnHolder();
		try {
			if (productDTO != null) {
				ProductDTO productDTOData = productService.saveProduct(productDTO);
				holder.setResult(productDTOData);
			} else {
				holder = new ReturnHolder(false, new ErrorObject("error", "Data Empty"));
			}

		} catch (Exception e) {
			holder = new ReturnHolder(false, new ErrorObject("error", "Unable to Save." + e));
		}
		return holder;
	}
	
	
	/*
	 * -----------------SAVE PRODUCT -------------
	 */
	@RequestMapping(value = "/product/save", method = RequestMethod.POST)
	public ReturnHolder saveProducts(@RequestBody ProductDTO productDTO) {
		ReturnHolder holder = new ReturnHolder();
		try {
			if (productDTO != null) {
				String productDTOData = productService.saveProductItems(productDTO);
				holder.setResult(productDTOData);
			} else {
				holder = new ReturnHolder(false, new ErrorObject("error", "Data Empty"));
			}

		} catch (Exception e) {
			holder = new ReturnHolder(false, new ErrorObject("error", "Unable to Save." + e));
		}
		return holder;
	}

	/*
	 * -----------------PRODUCT LIST BY ID-------------
	 */
	@RequestMapping(value = "/product/list/{subCategoryId}", method = RequestMethod.GET)
	public ReturnHolder getProductsbyId(@PathVariable("subCategoryId") Long subCategoryId) {
		ReturnHolder holder = new ReturnHolder();
		try {
			List<ProductDTO> productDTOList = productService.findProductsBySubCategryId(subCategoryId);
			holder.setResult(productDTOList);
		} catch (Exception e) {
			holder = new ReturnHolder(false, new ErrorObject("error", "Unable to Load." + e));
		}
		return holder;
	}

	/*
	 * -----------------SAVE QTY -------------
	 */
	@RequestMapping(value = "/quantity/save", method = RequestMethod.POST)
	public ReturnHolder saveQty(@RequestBody QuantityDTO quantityDTO) {
		ReturnHolder holder = new ReturnHolder();
		try {
			if (quantityDTO != null) {
				QuantityDTO quantityDTOData = quantityService.saveQuantity(quantityDTO);
				holder.setResult(quantityDTOData);
			} else {
				holder = new ReturnHolder(false, new ErrorObject("error", "Data Empty"));
			}

		} catch (Exception e) {
			holder = new ReturnHolder(false, new ErrorObject("error", "Unable to Save." + e));
		}
		return holder;
	}

	/*
	 * -----------------ALL QUANTITY LIST -------------
	 */
	@RequestMapping(value = "/quantity/list", method = RequestMethod.GET)
	public ReturnHolder getQty() {
		ReturnHolder holder = new ReturnHolder();
		try {
			List<QuantityDTO> qtyDTOList = quantityService.findAllQuantities();
			holder.setResult(qtyDTOList);
		} catch (Exception e) {
			holder = new ReturnHolder(false, new ErrorObject("error", "Unable to Load." + e));
		}
		return holder;
	}

}
