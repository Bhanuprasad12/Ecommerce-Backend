package com.ecommerce.cart.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.ecommerce.cart.client.ProductClient;
import com.ecommerce.cart.dto.CartDTO;
import com.ecommerce.cart.dto.CartItemDTO;
import com.ecommerce.cart.dto.ProductDTO;
import com.ecommerce.cart.entity.Cart;
import com.ecommerce.cart.entity.CartItem;
import com.ecommerce.cart.repository.CartItemRepository;
import com.ecommerce.cart.repository.CartRepository;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductClient client;

    /**
     * Retrieves a product from the Product service by ID.
     * 
     * @param id the ID of the product to retrieve
     * @return the retrieved product
     */
    public ProductDTO getProduct(Long id) {
        Optional<ProductDTO> productDTO = client.getProductById(id);
        return productDTO.get();
    }

    /**
     * Adds a new item to a user's cart, or updates an existing item in the cart.
     * 
     * @param userId the ID of the user whose cart to modify
     * @param cartItem the item to add or update
     * @return a message indicating whether the item was added or updated
     */
    public String addToCart(Long userId, CartItem cartItem) {

        // Check if the user already has a cart
        Cart cart = cartRepository.findByUserId(userId);

        if (cart == null) {
            // If the user does not have a cart, create a new one
            Cart newCart = new Cart();
            newCart.setUserId(userId);
            newCart.setCreatedDate(new Date());
            newCart.setLastModified(new Date());

            List<CartItem> cartItems = new ArrayList<>();

            // Retrieve the product being added to the cart from the Product service
            ProductDTO product = client.getProductById(cartItem.getProductId()).get();
            cartItem.setTotal(cartItem.getQuantity() * product.getPrice());
            cartItems.add(cartItem);
            newCart.setCartItems(cartItems);
            
            // Calculate the total price of the cart and save it
            newCart.setTotalPrice(cartItems);

            cartRepository.save(newCart);
            return "Item was added";

        } else {
            // If the user already has a cart, update it with the new item
            List<CartItem> cartItems = cart.getCartItems();

            // Retrieve the product being added to the cart from the Product service
            ProductDTO product = client.getProductById(cartItem.getProductId()).get();

            cartItem.setTotal(cartItem.getQuantity() * product.getPrice());
            
            for(CartItem item:cartItems) {
            	if(item.getProductId()==cartItem.getProductId()) {
            		return "This product has already been added to cart";
            	}
            }

            cartItems.add(cartItem);
            cart.setCartItems(cartItems);
            cart.setTotalPrice(cartItems);
            cartRepository.save(cart);

            return "added";

        }

    }
    
    /**
     * Converts a list of CartItems to a list of CartItemDTOs, including product details for each item.
     * 
     * @param cartItems the list of CartItems to convert
     * @return the converted list of CartItemDTOs
     */
    
	public List<CartItemDTO> findAllProducts(List<CartItem> cartItems){
		List<CartItemDTO> list = new ArrayList<>();
		
		for(CartItem cartItem : cartItems) {
			CartItemDTO item = new CartItemDTO();
			item.setId(cartItem.getId());
			item.setQuantity(cartItem.getQuantity());
			item.setTotal(cartItem.getTotal());
			item.setProduct(getProduct(cartItem.getProductId()));
			list.add(item);
		}
		
		return list;
	}
	

	
	public List<CartDTO> allCarts(){
		List<Cart> list = cartRepository.findAll();
		
		List<CartDTO> carts = new ArrayList<>();
		
		for(Cart cart : list) {
			CartDTO cartDTO = new CartDTO();
			
			cartDTO.setId(cart.getId());
			cartDTO.setUserId(cart.getUserId());
			cartDTO.setCreatedDate(cart.getCreatedDate());
			cartDTO.setLastModified(cart.getLastModified());
			cartDTO.setCartItems(findAllProducts(cart.getCartItems()));
			cartDTO.setTotalPrice(cart.getTotalPrice());
			
			carts.add(cartDTO);
			
		}
		
		return carts;
		
	}
	
	public CartDTO getCart(Long userId) {
		
		Cart cart = cartRepository.findByUserId(userId);
		CartDTO cartDTO = new CartDTO();
		
		
		cartDTO.setId(cart.getId());
		cartDTO.setUserId(cart.getUserId());
		cartDTO.setCreatedDate(cart.getCreatedDate());
		cartDTO.setLastModified(cart.getLastModified());
		cartDTO.setCartItems(findAllProducts(cart.getCartItems()));
		cartDTO.setTotalPrice(cart.getTotalPrice());
		
		return cartDTO;
		
		
	}
	
	
	public CartDTO removeCartItem(Long itemId, Long userId) {
		try {
			cartItemRepository.deleteById(itemId);
			
			Cart cart = cartRepository.findByUserId(userId);
			cart.setTotalPrice(cart.getCartItems());
			cartRepository.save(cart);
			
			
			return getCart(userId);
		}catch (Exception e) {
			
			throw new RuntimeException("Failed to remove cart item: " + e.getMessage());
		}
		
	}
	
	public CartDTO deleteAllCartItems(List<CartItem> cartItems, Long userId) {
		try {
			cartItemRepository.deleteAll(cartItems);
			
			Cart cart = cartRepository.findByUserId(userId);
			cart.setTotalPrice(cart.getCartItems());
			cartRepository.save(cart);

			return getCart(userId);
		}catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException("Failed to remove all cart item: " + e.getMessage());
		}
		
	}

}
