package com.balu.token19.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.balu.token19.dto.CartDTO;
import com.balu.token19.dto.ErrorObject;
import com.balu.token19.dto.ReturnHolder;
import com.balu.token19.service.CartService;

@RestController
@RequestMapping(value = "/cart")
public class CartController {

	@Autowired
	private CartService cartService;

	/*
	 * -----------------SAVE CART ITEMS -------------
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ReturnHolder saveCategory(@RequestBody CartDTO cartDTO) {
		ReturnHolder holder = new ReturnHolder();
		try {
			if (cartDTO != null) {
				CartDTO cartdto = cartService.findByProduct(cartDTO.getUserId(), cartDTO.getProductId());
				if (cartdto != null) {
					cartDTO.setCartId(cartdto.getCartId());
					CartDTO cartdtoData = cartService.saveCart(cartDTO);
					holder.setResult(cartdtoData);
				} else {
					CartDTO cartdtoData = cartService.saveCart(cartDTO);
					holder.setResult(cartdtoData);
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
	 * -----------------GET CART ITEMS -------------
	 */
	@RequestMapping(value = "/list/{userId}", method = RequestMethod.POST)
	public ReturnHolder getCartByUser(@PathParam("userId") Long userId) {
		ReturnHolder holder = new ReturnHolder();
		try {
			if (userId != null) {
				List<CartDTO> cartdtoList = cartService.findByUserId(userId);
				holder.setResult(cartdtoList);
			} else {
				holder = new ReturnHolder(false, new ErrorObject("error", "Data Empty"));
			}
		} catch (Exception e) {
			holder = new ReturnHolder(false, new ErrorObject("error", "Unable to Load." + e));
		}
		return holder;
	}

}
