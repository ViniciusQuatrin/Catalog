package com.projeto.catalog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projeto.catalog.dto.CategoryDTO;
import com.projeto.catalog.entities.Category;
import com.projeto.catalog.repositories.CategoryRepository;
import com.projeto.catalog.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;

	@Transactional(readOnly = true)
	public List<CategoryDTO> findAll() {
		List<Category> list = repository.findAll();
		return list.stream().map(x -> new CategoryDTO(x)).toList();
	}

	@Transactional(readOnly = true)
	public CategoryDTO findById(Long Id) {
		Category cat = repository.findById(Id).orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new CategoryDTO(cat);
	}

	@Transactional
	public CategoryDTO insert(CategoryDTO dto) {
		Category entity = new Category();
		entity.setName(dto.getName());
		entity = repository.save(entity);
		return new CategoryDTO(entity);
	}

	@Transactional
	public CategoryDTO update(Long Id, CategoryDTO dto) {
		try {
			Category entity = repository.getReferenceById(Id);
			entity.setName(dto.getName());
			entity = repository.save(entity);
			return new CategoryDTO(entity);
		} catch (EntityNotFoundException e) { 
			throw new ResourceNotFoundException("id not found " + Id);
		}
	}

}
