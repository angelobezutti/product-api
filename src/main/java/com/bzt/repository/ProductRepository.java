package com.bzt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bzt.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
