package ru.cs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "acc")
public class Acc {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@Column(name = "firm_id", nullable = false)
	private Long firmId;

	@Column(name = "name", length = 100, nullable = false)
	private String name;
	
	@Column(name = "sort_to")
	private Long sortTo;
	
	@Column(name = "sort_from")
	private Long sortFrom;
	
	@Column(name = "sort_status")
	private Long sortStatus;
	
	@Column(name = "sort_rep_in")
	private Long sortRepIn;
	
	@Column(name = "sort_rep_out")
	private Long sortRepOut;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getFirmId() {
		return firmId;
	}

	public void setFirmId(Long firmId) {
		this.firmId = firmId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getSortTo() {
		return sortTo;
	}

	public void setSortTo(Long sortTo) {
		this.sortTo = sortTo;
	}

	public Long getSortFrom() {
		return sortFrom;
	}

	public void setSortFrom(Long sortFrom) {
		this.sortFrom = sortFrom;
	}

	public Long getSortStatus() {
		return sortStatus;
	}

	public void setSortStatus(Long sortStatus) {
		this.sortStatus = sortStatus;
	}

	public Long getSortRepIn() {
		return sortRepIn;
	}

	public void setSortRepIn(Long sortRepIn) {
		this.sortRepIn = sortRepIn;
	}

	public Long getSortRepOut() {
		return sortRepOut;
	}

	public void setSortRepOut(Long sortRepOut) {
		this.sortRepOut = sortRepOut;
	}

	@Override
	public String toString() {
		return "Acc: " +
			"id=" + this.id +
			"firm_id=" + this.firmId +
			"name=" + this.name; 
	}

	
}
