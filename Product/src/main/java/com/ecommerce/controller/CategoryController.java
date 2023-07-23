package com.ecommerce.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.entity.Category;
import com.ecommerce.entity.SubCategory;
import com.ecommerce.repository.CategoryRepository;
import com.ecommerce.service.CategoryService;
import com.ecommerce.service.SubCategoryService;

@RestController
@CrossOrigin()
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private SubCategoryService subCategoryService;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@PostMapping("/addcategory")
	public String addCategory(@RequestBody Category category) {
		return categoryService.createCategory(category);
	}
	
	@PostMapping("/all")
	public void addall(@RequestBody List<Category> categories) {
		categoryRepository.saveAll(categories);
	}
	@GetMapping("/categories/active")
	public List<Category> getAllActive(){
		return categoryService.getAllActiveCategories();
				
	}
	
	@PutMapping("/addsubcategory/{categoryId}")
	public String addSubCategory(@RequestBody SubCategory subCategory, @PathVariable Long categoryId) {
		return categoryService.addSubCategory(subCategory, categoryId);
	}
	
	@GetMapping("/categories")
	public List<Category> listAllCategories(){
		return categoryService.getAllCategories();
	}
	
	@GetMapping("/maincategory/{catName}")
	public Optional<Category> getCategoryByName(@PathVariable String catName){
		return categoryRepository.findByCategoryName(catName);
	}
	
	@DeleteMapping("/delete/subcategory/{id}")
	public String deleteSubCategory(@PathVariable long id) {
		return subCategoryService.deleteSubCategory(id);
	}
	
	@DeleteMapping("/delete/category/{categoryId}")
	public String deleteCategory(@PathVariable long categoryId) {
		return categoryService.deleteCategory(categoryId);
	}
	
	@PutMapping("/category/update")
	public String updateCategory(@RequestBody Category category) {
		return categoryService.updateCategory(category);
	}
	
	@PutMapping("/subcategory/update")
	public String updateSubCategory(@RequestBody SubCategory subCategory) {
		return subCategoryService.updateSubCategory(subCategory);
	}
	
	@PutMapping("/category/enable")
	public String enableCategory(@RequestBody Category category) {
		return categoryService.enableCategory(category);
	}
	
	@PutMapping("/category/disable")
	public String disableCategory(@RequestBody Category category) {
		return categoryService.disableCategory(category);
	}
	
	@PutMapping("/subcategory/enable")
	public String enableSubCategory(@RequestBody SubCategory subCategory) {
		return subCategoryService.enableSubCategory(subCategory);
	}
	
	@PutMapping("/subcategory/disable")
	public String disableSubCategory(@RequestBody SubCategory subCategory) {
		return subCategoryService.disableSubCategory(subCategory);
	}
	
}
