package com.balu.token19.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balu.token19.domain.Cart;
import com.balu.token19.domain.ProductImages;
import com.balu.token19.dto.CartDTO;
import com.balu.token19.dto.ProductImagesDTO;
import com.balu.token19.repo.CartRepository;
import com.balu.token19.repo.ProductImagesRepository;
import com.balu.token19.repo.ProductQuantityRepository;
import com.balu.token19.repo.UserRepository;
import com.balu.token19.service.CartService;

@Service
public class CartServiceImpl implements CartService {

	private static final Mapper mapper = new DozerBeanMapper();

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProductQuantityRepository productQuantityRepository;

	@Autowired
	private ProductImagesRepository productImagesRepository;

	/*
	 * -----------------SAVE CART ITEM -------------
	 */
	@Override
	public CartDTO saveCart(CartDTO cartDTO) {
		try {
			Cart cart = new Cart();
			mapper.map(cartDTO, cart);
			cart.setUser(userRepository.getOne(cartDTO.getUserId()));
			cart.setProductquantities(productQuantityRepository.getOne(cartDTO.getProductQuantityId()));
			Cart cartData = cartRepository.save(cart);
			CartDTO saved_cartData = new CartDTO();
			mapper.map(cartData, saved_cartData);
			saved_cartData.setUserId(cartData.getUser().getUserId());
			saved_cartData.setProductId(cartData.getProductquantities().getProduct().getProductId());
			saved_cartData.setProductQuantityId(cartData.getProductquantities().getProductquantityId());
			return saved_cartData;
		} catch (Exception e) {
			return null;
		}
	}

	/*
	 * -----------------GET CART ITEM -------------
	 */
	@Override
	public CartDTO findByProduct(Long userId, Long productQtyId) {
		try {
			Cart cart = cartRepository.findByUserAndProduct(userId, productQtyId);
			if (cart != null) {
				CartDTO saved_cartData = new CartDTO();
				mapper.map(cart, saved_cartData);
				saved_cartData.setUserId(cart.getUser().getUserId());
				saved_cartData.setProductId(cart.getProductquantities().getProduct().getProductId());
				saved_cartData.setProductQuantityId(cart.getProductquantities().getProductquantityId());
				return saved_cartData;
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}

	}

	/*
	 * -----------------GET CART ITEM -------------
	 */

	@Override
	public List<CartDTO> findByUserId(Long userId) {
		List<Cart> cartList = cartRepository.findByUser(userId);
		List<CartDTO> cartdtoList = new ArrayList<CartDTO>();
		if (cartList.size() != 0) {
			for (Cart cart : cartList) {
				CartDTO saved_cartData = new CartDTO();
				mapper.map(cart, saved_cartData);
				saved_cartData.setUserId(cart.getUser().getUserId());
				saved_cartData.setProductId(cart.getProductquantities().getProduct().getProductId());
				saved_cartData.setProductQuantityId(cart.getProductquantities().getProductquantityId());
				saved_cartData.setProductName(cart.getProductquantities().getProduct().getProductName());
				saved_cartData.setProductDescription(cart.getProductquantities().getProduct().getShortDescription());
				saved_cartData.setProductImagePath(cart.getProductquantities().getProduct().getProductImagePath());
				List<ProductImagesDTO> productImagesDTOList = new ArrayList<>();
				List<ProductImages> productImagesList = productImagesRepository
						.findByProductQuantityId(cart.getProductquantities().getProductquantityId());

				for (ProductImages productImages : productImagesList) {
					ProductImagesDTO ProductImagesDto = new ProductImagesDTO();
					mapper.map(productImages, ProductImagesDto);
					productImagesDTOList.add(ProductImagesDto);
				}
				saved_cartData.setProductImages(productImagesDTOList);
				saved_cartData.setProductDiscount(cart.getProductquantities().getDiscount());
				saved_cartData.setSpecialOffer(cart.getProductquantities().getSellingprice());
				saved_cartData.setQuantityName(cart.getProductquantities().getQuantity().getQuantityName());
				saved_cartData.setQuantity(cart.getProductquantities().getDescription());
				cartdtoList.add(saved_cartData);
			}
		}
		return cartdtoList;
	}

	/*
	 * -----------------DELETE CART ITEM -------------
	 */
	@Override
	public String deletet(Long userId, Long productId) {
		try {
			cartRepository.deletItem(userId, productId);
			return "success";
		} catch (Exception e) {
			return "failed";
		}

	}

	@Override
	public String deletetByUserid(Long userId) {
		try {
			cartRepository.deletAllByUserId(userId);
			return "success";
		} catch (Exception e) {
			return "failed";
		}
	}

}
