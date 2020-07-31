package com.balu.token19.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.balu.token19.dto.ErrorObject;
import com.balu.token19.dto.OrderItemDTO;
import com.balu.token19.dto.OrdersDTO;
import com.balu.token19.dto.ReturnHolder;
import com.balu.token19.service.OrderItemService;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

	@Autowired
	private OrderItemService orderItemService;

	/*
	 * -----------------SAVE CART ITEMS -------------
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ReturnHolder saveCategory(@RequestBody OrdersDTO ordersDTO) {
		ReturnHolder holder = new ReturnHolder();
		try {
			if (ordersDTO != null) {
				String response = orderItemService.saveOrderItem(ordersDTO);
				if (response.equalsIgnoreCase("failed")) {
					holder = new ReturnHolder(false, new ErrorObject("error", "Unable to Save." + response));
				} else {
					holder.setResult(response);
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
	 * -----------------UPDATE ITEMS -------------
	 */
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ReturnHolder updateOrder(@RequestBody OrderItemDTO ordersDTO) {
		ReturnHolder holder = new ReturnHolder();
		try {
			if (ordersDTO != null) {
				String response = orderItemService.updateOrderItem(ordersDTO);
				if (response.equalsIgnoreCase("failed")) {
					holder = new ReturnHolder(false, new ErrorObject("error", "Unable to Update." + response));
				} else {
					holder.setResult(response);
				}
			} else {
				holder = new ReturnHolder(false, new ErrorObject("error", "Data Empty"));
			}
		} catch (Exception e) {
			holder = new ReturnHolder(false, new ErrorObject("error", "Unable to Update." + e));
		}
		return holder;
	}
	
	

	/*
	 * -----------------SAVE CART ITEMS -------------
	 */
	@RequestMapping(value = "/list/{orderId}", method = RequestMethod.GET)
	public ReturnHolder saveCategory(@PathVariable("orderId") String orderId) {
		ReturnHolder holder = new ReturnHolder();
		try {
			if (orderId != null) {
				OrdersDTO ordersDTO = orderItemService.getOrderItems(orderId);
				if (ordersDTO != null) {
					holder.setResult(ordersDTO);
				} else {
					holder = new ReturnHolder(false, new ErrorObject("error", "Data Emptys"));
				}

			} else {
				holder = new ReturnHolder(false, new ErrorObject("error", "Data Empty"));
			}
		} catch (Exception e) {
			holder = new ReturnHolder(false, new ErrorObject("error", "Unable to Load." + e));
		}
		return holder;
	}

}
