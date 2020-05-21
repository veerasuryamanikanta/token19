package com.balu.token19.service;

import com.balu.token19.dto.OrdersDTO;

public interface OrderItemService {

	String saveOrderItem(OrdersDTO ordersDTO);

	OrdersDTO getOrderItems(String orderId);

}
