package com.ecommerce.entity;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String categoryName;
	private String catImage;
	
	private boolean status;
	
	@UpdateTimestamp
	private Date lastModified;
	
	@OneToMany(targetEntity = SubCategory.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "cat_id",referencedColumnName = "id")
	private List<SubCategory> subCategories;

}
