package com.ecommerce.order.client;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ecommerce.order.dto.Product;



@FeignClient(name = "product-service", url = "localhost:8080")
public interface ProductClient {
	
	@GetMapping("/product/{productId}")
	public Optional<Product> getProductById(@PathVariable Long productId);

}
