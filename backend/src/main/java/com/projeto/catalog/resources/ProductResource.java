package com.projeto.catalog.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import com.projeto.catalog.dto.ProductDTO;
import com.projeto.catalog.services.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {

	@Autowired
	private ProductService service;

	@GetMapping
	public Page<ProductDTO> findAll(Pageable pageable) {
		Page<ProductDTO> list = service.findAllPaged(pageable);
		return list;
	}

	@GetMapping(value = "/{Id}")
	public ResponseEntity<ProductDTO> findById(@PathVariable Long Id) {
		ProductDTO dto = service.findById(Id);
		return ResponseEntity.ok().body(dto);
	}

	@PostMapping
	public ResponseEntity<ProductDTO> insert(@RequestBody ProductDTO dto) {
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{Id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

	@PutMapping(value = "/{Id}")
	public ProductDTO update(@PathVariable Long Id, @RequestBody ProductDTO dto) {
		dto = service.update(Id, dto);
		return dto;
	}

	@DeleteMapping(value = "/{Id}")
	public ResponseEntity<ProductDTO> delete(@PathVariable Long Id) {
		service.delete(Id);
		return ResponseEntity.noContent().build();
	}
}
