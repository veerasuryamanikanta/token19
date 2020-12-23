package com.balu.token19.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balu.token19.domain.OrderItems;
import com.balu.token19.domain.Orders;
import com.balu.token19.domain.ProductImages;
import com.balu.token19.dto.OrderItemDTO;
import com.balu.token19.dto.OrdersDTO;
import com.balu.token19.dto.ProductImagesDTO;
import com.balu.token19.repo.OrderItemsRepository;
import com.balu.token19.repo.OrderRepository;
import com.balu.token19.repo.ProductImagesRepository;
import com.balu.token19.repo.ProductQuantityRepository;
import com.balu.token19.repo.ProductRepository;
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
	private UserRepository userRepository;

	@Autowired
	private ProductQuantityRepository productQuantityRepository;

	@Autowired
	private ProductImagesRepository productImagesRepository;

	/*
	 * -----------------SAVE ORDER ITEM -------------
	 */

	@Override
	public String saveOrderItem(OrdersDTO ordersDTO) {
		try {
			List<OrderItemDTO> orderItemDTOList = ordersDTO.getOrderItemDTOList();
			List<OrderItems> orderItemsList = new ArrayList<OrderItems>();
			if (orderItemDTOList.size() != 0) {
				Orders order = new Orders();
				long rndNumber = Helper.createRandomInteger(55, 579026);
				order.setOrderId("ORDER_TOKEN_" + rndNumber);
				order.setUser(userRepository.getOne(ordersDTO.getUserId()));
				Orders orderData = orderRepository.save(order);
				String orderid = orderData.getOrderId();
				for (OrderItemDTO orderItemDTO : orderItemDTOList) {
					OrderItems orderItems = new OrderItems();
					mapper.map(orderItemDTO, orderItems);
					orderItems.setOrders(orderRepository.findByOrderId(orderid));
					orderItems.setProduct(productRepository.getOne(orderItemDTO.getProductId()));
					orderItems.setProductquantities(
							productQuantityRepository.getOne(orderItemDTO.getProductQuantityId()));
					orderItems.setIsactive(true);
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

	/*
	 * -----------------GET ORDER ITEM -------------
	 */
	@Override
	public OrdersDTO getOrderItems(String orderId) {
		try {
			Orders orderData = orderRepository.findByOrderId(orderId);
			List<OrderItems> orderItemsList = orderItemsRepository.findByOrderId(orderData.getId());
			List<OrderItemDTO> orderItemDtoList = new ArrayList<>();
			for (OrderItems orderItems : orderItemsList) {
				OrderItemDTO orderitemsdto = new OrderItemDTO();
				orderitemsdto.setOrderItemId(orderItems.getOrderItemId());
				orderitemsdto.setId(orderItems.getOrders().getId());
				orderitemsdto.setUserId(orderData.getUser().getUserId());
				orderitemsdto.setProductId(orderItems.getProduct().getProductId());
				orderitemsdto.setProductQuantityId(orderItems.getProductquantities().getProductquantityId());
				orderitemsdto.setProductName(orderItems.getProduct().getProductName());
				orderitemsdto.setProductDescription(orderItems.getProduct().getShortDescription());
				orderitemsdto.setProductImagePath(orderItems.getProduct().getProductImagePath());
				List<ProductImagesDTO> productImagesDTOList = new ArrayList<>();
				List<ProductImages> productImagesList = productImagesRepository
						.findByProductQuantityId(orderItems.getProductquantities().getProductquantityId());

				for (ProductImages productImages : productImagesList) {
					ProductImagesDTO ProductImagesDto = new ProductImagesDTO();
					mapper.map(productImages, ProductImagesDto);
					productImagesDTOList.add(ProductImagesDto);
				}

				orderitemsdto.setProductImages(productImagesDTOList);
				orderitemsdto.setProductMrp(orderItems.getProductMrp());
				orderitemsdto.setSellingPrice(orderItems.getSellingPrice());
				// orderitemsdto.setProductMrp(orderItems.getProductquantities().getMrpprice());
				orderitemsdto.setSpecialOffer(orderItems.getProductquantities().getSellingprice());
				orderitemsdto.setQuantityName(orderItems.getProductquantities().getQuantity().getQuantityName());
				orderitemsdto.setQuantity(orderItems.getProductquantities().getDescription());
				orderitemsdto.setItemQuantity(orderItems.getItemQuantity());
				orderitemsdto.setUpdateQuantity(orderItems.getUpdateQuantity());
				orderitemsdto.setIsactive(orderItems.isIsactive());
				orderitemsdto.setCreatedDate("" + orderItems.getCreatedDate());
				orderitemsdto.setUpdatedOn("" + orderItems.getUpdatedOn());
				orderItemDtoList.add(orderitemsdto);
			}
			OrdersDTO orderdto = new OrdersDTO();
			orderdto.setId(orderData.getId());
			orderdto.setUserId(orderData.getUser().getUserId());
			orderdto.setOrderId(orderData.getOrderId());
			orderdto.setOrderItemDTOList(orderItemDtoList);
			return orderdto;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public String updateOrderItem(OrderItemDTO orderItemDTO) {
		try {
			orderItemsRepository.updateOrder(orderItemDTO.getOrderItemId(), orderItemDTO.getIsactive());
			return "success";
		} catch (Exception e) {
			return "failed";
		}

	}

	@Override
	public String updateOrderItemCount(OrderItemDTO orderItemDTO) {
		try {
			orderItemsRepository.updateOrderCount(orderItemDTO.getOrderItemId(), orderItemDTO.getUpdateQuantity());
			return "success";
		} catch (Exception e) {
			return "failed";
		}

	}

}
