package com.bzt.service;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.util.UriComponentsBuilder;

import com.bzt.controller.dto.ProductDto;
import com.bzt.controller.form.UpdateProductForm;
import com.bzt.model.Product;
import com.bzt.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired 
	private ProductRepository productRepository;

	
	public List<Product> listProduct(){
		return productRepository.findAll();
	}
	
	public Product findById(Long id) {
		return productRepository.getById(id);
	}
	
	public ResponseEntity<ProductDto> createProduct(Product product, UriComponentsBuilder uriBuilder) {
		productRepository.save(product);
		
		URI uri = uriBuilder.path("/products/{id}").buildAndExpand(product.getId()).toUri();
		return ResponseEntity.created(uri).body(new ProductDto(product));
	}
	
	public ResponseEntity<ProductDto> updateProduct(Long id, UpdateProductForm updateForm) {
		Product product = productRepository.getById(id);
		updateForm.updateProduct(id, productRepository);
		return ResponseEntity.ok(new ProductDto(product));
		
	}
	
	public ResponseEntity<ProductDto> removeProduct(@PathVariable Long id){
		Product product = productRepository.getById(id);
		productRepository.delete(product);
		return ResponseEntity.ok().build();
	}
	
	public List<Product> searchProduct(String q, BigDecimal minPrice, BigDecimal maxPrice){
		return productRepository.findBySearch(q, minPrice, maxPrice);
	}
	
}
