package com.projeto.catalog.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

	@PostMapping
	public ResponseEntity<CategoryDTO> insert(@RequestBody CategoryDTO dto) {
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{Id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

	@PutMapping(value = "/{Id}")
	public CategoryDTO update(@PathVariable Long Id, @RequestBody CategoryDTO dto) {
		dto = service.update(Id, dto);
		return dto;
	}

	@DeleteMapping(value = "/{Id}")
	public ResponseEntity<CategoryDTO> delete(@PathVariable Long Id) {
		service.delete(Id);
		return ResponseEntity.noContent().build();
	}
}
