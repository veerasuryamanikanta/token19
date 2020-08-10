package com.balu.token19.service;

import com.balu.token19.dto.OrderItemDTO;
import com.balu.token19.dto.OrdersDTO;

public interface OrderItemService {

	String saveOrderItem(OrdersDTO ordersDTO);
	
	String updateOrderItem(OrderItemDTO orderItemDTO);
	
	String updateOrderItemCount(OrderItemDTO orderItemDTO);

	OrdersDTO getOrderItems(String orderId);

}
