package com.projeto.catalog.services;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.projeto.catalog.entities.Product;
import com.projeto.catalog.repositories.ProductRepository;
import com.projeto.catalog.services.exceptions.DatabaseException;
import com.projeto.catalog.services.exceptions.ResourceNotFoundException;
import com.projeto.catalog.tests.Factory;

@SpringBootTest
public class ProductServiceTests {

	@InjectMocks
	private ProductService service;

	@Mock
	private ProductRepository repository;

	private long id;
	private long dependentId;
	private long nonExId;
	private PageImpl<Product> page;
	private Product product;

	@BeforeEach
	void setup() throws Exception {
		id = 1L;
		dependentId = 4L;
		nonExId = 20000L;
		product = Factory.createProduct();
		page = new PageImpl<>(List.of(product));
		
		Mockito.when(repository.findAll((Pageable)ArgumentMatchers.any())).thenReturn(page);
		
		Mockito.doNothing().when(repository).deleteById(id);
		Mockito.doThrow(EmptyResultDataAccessException.class).when(repository).deleteById(nonExId);
		Mockito.doThrow(DataIntegrityViolationException.class).when(repository).deleteById(dependentId);
	}

	@Test
	public void deleteShouldDoesNothingWhenIdNotExists() {
		Assertions.assertDoesNotThrow(() -> {
			service.delete(id);
		});

		Mockito.verify(repository, Mockito.times(1)).deleteById(id);
	}

	@Test
	public void deleteShouldThrowDatabaseExceptionWhenDependentId() {
		Assertions.assertThrows(DatabaseException.class, () -> {
			service.delete(dependentId);
		});

		Mockito.verify(repository, Mockito.times(1)).deleteById(dependentId);
	}

	@Test
	public void deleteShouldThrowResourceNotFoundExceptionWhenIdNotExists() {
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.delete(nonExId);
		});

		Mockito.verify(repository, Mockito.times(1)).deleteById(nonExId);
	}
}
