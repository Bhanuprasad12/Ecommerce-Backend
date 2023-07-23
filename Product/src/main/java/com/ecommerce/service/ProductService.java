package com.ecommerce.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.entity.Product;
import com.ecommerce.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public String newProduct(Product product) {
		try {
			product.setLastModified(new Date());
			productRepository.save(product);
			return "Product added";
		}catch (Exception e) {
			// TODO: handle exception
			return e.getMessage();
		}
	}
	
	public List<Product> getAllProducts(){
		return productRepository.findAll();
	}
	
	
	public List<Product> listProductsByCategory(String category){
		return productRepository.findByCategory(category);
	}
	
	public List<Product> listProductsBySubCategory(String subCategory){
		return productRepository.findBySubCategory(subCategory);
	}
	
	
	public Optional<Product> getProduct(Long id) {
		return productRepository.findById(id);
	}
	
	
	public String updateProduct(Product product) {
		Optional<Product> optionalProduct = productRepository.findById(product.getId());
		if(optionalProduct.isPresent()) {
			
			product.setLastModified(new Date());
			productRepository.save(product);
			return "Product with Id: " + product.getId() + " updated";
		}else {
			return "Not found";
		}
	}
	
	public String deleteProduct(long id) {
		Optional<Product> optionalProduct = productRepository.findById(id);
		if(optionalProduct.isPresent()) {
			productRepository.deleteById(id);
			return "deleted";
		}else {
			return "not deleted";
		}
	}
	
	
	public List<Product> SearchProducts(String query){
		return productRepository.searchProducts(query);
	}

}
