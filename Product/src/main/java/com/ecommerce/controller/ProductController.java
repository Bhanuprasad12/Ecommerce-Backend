package com.ecommerce.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.entity.Product;
import com.ecommerce.service.ProductService;

@RestController
@CrossOrigin
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@PostMapping("/newproduct")
	public String newProduct(@RequestBody Product product) {
		return productService.newProduct(product);
	}
	
	@GetMapping("/products")
	public List<Product> listAllProducts(){
		return productService.getAllProducts();
	}
	
	@GetMapping("/category/{category}")
	public List<Product> getProductsByCategory(@PathVariable String category){
		return productService.listProductsByCategory(category);
	}
	
	
	@GetMapping("/subcategory/{subCategory}")
	public List<Product> getProductsBySubCategory(@PathVariable String subCategory){
		return productService.listProductsBySubCategory(subCategory);
	}
	
	@GetMapping("/product/{productId}")
	public Optional<Product> getProductById(@PathVariable Long productId) {
		return productService.getProduct(productId);
	}
	
	@PutMapping("/product/update")
	public String updateProduct(@RequestBody  Product product) {
		return productService.updateProduct(product);
	}
	
	@DeleteMapping("/product/delete/{id}")
	public String deleteProduct(@PathVariable Long id) {
		return productService.deleteProduct(id);
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<Product>> getSearchProducts(@RequestParam("query") String query){
		return ResponseEntity.ok(productService.SearchProducts(query));
		
	}
	
}
