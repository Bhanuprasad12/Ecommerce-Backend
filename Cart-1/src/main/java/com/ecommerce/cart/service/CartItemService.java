package com.ecommerce.cart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.cart.client.ProductClient;
import com.ecommerce.cart.dto.ProductDTO;
import com.ecommerce.cart.entity.Cart;
import com.ecommerce.cart.entity.CartItem;
import com.ecommerce.cart.repository.CartItemRepository;
import com.ecommerce.cart.repository.CartRepository;

@Service
public class CartItemService {

	@Autowired
	private CartItemRepository cartItemRepository;

	@Autowired
	private ProductClient productClient;

	@Autowired
	private CartRepository cartRepository;

	public String changeQuantity(CartItem cartItem, Long userId) {

		if (cartItem.getQuantity() > 0) {

			try {

				ProductDTO dto = productClient.getProductById(cartItem.getProductId()).get();
				cartItem.setTotal(dto.getPrice() * cartItem.getQuantity());
				cartItemRepository.save(cartItem);

				// Check if the user already has a cart
				Cart cart = cartRepository.findByUserId(userId);

				cart.setTotalPrice(cart.getCartItems());
				cartRepository.save(cart);

				return "quantity updated";
			} catch (Exception e) {
				// TODO: handle exception
				return e.getMessage();
			}

		} else {
			return "Quantity should be more than one";
		}
	}

	
	
	public String deleteCartItemsToPurchase(List<CartItem> cartItems) {
		try {
			cartItemRepository.deleteAll(cartItems);
			return "deleted";
		}catch (Exception e) {
			// TODO: handle exception
			return e.getMessage();
		}
	}
}
