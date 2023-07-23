package com.ecommerce.order.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.order.client.CartClient;
import com.ecommerce.order.client.ProductClient;
import com.ecommerce.order.dto.CartItem;
import com.ecommerce.order.dto.OrdersDTO;
import com.ecommerce.order.dto.Product;
import com.ecommerce.order.dto.PurchaseDTO;
import com.ecommerce.order.entity.Orders;
import com.ecommerce.order.entity.Purchase;
import com.ecommerce.order.repository.OrdersRepository;
import com.ecommerce.order.repository.PurchaseRepository;

@Service
public class PurchaseService {

	@Autowired
	private PurchaseRepository purchaseRepository;

	@Autowired
	private OrdersRepository ordersRepository;

	@Autowired
	private ProductClient client;

	@Autowired
	private CartClient cartClient;

	public String PurchaseItem(Long userId, Orders order) {

		Purchase purchase = purchaseRepository.findByUserId(userId);

		Product product = client.getProductById(order.getProductId()).get();

		if (product == null) {
			return "error occured or product not present";
		} else {
			order.setTotalPrice(product.getPrice() * order.getQuantity());
			order.setTransactionId(generateTransactionId(order.getProductId()));
			order.setItemStatus("TRNSINT");
		

		if (purchase == null) {

			List<Orders> list = new ArrayList<Orders>();
			list.add(order);

			Purchase purchase2 = new Purchase();

			purchase2.setUserId(userId);
			purchase2.setOrders(list);

			purchaseRepository.save(purchase2);
			return "Item purchased successfully and it's new. Here is the transaction Id: " + order.getTransactionId();
		} else {
			List<Orders> orders = purchase.getOrders();
			orders.add(order);

			purchase.setOrders(orders);
			purchaseRepository.save(purchase);
			return "Item purchased successfully. Here is the transaction Id: " + order.getTransactionId();

		}
		}

	}

	public String generateTransactionId(Long productId) {
		char first = (char) ((int) (Math.random() * 26) + 'A');
		char second = (char) ((int) (Math.random() * 26) + 'A');
		int number = (int) (Math.random() * 9000 + 1000);
		return "SHO" + productId + first + second + number;
	}

	public String cancelOrder(Long orderId) {
		Orders order = ordersRepository.findById(orderId).get();
		if (order != null) {
			order.setCancelStatus(true);
			ordersRepository.save(order);
			return "Order cancelled";
		}
		return "error occured try again";
	}

	public String modifyOrderStatus(Long orderId, String status) {

		Orders order = ordersRepository.findById(orderId).get();
		if (order != null) {
			order.setItemStatus(status);
			ordersRepository.save(order);
			return "Status changed to " + status;
		}

		return "Error occured";
	}

	public String cartPurchase(List<CartItem> cartItems, Long userId) {

		try {

			for (CartItem cartItem : cartItems) {
				Orders order = new Orders();

				Product product = client.getProductById(cartItem.getProductId()).get();

				order.setProductId(cartItem.getProductId());
				order.setQuantity(cartItem.getQuantity());
				order.setTotalPrice(product.getPrice() * order.getQuantity());
				order.setTransactionId(generateTransactionId(order.getProductId()));
				order.setItemStatus("TRNSINT");

				PurchaseItem(userId, order);
			}
			
		
				cartClient.removeAllCartItem(cartItems, userId);
				return "Puchased Successfull";
		
			
		} catch (Exception e) {

			return e.getMessage();
		}
	}

	
	public List<Purchase> getAll(){
		return purchaseRepository.findAll();
	}
	
	public PurchaseDTO getUserOrders(Long userId) {
		Purchase purchase = purchaseRepository.findByUserId(userId);
		
		PurchaseDTO dto = new PurchaseDTO();
		
		List<OrdersDTO> list = new ArrayList<>() ;
		
		dto.setId(purchase.getId());
		dto.setUserId(purchase.getUserId());
		
		for(Orders order : purchase.getOrders()) {
			OrdersDTO ordersDTO = new OrdersDTO();
			ordersDTO.setId(order.getId());
		 Product product =	client.getProductById(order.getProductId()).get();
			ordersDTO.setProduct(product);
			ordersDTO.setQuantity(order.getQuantity());
			ordersDTO.setTotalPrice(order.getTotalPrice());
			ordersDTO.setTransactionId(order.getTransactionId());
			ordersDTO.setItemStatus(order.getItemStatus());
			
			list.add(ordersDTO);
			
		}
		dto.setOrders(list);
		return dto;
	}
}
