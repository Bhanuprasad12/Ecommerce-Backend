package com.ecommerce.cart.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.cart.client.ProductClient;
import com.ecommerce.cart.dto.CartDTO;
import com.ecommerce.cart.dto.ProductDTO;
import com.ecommerce.cart.entity.CartItem;
import com.ecommerce.cart.service.CartItemService;
import com.ecommerce.cart.service.CartService;


@RestController
@CrossOrigin
public class CartController {
	
	
	@Autowired
	private CartService cartService;
	

	@Autowired
	private ProductClient client;
	
	
	@Autowired
	private CartItemService cartItemService;
	
	@GetMapping("/product/{id}")
	public Optional<ProductDTO> getProduct(@PathVariable Long id) {
		return client.getProductById(id);
	}
	
	@GetMapping("/cart/product/{id}")
	public ProductDTO getCartProduct(@PathVariable Long id) {
		return cartService.getProduct(id);
	}
	
	@GetMapping("/products")
	public List<ProductDTO> listAllProducts(){
		return client.listAllProducts();
	}
	
	
	@GetMapping("/allcp")
	public List<CartDTO> allCarts(){
		return cartService.allCarts();
	}

	
	@PostMapping("/add/cart/{userId}")
	public String addToCart(@RequestBody CartItem cartItem, @PathVariable Long userId) {
		
		return cartService.addToCart(userId, cartItem);
		
	}
	
	
	@GetMapping("/cart/user/{userId}")
	public CartDTO getUserCart(@PathVariable Long userId) {
		return cartService.getCart(userId);
	}
	
	@DeleteMapping("/cart/remove/{userId}/item/{itemId}")
	public CartDTO removeCartItem(@PathVariable Long itemId, @PathVariable Long userId) {
		return cartService.removeCartItem(itemId, userId);
	}
	
	
	@DeleteMapping("/cart/all/remove/{userId}")
	public CartDTO removeAllCartItem(@RequestBody List<CartItem> cartItems, @PathVariable Long userId) {
		return cartService.deleteAllCartItems(cartItems, userId);
	}
	
	
	@PutMapping("/cart/quantity/change/{userId}")
	public String updateQunatity(@RequestBody CartItem cartItem, @PathVariable Long userId) {
		return cartItemService.changeQuantity(cartItem,userId);
	}
	
	@DeleteMapping("/cart/delete/purchase")
	public String deleteCartItemsToPurchase(@RequestBody List<CartItem> cartItems) {
		return cartItemService.deleteCartItemsToPurchase(cartItems);
	}
	
	
	
}
