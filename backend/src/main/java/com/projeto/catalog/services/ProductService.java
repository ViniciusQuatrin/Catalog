package com.projeto.catalog.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projeto.catalog.dto.ProductDTO;
import com.projeto.catalog.entities.Product;
import com.projeto.catalog.repositories.ProductRepository;
import com.projeto.catalog.services.exceptions.DatabaseException;
import com.projeto.catalog.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;

	@Transactional(readOnly = true)
	public Page<ProductDTO> findAllPaged(PageRequest pageRequest) {
		Page<Product> list = repository.findAll(pageRequest);
		return list.map(x -> new ProductDTO(x));
	}

	@Transactional(readOnly = true)
	public ProductDTO findById(Long Id) {
		Optional<Product> obj = repository.findById(Id);
		Product prod = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new ProductDTO(prod, prod.getCategories());
	}

	@Transactional
	public ProductDTO insert(ProductDTO dto) {
		Product entity = new Product();
//		entity.setName(dto.getName());
		entity = repository.save(entity);
		return new ProductDTO(entity);
	}

	@Transactional
	public ProductDTO update(Long Id, ProductDTO dto) {
		try {
			Product entity = repository.getReferenceById(Id);
//			entity.setName(dto.getName());
			entity = repository.save(entity);
			return new ProductDTO(entity);
		} catch (EntityNotFoundException e) { 
			throw new ResourceNotFoundException("id not found " + Id);
		}
	}
	
	
	public void delete(Long Id) {
		if(!repository.existsById(Id)) { 
			throw new ResourceNotFoundException("id not found");
		}
		try { 
			repository.deleteById(Id);
		} catch (EmptyResultDataAccessException e) { 
			throw new ResourceNotFoundException("id not found" + Id);
		} catch (DataIntegrityViolationException e) { 
			throw new DatabaseException("Integraty violation");
		}
	}

}
