package com.ecommerce.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecommerce.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
	
	Optional<Category> findByCategoryName(String catName);
//
//	@Query("SELECT c FROM Category c WHERE c.status = true AND EXISTS (SELECT s FROM c.subCategories s WHERE s.status = true)")
//	List<Category> findAllActiveCategories();
	
//	@Query("Select c from Category c Where c.status = true AND  Exists (Select s From c.subCategories s Where s.status = true)")
//	List<Category> findAllActiveCategories();

}
