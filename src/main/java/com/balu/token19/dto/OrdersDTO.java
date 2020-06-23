package com.balu.token19.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class OrdersDTO {

	private Long id;

	private Long userId;

	private String orderId;

	private List<OrderItemDTO> orderItemDTOList;

}
