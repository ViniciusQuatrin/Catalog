package com.projeto.catalog.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.catalog.dto.CategoryDTO;
import com.projeto.catalog.services.CategoryService;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

	@Autowired
	private CategoryService service;

	@GetMapping
	public List<CategoryDTO> findAll() {
		List<CategoryDTO> list = service.findAll();
		return list;
	}

	@GetMapping(value = "/{Id}")
	public CategoryDTO findById(@PathVariable Long Id) {
		CategoryDTO dto = service.findById(Id);
		return dto;
	}
}
