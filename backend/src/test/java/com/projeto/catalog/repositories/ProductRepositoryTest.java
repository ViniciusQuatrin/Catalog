package com.projeto.catalog.repositories;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.projeto.catalog.entities.Product;
import com.projeto.catalog.tests.Factory;

@DataJpaTest
public class ProductRepositoryTest {
	
	@Autowired
	private ProductRepository repository;
	
	private long countProd;
	private long id;
	
	@BeforeEach 
	void setup() throws Exception { 
		id = 1L;
		countProd = 25L;
	}
	
	@Test
	public void saveShouldPersistWithAutoIncrementWhenIdIsNull() { 
		Product prod = Factory.createProduct();
		prod.setId(null);
		
		prod = repository.save(prod);
		Assertions.assertNotNull(prod.getId());
		Assertions.assertEquals(countProd + 1, prod.getId());
	}
	
	@Test
	public void deleteShouldDeleteObjectWhenIdExists() { 
		repository.deleteById(id);
		
		Optional<Product> result = repository.findById(id);
		
		Assertions.assertFalse(result.isPresent());
	}	
	
//	@Test
//	public void deleteShouldThrowEmptyResultNotFoundExceptionWhenIdNotExists() { 
//		long nonExistingId = 1000000000L;
//		
//		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
//			repository.deleteById(nonExistingId);
//		});
//	}
}
