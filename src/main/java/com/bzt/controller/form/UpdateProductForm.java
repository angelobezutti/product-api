package com.bzt.controller.form;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.bzt.model.Product;
import com.bzt.repository.ProductRepository;

public class UpdateProductForm {

	@NotNull @NotEmpty
	private String description;
	@NotNull @NotEmpty
	private String name;
	@Min(value = 0)
	private BigDecimal price;

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public Product updateProduct(Long id, ProductRepository productRepository) {
		Product product = productRepository.getById(id);
		
		product.setDescription(this.description);
		product.setName(this.name);
		product.setPrice(this.price);
		
		return product;
	}
	
}
