package com.spring.product.service;

import java.util.List;

import org.springframework.validation.annotation.Validated;

import com.spring.product.dto.ProductDto;
import com.spring.product.entity.Product;

@Validated
public interface ProductService {
	
	
	//public Product getProductById(int id);
	
	public List<ProductDto> getAllProducts();

	public void deleteProduct(int id);
	
	Product getProductById(int id);

	List<ProductDto> getProductByName(String productName);

	ProductDto createProduct(ProductDto product);

	ProductDto updateProduct(int id, ProductDto productDto);
	
	
}
