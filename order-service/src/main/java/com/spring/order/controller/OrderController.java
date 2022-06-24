package com.spring.order.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.order.dto.OrderProductRequest;
import com.spring.order.dto.ProductResponse;
import com.spring.order.entity.Order;
import com.spring.order.exception.ErrorResponse;
import com.spring.order.service.OrderService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/orders")
public class OrderController {
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	OrderService orderService;
		
	
	@GetMapping
	public ResponseEntity<List<Order>> getAllOrders() {
		LOGGER.debug("OrderController::getAllOrders");
		return new ResponseEntity<List<Order>>(orderService.getAllOrders(),HttpStatus.OK);
	}

	
	  @GetMapping("/{orderId}") 
	  public ResponseEntity<Order> getOrderAndProductByOrderId(@PathVariable(name = "orderId") int orderId) {
	  LOGGER.debug("OrderController::getOrderAndProductByOrderId"); return new
	  ResponseEntity<Order>(orderService.getOrderByOrderId(orderId),HttpStatus.OK);
	  }
	 
	  @GetMapping("users/{userId}") 
	  public ResponseEntity<List<Order>> getOrderDetailsByUserId(@PathVariable(name = "userId") int userId) {
	  LOGGER.debug("OrderController::getOrderAndProductByOrderId"); return new
	  ResponseEntity<List<Order>>(orderService.getOrderByUserId(userId),HttpStatus.OK);
	  }
	 
	
	@GetMapping("/products/{productName}")
	public ResponseEntity<List<ProductResponse>> getProductListByName(@PathVariable(name = "productName") String productName) {
		LOGGER.debug("OrderController::getProductListByName");
		return new ResponseEntity<List<ProductResponse>>(orderService.getProdListByName(productName),HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Order> createUserOrders(@Valid @RequestBody List<OrderProductRequest> oderProductReuest, int userID) {
		LOGGER.debug("OrderController::createUserOrders");
		return new ResponseEntity<Order>(orderService.createOrder(oderProductReuest, userID),HttpStatus.CREATED);

	}
	@DeleteMapping
	public ResponseEntity<ErrorResponse> deleteOrder(int OrderId) {
		LOGGER.debug("OrderController::deleteOrder");
		orderService.deleteOrder(OrderId); 
		ErrorResponse erorResponse = new ErrorResponse(HttpStatus.OK, "Poduct deleted successfully");
		return new ResponseEntity<ErrorResponse>(erorResponse, HttpStatus.OK);
	
	}
	
	@GetMapping("/lastMonth")
	public List<Order> listByLastMonthOrders() {
		LOGGER.debug("OrderController::listByLastMonthOrders");
		LOGGER.info("List of Orders created last month");
		return this.orderService.listByLastMonthOrders();
	
	}
	
	


	

}
