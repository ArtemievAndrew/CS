package ru.cs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "s_role")
public class SRole {
	
	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	private Long id;
	
	@Column(name = "name", length = 30, nullable = false)
	private String name;

	
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
	
}
