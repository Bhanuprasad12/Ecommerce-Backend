package com.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecommerce.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product> findByCategory(String category);

	List<Product> findBySubCategory(String subCategory);

	@Query("SELECT p FROM Product p WHERE " + "p.productName LIKE CONCAT('%',:query, '%')"
			+ "Or p.longDescription LIKE CONCAT('%',:query, '%')"
			+ "Or p.specifications LIKE CONCAT('%',:query, '%')"
			+ "Or p.category LIKE CONCAT('%',:query, '%')" 
			+ "Or p.subCategory LIKE CONCAT('%',:query, '%')")
	List<Product> searchProducts(String query);
}
