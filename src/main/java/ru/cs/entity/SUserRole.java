package ru.cs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "s_user_role")
public class SUserRole {

	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	private Long id;
 
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private SUser user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id", nullable = false)
	private SRole role;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SUser getUser() {
		return user;
	}

	public void setUser(SUser user) {
		this.user = user;
	}

	public SRole getRole() {
		return role;
	}

	public void setRole(SRole role) {
		this.role = role;
	}

}