package com.projeto.catalog.tests;

import java.time.Instant;

import com.projeto.catalog.dto.ProductDTO;
import com.projeto.catalog.entities.Category;
import com.projeto.catalog.entities.Product;

public class Factory {
	public static Product createProduct() { 
		Product prod = new Product(1L, "Notebook", "Notebook very efficient", 1600.0, "", Instant.now());
		prod.getCategories().add(new Category(3L, "Computadores"));
		return prod;
	}
	
	public static ProductDTO createProductDTO() { 
		Product prod = createProduct();
		return new ProductDTO(prod, prod.getCategories());
	}
}
