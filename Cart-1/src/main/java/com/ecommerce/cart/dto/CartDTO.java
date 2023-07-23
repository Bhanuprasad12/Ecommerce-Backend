package com.ecommerce.cart.dto;

import java.util.Date;
import java.util.List;

import com.ecommerce.cart.entity.CartItem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {

	private Long id;

	private Long userId;

	private List<CartItemDTO> cartItems;

	private double totalPrice;

	private Date createdDate;

	private Date lastModified;
}
