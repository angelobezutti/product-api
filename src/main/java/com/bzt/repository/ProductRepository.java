package com.bzt.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bzt.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
	@Query(value = "SELECT a.* FROM Product a WHERE "
			+ "(:q IS NULL OR (UPPER(name)"
			+ "LIKE UPPER(CONCAT('%', :q, '%'))"
			+ "OR UPPER(description)"
			+ "LIKE UPPER(CONCAT('%', :q, '%'))))"
            + "AND (:minPrice IS NULL OR price >= :minPrice)"
            + "AND (:maxPrice IS NULL OR price <= :maxPrice)", nativeQuery = true)
	List<Product> findBySearch(String q, BigDecimal minPrice, BigDecimal maxPrice);
}
