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
public class ValueModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "value")
	private String value;
	
	@Column(name = "valueExperience")
	private Integer valueExperience;
	
	@Column(name = "level")
	private Integer level;
	
	@Column(name = "ranking")
	private Integer ranking;
	
	//cascade = 元が消えたら関連テーブルはどうするか, fetch = 一緒に取り出すか, mappedBy = 関連ドメインクラス
	@OneToMany(cascade = CascadeType.ALL,orphanRemoval=true, fetch = FetchType.LAZY, mappedBy = "valueModel")
	private List<BigQuestModel> bqms;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getValueExperience() {
		return valueExperience;
	}

	public void setValueExperience(Integer valueExperience) {
		this.valueExperience = valueExperience;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getRanking() {
		return ranking;
	}

	public void setRanking(Integer ranking) {
		this.ranking = ranking;
	} 

}
