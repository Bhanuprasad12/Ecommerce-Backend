package com.ecommerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.entity.Category;
import com.ecommerce.entity.SubCategory;
import com.ecommerce.repository.SubCategoryRepository;

@Service
public class SubCategoryService {
	
	
	@Autowired
	private SubCategoryRepository subCategoryRepository;
	

	public String deleteSubCategory(long id) {
		Optional<SubCategory> optionalSubCategory = subCategoryRepository.findById(id);
		if(optionalSubCategory.isPresent()) {
			subCategoryRepository.deleteById(id);
			return "Deleted";
		}else {
			return "Not deleted";
		}
	}
	
	public String updateSubCategory(SubCategory subCategory) {
		Optional<SubCategory> optionalSubCategory = subCategoryRepository.findById(subCategory.getId());
		if(optionalSubCategory.isPresent()) {
			subCategoryRepository.save(subCategory);
			return "Updated subcategory";
		}
		return "Not found";
	}
	
	public String enableSubCategory(SubCategory subCategory) {
		Optional<SubCategory> optionalSubCategory = subCategoryRepository.findById(subCategory.getId());
		if(optionalSubCategory.isPresent()) {
			subCategory.setStatus(true);
			subCategoryRepository.save(subCategory);
			return  subCategory.getSubCategoryName() + " Subcategory enabled";
		}
		return "Not found";
	}
	public String disableSubCategory(SubCategory subCategory) {
		Optional<SubCategory> optionalSubCategory = subCategoryRepository.findById(subCategory.getId());
		if(optionalSubCategory.isPresent()) {
			subCategory.setStatus(false);
			subCategoryRepository.save(subCategory);
			return  subCategory.getSubCategoryName() + " Subcategory disabled";
		}
		return "Not found";
	}

}
