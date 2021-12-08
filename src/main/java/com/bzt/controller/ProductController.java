package com.bzt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
}
