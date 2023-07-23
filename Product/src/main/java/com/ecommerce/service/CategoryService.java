package com.ecommerce.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.entity.Category;
import com.ecommerce.entity.SubCategory;
import com.ecommerce.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public String createCategory(Category category) {
		try {
			category.setStatus(true);
			categoryRepository.save(category);
			return "category added";
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

	public String addSubCategory(SubCategory subCategory, Long categoryId) {
		Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
		if (optionalCategory.isPresent()) {
			Category category = optionalCategory.get();
			List<SubCategory> subCategories = category.getSubCategories();
			subCategory.setStatus(true);
			
			subCategories.add(subCategory);
			category.setSubCategories(subCategories);
			categoryRepository.save(category);
			return "added";
		} else {
			return "not found";
		}
	}
	
	
	public String deleteCategory(long categoryId) {
		Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
		if(optionalCategory.isPresent()) {
			categoryRepository.deleteById(categoryId);
			return "Deleted";
		}
		return "Not found or not deleted";
	}
	
	
	
	public String updateCategory(Category category) {
		Optional<Category> optionalCategory = categoryRepository.findById(category.getId());
		if(optionalCategory.isPresent()) {
			categoryRepository.save(category);
			return "Updated";
		}
		return "Not found || Not Updated";
	}
	
	
	public String enableCategory(Category category) {
		Optional<Category> optionalCategory = categoryRepository.findById(category.getId());
		if(optionalCategory.isPresent()) {
			category.setStatus(true);
			categoryRepository.save(category);
			return "Category Enabeled";
		}
		return "Not found";
	}
	
	public String disableCategory(Category category) {
		Optional<Category> optionalCategory = categoryRepository.findById(category.getId());
		if(optionalCategory.isPresent()) {
			category.setStatus(false);
			categoryRepository.save(category);
			return "Category disabeled";
		}
		return "Not found";
		
	}
	
	public List<Category> getAllActiveCategories() {
	    List<Category> categories = categoryRepository.findAll();
	    return categories.stream()
	            .filter(Category::isStatus) // check if category is active
	            .peek(category -> category.getSubCategories().removeIf(subCategory -> !subCategory.isStatus())) // remove inactive subcategories
//	            .filter(category -> category.getSubCategories().stream().allMatch(SubCategory::isStatus)) // check if all subcategories are active
	            .collect(Collectors.toList());
	}

	
	
	

}
