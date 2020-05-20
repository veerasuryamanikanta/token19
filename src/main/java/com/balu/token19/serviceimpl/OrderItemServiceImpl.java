package com.balu.token19.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balu.token19.domain.Order;
import com.balu.token19.domain.OrderItems;
import com.balu.token19.dto.OrderItemDTO;
import com.balu.token19.dto.OrdersDTO;
import com.balu.token19.repo.OrderItemsRepository;
import com.balu.token19.repo.OrderRepository;
import com.balu.token19.repo.ProductRepository;
import com.balu.token19.repo.QuantityRepository;
import com.balu.token19.repo.UserRepository;
import com.balu.token19.service.OrderItemService;
import com.balu.token19.utils.Helper;

@Service
public class OrderItemServiceImpl implements OrderItemService {

	private static final Mapper mapper = new DozerBeanMapper();

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderItemsRepository orderItemsRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private QuantityRepository quantityRepository;

	@Autowired
	private UserRepository userRepository;

	/*
	 * -----------------SAVE ORDER ITEM -------------
	 */

	@Override
	public String saveOrderItem(OrdersDTO ordersDTO) {
		try {
			List<OrderItemDTO> orderItemDTOList = ordersDTO.getOrderItemDTOList();
			List<OrderItems> orderItemsList = new ArrayList<OrderItems>();
			if (orderItemDTOList.size() != 0) {
				Order order = new Order();
				long rndNumber = Helper.createRandomInteger(55, 579026);
				order.setOrderId("ORDER_TOKEN_" + rndNumber);
				order.setUser(userRepository.getOne(ordersDTO.getUserId()));
				Order orderData = orderRepository.save(order);
				String orderid = orderData.getOrderId();
				for (OrderItemDTO orderItemDTO : orderItemDTOList) {
					OrderItems orderItems = new OrderItems();
					mapper.map(orderItemDTO, orderItems);
					orderItems.setOrder(orderRepository.findByOrderId(orderid));
					orderItems.setProduct(productRepository.getOne(orderItemDTO.getProductId()));
					orderItems.setQuantity(quantityRepository.getOne(orderItemDTO.getQuantityId()));
					orderItemsList.add(orderItems);
				}
				orderItemsRepository.saveAll(orderItemsList);
				return orderid;
			} else {
				return "failed";
			}
		} catch (Exception e) {
			return "failed";
		}
	}

}
