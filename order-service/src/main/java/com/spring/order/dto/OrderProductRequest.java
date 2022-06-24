package com.spring.order.dto;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@NoArgsConstructor 
@AllArgsConstructor
@ToString
public class OrderProductRequest {

	@NotEmpty(message="Please enter Product Id")
	private int productId; 
	
	@NotBlank
	@Min(1)
	private int quantity;

}
