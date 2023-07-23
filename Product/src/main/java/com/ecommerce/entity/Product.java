package com.ecommerce.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	
	
	@OneToMany(targetEntity = Images.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "product_id",referencedColumnName = "id")
	private List<Images> images;
	
	
	private String sellerEmail;
	
	
	

}
