package com.ecommerce.order.dto;

import lombok.Data;

@Data
public class OrdersDTO {

	private Long id;
	private Product product;
	private double totalPrice;
	private int quantity;
	private String transactionId;
	private String itemStatus;

}
