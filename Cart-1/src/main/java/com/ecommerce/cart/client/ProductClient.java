package com.ecommerce.cart.client;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ecommerce.cart.dto.ProductDTO;




@FeignClient(name="product-service",url = "localhost:8080")
public interface ProductClient {
	
	@GetMapping("/product/{productId}")
	public Optional<ProductDTO> getProductById(@PathVariable Long productId);

	@GetMapping("/products")
	public List<ProductDTO> listAllProducts();

}
