package com.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class GItemModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "gItem")
	private String gItem;
	
	@ManyToOne(fetch=FetchType.EAGER,cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			 CascadeType.DETACH, CascadeType.REFRESH}
	)
	private RarityModel rarity;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getgItem() {
		return gItem;
	}

	public void setgItem(String gItem) {
		this.gItem = gItem;
	}

	public RarityModel getRarity() {
		return rarity;
	}

	public void setRarity(RarityModel rarity) {
		this.rarity = rarity;
	}

}
