package com.spring.product.dto;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;



import com.spring.product.dto.ProductDto;

import lombok.Data;

@Data
public class ProductDto {

	private int id;
	
	@NotBlank(message = "Product name is required.")
	private String name;

	@Min(1)
	private double price;
	
}
