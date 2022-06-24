package com.spring.order.feignClient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.order.dto.ProductResponse;





@FeignClient(name = "http://PRODUCT-SERVICE/product/products")
public interface ProductClient {

	@GetMapping("/{productId}")
	public ProductResponse getProductById(@PathVariable(name = "productId") int productId);
	
	@GetMapping("/like/{productName}")
	List<ProductResponse> getProductListByName(@PathVariable(name = "productName") String productName);
	

}
