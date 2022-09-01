package com.laptrinhjavaweb.repository.entity;

import com.laptrinhjavaweb.anotation.Column;

public class BaseEntity {
	
	@Column(name = "id")
	private Long id;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
