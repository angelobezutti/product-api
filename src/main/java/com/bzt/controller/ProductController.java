package com.bzt.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.bzt.controller.dto.ProductDto;
import com.bzt.controller.form.UpdateProductForm;
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
	public ProductDto detailsProduct(@PathVariable Long id) {
		 Product product = productRepository.getById(id);
	      return new ProductDto(product);
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
		Optional<Product> optional = productRepository.findById(id);
		if (optional.isPresent()) {
			Product product = updateForm.updateProduct(id, productRepository);
			return ResponseEntity.ok(new ProductDto(product));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> removeProduct(@PathVariable Long id){
		Optional<Product> optional = productRepository.findById(id);
		if (optional.isPresent()) {
			productRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	
	
}
