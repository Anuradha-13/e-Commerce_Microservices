package com.spring.order.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.spring.order.dto.OrderProductRequest;
import com.spring.order.dto.ProductResponse;
import com.spring.order.entity.Order;
import com.spring.order.entity.OrderProducts;
import com.spring.order.exception.OrderNotFoundException;
import com.spring.order.repo.OrderProductsRepo;
import com.spring.order.repo.OrderRepo;
import com.spring.order.service.OrderServiceImpl;


@ExtendWith(SpringExtension.class)
public class OrderServicImplTest {

	@InjectMocks
	private OrderServiceImpl orderServiceImpl;

	@Mock
	private OrderRepo orderRepository;

	@Mock
	private OrderProductsRepo orderProductsRepository;

	private Order order;
	private OrderProducts orderProducts;
	
        
	
	//private List<OrderProductRequest> orderProductRequestList;
	
	@BeforeEach
	public void setup() {
		orderProducts = new OrderProducts();
		orderProducts.setId(101);
		orderProducts.setProductId(1);
		orderProducts.setQuantity(2);
		orderProducts.setOrder(order);
		
		order = new Order();
		order.setOrderId(1);
		order.setDateCreated(LocalDate.now());
		order.setUserId(5);
		order.setOrderProducts(List.of(orderProducts));
		
		//orderProductRequestList= new ArrayList<>();
		//orderProductRequestList.add(new OrderProductRequest(1, 5));
		}

	@Test
	public void testCreateOrder() {
		//Given
		List<OrderProductRequest> orderProductRequestList = new ArrayList<OrderProductRequest>();
	    List<OrderProductRequest> spyList = Mockito.spy(orderProductRequestList);

	    spyList.add(new OrderProductRequest(1, 5));
	  
		Mockito.when(orderRepository.save(order)).thenReturn(order);
		//When
		Order actual = orderServiceImpl.createOrder(spyList, 5);
		//Then
		assertNotNull(actual);
		assertEquals(5, actual.getUserId());
	}
	
	@Test
	public void testGetOrdersByOrderIdForSucess() {
		// Given
			Mockito.when(orderRepository.findById(1)).thenReturn(Optional.of(order));
			// When
			Order actual = orderServiceImpl.getOrderByOrderId(1);
			// Then
			assertNotNull(actual);
			assertEquals(1, actual.getOrderId());

	}
	@Test
	public void testGetOrdersByOrderIdForException() {
		// Given
		Mockito.when(orderRepository.findById(1)).thenReturn(Optional.of(order));
		Order actual = orderServiceImpl.getOrderByOrderId(2);
		// When
		Mockito.when(orderRepository.findById(5)).thenThrow(new OrderNotFoundException("Order not found"));
		// Then
		assertEquals(actual, new OrderNotFoundException("Order not found"));

	}

	
		

}
