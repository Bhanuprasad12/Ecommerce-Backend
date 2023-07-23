package com.ecommerce.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.order.client.CartClient;
import com.ecommerce.order.dto.CartItem;
import com.ecommerce.order.dto.PurchaseDTO;
import com.ecommerce.order.entity.Orders;
import com.ecommerce.order.entity.Purchase;
import com.ecommerce.order.service.PurchaseService;

@RestController
@CrossOrigin
public class OrderController {
	
	@Autowired
	private PurchaseService purchaseService;
	
	@Autowired
	private CartClient cartClient;
	
	
	@PostMapping("/order/{userId}")
	public String orderProduct(@PathVariable Long userId, @RequestBody Orders order) {
		return purchaseService.PurchaseItem(userId, order);
	}
	
	@DeleteMapping("/order/cancel/{orderId}")
	public String cancelOrder(@PathVariable Long orderId) {
		return purchaseService.cancelOrder(orderId);
	}
	
	@PutMapping("/order/modify/{orderId}/{status}")
	public String modifyOrderStatus(@PathVariable Long orderId, @PathVariable String status) {
		return purchaseService.modifyOrderStatus(orderId, status);
	}
	
	@PostMapping("/order/cart/{userId}")
	public String cartPurchase(@RequestBody List<CartItem> cartItems, @PathVariable Long userId) {
		
		return purchaseService.cartPurchase(cartItems, userId);
		
	}
	
	@GetMapping("/all")
	public List<Purchase> getAll(){
		return purchaseService.getAll();
	}
	
	
	@GetMapping("/myorders/{userId}")
	public PurchaseDTO getUserOrders(@PathVariable Long userId) {
		return purchaseService.getUserOrders(userId);
	}
	
	
	@DeleteMapping("/cart/delete/purchase")
	public String deleteCartItemsToPurchase(@RequestBody List<CartItem> cartItems) {
		return cartClient.deleteCartItemsToPurchase(cartItems);
	}
	
	
	
	
	
	
	

}
