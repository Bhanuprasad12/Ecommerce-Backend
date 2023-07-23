package com.ecommerce.order.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.ecommerce.order.dto.Cart;
import com.ecommerce.order.dto.CartItem;





@FeignClient(name = "cart-service", url = "localhost:8082")
public interface CartClient {

	@DeleteMapping("/cart/delete/purchase")
	public String deleteCartItemsToPurchase(@RequestBody List<CartItem> cartItems);
	
	
	@DeleteMapping("/cart/all/remove/{userId}")
	public Cart removeAllCartItem(@RequestBody List<CartItem> cartItems, @PathVariable Long userId);
}
