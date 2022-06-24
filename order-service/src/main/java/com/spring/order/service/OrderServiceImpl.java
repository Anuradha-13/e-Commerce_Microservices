package com.spring.order.service;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.spring.order.dto.OrderProductRequest;
import com.spring.order.dto.ProductQueryDetails;
import com.spring.order.dto.ProductResponse;
import com.spring.order.entity.Order;
import com.spring.order.entity.OrderProducts;
import com.spring.order.exception.OrderNotFoundException;
import com.spring.order.feignClient.ProductClient;
import com.spring.order.repo.OrderProductsRepo;
import com.spring.order.repo.OrderRepo;

@Service
public class OrderServiceImpl implements OrderService {
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

	@Autowired
	OrderRepo orderRepo;

	@Autowired
	ProductClient productClient;

	@Autowired
	OrderProductsRepo orderProductsRepo;

	@Autowired
	OrderService orderService;

	@Override
	public Order getOrderByOrderId(int OrderId) {
		LOGGER.debug("OrderServiceImpl::getOrderByOrderId");
		Optional<Order> order = orderRepo.findById(OrderId);
		if (order.isPresent()) {
			return order.get();
		} else {
			throw new OrderNotFoundException("Order does not exist");
		}

	}

	@Override
	public Order createOrder(List<OrderProductRequest> orderProductRequest, int userId) {

		Order order = new Order();
		order.setDateCreated(LocalDate.now());

		List<OrderProducts> orderProducts = new ArrayList<>();

		for (OrderProductRequest dto : orderProductRequest) {
			orderProducts.add(new OrderProducts(dto.getProductId(), order, dto.getQuantity()));

		}
		order.setOrderId(order.getOrderId());
		order.setOrderProducts((List<OrderProducts>) orderProducts);
		order.setUserId(userId);
		Order savedOrder = orderRepo.save(order);
		return savedOrder;

	}

	@Override
	public List<ProductQueryDetails> getProductDetailsJoin() {
		LOGGER.debug("OrderServiceImpl::getProductDetailsJoin");
		return this.orderRepo.getProductDetailsJoin();
	}

	@Override
	public List<Order> getAllOrders() {
		LOGGER.debug("OrderServiceImpl::getAllOrders");
		return orderRepo.findAll();
	}

	@Override
	public List<ProductResponse> getProdListByName(String productName) {
		LOGGER.debug("OrderServiceImpl::getProdListByName");
		List<ProductResponse> productResponse = productClient.getProductListByName(productName);
		return productResponse;

	}

	@Override
	public List<Order> listByLastMonthOrders() {
		LOGGER.debug("OrderServiceImpl::listByLastMonthOrders");
		LocalDate now = LocalDate.now();
		LocalDate startDate = now.minusMonths(1).with(TemporalAdjusters.firstDayOfMonth());
		LocalDate endDate = now.minusMonths(1).with(TemporalAdjusters.lastDayOfMonth());
		return orderRepo.findAllByDateCreatedBetween(startDate, endDate);
	}

	@Override
	public void deleteOrder(int OrderId) {
		LOGGER.debug("OrderServiceImpl::deleteOrder");
		Order order = orderRepo.findById(OrderId).orElseThrow(() -> new OrderNotFoundException("Invalid Order Id."));
		orderRepo.delete(order);
	}

	@Override
	public List<Order> getOrderByUserId(int userId) {
		LOGGER.debug("OrderServiceImpl::getOrderByOrderId");
		Optional<List<Order>> order = orderRepo.findByUserId(userId);//todo
		if (order.isPresent()) {
			return order.get().stream().collect(Collectors.toList());
		} else {
			throw new OrderNotFoundException("Order does not exist");
		}
	}

}
