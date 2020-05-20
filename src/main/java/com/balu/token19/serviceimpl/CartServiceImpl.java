package com.balu.token19.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balu.token19.domain.Cart;
import com.balu.token19.dto.CartDTO;
import com.balu.token19.repo.CartRepository;
import com.balu.token19.repo.ProductRepository;
import com.balu.token19.repo.QuantityRepository;
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
	private ProductRepository productRepository;

	@Autowired
	private QuantityRepository quantityRepository;

	/*
	 * -----------------SAVE CART ITEM -------------
	 */
	@Override
	public CartDTO saveCart(CartDTO cartDTO) {
		Cart cart = new Cart();
		mapper.map(cartDTO, cart);
		cart.setUser(userRepository.getOne(cartDTO.getUserId()));
		cart.setProduct(productRepository.getOne(cartDTO.getProductId()));
		cart.setQuantity(quantityRepository.getOne(cartDTO.getQuantityId()));
		Cart cartData = cartRepository.save(cart);
		CartDTO saved_cartData = new CartDTO();
		mapper.map(cartData, saved_cartData);
		saved_cartData.setUserId(cartData.getUser().getUserId());
		saved_cartData.setProductId(cartData.getProduct().getProductId());
		saved_cartData.setQuantityId(cartData.getQuantity().getQuantityId());
		return saved_cartData;
	}

	/*
	 * -----------------GET CART ITEM -------------
	 */
	@Override
	public CartDTO findByProduct(Long userId, Long productId) {
		Cart cart = cartRepository.findByUserAndProduct(userId, productId);
		if (cart != null) {
			CartDTO saved_cartData = new CartDTO();
			mapper.map(cart, saved_cartData);
			saved_cartData.setUserId(cart.getUser().getUserId());
			saved_cartData.setProductId(cart.getProduct().getProductId());
			saved_cartData.setQuantityId(cart.getQuantity().getQuantityId());
			return saved_cartData;
		} else {
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
				saved_cartData.setProductId(cart.getProduct().getProductId());
				saved_cartData.setQuantityId(cart.getQuantity().getQuantityId());
				saved_cartData.setProductName(cart.getProduct().getProductName());
				saved_cartData.setProductDescription(cart.getProduct().getProductDescription());
				saved_cartData.setProductImagePath(cart.getProduct().getProductImagePath());
				saved_cartData.setProductMrp(cart.getProduct().getProductMrp());
				saved_cartData.setProductDiscount(cart.getProduct().getProductDiscount());
				saved_cartData.setSpecialOffer(cart.getProduct().getSpecialOffer());
				saved_cartData.setQuantityName(cart.getProduct().getQuantity().getQuantityName());
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
			return "failed to delete" + e;
		}

	}

}
