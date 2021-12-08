package com.bzt.controller.dto;

import java.math.BigDecimal;

import com.bzt.model.Product;

public class ProductDto {
	private String description;
	private Long id;
	private String name;
	private BigDecimal price;
	
	public ProductDto(Product product) {
		this.description = product.getDescription();
		this.id = product.getId();
		this.name = product.getName();
		this.price = product.getPrice();
	}
	
	public String getDescription() {
		return description;
	}
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public BigDecimal getPrice() {
		return price;
	}
	
}
