package com.balu.token19.service;

import java.util.List;

import com.balu.token19.dto.CartDTO;

public interface CartService {

	CartDTO saveCart(CartDTO cartDTO);

	CartDTO findByProduct(Long userId, Long productId);

	List<CartDTO> findByUserId(Long userId);

	String deletet(Long userId, Long productId);

	String deletetByUserid(Long userId);

}
