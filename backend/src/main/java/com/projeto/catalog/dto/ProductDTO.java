package com.projeto.catalog.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.projeto.catalog.entities.Category;
import com.projeto.catalog.entities.Product;

public class ProductDTO {

	private Long id;
	private String name;
	private String description;
	private Double price;
	private String imgUrl;
	private Instant date;

	private List<CategoryDTO> categories = new ArrayList<CategoryDTO>();

	public ProductDTO() {
	}

	public ProductDTO(Long id, String name, String description, Double price, String imgUrl, Instant date) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imgUrl = imgUrl;
		this.date = date;
	}

	public ProductDTO(Product e) {
		this.id = e.getId();
		this.name = e.getName();
		this.description = e.getDescription();
		this.price = e.getPrice();
		this.imgUrl = e.getImgUrl();
		this.date = e.getDate();
	}

	public ProductDTO(Product e, Set<Category> categories) {
		this(e);
		categories.forEach(cat -> this.categories.add(new CategoryDTO(cat)));
	}

	@Override
	public String toString() {
		return "ProductDTO [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price
				+ ", imgUrl=" + imgUrl + ", date=" + date + ", categories=" + categories + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Instant getDate() {
		return date;
	}

	public void setDate(Instant date) {
		this.date = date;
	}

	public List<CategoryDTO> getCategories() {
		return categories;
	}

	public void setCategories(List<CategoryDTO> categories) {
		this.categories = categories;
	}

}
