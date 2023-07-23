package com.ecommerce.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDTO {

	
	private Long id;
	
	private ProductDTO product;
	
	private int quantity;
	
	private double total;
}
