package com.ecommerce.cart.dto;

import java.util.Date;
import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
	
	
	private Long id;
	private String productName;
	private String productBrand;
	private String company;
	private String shortDescription;
	private String longDescription;
	private String specifications;
	private double price;
	private String category;
	private String subCategory;
	private String quantity;
	private Date lastModified;
	
	
	
	private List<Images> images;
	
	
	private String sellerEmail;
}
