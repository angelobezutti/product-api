package com.bzt.controller.form;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.bzt.model.Product;
import com.bzt.repository.ProductRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor 
public class UpdateProductForm {

	@NotNull @NotEmpty
	private String description;
	@NotNull @NotEmpty
	private String name;
	@Min(value = 0)
	private BigDecimal price;
	
	public Product updateProduct(Long id, ProductRepository productRepository) {
		Product product = productRepository.getById(id);
		
		product.setDescription(this.description);
		product.setName(this.name);
		product.setPrice(this.price);
		
		return product;
	}
}
