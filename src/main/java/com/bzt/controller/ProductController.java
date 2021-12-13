package com.bzt.controller;

import java.math.BigDecimal;
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
import com.bzt.model.Product;
import com.bzt.service.ProductService;


@RestController
@RequestMapping("/products")
public class ProductController {
	@Autowired 
	private ProductService productService;
	
	@GetMapping
	public List<Product> listProduct(){
		return productService.listProduct();
	}
	
	@GetMapping("/{id}")
	public Product detailsProduct(@PathVariable Long id) {
		return productService.findById(id);
	    		  
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<ProductDto> createProduct(@RequestBody @Valid Product product, UriComponentsBuilder uriBuilder) {
		return productService.createProduct(product, uriBuilder);
	}	
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @RequestBody @Valid UpdateProductForm updateForm) {
		return productService.updateProduct(id, updateForm);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<ProductDto> removeProduct(@PathVariable Long id){
		return productService.removeProduct(id);
	}
	
	@GetMapping("/search")
	public List<Product> searchProduct(@RequestParam(required = false ,value = "q") String q, @RequestParam(required = false ,value = "minPrice") BigDecimal minPrice, @RequestParam(required = false , value = "maxPrice") BigDecimal maxPrice){
		return productService.searchProduct(q, minPrice, maxPrice);
	}
	
}
