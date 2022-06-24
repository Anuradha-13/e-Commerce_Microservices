package com.spring.product.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.product.exception.ErrorResponse;
import com.spring.product.dto.ProductDto;
import com.spring.product.entity.Product;
import com.spring.product.service.ProductService;


@RestController
@RequestMapping("/products")
public class ProductController {
	private static final Logger LOGGER=LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/{prodcutID}")
	public ResponseEntity<Product> getProductById(@PathVariable(name = "prodcutID") int prodcutID) {
		LOGGER.debug("ProductController::getProductListByName");
		return new ResponseEntity<Product>(productService.getProductById(prodcutID),HttpStatus.OK);
		
	}
	
	@GetMapping("/like/{productName}")
	public ResponseEntity<List<ProductDto>> getProductListByName(@PathVariable(name = "productName") String productName) {
		LOGGER.debug("ProductController::getProductListByName");
		return new ResponseEntity<List<ProductDto>>(productService.getProductByName(productName),HttpStatus.OK);
		
	}
	@PostMapping
	public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto productDto) {
		LOGGER.debug("ProductController::createProduct");
		return new ResponseEntity<ProductDto>(productService.createProduct(productDto),HttpStatus.CREATED);
		
	}
	@DeleteMapping("/{productId}")
	public ResponseEntity<ErrorResponse> deleteProduct(@PathVariable(name = "productId") int productId) {
		LOGGER.debug("ProductController::createProduct");
		productService.deleteProduct(productId);
		ErrorResponse erorResponse = new ErrorResponse(HttpStatus.OK, "Poduct deleted successfully");
		return new ResponseEntity<ErrorResponse>(erorResponse, HttpStatus.OK);
	
	}


}
