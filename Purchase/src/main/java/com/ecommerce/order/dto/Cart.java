package com.ecommerce.order.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

	private Long id;

	private Long userId;

	private List<CartItem> cartItems;

	private double totalPrice;

	private Date createdDate;

	private Date lastModified;
}
