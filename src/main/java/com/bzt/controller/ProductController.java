package com.bzt.controller;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.bzt.controller.dto.ProductDto;
import com.bzt.controller.form.UpdateProductForm;
import com.bzt.exception.ResourceNotFoundException;
import com.bzt.model.Product;
import com.bzt.repository.ProductRepository;


@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired 
	private ProductRepository productRepository;
	
	@GetMapping
	public List<Product> listProduct(){
		return productRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Product detailsProduct(@PathVariable Long id) {
		return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product Not Found " + id));
	    		  
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<ProductDto> createProduct(@RequestBody @Valid Product product, UriComponentsBuilder uriBuilder) {
		productRepository.save(product);
		
		URI uri = uriBuilder.path("/products/{id}").buildAndExpand(product.getId()).toUri();
		return ResponseEntity.created(uri).body(new ProductDto(product));
	}	
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @RequestBody @Valid UpdateProductForm updateForm) {
		Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product Not Found " + id));
		updateForm.updateProduct(id, productRepository);
		return ResponseEntity.ok(new ProductDto(product));
		
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<ProductDto> removeProduct(@PathVariable Long id){
		Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product Not Found " + id));
		productRepository.delete(product);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/search")
	public List<Product> searchProduct(@RequestParam(required = false ,value = "q") String q, @RequestParam(required = false ,value = "minPrice") BigDecimal minPrice, @RequestParam(required = false , value = "maxPrice") BigDecimal maxPrice){
		return productRepository.findBySearch(q, minPrice, maxPrice);
	}
	
	
}
