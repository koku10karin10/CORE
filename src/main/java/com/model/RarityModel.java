package com.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class RarityModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "rarityNumber")
	private Integer rarityNumber;
	
	public Integer getRarityNumber() {
		return rarityNumber;
	}

	public void setRarityNumber(Integer rarityNumber) {
		this.rarityNumber = rarityNumber;
	}

	@Column(name = "rarityName")
	private String rarityName;
	
	@OneToMany(mappedBy = "rarity",fetch=FetchType.LAZY,cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			 CascadeType.DETACH, CascadeType.REFRESH})
	private List<GItemModel> gims;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRarityName() {
		return rarityName;
	}

	public void setRarityName(String rarityName) {
		this.rarityName = rarityName;
	}

}
