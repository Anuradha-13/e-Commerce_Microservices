package com.spring.product.service;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.product.dto.ProductDto;
import com.spring.product.entity.Product;
import com.spring.product.exception.ProductNotFoundException;
import com.spring.product.repo.ProductRepo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(SpringExtension.class)
public class ProductServiceImplTest {

	private Product product;
	private ProductDto productDto;

	@Mock
	private ProductRepo productRepository;

	@InjectMocks
	ProductServiceImpl productService;

	@Mock
	private ModelMapper modelMapper;

	@InjectMocks
	private ProductServiceImpl productServiceImpl;

	@BeforeEach
	public void setup() {
		product = product.builder().id(1).name("cake").price(20).build();
		productDto = new ProductDto();
		productDto.setId(1);
		productDto.setName("cheese");
		productDto.setPrice(100);

	}

	@Test
	public void testGetProductByIdForSucess() {
		// Given
		Mockito.when(productRepository.findById(1)).thenReturn(Optional.of(product));
		// When
		Product savedP = productServiceImpl.getProductById(1);
		// Then
		assertNotNull(savedP);
		assertEquals("cake", savedP.getName());

	}

	@Test
	public void testGetProductByIdForException() {
		// Given
		Mockito.when(productRepository.findById(1)).thenReturn(Optional.of(product));
		Product savedP = productServiceImpl.getProductById(2);
		// When
		Mockito.when(productRepository.findById(5)).thenThrow(new ProductNotFoundException("Product id not found"));
		// Then
		assertEquals(savedP, new ProductNotFoundException("Product id not found"));

	}

	@Test
	public void getProductByNameLikeForSuccess() {
		// Given
		Mockito.when(productRepository.findByNameIgnoreCaseContaining(anyString())).thenReturn(List.of(product));
		Mockito.when(modelMapper.map(any(), any())).thenReturn(productDto);
		// When
		List<ProductDto> actual = productService.getProductByName(anyString());
		// Then
		assertEquals(1, actual.size());
		assertEquals("cheese", actual.get(0).getName());
	}

}
