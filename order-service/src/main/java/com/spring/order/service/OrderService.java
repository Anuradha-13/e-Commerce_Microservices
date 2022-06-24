package com.spring.order.service;

import java.util.List;
import java.util.Optional;

import org.springframework.validation.annotation.Validated;

import com.spring.order.dto.OrderProductRequest;
import com.spring.order.dto.OrderProductResponse;
import com.spring.order.dto.ProductQueryDetails;
import com.spring.order.dto.ProductRequest;
import com.spring.order.dto.ProductResponse;
import com.spring.order.entity.Order;
import com.spring.order.entity.OrderProducts;


public interface OrderService {
	
	public List<ProductQueryDetails> getProductDetailsJoin();
	
	Order getOrderByOrderId(int OrderId);
	
		Order createOrder(List<OrderProductRequest> orderProductRequest,int userId);

	public List<Order> getAllOrders();

	//public ProductResponse getProdById(int productId);
	
	public List<ProductResponse> getProdListByName(String productName);
	
	public List<Order> listByLastMonthOrders() ;
	
	void deleteOrder(int OrderId);

	public List<Order> getOrderByUserId(int userId);

	
	
	
	
	

	
	
	

		
	

}
