package com.ecommerce.cart.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="carts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	private Long userId;
	
	@OneToMany(targetEntity = CartItem.class, cascade = CascadeType.ALL)
	@JoinColumn(name="cart_id", referencedColumnName = "id")
	private List<CartItem> cartItems;
	
	private double totalPrice;
	
	private Date createdDate;
	
	private Date lastModified;
	
	
	public void setTotalPrice(List<CartItem> cartItems) {
		double price =0;
		for(CartItem cartItem : cartItems) {
			price = price + cartItem.getTotal();
		}
		
		this.totalPrice = price;
	}
	
	
	
	
}
